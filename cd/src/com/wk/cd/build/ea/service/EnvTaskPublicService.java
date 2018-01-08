/**
 * Title: EnvTaskPublicService.java
 * File Description: ���񹫹�������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.exc.EnvTaskIsNotExistException;
import com.wk.cd.build.exc.GetVersionListFileException;
import com.wk.cd.build.exc.TaskCannotExecuteException;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.xml.XmlReader;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.GBKProperties;
import com.wk.util.JaDateTime;

/**
 * Class Description: ���񹫹�������
 * @author Xul
 */
public class EnvTaskPublicService {
	@Inject
	private PgInteStepDaoService pgInteSrv;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private InstanceExeDaoService instanceExeDaoService;
	@Inject
	private EnvTagStorageDaoService envTagSrv;
	@Inject
	private EnvProgPublicService progPubSrv;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private UuFilelistDaoService uuListSrv;
	@Inject
	private EnvBuildTaskDaoService envBuildTkDaoSrv;
	@Inject
	private InteTaskInstanceService inteTaskService;
	@Inject
	private DtSocService dtSrv;
	@Inject
	private PublishTaskInstanceService pbTaskSrv;
	@Inject
	private GenNoService genNoSrv;

	private static final Log logger = LogFactory.getLog();

	private static final String pwdPrefix = "-+-+";
	private static final int pwdPrefixLen = "-+-+".length();

	/**
	 * Description: У����� �����ھ����쳣
	 * @param work_id
	 */
	public void checkEnvTaskIsExist(String work_id) {
		if (envTaskDaoService.countByWorkId(work_id) <= 0) {
			throw new EnvTaskIsNotExistException().addScene("TASK", work_id);
		}
	}

	public void checkEnvExcuteIsExist(String work_id) {
		if (envBuildTkDaoSrv.countExecuteTask(work_id) <= 0) {
			throw new EnvTaskIsNotExistException().addScene("TASK", work_id);
		}
	}

