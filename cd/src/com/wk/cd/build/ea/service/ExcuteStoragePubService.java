/**
 * Title: ExcuteStorageService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.Param;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.service.InstanceGenerateService;
import com.wk.cd.module1.service.TextLogInterceptor;
import com.wk.cd.module1.xml.XmlUtils;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: ��������
 * @author xuph
 */
public class ExcuteStoragePubService {
	@Inject
	private InteTaskInstanceService inteTaskInstanceService;
	@Inject
	private InstanceGenerateService instanceGenerateService;
	@Inject
	private TargetPackPublicService targetPackPublicService;
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicSrv;
	@Inject
	private DtSourceDaoService dtSourceDaoService;
	@Inject
	private CmSeqDaoService cmsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ������ʵ�����
	 * @param ce_server_name
	 * @param tar_package_list
	 * @param versionInfo
	 * @param exesocInfo
	 * @param ver_socs
	 * @return Instance_id
	 */
	public String getExcuteStorageId(String inte_ver_num, List<TargetPackageBean> tar_list, UuSocInfo exe_socs, UuSocInfo ver_socs, String storage_id, String env_name) {
		logger.debug("##################�������ʵ����ʼ######################");
		// ʵ��ID
		String instace_id = UUID.randomUUID().toString().replaceAll("-", "");
		// �������׶�
		List<Phase> phase_list = getStoragePhaseList(inte_ver_num, tar_list, exe_socs, ver_socs, storage_id, env_name);
		// �׶β�����Ϣ������ݲ�����������
		List<PhaseParam> param_list = new ArrayList<PhaseParam>();
		Instance instance = instanceGenerateService.phaseListGenerate(phase_list, param_list, null, instace_id, null);
		// �����ʱȡ�����е�ʵ��
		ProcessManager.instance.buildProcess(instance);
		// д��ʵ����Ϣ
		XmlWriter.write(instance);
		logger.debug("##################�������ʵ������#######################");
		return instance.getInstance_id();
	}

	/**
	 * Description:���ִ��
	 * @param inst_id
	 * @param storage_id
	 */
	public void excuteStorage(String inst_id, String storage_id) {
		boolean mark = true;
		Instance instance = new Instance(inst_id);
		instance = XmlReader.read(instance);
		Process process = ProcessManager.instance.getProcessInstance(inst_id);
		// Process process = ProcessManager.instance.buildProcess(instance);
		int total = process.getCtx().getInstance_info().getPhaseCount();
		// ��־
		String log_path = targetPackPublicService.getStorageDirectory() + storage_id + ".txt";
		process.addInterceptor(new TextLogInterceptor(log_path, total, storage_id));
		logger.info("���ִ���ܲ���total=[{}]��", total);
		// ִ��
		for (int j = 0; j < total; j++) {
			logger.info("���ִ�е�step=[{}]��", j);
			Result result = null;
			try {
				result = process.run(j);
				if (j == total - 1) {
					logger.info("�汾��ϢMsg=[{}]", result.getMsg());
					if (!Assert.isEmpty(result.getMsg())) {
						String ver_sion = formatMsg(result.getMsg());
						logger.info("�汾ID=[{}]", ver_sion);
						if (!Assert.isEmpty(ver_sion)) {
							envTagStorageDaoService.updateStroageInfoByKey(ver_sion, storage_id);
						}
					}
				}
				if (result.getStatus() != CMD_STATUS.SUCCEED) {
					mark = false;
					envTagStorageDaoService.updateStrStatusInfoByKey(JaDateTime.now(), STORAGE_STATUS.STORAGED, storage_id);
					cmsvc.getSession().commitAndResume();
					envTagStorageDaoService.updateStroageResultByKey(STORAGE_RESULT.FAIL, storage_id);
					cmsvc.getSession().commitAndResume();
					updateStroageTm(storage_id);
					break;
				}
			} catch (Exception e) {
				logger.debug("���ִ�д�����Ϣ��[{}]", ExceptionUtils.getStackTrace(e));
				mark = false;
				envTagStorageDaoService.updateStrStatusInfoByKey(JaDateTime.now(), STORAGE_STATUS.STORAGED, storage_id);
				cmsvc.getSession().commitAndResume();
				envTagStorageDaoService.updateStroageResultByKey(STORAGE_RESULT.FAIL, storage_id);
				cmsvc.getSession().commitAndResume();
				updateStroageTm(storage_id);
				break;
			}
		}
		if (mark) {
			// �������״̬
			envTagStorageDaoService.updateStroageResultByKey(STORAGE_RESULT.SUCCESS, storage_id);
			cmsvc.getSession().commitAndResume();
			// �޸�����ʱ���״̬
			envTagStorageDaoService.updateStrStatusInfoByKey(JaDateTime.now(), STORAGE_STATUS.STORAGED, storage_id);
			cmsvc.getSession().commitAndResume();
			updateStroageTm(storage_id);
		}
	}

