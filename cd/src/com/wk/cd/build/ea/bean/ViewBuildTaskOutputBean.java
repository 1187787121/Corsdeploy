/**
 * Title: ViewBuildTaskOutputBean.java
 * File Description: 查看配置文件改动列表输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.util.JaDateTime;

/**
 * Class Description: 查看配置文件改动列表输出接口
 * @author chiss
 */
public class ViewBuildTaskOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -1826609292259639223L;
	
	/**
	 * 待修改文件列表
	 */
	private List<EnvModFileBean> pend_list;
	
	public static final String PEND_MODIFY_LISTCN = "修改文件列表";
	
	/**
	 * 修改文件列表
	 */
	private List<EnvModFileBean> modify_list;
	
	public static final String MODIFY_LISTCN = "修改文件列表";
	
	/**
	 * 删除文件列表
	 */
	private List<EnvModFileBean> delete_list;
	
	public static final String DELETE_LISTCN = "删除文件列表";
	
	/**
	 * 文件列表
	 */
	private List<FileListBean> file_list_bean;
	
	public static final String FILE_LIST_BEANCN = "文件列表";
	
	/**
	 * 任务状态
	 */
	private TASK_STATUS task_status;
	
	public static final String TASK_STATUSCN = "任务状态";
	
	/**
	 * 执行结果
	 */
	private EXE_RESULT exe_result;
	
	public static final String EXE_RESULTCN = "执行结果";
	
	/**
	 * 执行动作
	 */
	private EXE_METHOD exe_method;
	
	public static final String EXE_METHODCN = "执行动作";
	
	/**
	 * 执行开始时间
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "执行开始时间";

	/**
	 * 执行结束时间
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "执行结束时间";
	
	/**
	 * 执行阶段列表
	 */
	private List<BuildMonPhaseBean> build_phase_list;
	
	public static final String BUILD_PHASE_LISTCN = "执行阶段列表";
	
	/**
	 * 执行日志
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "执行日志";
	
	/**
	 * 总阶段数
	 */
	private int all_phases;
	
	public static final String ALL_PHASESCN = "总阶段数";
	
	/**
	 * 当前阶段数
	 */
	private int exe_phase;
	
	public static final String EXE_PHASECN = "当前阶段数";
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";

	/**
	 * 任务名称
	 */
	private String task_bk_desc;
	
	public static final String TASK_BK_DESCCN = "任务名称";
	
	/**
	 * 项目编号
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "项目编号";
	
	/**
	 * 项目简称
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "项目简称";
	
	/**
	 * 当前构建步骤数
	 */
	private int build_step_id;
	
	public static final String BUILD_STEP_IDCN = "当前构建步骤数";
	
	/**
	 * 阶段列表
	 */
