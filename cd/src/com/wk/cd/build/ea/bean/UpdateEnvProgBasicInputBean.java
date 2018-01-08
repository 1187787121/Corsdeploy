/**
 * Title: UpdateEnvProgBasicInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��11��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author yangl
 */
public class UpdateEnvProgBasicInputBean
		extends ActionRootInputBean {

	/** 
	 * @Fields serialVersionUID : 5093890633405884808L
	 */ 
	private static final long serialVersionUID = 5093890633405884808L;

	/**
	 * ��������
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "�������";
	
	/**
	 * ��������
	 */
	private PROG_TYPE prog_type;
	
	public static final String PROG_TYPECN = "��������";
	
	/**
	 * ��������
	 */
	private String prog_name;
	
	public static final String PROG_NAMECN = "��������";
	
	/**
	 * ����ģ��
	 */
	private String pub_template_name;
	
	public static final String PUB_TEMPLATE_NAMECN = "����ģ��";
	
	/**
	 * 
	 */
	private String rol_template_name;
	
	public static final String ROL_TEMPLATE_NAMECN = "����ģ��";

	/**
	 * @return prog_type ��������
	 */
	public PROG_TYPE getProg_type() {
		return prog_type;
	}

	/**
	 * @param prog_type ��������
	 */
	public void setProg_type(PROG_TYPE prog_type) {
		this.prog_type = prog_type;
	}

	/**
	 * @return prog_name ��������
	 */
	public String getProg_name() {
		return prog_name;
	}

	/**
	 * @param prog_name ��������
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	
	/**
	 * @return prog_id
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return pub_template_name ����ģ��
	 */
	public String getPub_template_name() {
		return pub_template_name;
	}

	/**
	 * @param pub_template_name ����ģ��
	 */
	public void setPub_template_name(String pub_template_name) {
		this.pub_template_name = pub_template_name;
	}

	/**
	 * @return rol_template_name ����ģ��
	 */
	public String getRol_template_name() {
		return rol_template_name;
	}

	/**
	 * @param rol_template_name ����ģ��
	 */
	public void setRol_template_name(String rol_template_name) {
		this.rol_template_name = rol_template_name;
	}
}