	/**
	 * Description: �޸�����ʱ
	 * @param storage_id
	 */
	private void updateStroageTm(String storage_id) {
		EnvTagStorageInfo info = envTagStorageDaoService.getInfoByKey2(storage_id);
		JaDateTime start = info.getStart_bk_tm();
		JaDateTime end = info.getEnd_bk_tm();
		if (!Assert.isEmpty(start) && !Assert.isEmpty(end)) {
			int time_use = (int) (end.longValue() - start.longValue());
			int time_used = time_use / 1000;
			envTagStorageDaoService.updateStroageTimeByKey(time_used, storage_id);
		}
		cmsvc.getSession().commitAndResume();
	}

	/**
	 * Description: ����Phase
	 * @param phase_no
	 * @param phase_name
	 * @param cmds
	 * @param impl_type
	 * @param uu_soc
	 * @param storage_id
	 * @param env_name
	 * @return
	 */
	public Phase genernatePhase(int phase_no, String phase_name, String[] cmds, IMPL_TYPE impl_type, UuSocInfo uu_soc, String storage_id, String env_name) {
		Phase phase = new Phase();
		phase.setPhase_no(phase_no);
		phase.setPhase_name(phase_name);
		Script script = new Script();
		script.setCmds(cmds);
		script.setScript_type("default");
		phase.setScript(script);
		phase.setImpl_type(impl_type);
		List<Param> params = XmlUtils.matchParams1(phase.getScript().getCmds());
		phase.setParam_list(params);
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		srv_soc.add(inteTaskInstanceService.getStageSourceBean(uu_soc.getSoc_uuid()));
		phase.setSrv_soc(srv_soc);
		return phase;
	}

