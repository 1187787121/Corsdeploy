/**
 * Title: ViewTaskStorageAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月25日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author chiss
 */
public class ViewTaskStorageInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -446718490079293455L;

	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 项目名称
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "项目名称";
	
	/**
	 * 查询个数
	 */
	private int num;
	
	public static final String NUMCN = "查询个数";

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
	 * @return num 查询个数
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num 查询个数
	 */
	public void setNum(int num) {
		this.num = num;
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
}
