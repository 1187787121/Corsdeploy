/**
 * Title: DeleteCeSystemInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ɾ������Ӧ��ϵͳ����ӿ�
 * @author chiss
 */
public class DeleteSystemInputBean extends ActionRootInputBean {
	
	/** 
	 * @Fields serialVersionUID : 190630163987874213L
	 */ 
	private static final long serialVersionUID = 190630163987874213L;

	/**
	 * Ӧ��ϵͳ����
	 */
	private String sys_name;
	
	public static final String SYS_NAMECN = "Ӧ��ϵͳ����";

	/**
	 * @return sYS_NAME Ӧ��ϵͳ����
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 * @param sYS_NAME Ӧ��ϵͳ����
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	
}