	/**
	 * Description: �������׶���Ϣ�б�
	 * @param ce_server_name
	 * @param tar_package_list
	 */
	private List<Phase> getStoragePhaseList(String storage_ver_num, List<TargetPackageBean> tar_list, UuSocInfo exe_socs, UuSocInfo ver_socs, String storage_id, String env_name) {
		logger.debug("***********�������׶���Ϣ��ʼ**********");
		List<Phase> phase_list = new ArrayList<Phase>();
		// ִ������Դ
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(exe_socs.getExe_soc_name());
		DtSourceInfo dtsource = dtSourceDaoService.getInfoByKey(info);
		logger.info("���ִ������Դ��soc_name=[{}]", dtsource.getSoc_name());
		// �������Ŀ¼
		String local_root_path = env_name + "/" + storage_id + "/" + storage_ver_num;
		// ִ������Դ��Ŀ¼
		String local_socroot_path = formatDirA(dtsource.getUser_root_path());

		// �׶�һ�� �������Ŀ¼
		logger.debug("[phase1]#####�������Ŀ¼#####");
		String[] cmd1 = ("test -d ./" + env_name + "/" + storage_id + " || mkdir -p ./" + env_name + "/" + storage_id + "").split("\n");
		Phase phase1 = genernatePhase(0, "�������Ŀ¼", cmd1, IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
		phase_list.add(phase1);

		// ��汾�й� �׶ζ����ߣ���
		String version_type = CfgTool.getProjectPropterty("version.control");
		IMPL_TYPE impl_type = null;
		String[] cmd2 = null;
		if (version_type.equalsIgnoreCase("svn")) {
			impl_type = IMPL_TYPE.SVN;
			// �׶ζ���SVN����汾��
			logger.debug("[phase2]#####SVN����汾��#####");
			cmd2 = ("co " + formatDirA(ver_socs.getCode_bk_dir()) + storage_ver_num + " " + env_name + "/" + storage_id).split("\n");
		}

		Phase phase2 = genernatePhase(1, "SVN����汾��", cmd2, impl_type, ver_socs, storage_id, env_name);
		phase_list.add(phase2);

		// �׶����������汾���Ŀ¼
		logger.debug("[phase3]######�����汾���Ŀ¼######");
		int i = 2;
		for (TargetPackageBean tarbean : tar_list) {
			StringBuffer cmd = new StringBuffer();
			// ��԰汾��Ŀ¼���磺/APP����/DB
			String reltive_path = tarbean.getTarget_relative_path();
			cmd.append("cd " + local_socroot_path + local_root_path + "");
			if (!Assert.isEmpty(reltive_path)) {
				cmd.append("\n mkdir -p " + formatDirD(tarbean.getTarget_relative_path()));
			}
			Phase phase3 = genernatePhase(i, "�����汾���Ŀ¼", cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
			phase_list.add(phase3);
			i++;
		}
		// �׶��ģ�����Ŀ���
		logger.debug("[phase4]######����Ŀ���######");
		for (TargetPackageBean tarbean : tar_list) {
			List<UuFilelistInfo> file_list = tarbean.getFile_list();
			String packname = tarbean.getPackage_name();
			String root_path = local_socroot_path;
			// String root_path = formatDirA(tarbean.getStorage_bk_path());
			StringBuffer cmd = new StringBuffer();
			cmd.append("cd " + root_path);
			// �����Ŀ¼���ڴ����Ŀ���
			cmd.append("\n tar --exclude-vcs -zcvf " + packname + " ");
			for (UuFilelistInfo lis : file_list) {
				String file_path = formatDirA(lis.getFile_path());
				// ������Ϣ
				String tar_file_path = file_path.replace(root_path, "");
				String filename = lis.getFile_name();
				cmd.append(tar_file_path + filename + " ");
			}
			cmd.append("> /dev/null 2>&1");
			Phase phase4 = genernatePhase(i, "�������" + packname + ".tar", cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
			phase_list.add(phase4);
			i++;
		}

		// �׶��壺�ƶ�Ŀ������汾Ŀ¼
		logger.debug("[phase5]######�ƶ�Ŀ������汾Ŀ¼######");
		for (TargetPackageBean tarbean : tar_list) {
			String packname = tarbean.getPackage_name();
			StringBuffer cmd = new StringBuffer();
			// cmd.append("cd " + formatDirA(tarbean.getStorage_bk_path()));
			cmd.append("cd " + local_socroot_path);
			cmd.append("\n mv " + packname + " " + local_socroot_path + genernateRelPath(env_name, storage_id, null) + formatDirD(tarbean.getTarget_relative_path()) + "/");
			Phase phase5 = genernatePhase(i, "�ƶ�Ŀ������汾Ŀ¼" + packname, cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
			phase_list.add(phase5);
			i++;
		}
		// �׶������ƶ��嵥�ļ����汾Ŀ¼
		logger.debug("[phase6]######�ƶ��嵥�ļ����汾Ŀ¼######");
		String tar_file_name = envTaskPublicSrv.generateListName(storage_ver_num, storage_id);
		StringBuffer cmd = new StringBuffer();
		cmd.append(("test -d ./" + env_name + "/" + storage_id + "/" + " || mkdir -p ./" + env_name + "/" + storage_id + "/"));
		cmd.append("\n cd " + local_socroot_path);
		cmd.append("\n mv " + tar_file_name + " " + local_socroot_path + env_name + "/" + storage_id + "/");
		Phase phase6 = genernatePhase(i, "�ƶ��嵥�ļ����汾Ŀ¼" + tar_file_name, cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
		phase_list.add(phase6);
		i++;

		// �׶��ߣ�SVN���Ŀ����汾
		logger.debug("[phase7]######SVN�ύĿ����Ϣ######");
		logger.info("SVN�ϴ�����Դ��soc_name=[{}]", ver_socs.getVer_soc_name());
		String local_roots = local_socroot_path + env_name + "/" + storage_id;
		String[] cmd_svns = null;
		if (version_type.equalsIgnoreCase("svn")) {
			cmd_svns = new String[2];
			cmd_svns[0] = ("cd " + local_roots);
			cmd_svns[1] = ("add * --force");
		}
		Phase phase7 = genernatePhase(i, "SVN�ύĿ����Ϣ", cmd_svns, impl_type, ver_socs, storage_id, env_name);
		phase_list.add(phase7);
		i++;

		// �׶ΰˣ�SVN�ύĿ�굽��
		logger.debug("[phase8]######SVN�ύĿ�굽��######");
		logger.info("SVN�ύ��soc_name=[{}]", ver_socs.getVer_soc_name());
		String[] cmd_svn = new String[2];
		if (version_type.equalsIgnoreCase("svn")) {
			cmd_svn[0] = ("cd " + local_socroot_path + env_name + "/" + storage_id + "");
			cmd_svn[1] = ("ci \"svn commit test\"");
		}
		Phase phase8 = genernatePhase(i, "SVN�ύĿ�굽��", cmd_svn, impl_type, ver_socs, storage_id, env_name);
		phase_list.add(phase8);
		logger.debug("***********�������׶���Ϣ����************");
		return phase_list;
	}

	/**
	 * Description: ���ɰ汾tar�����Ŀ¼
	 * @param env_name
	 * @param storage_id
	 * @param inte_ver_num
	 * @return
	 */
	public String genernateRelPath(String env_name, String storage_id, String inte_ver_num) {
		if (Assert.isEmpty(inte_ver_num)) {
			return env_name + "/" + storage_id + "/";
		} else {
			return env_name + "/" + storage_id + "/" + inte_ver_num + "/";
		}
	}

	/**
	 * Description: ��ʽĿ¼(��/)
	 * @param file_path
	 * @return
	 */
	public String formatDirA(String file_path) {
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
	 * Description: ��ȡ���·��
	 * @param reletive_path
	 * @return
	 */
	public String getRelativePath(String reletive_path) {
		if (!Assert.isEmpty(reletive_path)) {
			reletive_path = reletive_path.substring(0, reletive_path.indexOf("/", 2));
		}
		return reletive_path;
	}

	/**
	 * Description: ��ʽĿ¼(��/)
	 * @param file_path
	 * @return
	 */
	public String formatDirD(String file_path) {
		if (!Assert.isEmpty(file_path)) {
			if ('/' == (file_path.charAt(0))) {
				file_path = file_path.substring(1);
			}
			if (file_path.length() > 0 && '/' == file_path.charAt(file_path.length() - 1)) {
				file_path = file_path.substring(0, file_path.length() - 1);
			}
		}
		return file_path;
	}

	/**
	 * Description: ��ʽ����Ϣ
	 * @param msg
	 * @return
	 */
	private String formatMsg(String msg) {
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

}