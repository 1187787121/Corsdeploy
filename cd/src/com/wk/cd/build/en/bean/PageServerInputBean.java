/**
 * Title: PageServerInputBean.java
 * File Description: 服务器分页查询搜索输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.ORDER_TYPE;

/**
 * Class Description: 服务器分页查询搜索输入接口
 * @author yangl
 */
public class PageServerInputBean extends PageQueryActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : -4062086765440146796L
	 */ 
	private static final long serialVersionUID = -4062086765440146796L;

	/**
	 * 服务器名称
	 */
	private String ce_server_name;
	public static final String CE_SERVER_NAMECN = "服务器名称";

	/**
	 * 服务器地址
	 */
	private String server_ip;
	public static final String SERVER_IP_CN = "服务器地址";
	
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
	 * @return 服务器名称
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param 服务器名称
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return 服务器地址
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param 服务器地址
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return order_col_name
	 */
	public String getOrder_col_name() {
		return order_col_name;
	}

	/**
	 * @param order_col_name
	 */
	public void setOrder_col_name(String order_col_name) {
		this.order_col_name = order_col_name;
	}

	/**
	 * @return order_type
	 */
	public ORDER_TYPE getOrder_type() {
		return order_type;
	}

	/**
	 * @param order_type
	 */
	public void setOrder_type(ORDER_TYPE order_type) {
		this.order_type = order_type;
	}

}
