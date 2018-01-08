/**
 * Title: InteTaskInstanceService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��12��14��
 */
package com.wk.cd.build.ea.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.exc.ServerDsNotValidException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.service.InstanceGenerateService;
import com.wk.cd.module1.service.TextLogInterceptor;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ���ɼ���ʵ���ķ���
 * @author Administrator
 */
public class InteTaskInstanceService {

	@Inject
	private UuFilelistDaoService uuFileSrv;
	@Inject
	private InstanceGenerateService instanceGenerateService;
	@Inject
	private PublishTaskInstanceService publishTaskInstanceService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private DtSocService dtSocService;
	@Inject
	private EnvTaskDaoService taskSrv;
	@Inject
	private EnvTaskPublicService taskPubSrv;
	@Inject
	private EnvBuildTaskDaoService envBuildSrv;
	private static final Log logger = LogFactory.getLog();
	public static final String TARGET_DIR = "target";
	public static final String SRC_CODE_DIR = "source";

	/**
	 * Description: ����ʵ������ʵ��ID
	 * @param pg_step_list
	 * @param env_name
	 * @param vercode_ver_num
	 * @return
	 */
	public Instance generateInstance(List<PgInteStepInfo> pg_step_list, String env_name, String code_ver_num, String instace_id) {
		// ��ȡģ��
		// Template template = new Template();
		List<Phase> phase_list = new ArrayList<Phase>();
		if (!Assert.isEmpty(pg_step_list)) {
			for (PgInteStepInfo pgInteStepInfo : pg_step_list) {
				// ���ݲ�ͬ���������ɲ�ͬ�Ľ׶�
				Phase phase = generatePhaseByType(pgInteStepInfo);
				phase_list.add(phase);
			}
		}
		// ��ȡϵͳ�����б�
		List<PhaseParam> system_params = new ArrayList<PhaseParam>();
		PhaseParam verno_path = new PhaseParam();
		verno_path.setParam_name("env_name");
		verno_path.setParam_value(env_name);
		system_params.add(verno_path);
		// ������
		PhaseParam task_no1 = new PhaseParam();
		task_no1.setParam_name("ver_num");
		task_no1.setParam_value(code_ver_num);
		system_params.add(task_no1);
		Instance instance = instanceGenerateService.phaseListGenerate(phase_list, null, system_params, instace_id, null);
		return instance;
	}

	/**
	 * Description: ���ݲ����������ɲ�ͬ���
	 * @param info ���ɷ�������INFO
	 * @return
	 */
	private Phase generatePhaseByType(PgInteStepInfo info) {
		// �ǿ�У��
		STEP_TYPE type = info.getStep_type();
		Assert.assertNotEmpty(type, "��������");
		String desc = info.getStep_expl();
		Assert.assertNotEmpty(desc, "����˵��");
		String soc_uuid = info.getSoc_uuid();
		List<UuSocInfo> soc_list = uuSocDaoService.queryListInfoByUU(soc_uuid);
		String version_path = formatDirA(soc_list.get(0).getCode_bk_dir());

		// ���ݲ������ͣ���ȡ��ͬ�����
		if (STEP_TYPE.VERSION.equals(type)) {
			return generateVersionModule(info, version_path);
		} else if (STEP_TYPE.COMPILE.equals(type)) {
			return generateCompileModule(info);
		} else if (STEP_TYPE.STORAGE.equals(type)) {
			return generateStorageModule(info, info.getStorage_list_uuid(), version_path);
		} else {
			return generateScriptModule(info);
		}
	}

	/**
	 * Description: ��ʽĿ¼(��/)
	 * @param file_path
	 * @return
	 */
	private String formatDirA(String file_path) {
		if (!Assert.isEmpty(file_path)) {
			if ('/' != (file_path.charAt(0))) {
				file_path = "/" + file_path;
			}
			if (file_path.length() > 0 && '/' != file_path.charAt(file_path.length() - 1)) {
				file_path = file_path + "/";
			}
		}
		return file_path;
	}

