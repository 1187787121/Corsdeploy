/**
 * Title: ViewBuildTaskAction.java
 * File Description: 构建相关服务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.tmatesoft.svn.core.SVNException;

import com.wk.cd.build.ea.bean.BuildExeScriptBean;
import com.wk.cd.build.ea.bean.BuildMonPhaseBean;
import com.wk.cd.build.ea.bean.BuildScriptBean;
import com.wk.cd.build.ea.bean.EnvModFileBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewBuildTaskInputBean;
import com.wk.cd.build.ea.bean.ViewBuildTaskOutputBean;
import com.wk.cd.build.ea.bean.ViewConfigModInputBean;
import com.wk.cd.build.ea.dao.BuildConfigfileDaoService;
import com.wk.cd.build.ea.dao.BuildScriptDaoService;
import com.wk.cd.build.ea.dao.BuildScriptExeDaoService;
import com.wk.cd.build.ea.dao.BuildStepDaoService;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.dao.UuParamDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.BuildConfigfileInfo;
import com.wk.cd.build.ea.info.BuildScriptInfo;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.BuildTaskLogGenService;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.ExeScriptPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.build.ea.service.ShellCallHelper;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeSystemCfgDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.ExportSvnDirException;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.SVNUtil;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.service.ModuleCommonService;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.remote.fp.service.RemoteFileList;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.Base64;
import com.wk.util.GBKProperties;
import com.wk.util.JaDateTime;

/**
 * Class Description: 构建相关服务列表
 * @author chiss
 */
