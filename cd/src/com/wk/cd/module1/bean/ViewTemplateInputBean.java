package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.TEMPLATE_TYPE;

public class ViewTemplateInputBean extends ActionRootInputBean {
	
	private static final long serialVersionUID = 1601177579662049328L;
	
	/**
	 * ��У�����
	 */
	private String data;
	
	public static final String DATACN = "��У�����";
	
	/**
	 * ģ������
	 */
	private TEMPLATE_TYPE template_type;
	
	public static final String TEMPLATE_TYPECN = "ģ������";

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public TEMPLATE_TYPE getTemplate_type() {
		return this.template_type;
	}

	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}
}