/**
 * Title: PageCeEnvironmentOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class PageEnvironmentOutputBean extends PageQueryActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : 5045196929217618855L
	 */ 
	private static final long serialVersionUID = 5045196929217618855L;
	
	/**
	 * Ӧ�û����б�
	 */
	private CeEnvironmentBean[] envir_list;
	
	public static final String ENVIR_LISTCN = "Ӧ�û����б�";

	/**
	 * @return envir_list Ӧ�û����б�
	 */
	public CeEnvironmentBean[] getEnvir_list() {
		return this.envir_list;
	}

	/**
	 * @param envir_list Ӧ�û����б�
	 */
	public void setEnvir_list(CeEnvironmentBean[]envir_list) {
		this.envir_list = envir_list;
	}
	
}
