package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;

public class DeleteTemplateInputBean extends ActionRootInputBean {
	
	private static final long serialVersionUID = -2519942304899070867L;
	
	/**
	 * 模板名
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "模板名";

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