package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;

public class DeleteTemplateInputBean extends ActionRootInputBean {
	
	private static final long serialVersionUID = -2519942304899070867L;
	
	/**
	 * ģ����
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "ģ����";

	/**
	 * @return template_name
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
}