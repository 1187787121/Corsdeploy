/**
 * Title: UpdateBuildConfigAction.java
 * File Description: �޸Ĺ��������ļ�����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.UpdateBuildConfigInputBean;
import com.wk.cd.build.ea.bean.UpdateBuildConfigOutputBean;
import com.wk.cd.build.ea.dao.BuildConfigfileDaoService;
import com.wk.cd.build.ea.info.BuildConfigfileInfo;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.CFG_TYPE;
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
 * Class Description: �޸Ĺ��������ļ�����
 * 
 * @author chiss
 */
public class UpdateBuildConfigAction extends ActionBasic<UpdateBuildConfigInputBean, UpdateBuildConfigOutputBean> {
	@Inject
	private BuildConfigfileDaoService buildConfigfileDaoService;
	@Inject
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private ServerCommonService serverCommonService;
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private FileStringService fileStringService;
	@Inject
	private CommonService commonService;
	@Inject
	private FTPRCallService ftprcallSrv;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	// �������������ļ����ش��·��
	private static final String LOCAL_PATH = "temp";

	/**
	 * Description: �޸Ĺ��������ļ�����
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateBuildConfigOutputBean doAction(UpdateBuildConfigInputBean input) {
		logger.info("-----------------UpdateBuildConfigAction Begin------------------");
		UpdateBuildConfigOutputBean output = new UpdateBuildConfigOutputBean();
		String env_name = input.getEnv_name();
		String ce_server_name = input.getCe_server_name();
		String work_id = input.getWork_id();
		String relative_path = input.getRelative_path();
		String encoding = input.getEncoding();
		String config_string = input.getConfig_string();
		CFG_TYPE cfg_type = input.getCfg_type();
		String system = input.getSystem();

		// �ǿ�У��
		Assert.assertNotEmpty(env_name, UpdateBuildConfigInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(ce_server_name, UpdateBuildConfigInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(work_id, UpdateBuildConfigInputBean.WORK_IDCN);
		Assert.assertNotEmpty(relative_path, UpdateBuildConfigInputBean.RELATIVE_PATHCN);
		Assert.assertNotEmpty(encoding, UpdateBuildConfigInputBean.ENCODINGCN);
		Assert.assertNotEmpty(cfg_type, UpdateBuildConfigInputBean.CFG_TYPECN);

		// �Ϸ���У��
		environmentPublicService.checkEnvNameIsExist(env_name);
		serverCommonService.checkServerIsExist(ce_server_name);

		String soc_name = serverCommonService.getFtpConfigSocByServerName(ce_server_name);

		// ��ȡ�ļ������ļ�Ŀ¼
		String file_name = FileTool.getFileName(relative_path);
		String file_path = FileTool.getFilePath(relative_path);
		// ��ȡ�ļ���ˮ��
		String file_work_seq = genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getServer_name(), input.getServer_port());
		// ��ȡ���ش��·��(����ǰ�˸�·��+��ʱ�ļ���+�ļ���ˮ���ļ���+�ļ���)
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		// ��ȡ�����ļ�����Ŀ¼
		String local_path = web_root_path + LOCAL_PATH + "/" + file_work_seq;
		File dir = new File(local_path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String local_full_path = local_path + "/" + file_name;
		// �ڱ��������޸ĺ��ļ�
		fileStringService.writeFile(config_string, local_full_path, encoding, system);
		// ��������Դ����ȡftp��sftp����ʵ��
		FTPBean ftpBean = commonService.getFTPBeanBySocName(soc_name, input.getWork_seq());
		// �ϴ��޸ĺ�Ļ��������ļ�
		ftprcallSrv.uploadFile(ftpBean, relative_path, local_full_path);

		// ɾ�����ɵ���ʱ�ļ�
		File file = new File(local_full_path);
		if (file.exists()) {
			file.delete();
			dir.delete();
		}

		// �����������������ļ������
		BuildConfigfileInfo info = new BuildConfigfileInfo();
		info.setFile_work_seq(file_work_seq);
		info.setWork_id(work_id);
		info.setCfg_type(cfg_type);
		info.setServer_name(ce_server_name);
		// ���ݷ��������Ʋ�ѯ��������ַ
		CeServerInfo server_info = ceServerDaoService.getInfoByServerName(ce_server_name);
		info.setServer_ip(Assert.isEmpty(server_info) ? null : server_info.getServer_ip());
		info.setFopt_type(FOPT_TYPE.MODIFY);
		info.setFile_bk_fname(file_name);
		info.setFile_bk_path(file_path);
		info.setDir_yn_flag(YN_FLAG.NO);
		info.setOpt_status(OPT_STATUS.SUCCESS);
		info.setModify_user_id(input.getOrg_user_id());
		info.setModify_bk_date(input.getDtbs_bk_date());
		info.setModify_bk_time(input.getDtbs_bk_time());
		buildConfigfileDaoService.insertInfo(info);
		logger.info("-----------------UpdateBuildConfigAction End------------------");
		return output;
	}

	/**
	 * Description: �޸Ĺ��������ļ�����
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateBuildConfigInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("����������" + input.getCe_server_name());
		lst_val.add("���·����" + input.getRelative_path());
		return lgsvc.getLogTxt("�޸������ļ�����", lst_val);
	}
}