	/**
	 * Description: ��ȡ���������·��
	 * @param work_id
	 * @return
	 */
	public String generateTaskRootPath(String work_id) {
		// ��ȡ���ش��·��
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		// ��ȡ���ش��·��
		String task_path = CfgTool.getProjectPropterty("ce.task.path");
		if (Assert.isEmpty(task_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "ce.task.path");
		}
		task_path = web_root_path + task_path + "/" + work_id + "/";
		File file = new File(task_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return task_path;
	}

	/**
	 * Description: ����ִ����־·��
	 * @param work_id
	 * @return
	 */
	public String generatePubExeLogPath(String work_id) {
		String log_path = generateTaskRootPath(work_id) + work_id + "_log.txt";
		return log_path;
	}

	/**
	 * Description: ��ȡǰ�����·��
	 * @param work_id
	 * @return
	 */
	public String getReleativePath(String work_id) {
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		String log_full_path = task_info.getExelog_bk_path();
		if (!Assert.isEmpty(log_full_path)) {
			log_full_path = log_full_path.replace(web_root_path, "");
		}
		return log_full_path;
	}

	/**
	 * Description: ������ʱ����嵥��Ŀ¼
	 * @return
	 */
	public String generateTempListPath() {
		// ��ȡ���ش��·��
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		String path = web_root_path + "temp/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * Description:
	 * @param work_id
	 * @param start
	 * @param rollback_flag
	 */
	public void updateExecuteStartStatus(String work_id, JaDateTime start, boolean rollback_flag) {
		if (rollback_flag) {
			envTaskDaoService.updateExecuteStartTime(start, work_id);
			envTaskDaoService.updateTaskStatus(TASK_STATUS.ROLLBACKING, work_id);
		} else {
			envTaskDaoService.updateExecuteStartTime(start, work_id);
			envTaskDaoService.updateTaskStatus(TASK_STATUS.RUNNING, work_id);
		}
	}

	/**
	 * Description: ÿ���׶�ִ�н����� ����ִ�н���޸�ִ��״̬
	 * @param rollback_flag
	 * @param result
	 * @param exe_bk_no
	 * @param count
	 * @param time_used
	 * @param work_id
	 * @return
	 */
	public boolean updateEndStatus(Result result, int exe_bk_no, int count, int time_used, String work_id, String instance_id) {
		String msg = cutMsg(result.getMsg());
		logger.debug("��ǰ��Ϣ�ĳ���[{}]", msg.length());
		CMD_STATUS cmd_status = result.getStatus();
		if (CMD_STATUS.SUCCEED.equals(cmd_status)) {
			instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.SUCCESS, JaDateTime.now(), time_used, instance_id, exe_bk_no);
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.EXECUTED, instance_id, exe_bk_no);
		} else if (CMD_STATUS.SKIP.equals(cmd_status)) {
			instanceExeDaoService.updateExecuteEnd(msg, null, JaDateTime.now(), time_used, instance_id, exe_bk_no);
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.SKIP, instance_id, exe_bk_no);
		} else {
			instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.FAIL, JaDateTime.now(), time_used, instance_id, exe_bk_no);
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.EXECUTED, instance_id, exe_bk_no);
			envTaskDaoService.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.FAIL, work_id);
			envTaskDaoService.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
			return false;
		}
		if (exe_bk_no == count) {// ִ�е����һ���׶�
			envTaskDaoService.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.SUCCESS, work_id);
			envTaskDaoService.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);

		} else {
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.PENDING, instance_id, exe_bk_no + 1);
		}
		return true;
	}

	private String cutMsg(String msg) {
		if (msg.length() < 200) {
			return msg;
		} else {
			String msg1 = msg.substring(1, 200);
			return msg1;
		}
	}

	/**
	 * Description: ɾ����������ִ�б�
	 * @param work_id
	 * @param inst_id
	 */
	public void deleteEnvTaskExeInfoList(String inst_id) {
		// ����ʵ��IDɾ����������ִ�б�
		instanceExeDaoService.deleteInfoByInst(inst_id);
	}

	/**
	 * Description: ������������ִ�б�
	 * @param pg_step_list
	 */
	public void addEnvTaskExeInfoList(String inst_id, List<PgInteStepInfo> pg_step_list) {
		List<InstanceExeInfo> exe_list = new ArrayList<InstanceExeInfo>();
		if (!Assert.isEmpty(pg_step_list)) {
			for (PgInteStepInfo info : pg_step_list) {
				InstanceExeInfo exe_info = new InstanceExeInfo();
				exe_info.setInstance_id(inst_id);
				exe_info.setInst_bk_no(info.getStep_id());
				exe_info.setTpl_bk_no(info.getStep_id());
				exe_info.setStep_bk_desc(info.getStep_expl());
				exe_info.setExe_status(EXE_STATUS.PENDING);
				exe_info.setStart_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
				exe_info.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
				String soc_uuid = info.getSoc_uuid();
				List<UuSocInfo> uu_soc_list = uuSocDaoService.queryListInfoByUU(soc_uuid);
				if (!Assert.isEmpty(uu_soc_list)) {
					exe_info.setServer_name(uu_soc_list.get(0).getExe_server_name());
					exe_info.setSoc_name(uu_soc_list.get(0).getExe_soc_name());

				}
				exe_list.add(exe_info);
			}
			instanceExeDaoService.insertListInfo(exe_list);
		}
	}

	/**
	 * Description: ִ�м�������ǰУ��
	 * @param work_id
	 */
	public void checkTaskBeforeExe(String work_id) {
		logger.debug("У������ִ�е�������[{}]", work_id);
		checkEnvTaskIsExist(work_id);
		EnvTaskInfo info = envTaskDaoService.getInfoByKey(work_id);
		if (!Assert.isEmpty(info)) {
			// У�黷�����Ƿ������������
			List<EnvTaskInfo> task_info_list = envTaskDaoService.getIdByEnvExceptId(info.getEnv_name(), work_id);
			if (!Assert.isEmpty(task_info_list)) {
				for (EnvTaskInfo task_info : task_info_list) {// ����к��Լ���ID��һ���ľͱ���
					if (work_id.equalsIgnoreCase(task_info.getRol_work_id()) && TASK_STATUS.ROLLBACKING.equals(task_info.getTask_status())) {// ��������е�
																																				// ������������һ��
																																				// �����������ڷ������ˣ�
						continue;
					}
					logger.debug("Ӱ��ִ�е�������[{}]", task_info.getWork_id());
					throw new TaskCannotExecuteException().addScene("REASON", "ִ�л����ϴ�����������ִ�е�����");
				}
			}
			List<EnvTagStorageInfo> tag_list = envTagSrv.getIdByEnv(info.getEnv_name());
			if (!Assert.isEmpty(tag_list)) {
				throw new TaskCannotExecuteException().addScene("REASON", "ִ�л����ϴ�������ִ�е��������");
			}

			// У������ִ��״̬
			if (EXE_RESULT.FAIL.equals(info.getExe_result())) {
				throw new TaskCannotExecuteException().addScene("REASON", "����ʧ�ܲ���");
			}

			// У�������Ƿ��ѹرջ���ִ��
			if (TASK_STATUS.EXECUTED.equals(info.getTask_status()) || TASK_STATUS.CLOSE.equals(info.getTask_status())) {
				throw new TaskCannotExecuteException().addScene("REASON", "�ѱ��رջ���ִ��");
			}
		}
	}

	public void checkRolTaskBeforeExe(String work_id) {
		logger.debug("У������ִ�е�������[{}]", work_id);
		EnvTaskInfo info = envTaskDaoService.getInfoByKey(work_id);
		if (!Assert.isEmpty(info)) {
			// У�黷�����Ƿ������������
			List<EnvTaskInfo> task_info_list = envTaskDaoService.getIdByEnvExceptId(info.getEnv_name(), work_id);
			if (!Assert.isEmpty(task_info_list)) {
				for (EnvTaskInfo task_info : task_info_list) {// ����к��Լ���ID��һ���ľͱ���
					if (work_id.equalsIgnoreCase(task_info.getRol_work_id()) && TASK_STATUS.ROLLBACKING.equals(task_info.getTask_status())) {// ��������е�
																																				// ������������һ��
																																				// �����������ڷ������ˣ�
						continue;
					}
					logger.debug("Ӱ��ִ�е�������[{}]", task_info.getWork_id());
					throw new TaskCannotExecuteException().addScene("REASON", "ִ�л����ϴ�����������ִ�е�����");
				}
			}
			List<EnvTagStorageInfo> tag_list = envTagSrv.getIdByEnv(info.getEnv_name());
			if (!Assert.isEmpty(tag_list)) {
				throw new TaskCannotExecuteException().addScene("REASON", "ִ�л����ϴ�������ִ�е��������");
			}

		}
	}

	public int getCurrentExecutePhase(String work_id) {
		EnvTaskInfo info = envTaskDaoService.getInfoByKey(work_id);
		if (!Assert.isEmpty(info)) {
			String id = info.getInstance_id();
			logger.debug("��ǰִ�в鿴��������[{}]ʵ��id[{}]", work_id, id);
			Process process = null;
			try {
				process = ProcessManager.instance.getProcessInstance(id);
			} catch (Exception e) {
				logger.debug("��־���������Ĵ���" + ExceptionUtils.getStackTrace(e));
				return 0;
			}
			ProcessContext pc = process.getCtx();
			return pc.getCurrentStage() + 1;
		} else {
			return -1;
		}

	}

	/**
	 * Description: �����������ж� �Ƿ��л���ʵ��
	 * @param work_id
	 * @return
	 */
	public YN_FLAG judgeRolInstExist(String work_id) {
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		if (!Assert.isEmpty(task_info)) {
			String rol_work_id = task_info.getRol_work_id();
			if (!Assert.isEmpty(rol_work_id)) {
				EnvTaskInfo rol_task_info = envTaskDaoService.getInfoByKey(rol_work_id);
				if (!Assert.isEmpty(rol_task_info)) {
					String inst_id = rol_task_info.getInstance_id();
					try {
						XmlReader.readInstance(inst_id);
						return YN_FLAG.YES;
					} catch (Exception e) {
						return YN_FLAG.NO;
					}

				}
			}
		}

		return YN_FLAG.NO;

	}

	/**
	 * Description:���ݲ������ͱ����Ҫ����Ϣ
	 */
	public void saveInfoByType(String work_id, int step_id, String msg, ActionRootInputBean input, boolean skip_flag) {
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		if (!Assert.isEmpty(task_info)) {
			String prog_id = task_info.getProg_id();
			// У�鷽������Ƿ����
			progPubSrv.checkProgIdIsNotExist(prog_id);
			PgInteStepInfo step_info = pgInteSrv.getInfoByKey2(prog_id, step_id);
			STEP_TYPE type = step_info.getStep_type();
			if (STEP_TYPE.VERSION.equals(type) && !skip_flag) {
				// Դ��汾���汾��
				String code_ver_num = getInteNum(msg);
				logger.debug("Դ��汾���汾��:[{}]", code_ver_num);
				envTaskDaoService.updateCodeVerNum(code_ver_num, work_id);
			} else if (STEP_TYPE.COMPILE.equals(type)) {
				// TODO ����Ŀ���嵥(��ʱ����)
			} else if (STEP_TYPE.STORAGE.equals(type) && !skip_flag) {
				/**
				 * ���淢��Ŀ��汾��
				 */
				String target_ver_num = getSvnNum(msg);
				envTaskDaoService.updateTargetVerNum(target_ver_num, work_id);

				String soc_uuid = step_info.getSoc_uuid();
				List<UuSocInfo> uu_ver_list = uuSocDaoService.queryListInfoByUU(soc_uuid);
				Assert.assertNotEmpty(uu_ver_list, "�������Դ��Ϣ");
				// ��ȡִ������Դ��Ϣ
				DtSourceInfo dt_info = dtSrv.getInfoByKey(uu_ver_list.get(0).getExe_soc_name());
				// ��ȡ�汾����Դ��Ϣ
				DtSourceInfo ver_dt_info = dtSrv.getInfoByKey(uu_ver_list.get(0).getVer_soc_name());
				/**
				 * ���漯��Ŀ����嵥
				 */
				String step_list_uuid = step_info.getStorage_list_uuid();
				String uuid = "";
				if (!Assert.isEmpty(task_info.getTagpac_list_uuid())) {
					uuid = task_info.getTagpac_list_uuid();
				} else {
					uuid = UUID.randomUUID().toString().replaceAll("-", "");
				}
				List<String> pac_list = uuListSrv.queryPacList(step_list_uuid);
				if (!Assert.isEmpty(pac_list)) {
					List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
					// �������漯��Ŀ����嵥
					for (String pac_name : pac_list) {
						UuFilelistInfo info = new UuFilelistInfo();
						info.setFile_name(pac_name);
						// ��ȡִ������Դ�û���·��
						String root_path = dt_info.getUser_root_path();
						if (!Assert.isEmpty(root_path)) {
							// ȥ����·��ĩβ��"/"
							if (root_path.endsWith("/")) {
								root_path = root_path.substring(0, root_path.length() - 1);
							}
						}
						// ��ȡ�����·��
						String relative_path = inteTaskService.getPacRelativePath(step_list_uuid, pac_name);
						// ��ȡ����Դ��汾��
						String ver_num = task_info.getVercode_ver_num();
						String path = root_path + "/" + task_info.getEnv_name() + "/" + InteTaskInstanceService.TARGET_DIR + "/" + ver_num + relative_path;
						info.setFile_path(path);
						info.setFile_work_seq(genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getRemote_ip(), input.getServer_port()));
						info.setList_uuid(uuid);
						info.setPackage_name(pac_name);
						info.setServer_name(uu_ver_list.get(0).getExe_server_name());
						file_list.add(info);
					}
					uuListSrv.insertListInfo(file_list);
					// ���¼���Ŀ����嵥UUID
					envTaskDaoService.updateTagpacListUuid(uuid, work_id);
				}

				// ��ȡ�����嵥Ŀ¼
				String vercode_ver_num = task_info.getVercode_ver_num();
				String root_path = generateTempListPath();
				String task_root_path = generateTaskRootPath(work_id);
				String list_name = generateListName(vercode_ver_num, null);
				// �ƶ���ʱĿ¼�ļ���Ŀ���ļ�
				File dest = new File(task_root_path + list_name);
				logger.debug("�ƶ��������嵥Ŀ¼####:[{}]", task_root_path);
				File temp_file = new File(root_path + list_name);
				if (temp_file.exists()) {
					pbTaskSrv.copyFolder(temp_file, dest);
				} else {
					throw new GetVersionListFileException().addScene("LIST", list_name);
				}

				/**
				 * ���嵥�ύ���汾��
				 */
				// ��ȡ��������Դ��Ϣ
				DtSourceInfo local_info = getLocalDtInfo();
				// ��ȡǰ�˸�·��
				String web_root_path = CfgTool.getProjectPropterty("web.root.path");
				if (Assert.isEmpty(web_root_path)) {
					throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
				}
				// ��ȡ�����嵥·��
				task_root_path = task_root_path.replace(web_root_path, "");
				String ver_bk_dir = uu_ver_list.get(0).getCode_bk_dir();
				logger.debug("**�����嵥·��**��" + task_root_path + list_name);
				logger.debug("**************���嵥�ύ���汾�� begin**************");
				logger.debug("���" + "co_empty " + ver_bk_dir + "/" + vercode_ver_num + " " + task_root_path);
				logger.debug("���" + "cd " + web_root_path + task_root_path);
				logger.debug("���" + "add " + list_name);
				logger.debug("���" + "ci -m " + "commit");
				ModuleSourceInfo minfo = new ModuleSourceInfo(local_info, ver_dt_info);
				String[] cmds = new String[] { "co_empty " + ver_bk_dir + "/" + vercode_ver_num + " " + task_root_path, "cd " + web_root_path + task_root_path, "pwd",
						"add " + list_name, "ci -m " + "commit" };
				SVN svn = new SVN(minfo, cmds);
				Result result = svn.run();
				logger.debug("**************���嵥�ύ���汾�� end**************");
				logger.debug(result.getMsg());

			}
		}
	}

	/**
	 * Description: ��ȡSVN�汾��
	 * @param msg
	 * @return
	 */
	private String getSvnNum(String msg) {
		String tar_msg = "";
		if (msg != null) {
			String mss[] = msg.split("\n");
			String[] msgg = mss[mss.length - 1].trim().split(" ");
			tar_msg = msgg[msgg.length - 1].trim().substring(0, msgg[msgg.length - 1].length() - 1);
		}
		String regex = "^[0-9]{1,10}$";
		boolean flag = tar_msg.matches(regex);
		if (flag) {
			return tar_msg;
		}
		return "";
	}

	/**
	 * Description: ��ȡSVN�汾��(�汾����)
	 * @param msg
	 * @return
	 */
	private String getInteNum(String msg) {
		String tar_msg = "";
		if (msg != null) {
			String mss[] = msg.split("\n");
			String[] msgg = mss[mss.length - 2].trim().split("\n");
			tar_msg = msgg[msgg.length - 1].trim().substring(0, msgg[msgg.length - 1].length() - 1);
			logger.debug("�汾���ֶΣ�[{}]", tar_msg);
		}
		Pattern p = Pattern.compile("[0-9\\.]+");
		Matcher m = p.matcher(tar_msg);
		while (m.find()) {
			return m.group();
		}
		return "";
	}

	/**
	 * Description: ���¹�������
	 * @param type
	 * @return
	 */
	public int getValue(TASK_TYPE type) {
		if (TASK_TYPE.BUILD.equals(type)) {
			return 1;
		} else if (TASK_TYPE.INTEGRATION.equals(type)) {
			return 2;
		} else if (TASK_TYPE.PUBLISH.equals(type) || TASK_TYPE.PUBLISHROLL.equals(type)) {
			return 4;
		}
		return 3;
	}

	/**
	 * Description: ��ȡ��������Դ��Ϣ
	 * @return
	 */
	@SuppressWarnings("resource")
	public DtSourceInfo getLocalDtInfo() {
		// ��ȡ��������
		String host = CfgTool.getProjectPropterty("local.host");
		String protocol = CfgTool.getProjectPropterty("local.protocol");
		String port = CfgTool.getProjectPropterty("local.port");
		String user = CfgTool.getProjectPropterty("local.user");
		String password = CfgTool.getProjectPropterty("local.password");
		GBKProperties system = CfgTool.getProperties("system.properties");
		String root_path = system.get("system.app.upload_base_path");
		logger.debug("host:" + host + " protocol:" + protocol + " port:" + port + " user:" + user + " password:" + password + " root_path:" + root_path);

		// �������
		if (password.startsWith(pwdPrefix)) {
			password = password.substring(pwdPrefixLen);
		} else {
			String key = DESUtil.getDef16Key();
			GBKProperties prop = null;
			InputStream in = null;
			FileOutputStream fos = null;
			prop = new GBKProperties(true);
			String config = CfgTool.getProjectRootPath() + "/config/cms.properties";
			try {
				in = new FileInputStream(new File(config));
				prop.load(in);
				fos = new FileOutputStream(config);
				Map<String, String> keys = new HashMap<String, String>();
				keys.put("local.password", pwdPrefix + DESUtil.docryptAllowReverse(true, key, password));
				prop.saveModifyKeys(fos, keys);
				logger.flog(16, "Local Cms Properties passwd encrtpt sucess!");
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e.getMessage());
			}
			password = DESUtil.docryptAllowReverse(true, key, password);
			logger.debug("password: " + password);
		}

		// ������������Դ��Ϣ
		DtSourceInfo local_info = new DtSourceInfo();
		local_info.setSoc_name("localSoc");
		if ("ssh".equalsIgnoreCase(protocol)) {
			local_info.setProtocol_type(PROTOCOL_TYPE.SSH);
		} else if ("telnet".equalsIgnoreCase(protocol)) {
			local_info.setProtocol_type(PROTOCOL_TYPE.TELNET);
		}
		local_info.setUser_root_path(root_path);
		local_info.setSoc_ip(host);
		local_info.setSoc_port(Integer.valueOf(port));
		local_info.setRemote_uname(user);
		local_info.setRemote_passwd(password);
		local_info.setBk_timeout(240);
		local_info.setKey_remote_passwd("a8cbfc6852835d24dcb9d17047cb04cf");
		return local_info;
	}

	/**
	 * Description: �����嵥�������ڰ汾����Ϊ�汾������������������������
	 * @return
	 */
	public String generateListName(String version, String work_id) {
		if (!Assert.isEmpty(version)) {
			return version + ".xlsx";
		} else if (!Assert.isEmpty(work_id)) {
			return work_id + ".xlsx";
		}
		return ".xlsx";
	}
}