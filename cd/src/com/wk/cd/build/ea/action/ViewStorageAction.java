/**
 * Title: ViewStorageAction.java
 * File Description: ���鿴����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.DownFileBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewStorageInputBean;
import com.wk.cd.build.ea.bean.ViewStorageOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.ExcuteStoragePubService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.EnvStoragePathException;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: ���鿴����
 * @author chiss
 */
public class ViewStorageAction extends IViewActionBasic<ViewStorageInputBean, ViewStorageOutputBean> {
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject
	private ExcuteStoragePubService excuteStorageService;
	@Inject
	private TargetPackPublicService targetPackPublicService;
	@Inject
	private CeProjectDaoService ceProjectDaoService;
	@Inject 
	private UuSocDaoService uuSocDaoSrv;
	@Inject
	private ServerCommonService serverComSrv;
	@Inject
	private DtSourceDaoService dtSourceDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicSrv;
	@Inject
	private CommonService commonService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: �鿴�����ϸ
	 * @param input
	 * @return
	 */
	public ViewStorageOutputBean queryStorageDetail(ViewStorageInputBean input) {
		logger.info("-----------queryStorageDetail begin----------");
		ViewStorageOutputBean output = new ViewStorageOutputBean();
		String storage_id = input.getStorage_id();
		// �ǿ�У��
		Assert.assertNotEmpty(storage_id, ViewStorageInputBean.STORAGE_IDCN);
		// �Ϸ���У��
		targetPackPublicService.checkTargetIsExist(storage_id);
		EnvTagStorageInfo storg_info = new EnvTagStorageInfo();
		storg_info.setStorage_id(storage_id);
		EnvTagStorageInfo storgInfo = envTagStorageDaoService.getInfoByKey(storg_info);
		output.setEnv_name(storgInfo.getEnv_name());
		output.setStorage_bk_desc(storgInfo.getStorage_bk_desc());
		output.setProject_name(storgInfo.getProject_name());
		output.setInte_ver_num(storgInfo.getInte_ver_num());
		//ͨ����Ŀ����ѯ��Ŀ��
		if(!Assert.isEmpty(storgInfo.getProject_name())) {
			CeProjectInfo info = ceProjectDaoService.getInfoByProjectName(storgInfo.getProject_name());
			output.setProject_short_name(info.getProject_short_name());
		}
		// ִ������Դ
		List<UuSocInfo> exsocInfoList = uuSocDaoSrv.queryListInfoByUU(storgInfo.getExe_soc_uuid());
		if (!Assert.isEmpty(exsocInfoList)) {
			UuSocInfo exsocInfo = exsocInfoList.get(0);
			output.setSoc_name(exsocInfo.getExe_soc_name());
			output.setCe_server_name(exsocInfo.getExe_server_name());
		}
		// �汾����Դ
		List<UuSocInfo> versocInfoList = uuSocDaoSrv.queryListInfoByUU(storgInfo.getTar_versoc_uuid());
		if (!Assert.isEmpty(versocInfoList)) {
			UuSocInfo versocInfo = versocInfoList.get(0);
			output.setTag_server_name(versocInfo.getVer_server_name());
			output.setTag_soc_name(versocInfo.getVer_soc_name());
			output.setTag_bk_dir(versocInfo.getCode_bk_dir());
		}
		// �ļ��嵥
		List<UuFilelistInfo> filelis = uuFilelistDaoService
				.getInfoByFileUuId(storgInfo.getStorage_list_uuid());
		List<String> packname = new ArrayList<String>();
		List<TargetPackageBean> tar_package_list = new ArrayList<TargetPackageBean>();
		if (!Assert.isEmpty(filelis)) {
			for (UuFilelistInfo listInfo : filelis) {
				String packn = listInfo.getPackage_name();
				if (!packname.contains(packn)) {
					packname.add(packn);
				}
			}
			if (!Assert.isEmpty(packname)) {
				for (String name : packname) {
					TargetPackageBean packbean = new TargetPackageBean();
					packbean.setPackage_name(name);
					List<UuFilelistInfo> lis = new ArrayList<UuFilelistInfo>();
					for (UuFilelistInfo listInfo : filelis) {
						if (listInfo.getPackage_name().equals(name)) {
							lis.add(listInfo);
						}
					}
					packbean.setFile_list(lis);
					tar_package_list.add(packbean);
				}
			}
			output.setTar_package_list(tar_package_list);
		}
		output.setStorage_status(storgInfo.getStorage_status());
		output.setStorage_result(storgInfo.getStorage_result());
		output.setStart_bk_tm(storgInfo.getStart_bk_tm());
		output.setEnd_bk_tm(storgInfo.getEnd_bk_tm());
		String file_name = envTaskPublicSrv.generateListName(storgInfo.getInte_ver_num(), storgInfo.getStorage_id());
		output.setFile_xlsx_path("/storage/"+file_name);
		logger.info("-----------queryStorageDetail begin----------");
		return output;
	}

