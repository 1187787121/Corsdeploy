/**
 * Title: AddDprlInputBean.java
 * File Description: 新增部门角色基础信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月6日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 新增部门角色基础信息输入接口
 * @author HT
 */
public class AddDprlInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 6492326328673215336L;
	
	/**
	 * 部门编码
	 */
	private String dept_id;
	
	public static final String DEPT_IDCN = "部门编码";

	/**
	 * 角色编码
	 */
	private String role_code;
	
	public static final String ROLE_CODECN = "角色编码";

	/**
	 * 部门角色说明
	 */
	private String bk_expl;
	
	public static final String BK_EXPLCN = "部门角色说明";
	
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
	/**
	 * @return role_code 角色编码
	 */
	public String getRole_code() {
		return this.role_code;
	}
	/**
	 * @param role_code 角色编码
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	/** 
	 * @return bk_expl 部门角色说明
	 */
	public String getBk_expl() {
		return this.bk_expl;
	}
	/**
	 * @param bk_expl 部门角色说明
	 */
	public void setBk_expl(String bk_expl) {
		this.bk_expl = bk_expl;
	}
}
