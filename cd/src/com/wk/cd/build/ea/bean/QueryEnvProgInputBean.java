/**
 * Title: PageEnvProgInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;

/**
 * Class Description: 
 * @author Administrator
 */
public class QueryEnvProgInputBean extends ActionRootInputBean{
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 6888894248440110525L;

	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
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
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
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
