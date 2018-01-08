/**
 * Title: AddProjectInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class AddProjectInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 7226821426420908297L
	 */ 
	private static final long serialVersionUID = 7226821426420908297L;

	/**
	 *项目编号
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "项目编号";

	/**
	 *项目简称
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "项目简称";

	/**
	 *项目描述
	 */
	private String project_bk_desc;

	public static final String PROJECT_BK_DESCCN = "项目描述";

	/**
	 *应用系统
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统";
	
	/**
	 *@return project_name 项目编号
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name 项目编号
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 *@return project_short_name 项目简称
	 */
	public String getProject_short_name() {
		return this.project_short_name;
	}

	/**
	 *@param project_short_name 项目简称
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 *@return project_bk_desc 项目描述
	 */
	public String getProject_bk_desc() {
		return this.project_bk_desc;
	}

	/**
	 *@param project_bk_desc 项目描述
	 */
	public void setProject_bk_desc(String project_bk_desc) {
		this.project_bk_desc = project_bk_desc;
	}

	/**
	 *@return sys_name 应用系统
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 应用系统
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

}
