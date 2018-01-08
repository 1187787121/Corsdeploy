/**
 * Title: ViewInteAction.java
 * File Description: 集成任务查看服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.DownFileBean;
import com.wk.cd.build.ea.bean.InteMonStepBean;
import com.wk.cd.build.ea.bean.TarPacDownloadBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewInteInputBean;
import com.wk.cd.build.ea.bean.ViewInteOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.BuildTaskLogGenService;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.GenerateExcelListService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.GetVersionListException;
import com.wk.cd.build.exc.GetVersionListFileException;
import com.wk.cd.build.exc.ServerNotExistException;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 集成任务查看服务
 * @author Xul
 */
public class ViewInteAction
		extends IViewActionBasic<ViewInteInputBean, ViewInteOutputBean> {

	@Inject
	private EnvTaskDaoService taskSrv;
	@Inject
	private EnvProgPublicService progPubSrv;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private InstanceExeDaoService instanceExeDaoService;
	@Inject
	private UuFilelistDaoService uulistSrv;
	@Inject
	private CeServerDsDaoService serverDsSrv;
	@Inject
	private EnvTaskPublicService taskPubSrv;
	@Inject
	private FTPRCallService ftpRSrv;
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private CommonService comsrv;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private DtSourceDaoService dtSourceDaoService;
	@Inject
	private CeProjectDaoService ceProjectDaoService;
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private TargetPackPublicService targetPubSrv;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private ServerCommonService serverComSrv;
	@Inject
	private BuildTaskLogGenService buildTaskLogGenService;
	@Inject
	private GenerateExcelListService generateExcelListService;
	@Inject
	private PublishTaskInstanceService pbTaskSrv;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 查看单个集成任务
	 * @param input
	 * @return
	 */
	public ViewInteOutputBean queryInteDetail(ViewInteInputBean input) {
		logger.info("-----------queryInteDetail begin----------");
		ViewInteOutputBean output = new ViewInteOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewInteInputBean.WORK_IDCN);

		EnvTaskInfo info = taskSrv.getInfoByKey(work_id);
		output.setEnv_name(info.getEnv_name());
		output.setTask_bk_desc(info.getTask_bk_desc());
		String project_name = info.getProject_name();
		if (!Assert.isEmpty(project_name)) {
			output.setProject_name(project_name);
			CeProjectInfo pj_info = ceProjectDaoService.getInfoByProjectName(project_name);
			if (!Assert.isEmpty(pj_info))
				output.setProject_short_name(pj_info.getProject_short_name());
		}
		String prog_id = info.getProg_id();
		if (!Assert.isEmpty(prog_id)) {
			output.setProg_id(prog_id);
			output.setProg_name(pgProgramDaoService.getInfoByKey(prog_id).getProg_name());
		}
		output.setVercode_ver_num(info.getVercode_ver_num());
		output.setTask_status(info.getTask_status());
		output.setExe_result(info.getExe_result());
		output.setExe_method(info.getExe_method());

		logger.info("-----------queryInteDetail end----------");
		return output;
	}

	/**
	 * Description: 监控任务执行进度
	 * @param input
	 * @return
	 */
	public ViewInteOutputBean monitorInte(ViewInteInputBean input) {
		logger.info("-----------monitorInte begin----------");
		ViewInteOutputBean output = new ViewInteOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewInteInputBean.WORK_IDCN);

		// 获取集成任务信息
		EnvTaskInfo task_info = taskSrv.getInfoByKey(work_id);
		output.setStart_bk_tm(task_info.getStart_bk_tm());
		output.setEnd_bk_tm(task_info.getEnd_bk_tm().equals(JaDateTime.valueOf("1971-01-01 00:00:00")) ? null : task_info.getEnd_bk_tm());
		output.setTask_status(task_info.getTask_status());
		output.setExe_result(task_info.getExe_result());

		List<InteMonStepBean> inte_step_list = new ArrayList<InteMonStepBean>();
		// 获取任务执行步骤列表
		List<InstanceExeInfo> env_task_list = instanceExeDaoService.getExeInstMsgByInstId(task_info.getInstance_id());
		if (!Assert.isEmpty(env_task_list)) {
			boolean flag = true;
			for (InstanceExeInfo info : env_task_list) {
				// 判断当前执行步骤数(状态为待执行或结果为失败)
				EXE_RESULT exe_result = info.getExe_result();
				boolean result_flag = false;
				if (!Assert.isEmpty(exe_result) && exe_result.equals(EXE_RESULT.FAIL)) {
					result_flag = true;
				}
				// 监控执行到第几步（规则：按步骤遍历，第一个待执行或执行中的步骤，或执行失败的步骤，或者最后一个跳过的步骤）
				if (flag) {
					// 判断执行状态为待执行或执行中或执行结果为失败的步骤
					if ((info.getExe_status().getValue() <= 2) || result_flag) {
						output.setExe_step(info.getInst_bk_no());
						flag = false;
					}
					// 判断执行状态为跳过的步骤
					if (EXE_STATUS.SKIP.equals(info.getExe_status())) {
						output.setExe_step(info.getInst_bk_no());
					}
					// 判断执行状态为已执行的步骤
					if (EXE_STATUS.EXECUTED.equals(info.getExe_status())) {
						output.setExe_step(info.getInst_bk_no());
					}
				}
				InteMonStepBean bean = new InteMonStepBean();
				bean.setStep_id(info.getInst_bk_no());
				bean.setStep_expl(info.getStep_bk_desc());
				bean.setExe_status(info.getExe_status());
				bean.setTask_exe_result(info.getExe_result());
				inte_step_list.add(bean);
			}
			output.setInte_step_list(inte_step_list);
			output.setAll_steps(env_task_list.size());
			String web_root_path = CfgTool.getProjectPropterty("web.root.path");
			if (Assert.isEmpty(web_root_path)) {
				throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
			}
			String log_path = task_info.getExelog_bk_path();
			output.setExelog_bk_path(Assert.isEmpty(log_path) ? log_path : log_path.replace(web_root_path, ""));
		}
		logger.info("-----------monitorInte end----------");
		return output;
	}

	/**
	 * Description: 根据任务ID获取可下载文件列表
	 * @param input
	 * @return
	 */
	public ViewInteOutputBean queryDownFileList(ViewInteInputBean input) {
		logger.info("-----------queryDownFileList begin----------");
		ViewInteOutputBean output = new ViewInteOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewInteInputBean.WORK_IDCN);

		List<DownFileBean> down_file_list = new ArrayList<DownFileBean>();
		// 获取环境任务根路径
		String task_root_path = taskPubSrv.generateTaskRootPath(work_id);
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		task_root_path = task_root_path.replace(web_root_path, "");
		// 获取集成任务信息
		EnvTaskInfo task_info = taskSrv.getInfoByKey(work_id);
		// 获取集成任务日志
		String log_full_path = task_info.getExelog_bk_path();
		if (!Assert.isEmpty(log_full_path)) {
			DownFileBean bean = new DownFileBean();
			log_full_path = log_full_path.replace(web_root_path, "");
			bean.setFile_name(FileTool.getFileName(log_full_path));
			bean.setFile_path(FileTool.getFilePath(log_full_path));
			bean.setFile_type(1);
			down_file_list.add(bean);
		}
		// 回退日志
		String rol_work_id = task_info.getRol_work_id();
		if (!Assert.isEmpty(rol_work_id)) {
			EnvTaskInfo task_rol_info = taskSrv.getInfoByKey(rol_work_id);
			if (!Assert.isEmpty(task_rol_info)) {
				String log_rol_path = task_rol_info.getExelog_bk_path();
				if (!Assert.isEmpty(log_rol_path)) {
					DownFileBean bean = new DownFileBean();
					log_rol_path = log_rol_path.replace(web_root_path, "");
					bean.setFile_name(FileTool.getFileName(log_rol_path));
					bean.setFile_path(FileTool.getFilePath(log_rol_path));
					bean.setFile_type(3);
					down_file_list.add(bean);
				}
			}
		}

		// 获取集成任务目标包
		String tagpac_list_uuid = task_info.getTagpac_list_uuid();
		if (!Assert.isEmpty(tagpac_list_uuid)) {
			List<String> pac_list = uulistSrv.queryPacList(tagpac_list_uuid);
			if (!Assert.isEmpty(pac_list)) {
				for (String str : pac_list) {
					DownFileBean bean = new DownFileBean();
					bean.setFile_name(str);
					bean.setFile_type(2);
					// ShellCallHelper.getFileSize(soc_name, file_path, str);
					// bean.setFile_size(0);
					down_file_list.add(bean);
				}
			}
		}
		// 若为构建任务，则更新构建日志
		if (TASK_TYPE.BUILD.equals(task_info.getTask_type())) {
			buildTaskLogGenService.generateBuildTaskLog(work_id);
		}
		output.setDown_file_list(down_file_list);
		logger.info("-----------queryDownFileList end----------");
		return output;
	}

	/**
	 * Description: 下载目标包
	 * @param input
	 * @return
	 */
	public ViewInteOutputBean getTargetPackage(ViewInteInputBean input) {
		logger.info("-----------getTargetPackage begin----------");
		ViewInteOutputBean output = new ViewInteOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		TASK_ALL_TYPE task_all_type = input.getTask_all_type();
		String[] pac_list = input.getPac_list();
		Assert.assertNotEmpty(work_id, ViewInteInputBean.WORK_IDCN);
		Assert.assertNotEmpty(Assert.isEmpty(task_all_type) ? null : task_all_type, ViewInteInputBean.TASK_ALL_TYPECN);
		Assert.assertNotEmpty(pac_list, ViewInteInputBean.PAC_LISTCN);
		List<String> full_path_list = new ArrayList<String>();

		// 获取本地存放根路径
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}

		// 根据任务类型，分别下载目标包
		if (TASK_ALL_TYPE.STORAGE.equals(task_all_type)) {
			for (String pac : pac_list) {
				TarPacDownloadBean bean = targetPubSrv.getRemoteStorFile(pac, work_id);
				downloadPac(bean, genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getRemote_ip(), input.getServer_port()));
				full_path_list.add(bean.getLocal_full_path().replace(web_root_path, ""));
			}
		} else if (TASK_ALL_TYPE.PUBLISH.equals(task_all_type)) {
			// TODO 暂无
		} else if (TASK_ALL_TYPE.INTEGRATION.equals(task_all_type)) {
			for (String pac : pac_list) {
				TarPacDownloadBean bean = getInteRpathAndLpathByPac(work_id, pac);
				downloadPac(bean, genNoSrv.getWorkSeq(input.getDtbs_bk_date(), input.getRemote_ip(), input.getServer_port()));
				full_path_list.add(bean.getLocal_full_path().replace(web_root_path, ""));
			}
		} else if (TASK_ALL_TYPE.BUILD.equals(task_all_type)) {
			// TODO 暂无
		}

		output.setFull_path_list(full_path_list);
		logger.info("-----------getTargetPackage end----------");
		return output;
	}

	/**
	 * Description: 根据包名和集成任务编号查询远程全路径和本地全路径
	 * @param work_id
	 * @param pac
	 * @return
	 */
	private TarPacDownloadBean getInteRpathAndLpathByPac(String work_id, String pac) {
		TarPacDownloadBean bean = new TarPacDownloadBean();
		EnvTaskInfo task_info = taskSrv.getInfoByKey(work_id);
		String list_uuid = task_info.getTagpac_list_uuid();
		List<UuFilelistInfo> file_list = uulistSrv.queryFileByPac(list_uuid, pac);
		// 若无记录则抛异常
		if (Assert.isEmpty(file_list)) {
			throw new FileNotExistException().addScene("FILE", pac);
		}
		String server_name = file_list.get(0).getServer_name();
		// 校验服务器是否存在
		if (Assert.isEmpty(server_name)) {
			throw new ServerNotExistException().addScene("SERVER", server_name);
		} else {
			server_name = server_name.trim();
			serverComSrv.checkServerIsExist(server_name);
		}
		// 根据服务器名称查询用途为配置的数据源
		String soc_name = serverComSrv.getFtpConfigSocByServerName(server_name);
		String file_path = file_list.get(0).getFile_path();
		bean.setLocal_full_path(taskPubSrv.generateTaskRootPath(work_id) + pac);
		bean.setRomote_full_path(file_path + pac);
		bean.setSoc_name(soc_name);
		return bean;
	}

	/**
	 * Description: 下载目标包
	 * @param bean
	 * @param work_seq
	 */
	private void downloadPac(TarPacDownloadBean bean, String work_seq) {
		Assert.assertNotEmpty(bean.getRomote_full_path(), TarPacDownloadBean.ROMOTE_FULL_PATHCN);
		Assert.assertNotEmpty(bean.getLocal_full_path(), TarPacDownloadBean.LOCAL_FULL_PATHCN);
		Assert.assertNotEmpty(bean.getSoc_name(), TarPacDownloadBean.SOC_NAMECN);
		// 若本地文件不存在则下载目标包
		if (!new File(bean.getLocal_full_path()).exists()) {
			FTPBean ftpBean = comsrv.getFTPBeanBySocName(bean.getSoc_name(), work_seq);
			ftpRSrv.downloadFile(ftpBean, bean.getRomote_full_path(), bean.getLocal_full_path());
		}
	}

	/**
	 * Description: 查看集成投产包及清单
	 * @param input
	 * @return
	 */
	public ViewInteOutputBean getListAndPac(ViewInteInputBean input) {
		logger.info("-----------getListAndPac begin----------");
		ViewInteOutputBean output = new ViewInteOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		String env_name = input.getEnv_name();
		String prog_id = input.getProg_id();
		String vercode_ver_num = input.getVercode_ver_num();
		Assert.assertNotEmpty(env_name, ViewInteInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(prog_id, ViewInteInputBean.PROG_IDCN);

		// 根据有无任务ID，判断为执行前获取清单，或执行后获取清单
		String list_name = "";
		String path = "";
		String root_path = taskPubSrv.generateTempListPath();
		logger.debug("任务编号--->work_id:[{}]",work_id);
		logger.debug("临时存放清单的目录：[{}]",root_path);
		if (Assert.isEmpty(work_id)) {
			list_name = taskPubSrv.generateListName(vercode_ver_num, null);
			path = root_path + list_name;
		} else {
			// 校验任务ID是否存在
			taskSrv.checkEnvIdIsNotExist(work_id);
			// 获取版本号
			EnvTaskInfo task_info = taskSrv.getInfoByKey(work_id);
			vercode_ver_num = task_info.getVercode_ver_num();
			// 获取生成清单目录
			String task_root_path = taskPubSrv.generateTaskRootPath(work_id);
			list_name = taskPubSrv.generateListName(vercode_ver_num, null);
			path = task_root_path + list_name;
			// 移动临时目录文件到目标文件
			File dest = new File(task_root_path);
			logger.debug("移动到任务清单目录:[{}]", task_root_path);
			File temp_file = new File(root_path + list_name);
			if (temp_file.exists()) {
				pbTaskSrv.copyFolder(temp_file, dest);
				logger.debug("文件复制完成");
//				pbTaskSrv.deleteAllFiles(temp_file);
			}else{
				throw new GetVersionListFileException().addScene("LIST", list_name);
			}
		}
		logger.debug("清单全路径:[{}]",path);
		// 生成清单
		generateExcelListService.generateExcelList(prog_id, path,vercode_ver_num);

		List<TargetPackageBean> list_list = new ArrayList<TargetPackageBean>();
		List<TargetPackageBean> pac_list = new ArrayList<TargetPackageBean>();
		// 获取本地存放路径
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		logger.debug("web_root_path:[{}]",web_root_path);
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		path = path.replace(web_root_path, "");
		logger.debug("返回路径path:[{}]",path);
		TargetPackageBean list_bean = new TargetPackageBean();
		list_bean.setPackage_name(list_name);
		list_bean.setDownload_path(path);
		list_list.add(list_bean);
		output.setList_list(list_list);
		
		// 获取入库步骤的清单UUID
		List<PgInteStepInfo> step_list = pgInteStepDaoService.getInfoByType(prog_id, STEP_TYPE.STORAGE);
		if (!Assert.isEmpty(step_list)) {
			String list_uuid = step_list.get(0).getStorage_list_uuid();
			// 获取包名列表
			List<String> package_list = uulistSrv.queryPacList(list_uuid);
			for (String pac : package_list) {
				TargetPackageBean pac_bean = new TargetPackageBean();
				pac_bean.setPackage_name(pac);
				// List<UuFilelistInfo> file_list =
				// uulistSrv.queryFileByPac(list_uuid, pac);
				// pac_bean.setFile_list(file_list);
				pac_list.add(pac_bean);
			}
		}

		output.setPac_list(pac_list);
		logger.info("-----------getListAndPac end----------");
		return output;
	}

	/**
	 * Description: 获取版本号列表
	 * @param input
	 * @return
	 */
	public ViewInteOutputBean queryVersionList(ViewInteInputBean input) {
		logger.info("-----------queryVersionList begin----------");
		ViewInteOutputBean output = new ViewInteOutputBean();
		// 非空校验
		String prog_id = input.getProg_id();
		String tag_soc_name = input.getTag_soc_name();
		String tag_bk_dir = input.getTag_bk_dir();
		// 通过方案获取版本信息
		if (!Assert.isEmpty(prog_id)) {
			progPubSrv.checkProgIdIsNotExist(prog_id);
			PgProgramInfo info = pgProgramDaoService.getInfoByKey(prog_id);
			PROG_TYPE prog_type = info.getProg_type();
			String soc_uuid = "";
			if (PROG_TYPE.INTEGRATION.equals(prog_type)) {
				List<PgInteStepInfo> step_list = pgInteStepDaoService.getInfoByType(prog_id, STEP_TYPE.VERSION);
				if (!Assert.isEmpty(step_list)) {
					soc_uuid = step_list.get(0).getSoc_uuid();
				} else {
					throw new GetVersionListException().addScene("REASON", "集成方案下无版本步骤");
				}
			} else if (PROG_TYPE.PUBLISH.equals(prog_type)) {
				PgReleInfo rele_info = pgReleDaoService.getInfoByKey(prog_id);
				soc_uuid = rele_info.getVer_soc_uuid();
				if (Assert.isEmpty(soc_uuid)) {
					throw new GetVersionListException().addScene("REASON", "入库方案下无版本数据源信息");
				}
			}
			logger.debug("数据源UUID：" + soc_uuid);
			UuSocInfo uu_soc_info = uuSocDaoService.queryListInfoByUU(soc_uuid).get(0);
			tag_soc_name = uu_soc_info.getVer_soc_name();
			tag_bk_dir = uu_soc_info.getCode_bk_dir();
		}

		// 获取版本数据源信息
		DtSourceInfo soc_info = new DtSourceInfo();
		soc_info.setSoc_name(tag_soc_name);
		DtSourceInfo ver_source_info = dtSourceDaoService.getInfoByKey(soc_info);
		// 获取本地数据源信息
		DtSourceInfo local_info = taskPubSrv.getLocalDtInfo();
		ModuleSourceInfo minfo = new ModuleSourceInfo(local_info, ver_source_info);
		String[] cmds = new String[] { "list " + tag_bk_dir };
		SVN svn = new SVN(minfo, cmds);
		Result result = svn.run();
		String msg = result.getMsg();
		List<String> version_list = new ArrayList<String>();
		if (CMD_STATUS.SUCCEED.equals(result.getStatus())) {
			// 获取目录列表
			logger.debug(msg);
			String[] list = msg.split("\n");
			for (String str : list) {
				if (str.endsWith("/")) {
					str = str.substring(0, str.length() - 1);
					version_list.add(str);
				}
			}
		} else {
			logger.debug(msg);
			throw new GetVersionListException().addScene("REASON", "版本目录不存在");
		}

		output.setVersion_list(version_list.toArray(new String[version_list.size()]));
		logger.info("-----------queryVersionList end----------");
		return output;
	}
}
