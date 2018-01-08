/**
 * Title: DeleteDeptInputBean.java
 * File Description: 删除部门输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 删除部门输入接口
 * @author xuy
 */
public class DeleteDeptInputBean extends ActionRootInputBean{
	
	private static final long serialVersionUID = 8339548305631466983L;
	private String dept_id;
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
