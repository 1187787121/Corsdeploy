/**
 * Title: DeleteCeSystemInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 删除单个应用系统输入接口
 * @author chiss
 */
public class DeleteSystemInputBean extends ActionRootInputBean {
	
	/** 
	 * @Fields serialVersionUID : 190630163987874213L
	 */ 
	private static final long serialVersionUID = 190630163987874213L;

	/**
	 * 应用系统名称
	 */
	private String sys_name;
	
	public static final String SYS_NAMECN = "应用系统名称";

	/**
	 * @return sYS_NAME 应用系统名称
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 * @param sYS_NAME 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	
}
