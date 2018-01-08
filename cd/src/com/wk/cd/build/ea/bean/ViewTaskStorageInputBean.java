/**
 * Title: ViewTaskStorageAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��25��
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
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
	/**
	 * ��Ŀ����
	 */
	private String project_name;
	
	public static final String PROJECT_NAMECN = "��Ŀ����";
	
	/**
	 * ��ѯ����
	 */
	private int num;
	
	public static final String NUMCN = "��ѯ����";

	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return num ��ѯ����
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num ��ѯ����
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return project_name ��Ŀ����
	 */
	public String getProject_name() {
		return project_name;
	}

	/**
	 * @param project_name ��Ŀ����
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
}
