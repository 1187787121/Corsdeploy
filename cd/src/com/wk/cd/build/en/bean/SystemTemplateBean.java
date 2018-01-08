/**
 * Title: CeSystemTemplateBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.bean;

import java.util.Arrays;

import com.wk.cd.enu.TEMPLATE_TYPE;

/**
 * Class Description: Ӧ��ϵͳģ�����
 * @author chiss
 */
public class SystemTemplateBean {
	
	/**
	 * ģ������
	 */
	private TEMPLATE_TYPE template_type;
	
	public static final String TEMPLATE_TYPECN = "ģ������";
	
	/**
	 * ģ������
	 */
	private String[] template_name;
	
	public static final String TEMPLATE_NAMECN = "ģ������";

	/**
	 * @return template_type ģ������
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return template_type;
	}

	/**
	 * @param template_type ģ������
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}

	/**
	 * @return template_name ģ������
	 */
	public String[] getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name ģ������
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
