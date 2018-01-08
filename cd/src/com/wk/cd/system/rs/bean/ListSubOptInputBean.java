/**
 * Title: ListSubOptInputBean.java
 * File Description: 查询资源的下级操作列表输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年5月27日
 */
package com.wk.cd.system.rs.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * @author HT
 */
public class ListSubOptInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2727759107327556362L;
	
	/**
	 * 资源编码
	 */
	private String rs_code; 
	
	public static final String RS_CODECN="资源编码";

	/**
	 * @return rs_code 资源编码
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/**
	 * @param rs_code 资源编码
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}
	
}