	/**
	 * Description: �鿴�������Ϣ
	 * @param input
	 * @return
	 */
	public ViewStorageOutputBean monitorStor(ViewStorageInputBean input) {
		logger.info("--------------------monitorStor Begin------------");
		ViewStorageOutputBean output = new ViewStorageOutputBean();
		// �ǿ�У��
		String inst_id = input.getInst_id();
		String storage_id = input.getStorage_id();
		Assert.assertNotEmpty(storage_id, ViewStorageInputBean.STORAGE_IDCN);
		Assert.assertNotEmpty(inst_id, ViewStorageInputBean.INST_IDCN);
		// �Ϸ���У��
		targetPackPublicService.checkTargetIsExist(storage_id);
		logger.info("ִ�������storage_id=[{}]", storage_id);
		// �����Ϣ
		EnvTagStorageInfo tagst = envTagStorageDaoService.getInfoByKey2(storage_id);
		// ���״̬
		output.setStorage_status(tagst.getStorage_status());
		logger.info("���״̬Storage_status=[{}]", tagst.getStorage_status().getCname());
		// �����
		if (!Assert.isEmpty(tagst.getStorage_result())) {
			output.setStorage_result(tagst.getStorage_result());
			logger.info("�����Storage_status=[{}]", tagst.getStorage_result().getCname());
		}
		// ��⿪ʼʱ��
		output.setStart_bk_tm(tagst.getStart_bk_tm());
		logger.info("��⿪ʼʱ��=[{}]", tagst.getStart_bk_tm().toDateString());
		Process process = null;
		try {
			process = ProcessManager.instance.getProcessInstance(inst_id);
		} catch (Exception e) {
			Instance instance = new Instance(inst_id);
			instance = XmlReader.read(instance);
			// �ܽ׶�
			output.setTotal_phase(instance.getPhaseCount());
			// ��ǰ�׶�
			output.setCurrent_phase(instance.getPhaseCount());
			output.setEnd_bk_tm(JaDateTime.now());
			// ������ʱ��
			if (!Assert.isEmpty(tagst.getEnd_bk_tm())) {
				output.setEnd_bk_tm(tagst.getEnd_bk_tm());
			}
			return output;
		}
		int total = process.getCtx().getInstance_info().getPhaseCount();
		int phase_id = process.getCtx().getCurrentStage();
		int current_step = process.getCtx().getCurrentStep();
		logger.info("��⵱ǰ����current_step=[{}]", current_step);
		// �ܽ׶�
		output.setTotal_phase(total);
		// ��ǰ�׶�
		output.setCurrent_phase(phase_id + 1);
		// ��ǰ����
		output.setCurrent_step(current_step + 1);
		logger.info("����ܲ���total=[{}]", total);
		// ������ʱ��
		if (!Assert.isEmpty(tagst.getEnd_bk_tm())) {
			output.setEnd_bk_tm(tagst.getEnd_bk_tm());
		}
		logger.info("--------------------monitorStor End--------------");
		return output;
	}

	/**
	 * Description: �õ������ļ��б�
	 * @param input
	 * @return
	 */
	public ViewStorageOutputBean queryDownFileList(ViewStorageInputBean input) {
		logger.info("--------------------queryDownFileList Begin------------");
		ViewStorageOutputBean output = new ViewStorageOutputBean();
		// �ǿ�У��
		String storage_id = input.getStorage_id();
		Assert.assertNotEmpty(storage_id, ViewStorageInputBean.STORAGE_IDCN);
		// �Ϸ���У��
		targetPackPublicService.checkTargetIsExist(storage_id);
		EnvTagStorageInfo tagst = envTagStorageDaoService.getInfoByKey2(storage_id);
		// ��ȡ����������־
		List<DownFileBean> down_file_list = new ArrayList<DownFileBean>();
		String file_dir = tagst.getLog_bk_path();
		if (!Assert.isEmpty(file_dir)) {
			DownFileBean downfile = new DownFileBean();
			downfile.setFile_name(storage_id + ".txt");
			downfile.setFile_path("/storage/");
			downfile.setFile_type(1);
			down_file_list.add(downfile);
		}
		List<UuFilelistInfo> filelist = uuFilelistDaoService.getInfoByFileUuId(tagst.getStorage_list_uuid());
		// ������
		//String env_name = tagst.getEnv_name();
		// �����ļ�������
		if (!Assert.isEmpty(filelist)) {
			for (UuFilelistInfo fils : filelist) {
				String pack_name = fils.getPackage_name();
				DownFileBean downfile = new DownFileBean();
				downfile.setFile_name(pack_name);
			  //downfile.setFile_path("/"+env_name+"/"+storage_id+"/");
				downfile.setFile_type(2);
				down_file_list.add(downfile);
			}
		}
		//�б�ȥ���ظ�
		if(!Assert.isEmpty(down_file_list)){
			for (int i = 0; i < down_file_list.size() - 1; i++) {
				for (int j = down_file_list.size() - 1; j > i; j--) {
					if (down_file_list.get(j).getFile_name().equals(down_file_list.get(i).getFile_name())) {
						down_file_list.remove(j);
					}
				}
			}
		}
		output.setDown_file_list(down_file_list);
		logger.info("--------------------queryDownFileList End--------------");
		return output;
	}

