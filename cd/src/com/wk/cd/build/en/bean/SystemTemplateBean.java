/**
 * Title: CeSystemTemplateBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import java.util.Arrays;

import com.wk.cd.enu.TEMPLATE_TYPE;

/**
 * Class Description: 应用系统模板参数
 * @author chiss
 */
public class SystemTemplateBean {
	
	/**
	 * 模板类型
	 */
	private TEMPLATE_TYPE template_type;
	
	public static final String TEMPLATE_TYPECN = "模板类型";
	
	/**
	 * 模板名称
	 */
	private String[] template_name;
	
	public static final String TEMPLATE_NAMECN = "模板名称";

	/**
	 * @return template_type 模板类型
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return template_type;
	}

	/**
	 * @param template_type 模板类型
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}

	/**
	 * @return template_name 模板名称
	 */
	public String[] getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name 模板名称
	 */
	public void setTemplate_name(String[] template_name) {
		this.template_name = template_name;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "SystemTemplateBean [template_type=" + template_type
				+ ", template_name=" + Arrays.toString(template_name) + "]";
	}
	

}
