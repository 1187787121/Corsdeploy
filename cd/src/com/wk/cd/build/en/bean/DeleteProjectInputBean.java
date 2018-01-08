/**
 * Title: DeleteProjectInputBean.java
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
public class DeleteProjectInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2246862089583638972L;
	
	/**
	 * 项目编号
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "项目编号";

	/**
	 * @return project_name 项目编号
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 * @param project_name 项目编号
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
}
