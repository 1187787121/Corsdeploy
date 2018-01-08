/**
 * Title: UsExtDprlInfo.java
 * File Description: 部门角色扩展信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-13
 */
package com.wk.cd.system.us.bean;

/**
 * Class Description: 部门角色扩展信息
 * @author link
 */
public class UsExtDprlBean {

	/**
	 * 表名称
	 */
	public static final String TABLECN = "部门角色信息扩展表";

	/**
	 * 部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 * 部门角色说明
	 */
	private String bk_expl;

	public static final String BK_EXPLCN = "部门角色说明";

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
	 * 部门编码
	 */
	private String dept_cn_name;

	public static final String DEPT_CN_NAMECN = "部门名称";

	/**
	 * 角色编码
	 */
	private String role_cn_name;

	public static final String ROLE_CN_NAMECN = "角色名称";

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
	 * @return dept_cn_name 部门中文名称
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 * @param dept_cn_name 部门中文名称
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}

	/**
	 * @return role_cn_name 角色中文名称
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 * @param role_cn_name 角色中文名称
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
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
