/**
 * Title: ExcuteStorageService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月17日
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
 * Class Description: 入库服务类
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
	 * Description: 获得入库实例编号
	 * @param ce_server_name
	 * @param tar_package_list
	 * @param versionInfo
	 * @param exesocInfo
	 * @param ver_socs
	 * @return Instance_id
	 */
	public String getExcuteStorageId(String inte_ver_num, List<TargetPackageBean> tar_list, UuSocInfo exe_socs, UuSocInfo ver_socs, String storage_id, String env_name) {
		logger.debug("##################生成入库实例开始######################");
		// 实例ID
		String instace_id = UUID.randomUUID().toString().replaceAll("-", "");
		// 生成入库阶段
		List<Phase> phase_list = getStoragePhaseList(inte_ver_num, tar_list, exe_socs, ver_socs, storage_id, env_name);
		// 阶段参数信息（入库暂不做参数化）
		List<PhaseParam> param_list = new ArrayList<PhaseParam>();
		Instance instance = instanceGenerateService.phaseListGenerate(phase_list, param_list, null, instace_id, null);
		// 监控暂时取缓存中的实例
		ProcessManager.instance.buildProcess(instance);
		// 写入实例信息
		XmlWriter.write(instance);
		logger.debug("##################生成入库实例结束#######################");
		return instance.getInstance_id();
	}

	/**
	 * Description:入库执行
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
		// 日志
		String log_path = targetPackPublicService.getStorageDirectory() + storage_id + ".txt";
		process.addInterceptor(new TextLogInterceptor(log_path, total, storage_id));
		logger.info("入库执行总步骤total=[{}]步", total);
		// 执行
		for (int j = 0; j < total; j++) {
			logger.info("入库执行第step=[{}]步", j);
			Result result = null;
			try {
				result = process.run(j);
				if (j == total - 1) {
					logger.info("版本信息Msg=[{}]", result.getMsg());
					if (!Assert.isEmpty(result.getMsg())) {
						String ver_sion = formatMsg(result.getMsg());
						logger.info("版本ID=[{}]", ver_sion);
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
				logger.debug("入库执行错误信息：[{}]", ExceptionUtils.getStackTrace(e));
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
			// 更改入库状态
			envTagStorageDaoService.updateStroageResultByKey(STORAGE_RESULT.SUCCESS, storage_id);
			cmsvc.getSession().commitAndResume();
			// 修改入库的时间和状态
			envTagStorageDaoService.updateStrStatusInfoByKey(JaDateTime.now(), STORAGE_STATUS.STORAGED, storage_id);
			cmsvc.getSession().commitAndResume();
			updateStroageTm(storage_id);
		}
	}

	/**
	 * Description: 修改入库耗时
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
	 * Description: 生成Phase
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
	 * Description: 生成入库阶段信息列表
	 * @param ce_server_name
	 * @param tar_package_list
	 */
	private List<Phase> getStoragePhaseList(String storage_ver_num, List<TargetPackageBean> tar_list, UuSocInfo exe_socs, UuSocInfo ver_socs, String storage_id, String env_name) {
		logger.debug("***********生成入库阶段信息开始**********");
		List<Phase> phase_list = new ArrayList<Phase>();
		// 执行数据源
		DtSourceInfo info = new DtSourceInfo();
		info.setSoc_name(exe_socs.getExe_soc_name());
		DtSourceInfo dtsource = dtSourceDaoService.getInfoByKey(info);
		logger.info("入库执行数据源：soc_name=[{}]", dtsource.getSoc_name());
		// 本地入库目录
		String local_root_path = env_name + "/" + storage_id + "/" + storage_ver_num;
		// 执行数据源根目录
		String local_socroot_path = formatDirA(dtsource.getUser_root_path());

		// 阶段一： 创建打包目录
		logger.debug("[phase1]#####创建打包目录#####");
		String[] cmd1 = ("test -d ./" + env_name + "/" + storage_id + " || mkdir -p ./" + env_name + "/" + storage_id + "").split("\n");
		Phase phase1 = genernatePhase(0, "创建打包目录", cmd1, IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
		phase_list.add(phase1);

		// 与版本有关 阶段二，七，八
		String version_type = CfgTool.getProjectPropterty("version.control");
		IMPL_TYPE impl_type = null;
		String[] cmd2 = null;
		if (version_type.equalsIgnoreCase("svn")) {
			impl_type = IMPL_TYPE.SVN;
			// 阶段二：SVN检出版本库
			logger.debug("[phase2]#####SVN检出版本库#####");
			cmd2 = ("co " + formatDirA(ver_socs.getCode_bk_dir()) + storage_ver_num + " " + env_name + "/" + storage_id).split("\n");
		}

		Phase phase2 = genernatePhase(1, "SVN检出版本库", cmd2, impl_type, ver_socs, storage_id, env_name);
		phase_list.add(phase2);

		// 阶段三：创建版本相对目录
		logger.debug("[phase3]######创建版本相对目录######");
		int i = 2;
		for (TargetPackageBean tarbean : tar_list) {
			StringBuffer cmd = new StringBuffer();
			// 相对版本的目录比如：/APP或者/DB
			String reltive_path = tarbean.getTarget_relative_path();
			cmd.append("cd " + local_socroot_path + local_root_path + "");
			if (!Assert.isEmpty(reltive_path)) {
				cmd.append("\n mkdir -p " + formatDirD(tarbean.getTarget_relative_path()));
			}
			Phase phase3 = genernatePhase(i, "创建版本相对目录", cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
			phase_list.add(phase3);
			i++;
		}
		// 阶段四：生成目标包
		logger.debug("[phase4]######生成目标包######");
		for (TargetPackageBean tarbean : tar_list) {
			List<UuFilelistInfo> file_list = tarbean.getFile_list();
			String packname = tarbean.getPackage_name();
			String root_path = local_socroot_path;
			// String root_path = formatDirA(tarbean.getStorage_bk_path());
			StringBuffer cmd = new StringBuffer();
			cmd.append("cd " + root_path);
			// 如果根目录存在打包的目标包
			cmd.append("\n tar --exclude-vcs -zcvf " + packname + " ");
			for (UuFilelistInfo lis : file_list) {
				String file_path = formatDirA(lis.getFile_path());
				// 命令信息
				String tar_file_path = file_path.replace(root_path, "");
				String filename = lis.getFile_name();
				cmd.append(tar_file_path + filename + " ");
			}
			cmd.append("> /dev/null 2>&1");
			Phase phase4 = genernatePhase(i, "打包生成" + packname + ".tar", cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
			phase_list.add(phase4);
			i++;
		}

		// 阶段五：移动目标包到版本目录
		logger.debug("[phase5]######移动目标包到版本目录######");
		for (TargetPackageBean tarbean : tar_list) {
			String packname = tarbean.getPackage_name();
			StringBuffer cmd = new StringBuffer();
			// cmd.append("cd " + formatDirA(tarbean.getStorage_bk_path()));
			cmd.append("cd " + local_socroot_path);
			cmd.append("\n mv " + packname + " " + local_socroot_path + genernateRelPath(env_name, storage_id, null) + formatDirD(tarbean.getTarget_relative_path()) + "/");
			Phase phase5 = genernatePhase(i, "移动目标包到版本目录" + packname, cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
			phase_list.add(phase5);
			i++;
		}
		// 阶段六：移动清单文件到版本目录
		logger.debug("[phase6]######移动清单文件到版本目录######");
		String tar_file_name = envTaskPublicSrv.generateListName(storage_ver_num, storage_id);
		StringBuffer cmd = new StringBuffer();
		cmd.append(("test -d ./" + env_name + "/" + storage_id + "/" + " || mkdir -p ./" + env_name + "/" + storage_id + "/"));
		cmd.append("\n cd " + local_socroot_path);
		cmd.append("\n mv " + tar_file_name + " " + local_socroot_path + env_name + "/" + storage_id + "/");
		Phase phase6 = genernatePhase(i, "移动清单文件到版本目录" + tar_file_name, cmd.toString().split("\n"), IMPL_TYPE.SHELL, exe_socs, storage_id, env_name);
		phase_list.add(phase6);
		i++;

		// 阶段七：SVN添加目标入版本
		logger.debug("[phase7]######SVN提交目标信息######");
		logger.info("SVN上传数据源：soc_name=[{}]", ver_socs.getVer_soc_name());
		String local_roots = local_socroot_path + env_name + "/" + storage_id;
		String[] cmd_svns = null;
		if (version_type.equalsIgnoreCase("svn")) {
			cmd_svns = new String[2];
			cmd_svns[0] = ("cd " + local_roots);
			cmd_svns[1] = ("add * --force");
		}
		Phase phase7 = genernatePhase(i, "SVN提交目标信息", cmd_svns, impl_type, ver_socs, storage_id, env_name);
		phase_list.add(phase7);
		i++;

		// 阶段八：SVN提交目标到库
		logger.debug("[phase8]######SVN提交目标到库######");
		logger.info("SVN提交：soc_name=[{}]", ver_socs.getVer_soc_name());
		String[] cmd_svn = new String[2];
		if (version_type.equalsIgnoreCase("svn")) {
			cmd_svn[0] = ("cd " + local_socroot_path + env_name + "/" + storage_id + "");
			cmd_svn[1] = ("ci \"svn commit test\"");
		}
		Phase phase8 = genernatePhase(i, "SVN提交目标到库", cmd_svn, impl_type, ver_socs, storage_id, env_name);
		phase_list.add(phase8);
		logger.debug("***********生成入库阶段信息结束************");
		return phase_list;
	}

	/**
	 * Description: 生成版本tar包存放目录
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
	 * Description: 格式目录(加/)
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
	 * Description: 截取相对路径
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
	 * Description: 格式目录(减/)
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
	 * Description: 格式化信息
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