	/**
	 * Description: �汾�׶�
	 * @param step_desc ����˵��
	 * @return
	 */
	private Phase generateVersionModule(PgInteStepInfo step_info, String version_path) {
		Phase phase = new Phase();
		phase.setPhase_no(step_info.getStep_id());
		phase.setPhase_name(step_info.getStep_expl());
		/**
		 * ���������Ŀ¼
		 */
		String root_path = "${env_name}/" + SRC_CODE_DIR;
		/**
		 * ��������
		 */
		// ��һ����У�黷�������Ŀ¼�Ƿ���ڣ������ڣ���ɾ����Ŀ¼
		String s1 = "if [ -d ./" + root_path + " ]; then rm -rf ./" + root_path + "; fi";

		// �ڶ������Ӱ汾�����Դ�뵽���ɻ���
		// String s2 = "co ${version_path}/${ver_num} " + root_path +
		// "/${ver_num}";
		String version_type = CfgTool.getProjectPropterty("version.control");
		String s2 = null ;
		IMPL_TYPE impl_type = null;
		if(version_type.equalsIgnoreCase("svn")) {
			s2 = "co " + version_path + "${ver_num} " + root_path + "/${ver_num}";
			impl_type = IMPL_TYPE.SVN;
		}
		Script script = new Script();
		script.setCmds(new String[] { s1, s2 });
		script.setScript_type("default");
		phase.setScript(script);
		phase.setImpl_type(impl_type);
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		srv_soc.add(getStageSourceBean(step_info.getSoc_uuid()));
		phase.setSrv_soc(srv_soc);

		return phase;
	}

	/**
	 * Description: ����׶�
	 * @param step_info ���ɷ�������INFO
	 * @return
	 */
	private Phase generateCompileModule(PgInteStepInfo step_info) {
		Assert.assertNotEmpty(step_info.getCompile_type(), "��������");
		Assert.assertNotEmpty(step_info.getComplie_bk_path(), "����·��");
		Phase phase = new Phase();
		phase.setPhase_name(step_info.getStep_expl());
		phase.setPhase_no(step_info.getStep_id());
		// info.setCn_name(step_info.getStep_expl());
		// ���ɹ���:�������������в�֣�+ �л�������·���� + �������ͣ���ant��+ �ո� + �����������jar��
		// if(COMPILE_TYPE.ANT.equals(type)){
		/**
		 * ��������
		 */
		// ��һ����У�����·���Ƿ����
		String s1 = "test -d " + step_info.getComplie_bk_path();

		// �ڶ�����ִ�л�������
		// String s2 = step_info.getEnv_variable();

		// ���������л�������·����
		String s3 = "cd " + step_info.getComplie_bk_path();

		// ���Ĳ���ִ�б�������
		String s4 = step_info.getCompile_type().getCname() + " " + step_info.getCompile_param();

		// String[] cmds = (s1 + "\r\n" + s2 + "\r\n" + s3 + "\r\n" +
		// s4).split("\r\n");
		String[] cmds = (s1 + "\r\n" + s3 + "\r\n" + s4).split("\r\n");
		// }
		Script script = new Script();
		script.setCmds(cmds);
		script.setScript_type("default");
		phase.setScript(script);
		phase.setImpl_type(IMPL_TYPE.SHELL);
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		srv_soc.add(getStageSourceBean(step_info.getSoc_uuid()));
		phase.setSrv_soc(srv_soc);
		return phase;
	}

