/**
 * Title: SysEnvAndTaskBean.java
 * File Description: 系统环境及任务明细接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.build.en.bean;

import java.io.Serializable;

import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.cd.enu.AF_FLAG;

/**
 * Class Description: 系统环境及任务明细接口
 * @author Xul
 */
public class SysEnvAndTaskBean implements Serializable{

	private static final long serialVersionUID = -3939350435708313209L;
	
	/**
	 *环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 *环境简称
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "环境简称";
	
	/**
	 *环境类型
	 */
	private ENV_TYPE env_type;

	public static final String ENV_TYPECN = "环境类型";
	
	/**
	 * 任务全类型
	 */
	private TASK_ALL_TYPE task_all_type;
	
	public static final String TASK_ALL_TYPECN = "任务全类型";
	
	/**
	 * 任务编号(包括入库编号)
	 */
	private String task_seq;
	
	public static final String TASK_SEQCN = "任务编号";
	
	/**
	 *实例ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "实例ID";

	/**
	 *允许禁止标志
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志";

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
	 *@return env_cn_name 环境简称
	 */
	public String getEnv_cn_name() {
		return this.env_cn_name;
	}

	/**
	 *@param env_cn_name 环境简称
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 * @return env_type 环境类型
	 */
	public ENV_TYPE getEnv_type() {
		return env_type;
	}

	/**
	 * @param env_type 环境类型
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}
	
	/**
	 * @return task_all_type 任务全类型
	 */
	public TASK_ALL_TYPE getTask_all_type() {
		return task_all_type;
	}

	/**
	 * @param task_all_type 任务全类型
	 */
	public void setTask_all_type(TASK_ALL_TYPE task_all_type) {
		this.task_all_type = task_all_type;
	}

	/**
	 * @return task_seq 任务编号
	 */
	public String getTask_seq() {
		return task_seq;
	}

	/**
	 * @param task_seq 任务编号
	 */
	public void setTask_seq(String task_seq) {
		this.task_seq = task_seq;
	}
	
	/**
	 *@return instance_id 实例ID
	 */
	public String getInstance_id() {
		return this.instance_id;
	}

	/**
	 *@param instance_id 实例ID
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	/**
	 * @return af_flag
	 */
	public AF_FLAG getAf_flag() {
		return af_flag;
	}

	/**
	 * @param af_flag
	 */
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}
}
