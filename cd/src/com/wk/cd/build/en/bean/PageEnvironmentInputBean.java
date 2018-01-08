/**
 * Title: PageCeEnvironmentInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;

/**
 * Class Description: 
 * @author xuph
 */
public class PageEnvironmentInputBean extends PageQueryActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 336819296478818031L
	 */ 
	private static final long serialVersionUID = 336819296478818031L;
	
	/**
	 * Ӧ��ϵͳ
	 */
	private String sys_name;
	
	public static final String SYS_NAMECN="Ӧ��ϵͳ";
	
	/**
	 * �����ֶ�
	 */
	private String order_col_name;

	public static final String ORDER_COL_NAMECN = "�����ֶ�";
	/**
	 * ��������
	 */
	private ORDER_TYPE order_type;

	public static final String ORDER_TYPECN = "��������";

	/**
	 * @return sys_name Ӧ��ϵͳ
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 * @param sys_name Ӧ��ϵͳ
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return order_col_name �����ֶ�
	 */
	public String getOrder_col_name() {
		return order_col_name;
	}

	/**
	 * @param order_col_name �����ֶ�
	 */
	public void setOrder_col_name(String order_col_name) {
		this.order_col_name = order_col_name;
	}

	/**
	 * @return order_type ��������
	 */
	public ORDER_TYPE getOrder_type() {
		return order_type;
	}

	/**
	 * @param order_type ��������
	 */
	public void setOrder_type(ORDER_TYPE order_type) {
		this.order_type = order_type;
	}

}