//	private List<PhasePublishBean> phases;
//	
//	public static final String PHASESCN = "阶段列表";
	private List<Phase> phases;
	
	public static final String PHASESCN = "阶段列表";

	/**
	 * 模板参数列表
	 */
	private List<PhaseParam> param_list;
	
	public static final String PARAM_LISTCN = "模板参数列表";
	
	/**
	 * 构建执行信息
	 */
	private List<InstanceExeInfo>exemsg_list;
	
	public static final String EXEMSG_LISTCN = "构建执行信息";
	
	/**
	 * 脚本命令
	 */
	private String[] script_text;
	
	public static final String SCRIPT_TEXTCN = "脚本命令";
	
	/**
	 * 模板名
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "模板名";
	
	/**
	 * 构建配置文件列表
	 */
	private List<String> cfg_name_list;
	
	public static final String CFG_NAME_LISTCN = "构建配置文件列表";
	
	/**
	 * 脚本信息
	 */
	private List<BuildScriptBean> script_list;
	
	public static final String SCRIPT_LISTCN = "脚本信息";
	
	/**
	 * 清单列表
	 */
	private List<TargetPackageBean> list_list;
	
	public static final String LIST_LISTCN = "清单列表";
	
	/**
	 * 投产包列表
	 */
	private List<TargetPackageBean> pac_list;
	
	public static final String PAC_LISTCN = "清单列表";
	
	/**
	 * 日志信息
	 */
	private TargetPackageBean log_bean;
	
	public static final String LOG_BEANCN = "日志信息";
	
	/**
	 * 数据源名称
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "数据源名称";
	
	/**
	 * 服务器名称
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "服务器名称";
	
	/**
	 * 版本根路径
	 */
	private String ver_root_path;
	
	public static final String VER_ROOT_PATHCN = "版本根路径";
	
	/**
	 * 投产包参数变化标志（true变、false不变）
	 */
	private boolean change_flag;
	
	public static final String CHANGE_FLAGCN = "投产包参数变化标志";
	
	/**
	 * @return pend_modify_list 待修改文件列表
	 */
	public List<EnvModFileBean> getPend_list() {
		return pend_list;
	}

	/**
	 * @param pend_list 待修改文件列表
	 */
	public void setPend_list(List<EnvModFileBean> pend_list) {
		this.pend_list = pend_list;
	}

	/**
	 * @return modify_list 修改文件列表
	 */
	public List<EnvModFileBean> getModify_list() {
		return modify_list;
	}

	/**
	 * @param modify_list 修改文件列表
	 */
	public void setModify_list(List<EnvModFileBean> modify_list) {
		this.modify_list = modify_list;
	}

	/**
	 * @return delete_list 删除文件列表
	 */
	public List<EnvModFileBean> getDelete_list() {
		return delete_list;
	}

	/**
	 * @param delete_list 删除文件列表
	 */
	public void setDelete_list(List<EnvModFileBean> delete_list) {
		this.delete_list = delete_list;
	}
	
	/**
	 * @return task_status 任务状态
	 */
	public TASK_STATUS getTask_status() {
		return task_status;
	}

	/**
	 * @param task_status 任务状态
	 */
	public void setTask_status(TASK_STATUS task_status) {
		this.task_status = task_status;
	}

	/**
	 * @return exe_result 执行结果
	 */
	public EXE_RESULT getExe_result() {
		return exe_result;
	}

	/**
	 * @param exe_result 执行结果
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}
	
	/**
	 * @return exe_method 执行动作
	 */
	public EXE_METHOD getExe_method() {
		return exe_method;
	}

	/**
	 * @param exe_method 执行动作
	 */
	public void setExe_method(EXE_METHOD exe_method) {
		this.exe_method = exe_method;
	}

	/**
	 * @return start_bk_tm 执行开始时间
	 */
	public JaDateTime getStart_bk_tm() {
		return start_bk_tm;
	}

	/**
	 * @param start_bk_tm 执行开始时间
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 * @return end_bk_tm 执行结束时间
	 */
	public JaDateTime getEnd_bk_tm() {
		return end_bk_tm;
	}

	/**
	 * @param end_bk_tm 执行结束时间
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}
	
	/**
	 * @return exelog_bk_path 执行日志
	 */
	public String getExelog_bk_path() {
		return exelog_bk_path;
	}

	/**
	 * @param exelog_bk_path 执行日志
	 */
	public void setExelog_bk_path(String exelog_bk_path) {
		this.exelog_bk_path = exelog_bk_path;
	}

	/**
	 * @return build_phase_list 执行阶段列表
	 */
	public List<BuildMonPhaseBean> getBuild_phase_list() {
		return build_phase_list;
	}

	/**
	 * @param build_phase_list 执行阶段列表
	 */
	public void setBuild_phase_list(List<BuildMonPhaseBean> build_phase_list) {
		this.build_phase_list = build_phase_list;
	}

	/**
	 * @return all_phases 总阶段数
	 */
	public int getAll_phases() {
		return all_phases;
	}

	/**
	 * @param all_phases 总阶段数
	 */
	public void setAll_phases(int all_phases) {
		this.all_phases = all_phases;
	}

	/**
	 * @return exe_phase 当前阶段数
	 */
	public int getExe_phase() {
		return exe_phase;
	}

	/**
	 * @param exe_phase 当前阶段数
	 */
	public void setExe_phase(int exe_phase) {
		this.exe_phase = exe_phase;
	}

	/**
	 * @return file_list_bean 文件列表
	 */
	public List<FileListBean> getFile_list_bean() {
		return file_list_bean;
	}

	/**
	 * @param file_list_bean 文件列表
	 */
	public void setFile_list_bean(List<FileListBean> file_list_bean) {
		this.file_list_bean = file_list_bean;
	}
	
	/**
	 * @return phases 阶段列表
	 */
	public List<Phase> getPhases() {
		return phases;
	}

	/**
	 * @param phases 阶段列表
	 */
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	/**
	 * @return param_list 模板参数列表
	 */
	public List<PhaseParam> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list 模板参数列表
	 */
	public void setParam_list(List<PhaseParam> param_list) {
		this.param_list = param_list;
	}

	/**
	 * @return env_name
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return task_bk_desc 任务名称
	 */
	public String getTask_bk_desc() {
		return task_bk_desc;
	}

	/**
	 * @param task_bk_desc 任务名称
	 */
	public void setTask_bk_desc(String task_bk_desc) {
		this.task_bk_desc = task_bk_desc;
	}

	/**
	 * @return project_name 项目编号
	 */
	public String getProject_name() {
		return project_name;
	}

	/**
	 * @param project_name 项目编号
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	/**
	 * @return project_short_name 项目简称
	 */
	public String getProject_short_name() {
		return project_short_name;
	}

	/**
	 * @param project_short_name 项目简称
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 * @return build_step_id 当前构建步骤数
	 */
	public int getBuild_step_id() {
		return build_step_id;
	}

	/**
	 * @param build_step_id 当前构建步骤数
	 */
	public void setBuild_step_id(int build_step_id) {
		this.build_step_id = build_step_id;
	}

	/**
	 * @return exemsg_list 构建执行信息
	 */
	public List<InstanceExeInfo> getExemsg_list() {
		return exemsg_list;
	}

	/**
	 * @param exemsg_list 构建执行信息
	 */
	public void setExemsg_list(List<InstanceExeInfo> exemsg_list) {
		this.exemsg_list = exemsg_list;
	}

	/**
	 * @return script_text 脚本命令
	 */
	public String[] getScript_text() {
		return script_text;
	}

	/**
	 * @param script_text 脚本命令
	 */
	public void setScript_text(String[] script_text) {
		this.script_text = script_text;
	}

	/**
	 * @return template_name 模板名
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name 模板名
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 * @return cfg_name_list 构建配置文件列表
	 */
	public List<String> getCfg_name_list() {
		return cfg_name_list;
	}

	/**
	 * @param cfg_name_list 构建配置文件列表
	 */
	public void setCfg_name_list(List<String> cfg_name_list) {
		this.cfg_name_list = cfg_name_list;
	}

	/**
	 * @return script_list
	 */
	public List<BuildScriptBean> getScript_list() {
		return script_list;
	}

	/**
	 * @param script_list
	 */
	public void setScript_list(List<BuildScriptBean> script_list) {
		this.script_list = script_list;
	}

	/**
	 * @return list_list 清单列表
	 */
	public List<TargetPackageBean> getList_list() {
		return list_list;
	}

	/**
	 * @param list_list 清单列表
	 */
	public void setList_list(List<TargetPackageBean> list_list) {
		this.list_list = list_list;
	}

	/**
	 * @return pac_list 投产包列表
	 */
	public List<TargetPackageBean> getPac_list() {
		return pac_list;
	}

	/**
	 * @param pac_list 投产包列表
	 */
	public void setPac_list(List<TargetPackageBean> pac_list) {
		this.pac_list = pac_list;
	}

	/**
	 * @return log_bean 日志信息
	 */
	public TargetPackageBean getLog_bean() {
		return log_bean;
	}

	/**
	 * @param log_bean 日志信息
	 */
	public void setLog_bean(TargetPackageBean log_bean) {
		this.log_bean = log_bean;
	}

	/**
	 * @return soc_name 数据源名称
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name 数据源名称
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return ce_server_name 服务器名称
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name 服务器名称
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return ver_root_path 版本根路径
	 */
	public String getVer_root_path() {
		return ver_root_path;
	}

	/**
	 * @param ver_root_path 版本根路径
	 */
	public void setVer_root_path(String ver_root_path) {
		this.ver_root_path = ver_root_path;
	}

	/**
	 * @return change_flag 投产包参数变化标志
	 */
	public boolean isChange_flag() {
		return change_flag;
	}

	/**
	 * @param change_flag 投产包参数变化标志
	 */
	public void setChange_flag(boolean change_flag) {
		this.change_flag = change_flag;
	}
}