	/**
	 * Description: �������׶�
	 * @param step_desc ����˵��
	 * @param list_uuid ����嵥UUID
	 * @return
	 */
	private Phase generateStorageModule(PgInteStepInfo step_info, String list_uuid, String version_path) {
		Assert.assertNotEmpty(list_uuid, "����嵥UUID");
		List<String> pac_list = uuFileSrv.queryPacList(list_uuid);
		Assert.assertNotEmpty(pac_list, "�������б�");

		/**
		 * ������Դ���Ŀ¼
		 */
		String srcpath = step_info.getStorage_bk_path();

		/**
		 * ������Ŀ���Ŀ¼
		 */
		String tagpath = "${env_name}/" + TARGET_DIR + "/${ver_num}";

		/**
		 * ��������
		 */
		// ��һ����У��Դ��Ŀ¼�Ƿ����
		String s1 = "test -d " + srcpath;

		// �ڶ������л���Դ��Ŀ¼��
		String s2 = "cd " + srcpath;

		// �����������ɴ������(һ����)
		String s3 = "";
		for (String pac : pac_list) {
			// ���ݰ�����ȡ�嵥�б�
			String pac_type = pac.substring(pac.lastIndexOf(".") + 1);
			List<UuFilelistInfo> file_list = uuFileSrv.queryFileByPac(list_uuid, pac);
			// ��ȡ�����·��
			String relative_path = file_list.get(0).getTarget_relative_path();
			if (!relative_path.endsWith("/")) {
				relative_path = relative_path + "/";
			}
			String cmd = "test -d " + relative_path + " || mkdir -p " + relative_path + " \r\n ";
			// ���ݴ���������ɲ�ͬ���Ŀǰ֧��tar��zip
			if ("tar".equals(pac_type)) {
				cmd += "tar -cf " + relative_path + pac;
			} else if ("zip".equals(pac_type)) {
				cmd += "zip -r " + relative_path + pac;
			} else {
				cmd += "tar -cf " + relative_path + pac;
			}
			if (!Assert.isEmpty(file_list)) {
				for (UuFilelistInfo file_info : file_list) {
					String full_path = file_info.getFile_path() + file_info.getFile_name();
					cmd += " " + full_path;
				}
			}
			s3 += cmd + "\r\n";
		}
		// �滻����������\r\n
		s3 = s3.substring(0, s3.lastIndexOf("\r\n"));

		// ���Ĳ�������汾������Ŀ��Ŀ¼��
		// String s4 = "co ${storage_path}/${ver_num} " + tagpath;
//		String s4 = "co " + version_path + "${ver_num} " + tagpath;

		// ���岽���л�����Ŀ¼��
		String s5 = "cd";

		// ɾ���ϵ��嵥�ļ�
		String delete_list = "rm ./" + tagpath + "/*.xlsx";

		// ����������Ŀ����ƶ���Ŀ��Ŀ¼��(һ��ಽ)
		String s6 = "";
		for (String pac : pac_list) {
			String relative_path = getPacRelativePath(list_uuid, pac);
			String cmd1 = "test -d ./" + tagpath + relative_path + " || mkdir ./" + tagpath + relative_path;
			String cmd2 = "mv " + srcpath + relative_path + pac + " ./" + tagpath + relative_path + pac;
			s6 += cmd1 + "\r\n" + cmd2 + "\r\n";
		}
		// �滻����������\r\n
		s6 = s6.substring(0, s6.lastIndexOf("\r\n"));

		// ���߲����л���Ŀ��Ŀ¼��
		String s7 = "cd ./" + tagpath;

		// �ڰ˲���SVN����Ŀ���
//		String s8 = "add * --force";

		// �ھŲ����ύSVN
//		String s9 = "ci -m " + "commit";

		String version_type = CfgTool.getProjectPropterty("version.control");
		String s4 = null;
		String s8 = null;
		String s9 = null;
		IMPL_TYPE impl_type = null;
		if(version_type.equalsIgnoreCase("svn")) {
			s4 = "co " + version_path + "${ver_num} " + tagpath;
			s8 = "add * --force";
			s9 = "ci -m " + "commit";
			impl_type = IMPL_TYPE.SVN;
		}
		
		Phase phase = new Phase();
		phase.setPhase_name(step_info.getStep_expl());
		phase.setPhase_no(step_info.getStep_id());
		Script script = new Script();
		script.setCmds(new String[] { s1, s2, s3, s4, s5, delete_list, s6, s7, s8, s9 });
		script.setScript_type("default");
		phase.setScript(script);
		phase.setImpl_type(impl_type);
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		srv_soc.add(getStageSourceBean(step_info.getSoc_uuid()));
		phase.setSrv_soc(srv_soc);
		return phase;
	}

