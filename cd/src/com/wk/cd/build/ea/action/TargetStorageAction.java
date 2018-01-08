/**
 * Title: TargetStorageAction.java
 * File Description: Ŀ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��15��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.TargetStorageInputBean;
import com.wk.cd.build.ea.bean.TargetStorageOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.ExcuteStoragePubService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.PackNameExistChineseException;
import com.wk.cd.build.exc.PackNameSuffixIsExistException;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: Ŀ��������
 * @author xuph
 */
public class TargetStorageAction extends ActionBasic<TargetStorageInputBean, TargetStorageOutputBean> {
	@Inject private EnvironmentPublicService environmentPublicService;
	@Inject private ServerCommonService serverCommonService;
	@Inject private TargetPackPublicService targetPackService;
	@Inject private UuFilelistDaoService uuFilelistDaoService;
	@Inject private ExcuteStoragePubService executeStorageSrv;
	@Inject private EnvTagStorageDaoService tagStoSrv;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private UuSocDaoService uuSocDaoSrv;
	@Inject private GenNoService genNoSrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: Ŀ��������
	 * @param input
	 * @return
	 */
	@Override
	protected TargetStorageOutputBean doAction(TargetStorageInputBean input) {
		logger.info("-----------TargetStorageAction begin----------");
		TargetStorageOutputBean output = new TargetStorageOutputBean();
		// �ǿ�У��
		String env_name = input.getEnv_name();
		String storage_bk_desc = input.getStorage_bk_desc();
		String project_name = input.getProject_name();
		String ce_server_name = input.getCe_server_name();
		String soc_name = input.getSoc_name();
		String tag_server_name = input.getTag_server_name();
		String tag_soc_name = input.getTag_soc_name();
		String tag_bk_dir = input.getTag_bk_dir();
		List<TargetPackageBean> tar_package_list = input.getTar_package_list();

		Assert.assertNotEmpty(env_name, TargetStorageInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(storage_bk_desc,TargetStorageInputBean.STORAGE_BK_DESCCN);
		Assert.assertNotEmpty(ce_server_name,TargetStorageInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(soc_name, TargetStorageInputBean.SOC_NAMECN);
		Assert.assertNotEmpty(tag_server_name,TargetStorageInputBean.TAG_SERVER_NAMECN);
		Assert.assertNotEmpty(tag_soc_name, TargetStorageInputBean.TAG_SOC_NAMECN);
		Assert.assertNotEmpty(tag_bk_dir, TargetStorageInputBean.TAG_BK_DIRCN);
		Assert.assertNotEmpty(tar_package_list,TargetStorageInputBean.TAR_PACKAGE_LISTCN);

		// У�鼯�ɻ����Ƿ����
		environmentPublicService.checkEnvNameIsExist(env_name);
		// �������Ƿ����
		serverCommonService.checkServerIsExist(ce_server_name);
		// Ŀ��汾�������Ƿ����
		serverCommonService.checkServerIsExist(tag_server_name);
		// ����������Դ�Ƿ����
		targetPackService.checkCeServerDsIsExist(ce_server_name, soc_name);
		// У��Ŀ�����������Դ��Ŀ¼�Ƿ����
		targetPackService.checkCeServerDsIsExist(tag_server_name, tag_soc_name);
		// У��Ŀ¼�Ƿ�Ϸ�
		targetPackService.checkPureDirectory(tag_bk_dir);
		// У�컷�������Ƿ���ڼ��ɡ�����е�����
		targetPackService.checkTargetStorage(env_name);
		// ���������
		String storage_id = genNoSrv.getStorageId(input.getDtbs_bk_date());
		// �������Ϣ���
		EnvTagStorageInfo info = new EnvTagStorageInfo();
		info.setEnv_name(env_name);
		info.setStorage_id(storage_id);
		info.setProject_name(project_name);
		info.setStorage_bk_desc(storage_bk_desc);
		info.setStorage_status(STORAGE_STATUS.STORAGING);
		info.setCrt_bk_date(input.getDtbs_bk_date());
		info.setCrt_bk_time(input.getDtbs_bk_time());
		info.setCrt_user_id(input.getOrg_user_id());
		info.setStorage_user_id(input.getOrg_user_id());
		info.setInte_ver_num(input.getInte_ver_num());
		info.setStart_bk_tm(JaDateTime.now());
		info.setLog_bk_path(targetPackService.getStorageDirectory()+ storage_id + ".txt");
		// ����ִ������ԴUUID
		String exe_soc_uuid = UUID.randomUUID().toString().replace("-", "");
		info.setExe_soc_uuid(exe_soc_uuid);
		// ����ִ������Դ
		UuSocInfo exe_soc = new UuSocInfo();
		exe_soc.setSoc_uuid(exe_soc_uuid);
		exe_soc.setSoc_bk_seq(1);
		exe_soc.setExe_soc_name(soc_name);
		exe_soc.setExe_server_name(ce_server_name);
		logger.debug("#####����ִ������Դ������#######");
		uuSocDaoSrv.insertInfo(exe_soc);
		// ����ִ������Դ�Ͱ汾����Դ
		String ver_soc_uuid = UUID.randomUUID().toString().replace("-", "");
		info.setTar_versoc_uuid(ver_soc_uuid);
		UuSocInfo ver_soc = new UuSocInfo();
		ver_soc.setSoc_uuid(ver_soc_uuid);
		ver_soc.setSoc_bk_seq(1);
		ver_soc.setExe_soc_name(soc_name);
		ver_soc.setExe_server_name(ce_server_name);
		ver_soc.setVer_soc_name(tag_soc_name);
		ver_soc.setVer_server_name(tag_server_name);
		ver_soc.setCode_bk_dir(tag_bk_dir);
		logger.debug("#####����汾����Դ������#######");
		uuSocDaoSrv.insertInfo(ver_soc);
		List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
		// �����ļ��嵥UUID
		String fil_id = UUID.randomUUID().toString().replace("-", "");
		if (!Assert.isEmpty(tar_package_list)) {
			for (TargetPackageBean tarpack : tar_package_list) {
				String packageName = tarpack.getPackage_name();
				String storage_root_path = tarpack.getStorage_bk_path();
				String relative_path = tarpack.getTarget_relative_path();
				//�жϰ�����׺
				if(targetPackService.isContainSuffix(packageName)){
					throw new PackNameSuffixIsExistException();
				}
				//У������ĺϷ��ԣ���֧�����ģ�
				if(targetPackService.isContainChinese(packageName)){
					throw new PackNameExistChineseException().addScene("NAME", packageName);
				}
				List<UuFilelistInfo> file_lis = tarpack.getFile_list();
				logger.debug("#####�ļ��б�������ʼ########");
				logger.debug("����[{}]",packageName);
				if (!Assert.isEmpty(file_lis)) {
					for (UuFilelistInfo filelistInfo : file_lis) {
						logger.debug("roll--->file--->>>>>:[{}]",fil_id);
						UuFilelistInfo fileInfo = new UuFilelistInfo();
						fileInfo.setList_uuid(fil_id);
						String work_seq = genNoSrv.getWorkSeq();
					    logger.debug("��ˮ�ţ�[{}]",work_seq);
						fileInfo.setFile_work_seq(work_seq);
						fileInfo.setFile_name(filelistInfo.getFile_name());
						fileInfo.setFile_path(targetPackService.mergeDir(filelistInfo.getFile_path()));
						fileInfo.setFile_type(filelistInfo.getFile_type());
						fileInfo.setServer_name(ce_server_name);
						fileInfo.setPackage_name(packageName);
						fileInfo.setStorage_bk_path(storage_root_path);
						fileInfo.setTarget_relative_path(relative_path);
						file_list.add(fileInfo);
						logger.debug("next-------->>>>[{}]","file");
					}
				}
				logger.debug("#####�ļ��б���������########");
			}
			logger.debug("#####�����ļ��嵥��#######");
			uuFilelistDaoService.insertListInfo(file_list);
		}
		info.setStorage_list_uuid(fil_id);
		// �����ļ��嵥EXCEL
		targetPackService.uploadStorExcel(tar_package_list, env_name, storage_id, ce_server_name,input.getInte_ver_num());
		// ִ�����
		String inst_id = executeStorageSrv.getExcuteStorageId(input.getInte_ver_num(),tar_package_list, exe_soc,ver_soc,storage_id, env_name);
		logger.debug("######���ʵ����ţ�[{}]",inst_id);
		// �������ʵ��ID
		info.setInstance_id(inst_id.trim());
		// Ŀ�������Ϣ����
		tagStoSrv.insertInfo(info);
		logger.debug("#####���������Ϣ#######");
		output.setStorage_id(storage_id);
		output.setInst_id(inst_id);
		logger.info("------------TargetStorageAction end-----------");
		return output;
	}

	/**
	 * Description: Ŀ��������
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(TargetStorageInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("��������" + input.getEnv_name());
		return lgsvc.getLogTxt("Ŀ��������", lst_val);
	}

}