/**
 * Title: ListResInputBean.java
 * File Description: 浏览单个功能点信息输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-12-3
 */
package com.wk.cd.system.rs.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 浏览单个功能点信息输入接口
 * @author xuy
 */
public class ListResInputBean extends ActionRootInputBean {
	
	private static final long serialVersionUID = -4630403627754299778L;
	private String rs_code;
	public static final String RS_CODECN = "资源编码";
	
	/**
	 * @return rs_code 资源编码
	 */
	public String getRs_code() {
		return rs_code;
	}
	/**
	 * @param rsCode 资源编码
	 */
	public void setRs_code(String rsCode) {
		rs_code = rsCode;
	}

	
}
