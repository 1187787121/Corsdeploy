/**
 * Title: UpdateDeptEnvPrivInputBean.java
 * File Description: 修改部门应用环境权限输入接口
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017年1月4日
 */
package com.wk.cd.system.ep.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改部门应用环境权限输入接口
 * @author HT
 */
public class UpdateDeptEnvPrivInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4168747589961265768L;
	
	/**
	 * 部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";
	
	/**
	 * 环境权限列表
	 */
	private List<EnvPrivBean> env_list;
	
	public static final String ENV_LISTCN = "环境权限列表";

	/**
	 * @return dept_id 部门编码
	 */
	public String getDept_id() {
		return dept_id;
	}

	/**
	 * @param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @return env_list 环境权限列表
	 */
	public List<EnvPrivBean> getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list 环境权限列表
	 */
	public void setEnv_list(List<EnvPrivBean> env_list) {
		this.env_list = env_list;
	}
}
