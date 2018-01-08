/**
 * Title: AddProjectInputBean.java
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
public class AddProjectInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 7226821426420908297L
	 */ 
	private static final long serialVersionUID = 7226821426420908297L;

	/**
	 *��Ŀ���
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "��Ŀ���";

	/**
	 *��Ŀ���
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "��Ŀ���";

	/**
	 *��Ŀ����
	 */
	private String project_bk_desc;

	public static final String PROJECT_BK_DESCCN = "��Ŀ����";

	/**
	 *Ӧ��ϵͳ
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ";
	
	/**
	 *@return project_name ��Ŀ���
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name ��Ŀ���
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 *@return project_short_name ��Ŀ���
	 */
	public String getProject_short_name() {
		return this.project_short_name;
	}

	/**
	 *@param project_short_name ��Ŀ���
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}

	/**
	 *@return project_bk_desc ��Ŀ����
	 */
	public String getProject_bk_desc() {
		return this.project_bk_desc;
	}

	/**
	 *@param project_bk_desc ��Ŀ����
	 */
	public void setProject_bk_desc(String project_bk_desc) {
		this.project_bk_desc = project_bk_desc;
	}

	/**
	 *@return sys_name Ӧ��ϵͳ
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name Ӧ��ϵͳ
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

}
