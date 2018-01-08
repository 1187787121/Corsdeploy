/**
 * Title: ReadConfigFileAction.java
 * File Description: ��ȡ�����ļ�����
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

import com.wk.cd.build.ea.bean.ReadEnvConfigFileInputBean;
import com.wk.cd.build.ea.bean.ReadEnvConfigFileOutputBean;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileStringService;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ȡ�����ļ�����
 * @author Xul
 */
public class ReadEnvConfigFileAction extends ActionBasic<ReadEnvConfigFileInputBean, ReadEnvConfigFileOutputBean>{
	
	@Inject private DtCheckSocExistService dtSocSrv;
	@Inject private FTPRCallService ftprcallSrv;
	@Inject private ServerCommonService serverCommonService;
	@Inject private CommonService comsrv;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private FileStringService fileStrsrv;
	private static final Log logger = LogFactory.getLog();
	
	//�������������ļ����ش��·��
	private static final String LOCAL_PATH = "temp";
	
	/** 
	 * Description: ��ȡ�����ļ�����
	 * @param input
	 * @return 
	 */
	@Override
	protected ReadEnvConfigFileOutputBean doAction(
			ReadEnvConfigFileInputBean input) {
		logger.info("-----------------ReadEnvConfigFileAction Begin------------------");
		ReadEnvConfigFileOutputBean output = new ReadEnvConfigFileOutputBean();
		
		//�ǿ�У��
		String ce_server_name = input.getCe_server_name();
		String relative_path = input.getRelative_path();
		String encoding = input.getEncoding();
		Assert.assertNotEmpty(ce_server_name, ReadEnvConfigFileInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(relative_path, ReadEnvConfigFileInputBean.RELATIVE_PATHCN);
		Assert.assertNotEmpty(encoding, ReadEnvConfigFileInputBean.ENCODINGCN);
		
		//�Ϸ���У��
		serverCommonService.checkServerIsExist(ce_server_name);
		
		String soc_name = serverCommonService.getFtpConfigSocByServerName(ce_server_name);
		
		// ��ȡ���ش��·��
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		// Ϊ�����ظ��������ļ�����Ϊʱ���
		String local_file_name = Long.toString(input.getDtbs_bk_time().longValue());
		// ���ļ��в����ڣ��򴴽�
		File dir = new File(web_root_path + LOCAL_PATH);
		if(!dir.exists()){
			dir.mkdir();
		}
		String local_full_path = web_root_path + LOCAL_PATH + "/"+ local_file_name;
		
		// ��������Դ����ȡftp��sftp����ʵ��
		FTPBean ftpBean = comsrv.getFTPBeanBySocName(soc_name,input.getWork_seq());
		// �����ļ�
		logger.debug("�����ļ�from[{}],to[{}]", relative_path, local_full_path);
		ftprcallSrv.downloadFile(ftpBean, relative_path, local_full_path);
		
		// ��ȡ�ļ����������ַ���
		List<String> list = fileStrsrv.readFileToString(local_full_path, encoding);
		String config_string = list.get(0);
		String system = list.get(1);
		output.setConfig_string(config_string);
		output.setSystem(system);
		// ɾ�����ص��ļ�
		File file = new File(local_full_path);
		if(file.exists()){
			file.delete();
		}
		
		logger.info("-----------------ReadEnvConfigFileAction End------------------");
		return output;
	}

	/** 
	 * Description: ��ȡ�����ļ�����
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ReadEnvConfigFileInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("����Դ����" + input.getServer_name());
		lst_val.add("���·����" + input.getRelative_path());
		lst_val.add("�ַ�����" + input.getEncoding());
		return lgsvc.getLogTxt("��ȡ�����ļ�����", lst_val);
	}

}