	/**
	 * Description:������ʵ��ID
	 * @param input
	 * @return
	 */
	public ViewStorageOutputBean getStorageInstId(ViewStorageInputBean input) {
		logger.info("--------------------getStorageInstId Begin------------");
		ViewStorageOutputBean output = new ViewStorageOutputBean();
		String storage_id = input.getStorage_id();
		Assert.assertNotEmpty(storage_id, ViewStorageInputBean.STORAGE_IDCN);
		// �Ϸ���У��
		targetPackPublicService.checkTargetIsExist(storage_id);
		// ��������Ϣ
		EnvTagStorageInfo storg_info = new EnvTagStorageInfo();
		storg_info.setStorage_id(storage_id);
		EnvTagStorageInfo storgInfo = envTagStorageDaoService.getInfoByKey(storg_info);
		// �ļ��嵥
		List<UuFilelistInfo> filelis = uuFilelistDaoService.getInfoByFileUuId(storgInfo.getStorage_list_uuid());
		List<String> packname = new ArrayList<String>();
	    //�ļ�tar���б�
		List<TargetPackageBean> tar_list = new ArrayList<TargetPackageBean>();
		if (!Assert.isEmpty(filelis)) {
			for (UuFilelistInfo listInfo : filelis) {
				String packn = listInfo.getPackage_name();
				if (!packname.contains(packn)) {
					packname.add(packn);
				}
			}
			if (!Assert.isEmpty(packname)) {
				for (String name : packname) {
					TargetPackageBean packbean = new TargetPackageBean();
					packbean.setPackage_name(name);
					List<UuFilelistInfo> lis = new ArrayList<UuFilelistInfo>();
					for (UuFilelistInfo listInfo : filelis) {
						if (listInfo.getPackage_name().equals(name)) {
							lis.add(listInfo);
						}
					}
					packbean.setFile_list(lis);
					tar_list.add(packbean);
				}
			}
		}
		// ִ������Դ
		UuSocInfo exsocInfo = new UuSocInfo();
	    List<UuSocInfo>exe_soc_list = uuSocDaoSrv.queryListInfoByUU(storgInfo.getExe_soc_uuid());
		if (!Assert.isEmpty(exe_soc_list)) {
			 exsocInfo = exe_soc_list.get(0);
		}
		// �汾����Դ
		List<UuSocInfo>ver_soc_list = uuSocDaoSrv.queryListInfoByUU(storgInfo.getTar_versoc_uuid());
		UuSocInfo verInfo = new UuSocInfo();
		if (!Assert.isEmpty(ver_soc_list)) {
			 verInfo = ver_soc_list.get(0);
		}
		//������
		String env_name = storgInfo.getEnv_name();
		String inst_id = null;
		if(!Assert.isEmpty(tar_list)&&!Assert.isEmpty(exsocInfo)&&!Assert.isEmpty(verInfo)&&!Assert.isEmpty(env_name)){
			// ���ʵ��ID
		   inst_id = excuteStorageService.getExcuteStorageId("",tar_list,exsocInfo,verInfo,storage_id,env_name);
		}
		if (!Assert.isEmpty(inst_id)) {
			output.setInst_id(inst_id);
		}
		logger.info("--------------------getStorageInstId Eed -------------");
		return output;
	}
	
	/**
	 * Description: У������·���Ƿ����
	 * @param input
	 * @return
	 */
	public ViewStorageOutputBean checkStorPath(ViewStorageInputBean input) {
		logger.info("--------------------checkStorPath Begin------------");
		ViewStorageOutputBean output = new ViewStorageOutputBean();
		
		//�ǿ�У��
		String server_name = input.getCe_server_name();
		String storage_bk_path = input.getStorage_bk_path();
		
		//�Ϸ���У��
		serverComSrv.checkServerIsExist(server_name);
		
		//У��Զ��Ŀ¼�Ƿ����
		String soc_name = serverComSrv.getFtpConfigSocByServerName(server_name);
		commonService.checkRemoteFileExist(soc_name, input.getWork_seq(), storage_bk_path, false);
		
		//У������·���Ƿ�������û���·������
		String root_path = dtSourceDaoService.getUserRootPath(soc_name);
		if(!storage_bk_path.contains(root_path)){
			throw new EnvStoragePathException().addScene("PATH", root_path);
		}
		
		output.setStorage_bk_path(storage_bk_path);
		logger.info("--------------------checkStorPath End------------");
		return output;
	}

}