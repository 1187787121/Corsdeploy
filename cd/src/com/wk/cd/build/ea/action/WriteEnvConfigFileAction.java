/**
 * Title: WriteConfigFileAction.java
 * File Description: �޸������ļ�����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.WriteEnvConfigFileInputBean;
import com.wk.cd.build.ea.bean.WriteEnvConfigFileOutputBean;
import com.wk.cd.build.ea.dao.EnvConfigfileModDaoService;
import com.wk.cd.build.ea.info.EnvConfigfileModInfo;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.enu.OPT_STATUS;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileStringService;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸������ļ�����
 * @author Xul
 */
public class WriteEnvConfigFileAction extends ActionBasic<WriteEnvConfigFileInputBean, WriteEnvConfigFileOutputBean>{
	
	@Inject private EnvConfigfileModDaoService confModSrv;
	@Inject private EnvironmentPublicService envPubSrv;
	@Inject private ServerCommonService serverComSrv;
	@Inject private GenNoService genNoSrv;
	@Inject private CeServerDaoService serverSrv;
	@Inject private FileStringService fileStrsrv;
	@Inject private CommonService comsrv;
	@Inject private FTPRCallService ftprcallSrv;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	//�������������ļ����ش��·��
	private static final String LOCAL_PATH = "temp";
	
	/** 
	 * Description: �޸������ļ�����
	 * @param input
	 * @return 
	 */
	@Override
	protected WriteEnvConfigFileOutputBean doAction(WriteEnvConfigFileInputBean input) {
		logger.info("-----------------WriteEnvConfigFileAction Begin------------------");
		WriteEnvConfigFileOutputBean output = new WriteEnvConfigFileOutputBean();
		
		//�ǿ�У��
		String env_name = input.getEnv_name();
		String ce_server_name = input.getCe_server_name();
		String batch_no = input.getBatch_no();
		String relative_path = input.getRelative_path();
		String encoding = input.getEncoding();
		String config_string = input.getConfig_string();
		String system = input.getSystem();
		Assert.assertNotEmpty(env_name, WriteEnvConfigFileInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(ce_server_name, WriteEnvConfigFileInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(relative_path, WriteEnvConfigFileInputBean.RELATIVE_PATHCN);
		Assert.assertNotEmpty(encoding, WriteEnvConfigFileInputBean.ENCODINGCN);
		
		//�Ϸ���У��
		envPubSrv.checkEnvNameIsExist(env_name);
		serverComSrv.checkServerIsExist(ce_server_name);
		
		String soc_name = serverComSrv.getFtpConfigSocByServerName(ce_server_name);
		
		//�����°汾��
		if(Assert.isEmpty(batch_no)){
			batch_no = genNoSrv.getBatchNo(input.getDtbs_bk_date());
			logger.debug("�汾��Ϊ�գ������°汾��[{}]", batch_no);
			output.setBatch_no(batch_no);
		}
		
		EnvConfigfileModInfo info = new EnvConfigfileModInfo();
		//��ȡ�ļ������ļ�Ŀ¼
		String file_name = FileTool.getFileName(relative_path);
		String file_path = FileTool.getFilePath(relative_path);
		//��ȡ�ļ���ˮ��
		String file_work_seq = genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getServer_name(), input.getServer_port());
		
		// ��ȡ���ش��·��(����ǰ�˸�·��+��ʱ�ļ���+�ļ���ˮ���ļ���+�ļ���)
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		//��ȡ�����ļ�����Ŀ¼
		String local_path = web_root_path + LOCAL_PATH + "/" + file_work_seq;
		File dir = new File(local_path);
		if(!dir.exists()){
			dir.mkdir();
		}
		String local_full_path = local_path + "/" + file_name;
		//�ڱ��������޸ĺ��ļ�
		fileStrsrv.writeFile(config_string, local_full_path, encoding, system);
		
		// ��������Դ����ȡftp��sftp����ʵ��
		FTPBean ftpBean = comsrv.getFTPBeanBySocName(soc_name,input.getWork_seq());
		// �ϴ��޸ĺ�Ļ��������ļ�
		ftprcallSrv.uploadFile(ftpBean, relative_path, local_full_path);
		
		// ɾ�����ɵ���ʱ�ļ�
		File file = new File(local_full_path);
		if(file.exists()){
			file.delete();
			dir.delete();
		}
		
		//�������������ļ������
		logger.info("�������������ļ������");
		info.setFile_work_seq(file_work_seq);
		info.setBatch_no(batch_no);
		info.setEnv_name(env_name);
		info.setServer_name(ce_server_name);
		//���ݷ��������Ʋ�ѯ��������ַ
		CeServerInfo server_info = serverSrv.getInfoByServerName(ce_server_name);
		info.setServer_ip(Assert.isEmpty(server_info) ? null : server_info.getServer_ip());
		info.setFopt_type(FOPT_TYPE.MODIFY);
		info.setFile_bk_fname(file_name);
		info.setFile_bk_path(file_path);
		info.setDir_yn_flag(YN_FLAG.NO);
		info.setOpt_status(OPT_STATUS.SUCCESS);
		info.setModify_user_id(input.getOrg_user_id());
		info.setModify_bk_date(input.getDtbs_bk_date());
		info.setModify_bk_time(input.getDtbs_bk_time());
		confModSrv.insertInfo(info);
		
		logger.info("-----------------WriteEnvConfigFileAction End------------------");
		return output;
	}

	/** 
	 * Description: �޸������ļ�����
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(WriteEnvConfigFileInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("����������" + input.getCe_server_name());
		lst_val.add("���·����" + input.getRelative_path());
		return lgsvc.getLogTxt("�޸������ļ�����", lst_val);
	}

}