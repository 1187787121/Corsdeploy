/**
 * Title: SysEnvBean.java
 * File Description: 服务器下系统环境接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.build.en.bean;

import java.io.Serializable;
import java.util.List;

import com.wk.cd.enu.SYS_TYPE;

/**
 * Class Description: 服务器下系统环境接口
 * @author Xul
 */
public class SysEnvBean implements Serializable{

	private static final long serialVersionUID = 2193974039313925532L;
	
	/**
	 *应用系统名称
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统名称";

	/**
	 *应用系统简称
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "应用系统简称";
	
	/**
	 *系统类型
	 */
	private SYS_TYPE sys_type;
	
	public static final String SYS_TYPECN = "系统类型";
	
	/**
	 *环境任务列表
	 */
	private List<SysEnvAndTaskBean> env_task_list;
	
	public static final String ENV_TASK_LISTCN = "环境任务列表";
	
	/**
	 *@return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return sys_cn_name 应用系统简称
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 *@param sys_cn_name 应用系统简称
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}
	
	/**
	 * @return sys_type 系统类型
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type 系统类型
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 * @return env_task_list 环境任务列表
	 */
	public List<SysEnvAndTaskBean> getEnv_task_list() {
		return env_task_list;
	}

	/**
	 * @param env_task_list 环境任务列表
	 */
	public void setEnv_task_list(List<SysEnvAndTaskBean> env_task_list) {
		this.env_task_list = env_task_list;
	}
}
