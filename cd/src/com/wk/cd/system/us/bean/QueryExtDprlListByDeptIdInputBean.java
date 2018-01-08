/**
 * Title: PageExtDprlListByDeptIdInputBean.java
 * File Description:  根据部门编码查询部门角色编码的相关详细信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 根据部门编码查询部门角色编码的相关详细信息输入接口
 * @author link
 */
public class QueryExtDprlListByDeptIdInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 4423864770051923187L;
	private String[] dept_arr;
	public static final String DEPT_ARRCN = "部门编码数组";

	/**
	 * @return dept_arr 部门编码数组
	 */
	public String[] getDept_arr() {
		return this.dept_arr;
	}

	/**
	 * @param dept_arr 部门编码数组
	 */
	public void setDept_arr(String[] dept_arr) {
		this.dept_arr = dept_arr;
	}

}
