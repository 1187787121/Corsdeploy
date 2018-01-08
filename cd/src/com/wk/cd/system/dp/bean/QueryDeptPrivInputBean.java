/**
 * Title: QueryDeptPrivInputBean.java
 * File Description: 查询部门权限信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 查询部门权限信息输入接口
 * @author HT
 */
public class QueryDeptPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -6494915284587980893L;
	
	/**
	 * 部门编码
	 */
	private String dept_id;
	
	public static final String DEPT_IDCN = "部门编码";

	/**
	 * @return dept_id
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
}
