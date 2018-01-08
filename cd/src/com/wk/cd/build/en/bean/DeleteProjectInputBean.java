/**
 * Title: DeleteProjectInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
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
	 * ��Ŀ���
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "��Ŀ���";

	/**
	 * @return project_name ��Ŀ���
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 * @param project_name ��Ŀ���
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
}
