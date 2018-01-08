/**
 * Title: ViewPublishAction.java
 * File Description: ��������鿴����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��18��
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wk.cd.build.ea.bean.PubMonStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewPublishInputBean;
import com.wk.cd.build.ea.bean.ViewPublishOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.dao.UuParamDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.build.exc.ExportSvnDirException;
import com.wk.cd.build.exc.GetVersionListFileException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.GBKProperties;
import com.wk.util.JaDateTime;

/**
 * Class Description: ��������鿴����
 * @author chiss
 */
public class ViewPublishAction
		extends IViewActionBasic<ViewPublishInputBean, ViewPublishOutputBean> {
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private PgReleStepDaoService pgReleStepDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private CeProjectDaoService ceProjectDaoService;
	@Inject
	private InstanceExeDaoService instanceExeDaoService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private UuParamDaoService uuParamDaoService;
	@Inject
	private DtSourceDaoService dtSourceDaoService;
	@Inject
	private ViewBuildTaskAction viewBuildTaskAction;
	@Inject
	private PublishTaskInstanceService publishTaskService;

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: �鿴������������
	 * @param input
	 * @return
	 */
	public ViewPublishOutputBean queryPubDetail(ViewPublishInputBean input) {
		ViewPublishOutputBean output = new ViewPublishOutputBean();
		logger.info("-----------queryPubDetail begin----------");
		String work_id = input.getWork_id();
		// �ǿ�У��
		Assert.assertNotEmpty(work_id, ViewPublishInputBean.WORK_IDCN);

		EnvTaskInfo taskInfo = envTaskDaoService.getInfoByKey(work_id);
		output.setEnv_name(taskInfo.getEnv_name());
		output.setTask_bk_desc(taskInfo.getTask_bk_desc());
		output.setProject_name(taskInfo.getProject_name());
		output.setProg_id(taskInfo.getProg_id());
		output.setTarget_ver_num(taskInfo.getTarget_ver_num());
		output.setTask_status(taskInfo.getTask_status());
		output.setExe_result(taskInfo.getExe_result());
		output.setExe_method(taskInfo.getExe_method());

		PgProgramInfo ppi = pgProgramDaoService.getInfoByKey(taskInfo.getProg_id());
		CeProjectInfo cpi = ceProjectDaoService.getInfoByProjectName(taskInfo.getProject_name());
		if (!Assert.isEmpty(cpi)) {
			output.setProject_short_name(cpi.getProject_short_name());
		}
		if (!Assert.isEmpty(ppi)) {
			output.setProg_name(ppi.getProg_name());
		}
		logger.info("-----------queryPubDetail begin----------");
		return output;
	}

	/**
	 * Description: ��ط���ִ�з���
	 * @param input
	 * @return
	 */
	public ViewPublishOutputBean monitorExePublish(ViewPublishInputBean input) {
		ViewPublishOutputBean output = new ViewPublishOutputBean();
		logger.info("-----------monitorExePublish begin----------");
		String work_id = input.getWork_id();
		String new_id = "";
		Assert.assertNotEmpty(work_id, ViewPublishInputBean.WORK_IDCN);
		logger.debug("-----current monitor work_id [{}]-------", work_id);
		envTaskPublicService.checkEnvTaskIsExist(work_id);
		if (input.isRollback_flag()) {
			EnvTaskInfo env_task_info = envTaskDaoService.getInfoByKey(work_id);
			new_id = env_task_info.getRol_work_id();
			Assert.assertNotEmpty(new_id, ViewPublishInputBean.WORK_IDCN);
			envTaskPublicService.checkEnvTaskIsExist(new_id);
		} else {
			new_id = work_id;
		}
		EnvTaskInfo taskInfo = envTaskDaoService.getInfoByKey(new_id);
		output.setStart_bk_tm(taskInfo.getStart_bk_tm());
		output.setEnd_bk_tm(taskInfo.getEnd_bk_tm().equals(JaDateTime.valueOf("1971-01-01 00:00:00")) ? null : taskInfo.getEnd_bk_tm());
		if (input.isRollback_flag()) {
			EnvTaskInfo rol_task_info = envTaskDaoService.getInfoByKey(work_id);
			output.setTask_status(rol_task_info.getTask_status());
		} else {
			output.setTask_status(taskInfo.getTask_status());
		}

		output.setExe_result(taskInfo.getExe_result());
		output.setExelog_bk_path(envTaskPublicService.getReleativePath(new_id));
		List<PubMonStepBean> pub_phase_list = new ArrayList<PubMonStepBean>();
		List<InstanceExeInfo> step_info_list = instanceExeDaoService.getExeInstMsgByInstId(taskInfo.getInstance_id());
		if (!Assert.isEmpty(step_info_list)) {
			output.setTotal_phase(step_info_list.size());
			for (InstanceExeInfo info : step_info_list) {
				pub_phase_list.add(InstanceExeInfo.copyToMonBean(info));
			}
		}
		output.setCurrent_phase(envTaskPublicService.getCurrentExecutePhase(new_id));
		output.setPub_phase_list(pub_phase_list);
		logger.info("-----------monitorExePublish begin----------");
		return output;
	}

	/**
	 * Description: ��ط���ִ�з���App��ר�ã�
	 * @param input
	 * @return
	 */
	public ViewPublishOutputBean monitorExePublishForApp(ViewPublishInputBean input) {
		ViewPublishOutputBean output = new ViewPublishOutputBean();
		logger.info("-----------monitorExePublish begin----------");
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewPublishInputBean.WORK_IDCN);
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		TASK_STATUS task_status = task_info.getTask_status();
		boolean roll_flag = false;
		if (task_status.getValue() >= 4 && task_status.getValue() <= 6) {
			roll_flag = true;
		}
		String new_id = "";
		logger.debug("-----current monitor work_id [{}]-------", work_id);
		envTaskPublicService.checkEnvTaskIsExist(work_id);
		if (roll_flag) {
			EnvTaskInfo env_task_info = envTaskDaoService.getInfoByKey(work_id);
			new_id = env_task_info.getRol_work_id();
			Assert.assertNotEmpty(new_id, ViewPublishInputBean.WORK_IDCN);
			envTaskPublicService.checkEnvTaskIsExist(new_id);
		} else {
			new_id = work_id;
		}
		EnvTaskInfo taskInfo = envTaskDaoService.getInfoByKey(new_id);
		output.setStart_bk_tm(taskInfo.getStart_bk_tm());
		output.setEnd_bk_tm(taskInfo.getEnd_bk_tm().equals(JaDateTime.valueOf("1971-01-01 00:00:00")) ? null : taskInfo.getEnd_bk_tm());
		if (roll_flag) {
			EnvTaskInfo rol_task_info = envTaskDaoService.getInfoByKey(work_id);
			output.setTask_status(rol_task_info.getTask_status());
		} else {
			output.setTask_status(taskInfo.getTask_status());
		}

		output.setExe_result(taskInfo.getExe_result());
		output.setExelog_bk_path(envTaskPublicService.getReleativePath(new_id));
		List<PubMonStepBean> pub_phase_list = new ArrayList<PubMonStepBean>();
		List<InstanceExeInfo> step_info_list = instanceExeDaoService.getExeInstMsgByInstId(taskInfo.getInstance_id());
		if (!Assert.isEmpty(step_info_list)) {
			output.setTotal_phase(step_info_list.size());
			for (InstanceExeInfo info : step_info_list) {
				pub_phase_list.add(InstanceExeInfo.copyToMonBean(info));
			}
		}
		output.setCurrent_phase(envTaskPublicService.getCurrentExecutePhase(new_id));
		output.setPub_phase_list(pub_phase_list);
		logger.info("-----------monitorExePublish begin----------");
		return output;
	}

	/**
	 * Description: ��ȡ�����嵥��Ͷ����
	 * @param input
	 * @return
	 */
	public ViewPublishOutputBean getPublishListAndPac(ViewPublishInputBean input) {
		ViewPublishOutputBean output = new ViewPublishOutputBean();
		logger.info("-----------getPublishListAndPac begin----------");

		// �ǿ�У��
		String work_id = input.getWork_id();
		String prog_id = input.getProg_id();
		String env_name = input.getEnv_name();
		String target_ver_num = input.getTarget_ver_num();

		// ��ȡǰ���ϴ���·��
		GBKProperties system = CfgTool.getProperties("system.properties");
		String root_path = system.get("system.app.upload_base_path");

		// ���Դ�������ID�����ѯ���в���
		if (!Assert.isEmpty(work_id)) {
			EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
			prog_id = task_info.getProg_id();
			target_ver_num = task_info.getTarget_ver_num();
			env_name = task_info.getEnv_name();
			// ������־��Ϣ
			String log = task_info.getExelog_bk_path();
			if (!Assert.isEmpty(log)) {
				log = log.replace(root_path, "");
				TargetPackageBean log_bean = new TargetPackageBean();
				log_bean.setPackage_name(FileTool.getFileName(log));
				log_bean.setDownload_path(log);
				output.setLog_bean(log_bean);
			}
		}
		// ��ȡͶ����������Ϣ
		PgReleInfo rele_info = pgReleDaoService.getInfoByKey(prog_id);
		String publ_param_uuid = rele_info.getPubl_param_uuid();
		logger.debug("��������UUID:[{}]", publ_param_uuid);
//		List<String> pac_path_list = uuParamDaoService.getValueByKey(publ_param_uuid);
		
		List<String> pac_path_list = new ArrayList<String>();
		Program program = new Program(prog_id);
		program = XmlReader.read(program);
		if(!Assert.isEmpty(program.getParam_list())) {
			for (PhaseParam param : program.getParam_list()) {
				if(param.getParam_type()==PARAM_TYPE.PDDPARAM) {
					String param_value = param.getParam_value();
//					if(param.getParam_value().contains("${verno_path}")) {
//						param_value = param_value.replace("${verno_path}", target_ver_num);
//					}
					pac_path_list.add(param_value);
				}
			}
		}
		logger.debug("Ͷ��������:[{}]", pac_path_list.toString());
		// ������ʱ����ID
		String temp_work_id = env_name + "_temp";
		logger.debug("���ɵ���ʱ����ID:[{}]", temp_work_id);
		// ��ȡ����Դ��Ϣ
		String soc_uuid = rele_info.getVer_soc_uuid();
		logger.debug("����ԴUUID:[{}]", soc_uuid);
		UuSocInfo uu_soc_info = uuSocDaoService.queryListInfoByUU(soc_uuid).get(0);
		String ver_soc_name = uu_soc_info.getVer_soc_name();
		String ver_root_path = uu_soc_info.getCode_bk_dir();
		// ��ȡ�汾����Դ��Ϣ
		DtSourceInfo soc_info = new DtSourceInfo();
		soc_info.setSoc_name(ver_soc_name);
		DtSourceInfo ver_source_info = dtSourceDaoService.getInfoByKey(soc_info);
		// ��ȡ��������Դ��Ϣ
		DtSourceInfo local_info = envTaskPublicService.getLocalDtInfo();
		// �жϸ�·���Ƿ��Ѿ�checkout����������checkout
		File root_dir = new File(root_path + "task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + "/" + target_ver_num);

		// ���Դ�������ID�������ļ�������Ŀ¼��
		if (!Assert.isEmpty(work_id)) {
			String task_root_path = envTaskPublicService.generateTaskRootPath(work_id);
			// ��ȡ����Ŀ¼
			File dest = new File(task_root_path + ViewBuildTaskAction.EXPORT_DIR + "/" + target_ver_num);
			logger.debug("����Ŀ¼:[{}]", task_root_path + ViewBuildTaskAction.EXPORT_DIR + "/" + target_ver_num);
			if (root_dir.exists()) {
				publishTaskService.copyFolder(root_dir, dest);
				publishTaskService.deleteAllFiles(root_dir);
			}
			temp_work_id = work_id;
		} else {
			if (root_dir.exists()) {
				publishTaskService.deleteAllFiles(root_dir);
			}
			logger.debug("��һ��export");
			ModuleSourceInfo minfo = new ModuleSourceInfo(local_info, ver_source_info);
			String cmd1 = "cd ./task/" + temp_work_id + "/";

			String cmd2 = "export " + ver_root_path + "/" + target_ver_num + " " + "task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + "/" + target_ver_num;
			String[] cmds = new String[] { cmd1, cmd2 };
			logger.debug("##########������Ϣcmds:[{}]", Arrays.toString(cmds));
			SVN svn = new SVN(minfo, cmds);
			Result result = svn.run();
			if (CMD_STATUS.SUCCEED.equals(result.getStatus())) {
				logger.debug(result.getMsg());
			} else {
				logger.debug(result.getMsg());
				throw new ExportSvnDirException();
			}
			// }
		}
		// ��ȡ�嵥�ļ�
		root_dir = new File(root_path + "task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + "/" + target_ver_num);
		List<TargetPackageBean> list_list = getListFile(root_dir, target_ver_num, temp_work_id);
		output.setList_list(list_list);

		// ��ȡͶ����
		List<TargetPackageBean> pac_list = getPublishPacFile(pac_path_list, ver_root_path, root_path, temp_work_id, target_ver_num);
		output.setPac_list(pac_list);

		logger.info("-----------getPublishListAndPac end----------");
		return output;
	}

	/**
	 * ��ȡ�嵥�ļ�
	 */
	public List<TargetPackageBean> getListFile(File root_dir, String target_ver_num, String temp_work_id) {
		List<TargetPackageBean> list_list = new ArrayList<TargetPackageBean>();
		logger.debug("�嵥��·����" + root_dir.getAbsolutePath());
		File[] list2 = root_dir.listFiles();
		String list_name = envTaskPublicService.generateListName(target_ver_num, null);
		if (!Assert.isEmpty(list2)) {
			for (File f : list2) {
				logger.debug("�汾��Ŀ¼���ļ���" + f.getName());
				if (list_name.equals(f.getName())) {
					TargetPackageBean bean = new TargetPackageBean();
					bean.setPackage_name(f.getName());
					bean.setDownload_path("task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + "/" + target_ver_num + "/" + f.getName());
					list_list.add(bean);
				}
			}
		}
		// �������ڰ汾�嵥�ļ����׳��쳣
		if (list_list.size() == 0) {
			throw new GetVersionListFileException().addScene("LIST", list_name);
		}
		return list_list;
	}

	/**
	 * Description: ��ȡͶ�����ļ�
	 * @return
	 */
	public List<TargetPackageBean> getPacFile(List<String> pac_path_list, String ver_root_path, String root_path, String temp_work_id) {
		logger.debug("��ȡͶ�����ļ�--------- begin");
		List<TargetPackageBean> pac_list = new ArrayList<TargetPackageBean>();
		// ������Ŀ¼
		if (!Assert.isEmpty(pac_path_list)) {
			for (String str : pac_path_list) {
				str = str.replace(ver_root_path, "");
				if (!str.startsWith("/")) {
					str = "/" + str;
				}
				if (!str.endsWith("/")) {
					str = str + "/";
				}
				str = str.replace("${verno_path}", "");
				File file = new File(root_path + "task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + str);
				logger.debug("Ͷ����Ŀ¼��" + file.getAbsolutePath());
				File[] list = file.listFiles();
				if (!Assert.isEmpty(list)) {
					// List<UuFilelistInfo> file_list = new
					// ArrayList<UuFilelistInfo>();
					for (File fil : list) {
						TargetPackageBean bean = new TargetPackageBean();
						String pac_name = fil.getName();
						if (!pac_name.endsWith(".tar") && !pac_name.endsWith(".zip") && !pac_name.endsWith(".gzip")) {
							continue;
						}
						// //��ѹtar������ȡ�ļ��б�
						// logger.debug("��ѹtar��: "+ pac_name +"����ȡ�ļ��б�");
						// List<String> fil_list =
						// viewBuildTaskAction.decompressTar(new File(root_path
						// + "task/" + temp_work_id + "/" +
						// ViewBuildTaskAction.EXPORT_DIR + str + pac_name));
						// for(String s : fil_list){
						// UuFilelistInfo info = new UuFilelistInfo();
						// String file_path = FileTool.getFilePath(s);
						// String file_name = FileTool.getFileName(s);
						// info.setFile_path(file_path);
						// info.setFile_name(file_name);
						// file_list.add(info);
						// }
						// bean.setFile_list(file_list);
						bean.setPackage_name(pac_name);
						bean.setDownload_path("task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + str + pac_name);
						pac_list.add(bean);
					}
				}
			}
		}
		logger.debug("��ȡͶ�����ļ�--------- end");
		return pac_list;
	}

	/**
	 * Description: ��ȡ����Ͷ�����ļ�
	 * @return
	 */
	public List<TargetPackageBean> getPublishPacFile(List<String> pac_path_list, String ver_root_path, String root_path, String temp_work_id, String version_num) {
		logger.debug("��ȡͶ�����ļ�--------- begin");
		List<TargetPackageBean> pac_list = new ArrayList<TargetPackageBean>();
		// ������Ŀ¼
		if (!Assert.isEmpty(pac_path_list)) {
			for (String str : pac_path_list) {
				str = str.replace(ver_root_path, "");
				if (!str.startsWith("/")) {
					str = "/" + str;
				}
				if (!str.endsWith("/")) {
					str = str + "/";
				}
				str = str.replace("${verno_path}", "");
				File file = new File(root_path + "task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + "/" + version_num + str);
				logger.debug("Ͷ����Ŀ¼��" + file.getAbsolutePath());
				File[] list = file.listFiles();
				if (!Assert.isEmpty(list)) {
					for (File fil : list) {
						TargetPackageBean bean = new TargetPackageBean();
						String pac_name = fil.getName();
						if (!pac_name.endsWith(".tar") && !pac_name.endsWith(".zip") && !pac_name.endsWith(".gzip")) {
							continue;
						}
						bean.setPackage_name(pac_name);
						bean.setDownload_path("task/" + temp_work_id + "/" + ViewBuildTaskAction.EXPORT_DIR + "/" + version_num + str + pac_name);
						pac_list.add(bean);
					}
				}
			}
		}
		logger.debug("��ȡͶ�����ļ�--------- end");
		return pac_list;
	}


}