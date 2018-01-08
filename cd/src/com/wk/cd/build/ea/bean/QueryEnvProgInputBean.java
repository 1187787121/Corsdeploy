/**
 * Title: PageEnvProgInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月10日
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
	 * @return order_col_name 排序字段
	 */
	public String getOrder_col_name() {
		return order_col_name;
	}

	/**
	 * @param order_col_name 排序字段
	 */
	public void setOrder_col_name(String order_col_name) {
		this.order_col_name = order_col_name;
	}

	/**
	 * @return order_type 排序类型
	 */
	public ORDER_TYPE getOrder_type() {
		return order_type;
	}

	/**
	 * @param order_type 排序类型
	 */
	public void setOrder_type(ORDER_TYPE order_type) {
		this.order_type = order_type;
	}
	
}
