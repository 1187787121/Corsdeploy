/**
 * Title: ViewCeInput.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.TEMPLATE_TYPE;

/**
 * Class Description: 查看应用系统输入接口
 * @author chiss
 */
public class ViewSystemInputBean extends ActionRootInputBean {

	/** 
	 * @Fields serialVersionUID : -80812119652838691L
	 */ 
	private static final long serialVersionUID = -80812119652838691L;
	
	/**
	 * 模板类型
	 */
	private TEMPLATE_TYPE template_type;
	
	public static final String TEMPLATE_TYPECN = "模板类型";
	
	/**
	 * 应用系统名称
	 */
	private String sys_name;
	
	public static final String SYS_NAMECN = "应用系统名称";
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 排序字段
	 */
	private String order_col_name;

	public static final String ORDER_COL_NAMECN = "排序字段";
	/**
	 * 排序类型
	 */
	private ORDER_TYPE order_type;

	public static final String ORDER_TYPECN = "排序类型";

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
	 * @return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return env_name 环境名称
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}
	/**
	 * @param order_col_name 排序字段
	 */
	public void setOrder_col_name(String order_col_name) {
		this.order_col_name = order_col_name;
	}

	/**
	 * @return order_col_name 排序字段
	 */
	public String getOrder_col_name() {
		return order_col_name;
	}

	/**
	 * @param order_type 排序类型
	 */
	public void setOrder_type(ORDER_TYPE order_type) {
		this.order_type = order_type;
	}

	/**
	 * @return order_type 排序类型
	 */
	public ORDER_TYPE getOrder_type() {
		return order_type;
	}	
	
}
