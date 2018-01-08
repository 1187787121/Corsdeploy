package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootOutputBean;

public class ViewTemplateOutputBean extends ActionRootOutputBean {
	
	private static final long serialVersionUID = 7073094032829138121L;
	
	/**
	 * 校验结果
	 */
	private boolean result;
	
	public static final String RESULTCN = "校验结果";
	
	/**
	 * 模板统一存放目录
	 */
	private String template_common_path;
	
	public static final String TEMPLATE_COMMON_PATHCN = "模板统一存放目录";
	
	/**
	 * 模板名列表
	 */
	private String[] template_names;
	
	public static final String TEMPLATE_NAMESCN = "模板名列表";
	
	/**
	 * 发布模板名列表
	 */
	private String[] pub_template_names;
	
	public static final String PUB_TEMPLATE_NAMESCN = "发布模板名列表";
	
	/**
	 * 回退模板名列表
	 */
	private String[] rol_template_names;
	
	public static final String ROL_TEMPLATE_NAMESCN = "回退模板名列表";

	public boolean getResult() {
		return this.result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getTemplate_common_path() {
		return this.template_common_path;
	}

	public void setTemplate_common_path(String template_common_path) {
		this.template_common_path = template_common_path;
	}

	public String[] getTemplate_names() {
		return this.template_names;
	}

	public void setTemplate_names(String[] template_names) {
		this.template_names = template_names;
	}

	public String[] getPub_template_names() {
		return this.pub_template_names;
	}

	public void setPub_template_names(String[] pub_template_names) {
		this.pub_template_names = pub_template_names;
	}

	public String[] getRol_template_names() {
		return this.rol_template_names;
	}

	public void setRol_template_names(String[] rol_template_names) {
		this.rol_template_names = rol_template_names;
	}
}