public class ViewBuildTaskAction
		extends IViewActionBasic<ViewBuildTaskInputBean, ViewBuildTaskOutputBean> {
	@Inject
	private BuildConfigfileDaoService buildConfigfileDaoService;
	@Inject
	private BuildScriptDaoService buildScriptDaoSrv;
	@Inject
	private BuildScriptExeDaoService buildScriptExeDaoSrv;
	@Inject
	private InstanceExeDaoService instExeDaoSrv;
	@Inject
	private BuildStepDaoService buildStepDaoService;
	@Inject
	private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject
	private CeProjectDaoService ceProjectDaoService;
	@Inject
	private DtCheckSocExistService dtCheckSocExistService;
	@Inject
	private RemoteFileList remoteFileList;
	@Inject
	private ViewPublishAction viewPublishAction;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private UuParamDaoService uuParamDaoService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private ExeScriptPublicService exeScriptPublicService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private InstanceExeDaoService instanceExeDaoService;
	@Inject
	private EnvBuildTaskDaoService envBuildTaskDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicService;
	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private BuildTaskLogGenService buildTaskLogGenService;
	@Inject
	private CommonService commonService;
	@Inject
	private MoTemplateDaoService moTemplateDaoService;
	@Inject
	private FTPRCallService ftprcallSrv;
	@Inject
	private ModuleCommonService moduleCommonService;
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private DtSourceDaoService dtSourceDaoService;
	@Inject
	private PublishTaskInstanceService publishTaskInstanceService;
	@Inject
	private ServerCommonService serverComService;
	private static final Log logger = LogFactory.getLog();
	
	//定义export目录
	public static final String EXPORT_DIR = "version";

	/**
	 * Description: 查看配置文件改动列表
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean getConfigModList(ViewBuildTaskInputBean input) {
		logger.info("-----------getConfigModList begin----------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		String env_name = input.getEnv_name();
		String work_id = input.getWork_id();
		String ce_server_name = input.getCe_server_name();
		CFG_TYPE cfg_type = input.getCfg_type();
		// 非空校验
		Assert.assertNotEmpty(env_name, ViewBuildTaskInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(ce_server_name, ViewBuildTaskInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(cfg_type, ViewBuildTaskInputBean.CFG_TYPECN);

		// 根据任务号及操作类型查询删除信息列表
		List<String> full_path_list = new ArrayList<String>();
		List<String> path_list = new ArrayList<String>();
		List<EnvModFileBean> delete_list = new ArrayList<EnvModFileBean>();
		List<BuildConfigfileInfo> delete_info_list = buildConfigfileDaoService.getInfoByWorkAndFopt(ce_server_name,
				work_id, FOPT_TYPE.DELETE, cfg_type);
		for (BuildConfigfileInfo info : delete_info_list) {
			EnvModFileBean bean = new EnvModFileBean();
			String file_bk_path = info.getFile_bk_path();
			String file_bk_fname = info.getFile_bk_fname();
			// 若目录不以"/"结尾则补全
			if (!file_bk_path.endsWith("/")) {
				file_bk_path = file_bk_path + "/";
			}
			bean.setDir_yn_flag(info.getDir_yn_flag());
			bean.setFull_path(file_bk_path + file_bk_fname);
			full_path_list.add(file_bk_path + file_bk_fname);
			// 若为目录删除，则添加
			if (YN_FLAG.YES.equals(info.getDir_yn_flag())) {
				path_list.add(file_bk_path);
			}
			delete_list.add(bean);
		}

		// 根据任务编号和操作类型查询修改信息列表
		List<EnvModFileBean> modify_list = new ArrayList<EnvModFileBean>();
		List<BuildConfigfileInfo> modify_info_list = buildConfigfileDaoService.getInfoByWorkAndFopt(ce_server_name,
				work_id, FOPT_TYPE.MODIFY, cfg_type);
		for (BuildConfigfileInfo info : modify_info_list) {
			EnvModFileBean bean = new EnvModFileBean();
			String file_bk_path = info.getFile_bk_path();
			String file_bk_fname = info.getFile_bk_fname();
			bean.setDir_yn_flag(info.getDir_yn_flag());
			bean.setFull_path(file_bk_path + file_bk_fname);
			// 标志出已删除的文件
			if (full_path_list.contains(file_bk_path + file_bk_fname)) {
				bean.setDel_mod_flag(true);
				// 若修改文件所在目录已被删除，则加上标志
			} else if (path_list.contains(file_bk_path)) {
				bean.setDel_mod_flag(true);
			} else {
				bean.setDel_mod_flag(false);
			}
			modify_list.add(bean);
		}

		// 根据配置类型输出
		if (CFG_TYPE.NORMAL.equals(cfg_type)) {
			output.setModify_list(modify_list);
			output.setDelete_list(delete_list);
		} else {
			output.setModify_list(modify_list);
		}
		logger.info("-----------getConfigModList end----------");
		return output;
	}

	/**
	 * Description: 获取构建配置文件列表
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean getCfgNameList(ViewBuildTaskInputBean input) {
		logger.info("-----------queryCfgNameList begin----------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		String env_name = input.getEnv_name();
		CFG_TYPE cfg_type = input.getCfg_type();
		// 非空校验
		Assert.assertNotEmpty(env_name, ViewBuildTaskInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(env_name, ViewBuildTaskInputBean.CFG_TYPECN);
		// 根据环境名获取应用系统名下的配置文件列表
		if (CFG_TYPE.NORMAL.equals(cfg_type)) {
			List<String> cfg_name_list = ceSystemCfgDaoService.queryCfgNameByEnvName(env_name);
			output.setCfg_name_list(cfg_name_list);
		} else {
			List<String> cfg_name_list = new ArrayList<String>();
			cfg_name_list.add("server.xml");
			cfg_name_list.add("web.xml");
			output.setCfg_name_list(cfg_name_list);
		}
		logger.info("-----------queryCfgNameList end----------");
		return output;
	}

	/**
	 * Description: 获取构建配置文件列表
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean getConfigPendList(ViewBuildTaskInputBean input) {
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		String env_name = input.getEnv_name();
		String ce_server_name = input.getCe_server_name();
		Assert.assertNotEmpty(ce_server_name, ViewConfigModInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(env_name, ViewConfigModInputBean.ENV_NAMECN);
		serverComService.checkServerIsExist(ce_server_name);
		CeServerInfo server_info = ceServerDaoService.getInfoByServerName(ce_server_name);
		String soc_name = null;
		String server_ip = server_info.getServer_ip();
		if (server_info.getAgent_config_yn_flag() == YN_FLAG.YES) {
			soc_name = server_ip.substring(server_ip.lastIndexOf(".") + 1) + "agent";
		} else {
			soc_name = serverComService.getFtpConfigSocByServerName(ce_server_name);
		}
		List<EnvModFileBean> pend_modify_list = new ArrayList<EnvModFileBean>();
		List<String> name_list = ceSystemCfgDaoService.queryCfgNameByEnvName(env_name);
		List<String> file_list = ShellCallHelper.findFiles(name_list, soc_name, server_ip);
		for (String file : file_list) {
			EnvModFileBean bean = new EnvModFileBean();
			bean.setDir_yn_flag(YN_FLAG.NO);
			bean.setFull_path(file);
			pend_modify_list.add(bean);
		}
		output.setPend_list(pend_modify_list);
		return output;
	}

	// /**
	// * Description: 查看配置文件和中间件列表
	// * @param input
	// * @return
	// */
	// public ViewBuildTaskOutputBean queryDirectoryList(ViewBuildTaskInputBean
	// input) {
	// logger.info("-----------queryDirectoryList begin----------");
	// ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
	// String soc_name = input.getSoc_name();
	// String relative_path = input.getRelative_path();
	// boolean mod_flag = input.isMod_flag();
	// CFG_TYPE cfg_type = input.getCfg_type();
	// // 非空校验
	// Assert.assertNotEmpty(soc_name, ViewBuildTaskInputBean.SOC_NAMECN);
	// Assert.assertNotEmpty(relative_path,
	// ViewBuildTaskInputBean.RELATIVE_PATHCN);
	// Assert.assertNotEmpty(cfg_type, ViewBuildTaskInputBean.CFG_TYPECN);
	// // 合法性校验
	// dtCheckSocExistService.checkSocExist(soc_name);
	// // 根据不同配置类型调整文件可修改标志
	// if (CFG_TYPE.NORMAL.equals(cfg_type)) {
	// // 查看远程文件列表
	// List<FileListBean> file_list_bean =
	// remoteFileList.showRemoteFileList(relative_path, soc_name,
	// input.getWork_seq(), mod_flag);
	// output.setFile_list_bean(file_list_bean);
	// } else {
	// List<FileListBean> file_list_bean =
	// remoteFileList.showRemoteFileList(relative_path, soc_name,
	// input.getWork_seq(), false);
	// if (!Assert.isEmpty(file_list_bean)) {
	// // 只可修改server.xml、web.xml文件
	// for (FileListBean bean : file_list_bean) {
	// if ("server.xml".equals(bean.getFile()) ||
	// "web.xml".equals(bean.getFile())) {
	// bean.setEdit_flag(true);
	// }
	// }
	// }
	// output.setFile_list_bean(file_list_bean);
	// }
	// logger.info("-----------queryDirectoryList end----------");
	// return output;
	// }

	/**
	 * Description: 查看环境构建部署信息
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean getBuildDeployInfo(ViewBuildTaskInputBean input) {
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		logger.info("-----------getBuildDeployInfo start----------");

		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		EnvBuildTaskInfo build_info = envBuildTaskDaoService.getInfoByKey(work_id);
		
		String template_name = build_info.getTemplate_name();
		MoTemplateInfo templateInfo = moTemplateDaoService.getInfoByKey(template_name);
		if(!Assert.isEmpty(templateInfo)) {
			Template template = new Template(templateInfo.getRef_module_id());
			template = com.wk.cd.module1.xml1.XmlReader.read(template);
			List<Phase> phase_list = template.getPhase_list();
			//由于参数值param_value不全，不能直接用文件里面的全局参数列表，暂时先从表里面拿
			List<PhaseParam> param_list = new ArrayList<PhaseParam>();
			
			String template_param_uuid = build_info.getTemplate_param_uuid();
			//没有生成实例之前,返回的phase_list不会有数据源
			if(Assert.isEmpty(template_param_uuid)) {
				param_list = template.getParam_list();
				List<Phase> new_phase_list = new ArrayList<Phase>();
//				List<Phase> phase_list = template.getPhase_list();
				if(!Assert.isEmpty(phase_list)) {
					for (Phase phase : phase_list) {
						new_phase_list.add(phase.copy(phase));
					}
				}
				output.setPhases(new_phase_list);
				output.setParam_list(param_list);
			} else {
				List<UuParamInfo> params = uuParamDaoService.getInfoByUuid(template_param_uuid);
				if(!Assert.isEmpty(params)) {
					for (UuParamInfo uuParamInfo : params) {
						PhaseParam param = new PhaseParam();
						param.setParam_name(uuParamInfo.getParam_name());
						param.setParam_bk_desc(uuParamInfo.getParam_bk_desc());
						param.setParam_cn_name(uuParamInfo.getParam_cn_name());
						param.setParam_type(uuParamInfo.getParam_type());
						param.setParam_value(uuParamInfo.getParam_value());
						param.setModify_flag(uuParamInfo.getParam_modify_flag());
						param_list.add(param);
					}
				}
				output.setPhases(phase_list);
				output.setParam_list(param_list);
			}
			
		}
		// 获取构建信息
		EnvTaskInfo taskInfo = envTaskDaoService.getInfoByKey(work_id);
		output.setTemplate_name(template_name);
		output.setTask_status(build_info.getTask_status());
		output.setExe_result(build_info.getExe_result());
		output.setExe_method(taskInfo.getExe_method());
		
//		if (!Assert.isEmpty(template_name)) {
//			// 获取阶段列表
//			List<PhasePublishBean> phases = new ArrayList<PhasePublishBean>();
//			List<BuildStepInfo> build_step_list = buildStepDaoService.queryListInfoByIdAndTp(work_id, template_name);
//			for (BuildStepInfo info : build_step_list) {
//				PhasePublishBean bean = new PhasePublishBean();
//				bean.setPhase_no(info.getPhase_id());
//				bean.setCn_name(info.getPhase_bk_desc());
//				bean.setGen_flag(info.getGen_yn_flag());
//				bean.setImpl_type(info.getImpl_type());
//				List<SrvSocBean> soc_list = new ArrayList<SrvSocBean>();
//				String soc_uuid = info.getSoc_uuid();
//				List<UuSocInfo> uu_soc_list = uuSocDaoService.queryListInfoByUU(soc_uuid);
//				if (!Assert.isEmpty(uu_soc_list)) {
//					for (UuSocInfo soc_info : uu_soc_list) {
//						SrvSocBean soc_bean = new SrvSocBean();
//						String server_name = soc_info.getExe_server_name();
//						String ver_server_name = soc_info.getVer_server_name();
//						soc_bean.setVer_server_name(ver_server_name);
//						soc_bean.setExe_server_name(server_name);
//						if (!Assert.isEmpty(server_name)) {
//							CeServerInfo server_info = ceServerDaoService.getInfoByServerName(server_name);
//							soc_bean.setExe_ip(server_info.getServer_ip());
//							soc_bean.setExe_cn_name(server_info.getServer_cn_name());
//						}
//						if (!Assert.isEmpty(ver_server_name)) {
//							CeServerInfo server_info = ceServerDaoService.getInfoByServerName(ver_server_name);
//							soc_bean.setVer_ip(server_info.getServer_ip());
//							soc_bean.setVer_cn_name(server_info.getServer_cn_name());
//						}
//						soc_bean.setExe_soc_name(soc_info.getExe_soc_name());
//						soc_bean.setVer_soc_name(soc_info.getVer_soc_name());
//						soc_list.add(soc_bean);
//					}
//				}
//				bean.setSrv_soc(soc_list.toArray(new SrvSocBean[soc_list.size()]));
//				phases.add(bean);
//			}
//
//			// 获取模板参数列表
//			String template_param_uuid = build_info.getTemplate_param_uuid();
//			List<UuParamInfo> param_list = uuParamDaoService.getInfoByUuid(template_param_uuid);
//
//			output.setPhases(phases);
//			output.setParam_list(param_list);
//		}
		logger.info("-----------getBuildDeployInfo end----------");
		return output;
	}

	/**
	 * Description: 监控构建应用部署执行进度
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean monitorBuildDeploy(ViewBuildTaskInputBean input) {
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		logger.info("-----------monitorBuildDeploy start----------");

		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);

		// 获取监控信息
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		EnvBuildTaskInfo buildInfo = envBuildTaskDaoService.getInfoByKey(work_id);
		output.setStart_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00").equals(buildInfo.getStart_bk_tm()) ? null : buildInfo.getStart_bk_tm());
		output.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00").equals(buildInfo.getEnd_bk_tm()) ? null : buildInfo.getEnd_bk_tm());
		output.setTask_status(buildInfo.getTask_status());
		output.setExe_result(buildInfo.getExe_result());
		
		Instance instance = new Instance(task_info.getInstance_id());
		instance = com.wk.cd.module1.xml1.XmlReader.read(instance);
		List<InstancePhase> phases = instance.getPhase();
		
		// 获取前端根路径
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		String log_path = buildInfo.getExelog_bk_path();
		output.setExelog_bk_path(Assert.isEmpty(log_path) ? log_path : log_path.replace(web_root_path, ""));
		
		List<BuildMonPhaseBean> build_phase_list = new ArrayList<BuildMonPhaseBean>();
		// 根据任务编号查询任务执行列表
		List<InstanceExeInfo> task_exe_list = instanceExeDaoService.getExeInstMsgByInstId(task_info.getInstance_id());
		if(!Assert.isEmpty(task_exe_list)) {
			
			//返回当前执行阶段数
			for (InstanceExeInfo info : task_exe_list) {
				if(EXE_STATUS.PENDING.equals(info.getExe_status()) && Assert.isEmpty(info.getExe_result())) {
					output.setExe_phase(info.getInst_bk_no());
					break;
				}
			}
			
			output.setAll_phases(task_exe_list.size());
			for (InstanceExeInfo info : task_exe_list) {
				BuildMonPhaseBean bean = new BuildMonPhaseBean();
				int phase_id = info.getInst_bk_no();
//				output.setExe_phase(phase_id);
				bean.setPhase_id(phase_id);
				if(!Assert.isEmpty(phases)) {
					bean.setImpl_type(phases.get(phase_id-1).getImpl_type());
					bean.setScript(phases.get(phase_id-1).getScript());
					ModuleSourceInfo module_info = phases.get(phase_id-1).getModule_source_info();
					if(!Assert.isEmpty(module_info)) {
						bean.setExe_server_name(module_info.getExe_server_name());
						bean.setExe_soc_name(module_info.getExe_soc_name());
						if(!Assert.isEmpty(module_info.getVer_server_name())) {
							bean.setVer_server_name(module_info.getVer_server_name());
						}
						if(!Assert.isEmpty(module_info.getVer_soc_name())) {
							bean.setVer_soc_name(module_info.getVer_soc_name());
						}
					}
					
				}
				
				if(EXE_STATUS.PENDING.equals(info.getExe_status())){
					bean.setExe_status(EXE_STATUS.PENDING);
				}else if(EXE_STATUS.RUNNING.equals(info.getExe_status())){
					bean.setExe_status(EXE_STATUS.RUNNING);
//					status_flag = false;
				}else if(EXE_STATUS.SKIP.equals(info.getExe_status())){
					bean.setExe_status(EXE_STATUS.SKIP);
				}else if(EXE_STATUS.EXECUTED.equals(info.getExe_status())){
					bean.setExe_status(EXE_STATUS.EXECUTED);
				}
				//判断模板阶段结果
				if(EXE_RESULT.FAIL.equals(info.getExe_result())){
					bean.setTask_exe_result(EXE_RESULT.FAIL);
					bean.setExe_status(EXE_STATUS.EXECUTED);
//					status_flag = false;
				}else if(EXE_RESULT.SUCCESS.equals(info.getExe_result())){
					bean.setTask_exe_result(EXE_RESULT.SUCCESS);
				}
				bean.setPhase_name(info.getStep_bk_desc());
				build_phase_list.add(bean);
			}
			output.setBuild_phase_list(build_phase_list);
		}
		
//		if (!Assert.isEmpty(task_exe_list)) {
//			output.setAll_phases(task_exe_list.size());
//			// 定义实例阶段MAP
//			Map<Integer,List<InstanceExeInfo>> inst_phase_map = new HashMap<Integer, List<InstanceExeInfo>>();
//			List<InstanceExeInfo> phase_list = new ArrayList<InstanceExeInfo>();
//			int src_phase_id = 0;
//			boolean result_flag = false;
//			boolean flag = true;
//			for (InstanceExeInfo info : task_exe_list) {
//				int phase_id = info.getTpl_bk_no();
//				if(src_phase_id != phase_id){
//					if(src_phase_id != 0){
//						inst_phase_map.put(src_phase_id, phase_list);
//					}
//					phase_list = new ArrayList<InstanceExeInfo>();
//					src_phase_id = phase_id;
//					
//					//判断当前执行步骤数(状态为待执行或结果为失败)
//					EXE_RESULT exe_result = info.getExe_result();
//					if(!Assert.isEmpty(exe_result) && exe_result.equals(EXE_RESULT.FAIL)){
//						result_flag = true;
//					}
//					//监控执行到第几步（规则：按步骤遍历，第一个待执行或执行中的步骤，或执行失败的步骤，或者最后一个跳过的步骤）
//					if(flag){
//						//判断执行状态为待执行或执行中或执行结果为失败的步骤
//						if((info.getExe_status().getValue() <= 2) || result_flag){
//							output.setExe_phase(phase_id);
//							flag = false;
//						}
//						//判断执行状态为跳过的步骤
//						if(EXE_STATUS.SKIP.equals(info.getExe_status())){
//							output.setExe_phase(phase_id);
//						}
//						//判断执行状态为已执行的步骤
//						if(EXE_STATUS.EXECUTED.equals(info.getExe_status())){
//							output.setExe_phase(phase_id);
//						}
//					}
//				}
//				phase_list.add(info);
//			}
//			inst_phase_map.put(src_phase_id, phase_list);
//			
//			//遍历map
//			List<BuildMonPhaseBean> build_phase_list = new ArrayList<BuildMonPhaseBean>();
//			boolean status_flag = true;
//			for(int i = 1; i <= inst_phase_map.size(); i++){
//				BuildMonPhaseBean bean = new BuildMonPhaseBean();
//				bean.setPhase_id(i);
//				if(!Assert.isEmpty(phases)) {
//					bean.setImpl_type(phases.get(i-1).getImpl_type());
//					bean.setScript(phases.get(i-1).getScript());
//					ModuleSourceInfo module_info = phases.get(i-1).getModule_source_info();
//					if(!Assert.isEmpty(module_info)) {
//						bean.setExe_server_name(module_info.getExe_server_name());
//						bean.setExe_soc_name(module_info.getExe_soc_name());
//						if(!Assert.isEmpty(module_info.getVer_server_name())) {
//							bean.setVer_server_name(module_info.getVer_server_name());
//						}
//						if(!Assert.isEmpty(module_info.getVer_soc_name())) {
//							bean.setVer_soc_name(module_info.getVer_soc_name());
//						}
//					}
//					
//				}
//				List<InstanceExeInfo> inst_info_list = inst_phase_map.get(i);
//				List<InstPhaseBean> inst_phase_list = new ArrayList<InstPhaseBean>();
//				int size = 1;
//				for(InstanceExeInfo info : inst_info_list){
//					InstPhaseBean bean2 = new InstPhaseBean();
//					bean2.setExe_status(info.getExe_status());
//					bean2.setTask_exe_result(info.getExe_result());
//					
////					if(!Assert.isEmpty(uu_soc_list) && IMPL_TYPE.SVN.equals(build_step_info.getImpl_type())){
////						bean2.setSoc_name(uu_soc_list.get(size-1).getExe_soc_name()+"/"+uu_soc_list.get(size-1).getVer_soc_name());
////					}else{
////						bean2.setSoc_name(info.getSoc_name());
////					}
//					//判断模板阶段状态
//					if(EXE_STATUS.PENDING.equals(info.getExe_status()) && status_flag){
//						bean.setExe_status(EXE_STATUS.PENDING);
//					}else if(EXE_STATUS.RUNNING.equals(info.getExe_status())){
//						bean.setExe_status(EXE_STATUS.RUNNING);
//						status_flag = false;
//					}else if(EXE_STATUS.SKIP.equals(info.getExe_status())){
//						bean.setExe_status(EXE_STATUS.SKIP);
//					}else if(EXE_STATUS.EXECUTED.equals(info.getExe_status()) && size == inst_info_list.size()){
//						bean.setExe_status(EXE_STATUS.EXECUTED);
//					}
//					//判断模板阶段结果
//					if(EXE_RESULT.FAIL.equals(info.getExe_result())){
//						bean.setTask_exe_result(EXE_RESULT.FAIL);
//						bean.setExe_status(EXE_STATUS.EXECUTED);
//						status_flag = false;
//					}else if(EXE_RESULT.SUCCESS.equals(info.getExe_result())){
//						bean.setTask_exe_result(EXE_RESULT.SUCCESS);
//					}
//					bean.setPhase_name(info.getStep_bk_desc());
//					inst_phase_list.add(bean2);
//					size++;
//				}
//				bean.setInst_phase_list(inst_phase_list);
//				build_phase_list.add(bean);
//			}
//			output.setBuild_phase_list(build_phase_list);
//		}

		logger.info("-----------monitorBuildDeploy end----------");
		return output;
	}
	
	/**
	 * Description: 查看构建脚本信息
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean queryBuildScriptMsg(ViewBuildTaskInputBean input) {
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		logger.info("---------------queryBuildScriptMsg Begin---------------------");
		String work_id = input.getWork_id();
		long script_bk_seq = input.getScript_bk_seq();
		SCRIPT_TYPE script_type = input.getScript_type();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(script_type.getValue() == 0 ? null : script_type, ViewBuildTaskInputBean.SCRIPT_TYPECN);
		Assert.assertNotEmpty(script_bk_seq == 0 ? null : script_bk_seq, ViewBuildTaskInputBean.SCRIPT_BK_SEQCN);
		// 合法性校检
		exeScriptPublicService.checkExeScriptIsExist(work_id, script_bk_seq, script_type);
		// 获得实例ID
		BuildScriptInfo build_info = buildScriptDaoSrv.getScriptByKey(work_id, script_bk_seq, script_type);
		String inst_id = build_info.getInstance_id();
		SCRIPT_METHOD method = build_info.getScript_method();
		if (method == SCRIPT_METHOD.COMP) {
			String comp_id = build_info.getModule_id();
			Instance instance = new Instance(inst_id);
			List<InstancePhase> modu_list = XmlReader.read(instance).getPhase();
			if (!Assert.isEmpty(comp_id)&&!Assert.isEmpty(modu_list)) {
				output.setScript_text(modu_list.get(0).getScript().getCmds());
			}
		} else {
			String msg = build_info.getScript_text();
			if (msg != null) {
				output.setScript_text(msg.split("\n"));
			}
		}
		// 通过实例Id获得执行信息
		List<InstanceExeInfo> exemsg_list = instExeDaoSrv.getExeInstMsgByInstId(inst_id);
		List<InstanceExeInfo> exe_text_list = new ArrayList<InstanceExeInfo>();
		if (!Assert.isEmpty(exemsg_list)) {
			for (InstanceExeInfo bscrp : exemsg_list) {
				InstanceExeInfo info = new InstanceExeInfo();
				String msg = "";
				if (!Assert.isEmpty(bscrp.getExec_text())) {
					msg = bscrp.getExec_text();
					if (!Assert.isEmpty(msg)) {
						// 将字符串转换成byte数组，并转码
						try {
							msg = new String(Base64.encode(msg.getBytes("UTF-8")));
						} catch (UnsupportedEncodingException e) {
							throw new com.wk.cd.exc.UnsupportedEncodingException();
						}

					}
				}
				info.setExec_text(msg);
				info.setEnd_bk_tm(bscrp.getEnd_bk_tm());
				info.setInst_bk_no(bscrp.getInst_bk_no());
				info.setExe_result(bscrp.getExe_result());
				info.setExe_status(bscrp.getExe_status());
				info.setInstance_id(bscrp.getInstance_id());
				info.setServer_name(bscrp.getServer_name());
				info.setSoc_name(bscrp.getSoc_name());
				info.setStart_bk_tm(bscrp.getStart_bk_tm());
				info.setTime_used(bscrp.getTime_used());
				exe_text_list.add(info);
			}
		}
		output.setExemsg_list(exe_text_list);
		logger.info("---------------queryBuildScriptMsg End-----------------------");
		return output;
	}

	/**
	 * Description: 监控构建脚本执行进度(待定)
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean monitorBuildScript2(ViewBuildTaskInputBean input) {
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		logger.info("---------------monitorBuildScript Begin---------------------");
		String work_id = input.getWork_id();
		long script_bk_seq = input.getScript_bk_seq();
		SCRIPT_TYPE script_type = input.getScript_type();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(script_type.getValue() == 0 ? null : script_type, ViewBuildTaskInputBean.SCRIPT_TYPECN);
		Assert.assertNotEmpty(script_bk_seq == 0 ? null : script_bk_seq, ViewBuildTaskInputBean.SCRIPT_BK_SEQCN);
		// 合法性校检
		exeScriptPublicService.checkExeScriptIsExist(work_id, script_bk_seq, script_type);
		BuildScriptInfo build_info = buildScriptDaoSrv.getScriptByKey(work_id, script_bk_seq, script_type);
		String inst_id = build_info.getInstance_id();
		int total = 0;
		int phase_id = 0;
		if (!Assert.isEmpty(inst_id)) {
			Process process = null;
			try {
				process = ProcessManager.instance.getProcessInstance(inst_id);
				total = process.getCtx().getInstance_info().getPhaseCount();
				phase_id = process.getCtx().getCurrentStage();
				// 执行结果
				output.setExe_result(build_info.getExe_result());
				// 总阶段
				output.setAll_phases(total);
				// 当前阶段
				output.setExe_phase(phase_id + 1);
				logger.info("构建总阶段total=[{}]", total);
			} catch (Exception e) {
				// 总阶段
				output.setAll_phases(total);
				// 当前阶段
				output.setExe_phase(phase_id + 1);
				output.setExe_result(build_info.getExe_result());
				return output;
			}
		}
		logger.info("---------------monitorBuildScript End-----------------------");
		return output;
	}

	/**
	 * Description: 监控构建脚本执行进度
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean monitorBuildScript(ViewBuildTaskInputBean input) {
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		logger.info("---------------monitorBuildScript Begin---------------------");
		String work_id = input.getWork_id();
		long script_bk_seq = input.getScript_bk_seq();
		SCRIPT_TYPE script_type = input.getScript_type();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(script_type.getValue() == 0 ? null : script_type, ViewBuildTaskInputBean.SCRIPT_TYPECN);
		Assert.assertNotEmpty(script_bk_seq == 0 ? null : script_bk_seq, ViewBuildTaskInputBean.SCRIPT_BK_SEQCN);
		// 合法性校检
		exeScriptPublicService.checkExeScriptIsExist(work_id, script_bk_seq, script_type);
		BuildScriptInfo build_info = buildScriptDaoSrv.getScriptByKey(work_id, script_bk_seq, script_type);
		String inst_id = build_info.getInstance_id();
		if (!Assert.isEmpty(inst_id)) {
			List<InstanceExeInfo> exe_list = instExeDaoSrv.getExeInstMsgByInstId(inst_id);
			sortWithExeBkNo(exe_list);
			if (!Assert.isEmpty(exe_list)) {
				for (InstanceExeInfo bexemsg : exe_list) {
					EXE_RESULT result = bexemsg.getExe_result();
					//output.setExe_result(result);
					if (result == EXE_RESULT.FAIL) {
						output.setExe_phase(bexemsg.getInst_bk_no() + 1);
						//output.setExe_result(result);
						break;
					}
					if (result == null) {
						output.setExe_phase(bexemsg.getInst_bk_no() + 1);
						//output.setExe_result(result);
						break;
					}
					output.setExe_phase(bexemsg.getInst_bk_no() + 1);
				}
				output.setAll_phases(exe_list.size());
				output.setExe_result(build_info.getExe_result());
				List<InstanceExeInfo> exe_text_list = new ArrayList<InstanceExeInfo>();
				if (!Assert.isEmpty(exe_list)) {
					for (InstanceExeInfo bscrp : exe_list) {
						InstanceExeInfo info = new InstanceExeInfo();
						String msg = "";
						if (!Assert.isEmpty(bscrp.getExec_text())) {
							msg = bscrp.getExec_text();
							if (!Assert.isEmpty(msg)) {
								// 将字符串转换成byte数组，并转码
								try {
									msg = new String(Base64.encode(msg.getBytes("UTF-8")));
								} catch (UnsupportedEncodingException e) {
									throw new com.wk.cd.exc.UnsupportedEncodingException();
								}
							}
						}
						info.setExec_text(msg);
						info.setEnd_bk_tm(bscrp.getEnd_bk_tm());
						info.setInst_bk_no(bscrp.getInst_bk_no());
						info.setExe_result(bscrp.getExe_result());
						info.setExe_status(bscrp.getExe_status());
						info.setInstance_id(bscrp.getInstance_id());
						info.setServer_name(bscrp.getServer_name());
						info.setSoc_name(bscrp.getSoc_name());
						info.setStart_bk_tm(bscrp.getStart_bk_tm());
						info.setTime_used(bscrp.getTime_used());
						exe_text_list.add(info);
					}
				}
				output.setExemsg_list(exe_text_list);
				return output;
			}
		}
		logger.info("---------------monitorBuildScript End-----------------------");
		return output;
	}

	/**
	 * 查看环境构建任务
	 */
	public ViewBuildTaskOutputBean getBuildInfo(ViewBuildTaskInputBean input) {
		logger.info("-----------getBuildInfo begin----------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		String work_id = input.getWork_id();

		// 非空校验
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);

		envTaskPublicService.checkEnvTaskIsExist(work_id);
		// 获取构建信息
		EnvTaskInfo taskInfo = envTaskDaoService.getInfoByKey(work_id);
		EnvBuildTaskInfo buildtaskInfo = envBuildTaskDaoService.getInfoByKey(work_id);
		output.setEnv_name(taskInfo.getEnv_name());
		output.setTask_bk_desc(taskInfo.getTask_bk_desc());
		String project_name = taskInfo.getProject_name();
		if (!Assert.isEmpty(project_name)) {
			output.setProject_name(project_name);
			output.setProject_short_name(
					ceProjectDaoService.getInfoByProjectName(project_name).getProject_short_name());
		}
		output.setTemplate_name(buildtaskInfo.getTemplate_name());
		output.setBuild_step_id(buildtaskInfo.getBuild_step_id());
		// 获取前端根路径
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		String exelog_bk_path = taskInfo.getExelog_bk_path();
		if (!Assert.isEmpty(exelog_bk_path)) {
			output.setExelog_bk_path(exelog_bk_path.replace(web_root_path, ""));
		}

		logger.info("-----------getBuildDeployInfo end----------");
		return output;
	}

	/**
	 * 跳转下一步骤
	 */
	public ViewBuildTaskOutputBean gotoNextStep(ViewBuildTaskInputBean input) {
		logger.info("-----------getBuildInfo begin----------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		String work_id = input.getWork_id();
		int build_step_id = input.getBuild_step_id();

		// 非空校验
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);

		// 获取构建信息
		EnvBuildTaskInfo buildTaskInfo = envBuildTaskDaoService.getInfoByKey(work_id);
		int old_Build_step_id = buildTaskInfo.getBuild_step_id();

		// 更新构建步骤数
		if (old_Build_step_id < build_step_id) {
			envBuildTaskDaoService.updateBuildStepIdById(build_step_id, work_id);
		}

		logger.info("-----------getBuildDeployInfo end----------");
		return output;
	}

	/**
	 * Description: sort
	 * @param list
	 */
	private void sortWithExeBkNo(List<InstanceExeInfo> list) {
		Collections.sort(list, new Comparator<InstanceExeInfo>() {
			@Override
			public int compare(InstanceExeInfo bean1, InstanceExeInfo bean2) {
				return ((Integer) bean1.getInst_bk_no()).compareTo(bean2.getInst_bk_no());
			}
		});
	}

	/**
	 * Description: 生成构建日志
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean generateBuildLog(ViewBuildTaskInputBean input) {
		logger.info("-----------generateBuildLog begin----------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);

		// 生成构建日志
		String exelog_bk_path = buildTaskLogGenService.generateBuildTaskLog(work_id);
		output.setExelog_bk_path(exelog_bk_path);

		logger.info("-----------generateBuildLog end----------");
		return output;
	}

	/**
	 * Description: 获得构建信息
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean getBuildScriptMsg(ViewBuildTaskInputBean input) {
		logger.info("------------getBuildScriptMsg Begin-------------------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		String work_id = input.getWork_id();
		int[] script_type_list = input.getScript_type_list();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(script_type_list, ViewBuildTaskInputBean.SCRIPT_TYPE_LISTCN);
		List<BuildScriptBean> script_lists = new ArrayList<BuildScriptBean>();
		for (int type : script_type_list) {
			List<BuildExeScriptBean> spt_list = new ArrayList<BuildExeScriptBean>();
			BuildScriptBean spbean = new BuildScriptBean();
			spbean.setScript_type(EnumValue.valueOf(SCRIPT_TYPE.class, type));
			List<BuildScriptInfo> script_list = buildScriptDaoSrv.getScriptByIdAndType(work_id,
					EnumValue.valueOf(SCRIPT_TYPE.class, type));
			if (!Assert.isEmpty(script_list)) {
				for (BuildScriptInfo spt : script_list) {
					BuildExeScriptBean bean = new BuildExeScriptBean();
					String inst_id = spt.getInstance_id();
					if (!Assert.isEmpty(inst_id)) {
						List<InstanceExeInfo> exe_list = instExeDaoSrv.getExeInstMsgByInstId(inst_id);
						sortWithExeBkNo(exe_list);
						bean.setExe_result(spt.getExe_result());
						if (!Assert.isEmpty(exe_list)) {
							for (InstanceExeInfo bexemsg : exe_list) {
								EXE_RESULT result = bexemsg.getExe_result();
								bean.setExe_result(result);
								bean.setExe_phase(bexemsg.getInst_bk_no() + 1);
								if (result == EXE_RESULT.FAIL) {
									bean.setExe_phase(bexemsg.getInst_bk_no() + 1);
									bean.setExe_result(result);
									break;
								}
								if (result == null) {
									bean.setExe_phase(bexemsg.getInst_bk_no() + 1);
									bean.setExe_result(result);
									break;
								}
							}
							bean.setAll_phases(exe_list.size());
						}
					}
					bean.setId(spt.getModule_id());
					bean.setWork_id(spt.getWork_id());
					bean.setCn_name(spt.getModule_cn_name());
					bean.setScript_method(spt.getScript_method());
					bean.setScript_type(spt.getScript_type());
					bean.setScript_bk_seq(spt.getScirpt_bk_seq());
					String soc_uuid = spt.getSoc_uuid();
					if (!Assert.isEmpty(soc_uuid)) {
						List<UuSocInfo> soc_list = uuSocDaoService.queryListInfoByUU(soc_uuid);
						bean.setSoc_list(soc_list);
					}
					if (!Assert.isEmpty(spt.getScript_text())) {
						bean.setScript_text(spt.getScript_text().split("\n"));
					}
					String param_uuid = spt.getModule_param_uuid();
					if (!Assert.isEmpty(param_uuid)) {
						List<UuParamInfo> param_list = uuParamDaoService.getInfoByUuid(param_uuid);
						bean.setParam_list(UuParamInfo.copyToParamInfos(param_list));
					}
					spt_list.add(bean);
				}
				spbean.setScript_list(spt_list);
			}
			script_lists.add(spbean);
		}
		output.setScript_list(script_lists);
		logger.info("------------getBuildScriptMsg End-------------------");
		return output;
	}
	
	/**
	 * Description: 查看构建清单及投产包信息
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean queryBuildListAndPacInfo(ViewBuildTaskInputBean input) {

		logger.info("------------queryBuildListAndPacInfo Begin-------------------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();

		// 非空校验
		String work_id = input.getWork_id();
		List<PhaseParam> param_list = input.getParam_list();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);

		// 校验任务ID是否存在
		envTaskDaoService.checkEnvIdIsNotExist(work_id);

		// 获取构建信息
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		EnvBuildTaskInfo build_info = envBuildTaskDaoService.getInfoByKey(work_id);
		String template_name = build_info.getTemplate_name();
		String ver_soc_uuid = build_info.getVer_soc_uuid();
		// 获取参数列表
		boolean save_flag = true;
		String param_uuid = build_info.getTemplate_param_uuid();
		if (Assert.isEmpty(param_list)) {
			if (!Assert.isEmpty(param_uuid)) {
				List<PhaseParam> new_params = new ArrayList<PhaseParam>();
				List<UuParamInfo> params = uuParamDaoService.getInfoByUuid(param_uuid);
				if (!Assert.isEmpty(params)) {
					for (UuParamInfo uuParamInfo : params) {
						PhaseParam param = new PhaseParam();
						param.setParam_name(uuParamInfo.getParam_name());
						param.setParam_bk_desc(uuParamInfo.getParam_bk_desc());
						param.setParam_cn_name(uuParamInfo.getParam_cn_name());
						param.setParam_type(uuParamInfo.getParam_type());
						param.setParam_value(uuParamInfo.getParam_value());
						param.setModify_flag(uuParamInfo.getParam_modify_flag());
						new_params.add(param);
					}
				}
				save_flag = false;
				param_list = new_params;
			}
		}

		// 比较投产包参数有无变化
		boolean change_flag = publishTaskInstanceService.comparePacParam(work_id, param_list);
		output.setChange_flag(change_flag);

		// 返回日志信息
		String log = task_info.getExelog_bk_path();
		if (!Assert.isEmpty(log)) {
			// 获取前端上传根路径
			GBKProperties system = CfgTool.getProperties("system.properties");
			String root_path = system.get("system.app.upload_base_path");
			log = log.replace(root_path, "");
			// 生成构建日志
			buildTaskLogGenService.generateBuildTaskLog(work_id);
			TargetPackageBean log_bean = new TargetPackageBean();
			log_bean.setPackage_name(FileTool.getFileName(log));
			log_bean.setDownload_path(log);
			output.setLog_bean(log_bean);
		}
		if (!Assert.isEmpty(ver_soc_uuid)) {
			List<UuSocInfo> soc_list = uuSocDaoService.queryListInfoByUU(ver_soc_uuid);
			if (!Assert.isEmpty(soc_list)) {
				String ver_server_name = soc_list.get(0).getVer_server_name();
				String ver_soc_name = soc_list.get(0).getVer_soc_name();
				String ver_root_path = soc_list.get(0).getCode_bk_dir();
				output.setCe_server_name(ver_server_name);
				output.setSoc_name(ver_soc_name);
				output.setVer_root_path(ver_root_path);

				// 获取清单投产包
				GBKProperties system = CfgTool.getProperties("system.properties");
				String root_path = system.get("system.app.upload_base_path");
				File root_dir = new File(root_path + "task/" + work_id + "/" + EXPORT_DIR);
				// 若无变化，则返回清单投产包信息
				if (!change_flag) {
					/**
					 * 获取清单文件
					 */
					File[] list2 = root_dir.listFiles();
					List<TargetPackageBean> list_list = new ArrayList<TargetPackageBean>();
					if (!Assert.isEmpty(list2)) {
						for (File f : list2) {
							logger.debug("版本根目录下文件：" + f.getName());
							if (f.getName().endsWith(".xlsx")) {
								TargetPackageBean bean = new TargetPackageBean();
								bean.setPackage_name(f.getName());
								bean.setDownload_path("task/" + work_id + "/" + EXPORT_DIR + "/" + f.getName());
								list_list.add(bean);
							}
						}
					}
					output.setList_list(list_list);

					// 获取投产包,根据param_uuid来判断是从表里面拿还是文件里面拿
					List<String> dpp_path_list = new ArrayList<String>();
					if (Assert.isEmpty(param_uuid)) {
						MoTemplateInfo template_info = moTemplateDaoService.getInfoByKey(template_name);
						Template template = new Template(template_info.getRef_module_id());
						template = com.wk.cd.module1.xml1.XmlReader.read(template);
						if (!Assert.isEmpty(template.getParam_list())) {
							for (PhaseParam param : template.getParam_list()) {
								if (param.getParam_type() == PARAM_TYPE.PDDPARAM) {
									if(!dpp_path_list.contains(param.getParam_value())) {
										dpp_path_list.add(param.getParam_value());
									}
								}
							}
						}
					} else {
						dpp_path_list = uuParamDaoService.getValueByKey(param_uuid);
					}

					List<TargetPackageBean> pac_list = viewPublishAction.getPacFile(dpp_path_list, ver_root_path, root_path, work_id);
					output.setPac_list(pac_list);
				}
			}
		}

		// 保存参数列表
		if (save_flag) {
			logger.info("保存参数列表信息:任务编号[{}]", work_id);
			String template_param_uuid = UUID.randomUUID().toString().replace("-", "");
			envProgPublicService.deleteUuParam(work_id);
			envProgPublicService.insertUuParamByParams(param_list, template_param_uuid);
			logger.info("保存构建任务扩展表:任务编号[{}]", work_id);
			envBuildTaskDaoService.updateBuildParamInfoByKey(template_name, template_param_uuid, work_id);
		}

		logger.info("------------queryBuildListAndPacInfo End-------------------");
		return output;
	}
	
	/**
	 * Description: 获取构建清单及投产包
	 * @param input
	 * @return
	 */
	public ViewBuildTaskOutputBean getBuildListAndPac(ViewBuildTaskInputBean input) {
		logger.info("------------getBuildListAndPac Begin-------------------");
		ViewBuildTaskOutputBean output = new ViewBuildTaskOutputBean();
		
		//非空校验
		String work_id = input.getWork_id();
		String ver_server_name = input.getCe_server_name();
		String ver_soc_name = input.getSoc_name();
		String ver_root_path = input.getVer_root_path();
		Assert.assertNotEmpty(work_id, ViewBuildTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(ver_server_name, ViewBuildTaskInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(ver_soc_name, ViewBuildTaskInputBean.SOC_NAMECN);
		Assert.assertNotEmpty(ver_root_path, ViewBuildTaskInputBean.VER_ROOT_PATHCN);
		
		//校验任务ID是否存在
		envTaskDaoService.checkEnvIdIsNotExist(work_id);
		
		//获取版本数据源信息
		DtSourceInfo soc_info = new DtSourceInfo();
		soc_info.setSoc_name(ver_soc_name);
		DtSourceInfo ver_source_info = dtSourceDaoService.getInfoByKey(soc_info);
		//获取本地数据源信息
		DtSourceInfo local_info = envTaskPublicService.getLocalDtInfo();
		
		//判断根路径是否已经checkout过，若无则checkout
		GBKProperties system = CfgTool.getProperties("system.properties");
		String root_path = system.get("system.app.upload_base_path");
		File root_dir = new File(root_path + "task/" + work_id + "/" + EXPORT_DIR);
		if(!root_dir.exists()){
			logger.debug("第一次export");
			if (CfgTool.getProjectPropterty("cv.svn.use.svnkit.client").equalsIgnoreCase("true")) {
				try {
					String remote_path = ver_root_path.replace(ver_source_info.getUser_root_path(), "");
					SVNUtil.export(ver_source_info, remote_path, CfgTool.getWebRootPath()+"task/" + work_id + "/" + EXPORT_DIR);
				} catch (SVNException e) {
					logger.debug(e.getMessage(), e);
					throw new FileNotExistException().addScene("FILE", ver_root_path);
				}
			} else {
				ModuleSourceInfo minfo = new ModuleSourceInfo(local_info, ver_source_info);
				String[] cmds = new String[] { "cd ./task/" + work_id + "/", "export " + ver_root_path + " " + "task/" + work_id + "/" + EXPORT_DIR};
				SVN svn = new SVN(minfo, cmds);
				Result result = svn.run();
				if(CMD_STATUS.SUCCEED.equals(result.getStatus())){
					logger.debug(result.getMsg());
				}else{
					logger.debug(result.getMsg());
					throw new ExportSvnDirException();
				}
			}
		}
		
		/**
		 * 获取清单文件
		 */
		File[] list2 = root_dir.listFiles();
		List<TargetPackageBean> list_list = new ArrayList<TargetPackageBean>();
		if(!Assert.isEmpty(list2)){
			for(File f : list2){
				logger.debug("版本根目录下文件：" + f.getName());
				if(f.getName().endsWith(".xlsx")){
					TargetPackageBean bean = new TargetPackageBean();
					bean.setPackage_name(f.getName());
					bean.setDownload_path("task/" + work_id + "/" + EXPORT_DIR + "/" + f.getName());
					list_list.add(bean);
				}
			}
		}
		output.setList_list(list_list);
		
		//获取投产包参数
		EnvBuildTaskInfo task_info = envBuildTaskDaoService.getInfoByKey(work_id);
		String param_uuid = task_info.getTemplate_param_uuid();
		List<String> dpp_path_list = new ArrayList<String>();
		if(Assert.isEmpty(param_uuid)) {
			String template_name = task_info.getTemplate_name();
			MoTemplateInfo template_info = moTemplateDaoService.getInfoByKey(template_name);
			Template template = new Template(template_info.getRef_module_id());
			template = com.wk.cd.module1.xml1.XmlReader.read(template);
			if(!Assert.isEmpty(template.getParam_list())) {
				for (PhaseParam param : template.getParam_list()) {
					if(param.getParam_type()==PARAM_TYPE.PDDPARAM) {
						if(!dpp_path_list.contains(param.getParam_value())) {
							dpp_path_list.add(param.getParam_value());
						}
					}
				}
			}
		} else {
			dpp_path_list = uuParamDaoService.getValueByKey(param_uuid);
		}
		
		//获取投产包
		List<TargetPackageBean> pac_list = viewPublishAction.getPacFile(dpp_path_list, ver_root_path, root_path, work_id);
		output.setPac_list(pac_list);
		
		//保存相关信息
		String uuid = UUID.randomUUID().toString().replace("-", "");
		UuSocInfo info = new UuSocInfo();
		info.setSoc_uuid(uuid);
		info.setSoc_bk_seq(1);
		info.setExe_server_name(ver_server_name);
		info.setExe_soc_name(ver_soc_name);
		info.setVer_server_name(ver_server_name);
		info.setVer_soc_name(ver_soc_name);
		info.setCode_bk_dir(ver_root_path);
		uuSocDaoService.insertInfo(info);
		
		//插入数据源UUID
		envBuildTaskDaoService.updateSocUuid(uuid, work_id);
		
		logger.info("------------getBuildListAndPac End-------------------");
		return output;
	}
	
	/**
	 * Description: 将生成的tar文件解归档
	 * @param filename
	 * @param descDir
	 */
	public List<String> decompressTar(File file) {
		logger.debug("开始解压Tar文件[{}]", file.getName());
		List<String> list = new ArrayList<String>();
		TarInputStream tar = null;
		try {
			// 打开.tar文件
			tar = new TarInputStream(new FileInputStream(file));
			TarEntry entry = null;
			// 遍历tar文件
			while ((entry = tar.getNextEntry()) != null) {
				String entryName = entry.getName();
				if (entry.isDirectory()) {
					String outPath = (file.getAbsolutePath() + entryName).replaceAll("\\*", "/");
					File file2 = new File(outPath.substring(0, outPath.lastIndexOf('/')));
					if (!file2.exists()) {
						continue;
					}
				} else {
					list.add(entryName);
				}
			}
			logger.debug("解压Tar文件[{}]结束", file.getName());
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				File file_demo = new File("demo.tar");
				file_demo.delete();
				if (tar != null) {
					tar.close();
				}
			} catch (Exception e) {
			}
		}
		return list;
	}
}