	/**
	 * Description: ���ɽű����
	 * @param step_desc ����˵��
	 * @param script �ű�
	 * @return
	 */
	private Phase generateScriptModule(PgInteStepInfo step_info) {
		String step_desc = step_info.getStep_expl();
		String cmd = step_info.getStep_bk_script();
		Assert.assertNotEmpty(cmd, "�ű�");
		Phase phase = new Phase();
		phase.setPhase_name(step_desc);
		phase.setPhase_no(step_info.getStep_id());
		Script script = new Script();
		script.setCmds(cmd.split("\r\n"));
		script.setScript_type("default");
		phase.setScript(script);
		phase.setImpl_type(IMPL_TYPE.SHELL);

		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		srv_soc.add(getStageSourceBean(step_info.getSoc_uuid()));
		phase.setSrv_soc(srv_soc);
		return phase;
	}

	/**
	 * Description: ���ݰ�����ȡ���·��
	 * @param list_uuid
	 * @param pac_name
	 * @return
	 */
	public String getPacRelativePath(String list_uuid, String pac_name) {
		// ���ݰ�����ȡ�嵥�б�
		List<UuFilelistInfo> file_list = uuFileSrv.queryFileByPac(list_uuid, pac_name);
		// ��ȡ�����·��
		String relative_path = file_list.get(0).getTarget_relative_path();
		if (!relative_path.endsWith("/")) {
			relative_path = relative_path + "/";
		}
		if (relative_path.startsWith(".")) {
			relative_path = relative_path.substring(1, relative_path.length());
		}
		return relative_path;
	}

	/**
	 * Description: ���ɲ����б�
	 * @param env_name ������
	 * @param code_ver_num ����Դ��汾��
	 * @return
	 */
	private List<UuParamInfo> generateParams(String env_name, String code_ver_num) {
		// ��ȡ�����б�
		List<UuParamInfo> param_list = new ArrayList<UuParamInfo>();
		// ����������Ϊ����
		UuParamInfo param_info = new UuParamInfo();
		param_info.setParam_value(env_name);
		param_info.setParam_name("env_name");
		param_list.add(param_info);
		// ������Դ��汾����Ϊ����
		UuParamInfo param_info2 = new UuParamInfo();
		param_info2.setParam_name("ver_num");
		param_info2.setParam_value(code_ver_num);
		param_list.add(param_info2);
		return param_list;
	}

	public StageSourceBean getStageSourceBean(String soc_uuid) {
		StageSourceBean soc_bean = new StageSourceBean();
		UuSocInfo info = new UuSocInfo();
		info.setSoc_uuid(soc_uuid);
		info.setSoc_bk_seq(1);
		UuSocInfo soc_info = uuSocDaoService.getInfoByKey(info);

		if (!Assert.isEmpty(soc_info.getExe_soc_name())) {
			// String exe_soc = soc_info.getExe_soc_name();
			DtSourceInfo exe_info = dtSocService.getInfoByKey(soc_info.getExe_soc_name());
			// ����Դ����У��
			PROTOCOL_TYPE type = exe_info.getProtocol_type();
			if (type != PROTOCOL_TYPE.SSH && type != PROTOCOL_TYPE.TELNET) {
				throw new ServerDsNotValidException();
			}
			soc_bean.setExe_soc_name(exe_info.getSoc_name());
			soc_bean.setExe_ip(exe_info.getSoc_ip());
			soc_bean.setExe_protocol_type(exe_info.getProtocol_type());
		}

		if (!Assert.isEmpty(soc_info.getVer_soc_name())) {
			DtSourceInfo ver_info = dtSocService.getInfoByKey(soc_info.getVer_soc_name());
			soc_bean.setVer_soc_name(ver_info.getSoc_name());
			soc_bean.setVer_ip(ver_info.getSoc_ip());
			soc_bean.setVer_protocol_type(ver_info.getProtocol_type());

		}
		return soc_bean;
	}

