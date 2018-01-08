/**
 * Title: QueryDprlPrivInputBean.java
 * File Description: 查询部门角色权限信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月7日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 查询部门角色权限信息输入接口
 * @author HT
 */
public class QueryDprlPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -3528022416110479155L;
	
	/**
	 * 部门角色编码
	 */
	private String dprl_code;
	
	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 * @return dprl_code 部门角色编码
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 * @param dprl_code 部门角色编码
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}
}
