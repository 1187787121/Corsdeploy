/**
 * Title: DeleteEnvironmentInputBean.java
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
public class DeleteEnvironmentInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 3386978959740997538L
	 */ 
	private static final long serialVersionUID = 3386978959740997538L;
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";

	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

}
