/**
 * Title: QueryDeptInfoInputBean.java
 * File Description: 查询部门详细信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 查询部门详细信息输入接口
 * @author HT
 */
public class QueryDeptInfoInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -3739193845651343202L;
	
	/**
	 * 部门编码
	 */
	private String dept_id ;
	
	public static final String DEPT_IDCN = "部门编码";

	/**
	 * @return dept_id 部门编码
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
}
