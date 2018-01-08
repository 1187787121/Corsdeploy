/**
 * Title: ViewEnvirInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class ViewEnvirInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 5098170044723205927L
	 */ 
	private static final long serialVersionUID = 5098170044723205927L;
	
	/**
	 * ������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "������";
	
	/**
	 * �������б�
	 */
	private String[] env_list;
	
	public static final String ENV_LISTCN = "�������б�";

	/**
	 * @return env_name ������
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 * @param env_name ������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return env_list �������б�
	 */
	public String[] getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list �������б�
	 */
	public void setEnv_list(String[] env_list) {
		this.env_list = env_list;
	}
}
