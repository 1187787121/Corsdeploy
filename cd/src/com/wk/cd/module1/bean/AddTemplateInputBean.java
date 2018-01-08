package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.module1.entity.Template;

public class AddTemplateInputBean extends ActionRootInputBean {
	
	private static final long serialVersionUID = -3355898642183160693L;
	
	/**
	 * 模版信息
	 */
	private Template template;
	
	public static final String TEMPLATECN = "模版信息";
	/**
	 * @return template
	 */
	public Template getTemplate() {
		return template;
	}
	/**
	 * @param template
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

	
}