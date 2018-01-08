/**
 * Title: TaskSrorageBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月25日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

/**
 * Class Description: 
 * @author chiss
 */
public class TaskSrorageBean {
	/**
	 * 任务类型
	 */
	private int task_type;
	
	public static final String TASK_TYPECN = "任务类型";
	
	/**
	 * 任务编号
	 */
	private String task_id;
	
	public static final String TASK_IDCN = "任务编号";
	
	/**
	 * 项目名称
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "项目名称";
	
	/**
	 * 版本号
	 */
	private String code_ver_num;
	
	public static final String CODE_VER_NUMCN = "项目名称";
	
	/**
	 * 状态
	 */
	private int status;
	
	public static final String STATUSCN = "状态";
	
	/**
	 * 结果
	 */
	private int result;
	
	public static final String RESULTCN = "结果";
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 包名列表
	 */
	private List<String> package_list;
	
	public static final String PACKAGE_LISTCN = "包名列表";
	
	/**
	 * 时间
	 */
	private String task_time;

	public static final String TASK_TIMECN = "时间";
	
	/**
	 * @return task_type 任务类型
	 */
	public int getTask_type() {
		return task_type;
	}

	/**
	 * @param task_type 任务类型
	 */
	public void setTask_type(int task_type) {
		this.task_type = task_type;
	}

	/**
	 * @return task_id 任务编号
	 */
	public String getTask_id() {
		return task_id;
	}

	/**
	 * @param task_id 任务编号
	 */
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	/**
	 * @return project_name 项目名称
	 */
	public String getProject_name() {
		return project_name;
	}

	/**
	 * @param project_name 项目名称
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 * @return code_ver_num 版本号
	 */
	public String getCode_ver_num() {
		return code_ver_num;
	}

	/**
	 * @param code_ver_num 版本号
	 */
	public void setCode_ver_num(String code_ver_num) {
		this.code_ver_num = code_ver_num;
	}

	/**
	 * @return status 状态
	 */
	public int getStatus() {
		return status;
	}

	/** 
	 * @param status 状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return result 结果
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result 结果
	 */
	public void setResult(int result) {
		this.result = result;
	}

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
	 * @return package_list 包名列表
	 */
	public List<String> getPackage_list() {
		return package_list;
	}

	/**
	 * @param package_list 包名列表
	 */
	public void setPackage_list(List<String> package_list) {
		this.package_list = package_list;
	}

	/**
	 * @return task_time 时间
	 */
	public String getTask_time() {
		return task_time;
	}

	/**
	 * @param task_time 时间
	 */
	public void setTask_time(String task_time) {
		this.task_time = task_time;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "TaskSrorageBean [task_type=" + task_type + ", task_id="
				+ task_id + ", project_name=" + project_name
				+ ", code_ver_num=" + code_ver_num + ", status=" + status
				+ ", result=" + result + ", env_name=" + env_name
				+ ", package_list=" + package_list + ", task_time=" + task_time
				+ "]";
	}
}