	/**
	 * Description: ͨ�������Ż�ȡʵ��
	 * @param work_id
	 * @return
	 */
	public Instance readInstanceByWorkId(String work_id) {
		logger.debug("��XML��ȡʵ��, ����ID:[{}]", work_id);
		EnvTaskInfo info = taskSrv.getInfoByKey(work_id);
		String inst_id = info.getInstance_id();
		Assert.assertNotEmpty(inst_id, "ʵ��ID");
		Instance instance = new Instance(inst_id);
		instance = XmlReader.read(instance);
		return instance;
	}

	/**
	 * Description: ��������ִ����־(ֻ�ڵ�һ�����)
	 * @param work_id ������
	 * @param step_id ������
	 * @param proc process
	 * @param all_steps �ܲ�����
	 */
	public void addExeLog(String work_id, int step_id, Process proc, int all_steps) {
		// ��ȡ���������·��
		String task_root_path = taskPubSrv.generateTaskRootPath(work_id);
		// ������־�ļ�������־���ƹ���LOG + WORK_ID(������).txt��
		String file_name = "LOG" + work_id + ".txt";
		// ��ӡ��־
		logger.info("��ӡ��־" + task_root_path + file_name);
		proc.addInterceptor(new TextLogInterceptor(task_root_path + file_name, all_steps, "��������" + work_id));
		// �����������־��Ϣ
		if (step_id == 1) {
			taskSrv.updateExecuteLog(task_root_path + file_name, work_id);
		}
	}

	/**
	 * Description: ������ִ������
	 * @param work_id ������
	 * @param step_id ������
	 * @param proc process
	 * @param skip_flag �Ƿ�������־
	 * @return
	 */
	public Result executeTaskByStep(String work_id, int step_id, Process proc, boolean skip_flag) {
		Result result = null;
		// ��Ϊִ������
		if (skip_flag) {
			// ִ����������0��ʼ��
			logger.plog("ִ������");
			result = proc.skip(step_id - 1);
		} else {
			// ִ�в��貢��ȡ���ؽ��
			logger.plog("��ʼִ��");
			// ִ�в��裨��0��ʼ��
			String remote_relative_dir = "compTest/" + work_id;
			result = proc.runStage(remote_relative_dir, step_id - 1, false);
			// result = proc.run(step_id - 1);
			logger.plog("ִ�н���");
		}
		return result;
	}

	/**
	 * Description: ��������ִ����־(ֻ�ڵ�һ�׶δ��)
	 * @param work_id ������
	 * @param step_id ������
	 * @param proc process
	 * @param all_steps �ܲ�����
	 */
	public void addBuildExeLog(String work_id, int step_id, Process proc, int all_steps, String inst_id) {
		// ��ȡ���������·��
		String task_root_path = taskPubSrv.generateTaskRootPath(work_id);
		// ������־�ļ�������־���ƹ���inst_id(ʵ��ID)+_log.txt��
		String file_name = inst_id + "_log.txt";
		// ��ӡ��־
		logger.info("��ӡ��־" + task_root_path + file_name);
		proc.addInterceptor(new TextLogInterceptor(task_root_path + file_name, all_steps, "����Ӧ�ò���" + work_id));
		// �����������־��Ϣ
		if (step_id == 1) {
			envBuildSrv.updateExecuteLog(task_root_path + file_name, work_id);
		}
	}
}