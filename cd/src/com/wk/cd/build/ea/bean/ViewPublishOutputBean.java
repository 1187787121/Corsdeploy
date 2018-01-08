/**
 * Title: ViewPublishOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月18日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.util.JaDateTime;

/**
 * Class Description: 
 * @author chiss
 */
public class ViewPublishOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 6452318033719876129L;
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 任务描述
	 */
	private String task_bk_desc;
	
	public static final String TASK_BK_DESCCN = "任务描述";
	
	/**
	 * 项目编号
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "项目编号";
	
	/**
	 *项目简称
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "项目简称";
	
	/**
	 *方案名称
	 */
	private String prog_name;

	public static final String PROG_NAMECN = "方案名称";
	
	/**
	 * 方案编号
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "方案编号";
	
	/**
	 * (发布)目标版本号
	 */
	private String target_ver_num;
	
	public static final String TARGET_VER_NUMCN = "(发布)目标版本号";
	
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
	 * 执行阶段表
	 */
	private List<PubMonStepBean> pub_phase_list;
	
	public static final String PUB_PHASE_LISTCN = "执行阶段表";
	
	/**
	 *执行日志
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "执行日志";
	
	private int total_phase;
	
	public static final String TOTAL_PHASECN = "阶段总数";
	
	private int current_phase;
	
	public static final String CURRENT_PHASECN = "当前阶段号";
	
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
	 * @return env_name 环境名称
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return task_bk_desc 任务描述
	 */
	public String getTask_bk_desc() {
		return task_bk_desc;
	}

	/**
	 * @param task_bk_desc 任务描述
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
	 * @return prog_id 方案编号
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id 方案编号
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return target_ver_num (发布)目标版本号
	 */
	public String getTarget_ver_num() {
		return target_ver_num;
	}

	/**
	 * @param target_ver_num (发布)目标版本号
	 */
	public void setTarget_ver_num(String target_ver_num) {
		this.target_ver_num = target_ver_num;
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
	 * @return exe_method
	 */
	public EXE_METHOD getExe_method() {
		return exe_method;
	}

	/**
	 * @param exe_method
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
	 * @return pub_phase_list 执行阶段表
	 */
	public List<PubMonStepBean> getPub_phase_list() {
		return pub_phase_list;
	}

	/**
	 * @param pub_phase_list 执行阶段表
	 */
	public void setPub_phase_list(List<PubMonStepBean> pub_phase_list) {
		this.pub_phase_list = pub_phase_list;
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
	 * @return total_phase
	 */
	public int getTotal_phase() {
		return total_phase;
	}

	/**
	 * @param total_phase
	 */
	public void setTotal_phase(int total_phase) {
		this.total_phase = total_phase;
	}

	/**
	 * @return current_phase
	 */
	public int getCurrent_phase() {
		return current_phase;
	}

	/**
	 * @param current_phase
	 */
	public void setCurrent_phase(int current_phase) {
		this.current_phase = current_phase;
	}

	/**
	 * @return project_short_name
	 */
	public String getProject_short_name() {
		return project_short_name;
	}

	/**
	 * @param project_short_name
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 * @return prog_name
	 */
	public String getProg_name() {
		return prog_name;
	}

	/**
	 * @param prog_name
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
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
}
