/**
 * Title: UsDeptRoleInfo.java
 * File Description: 部门角色关联表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:部门角色关联表
 * @author AutoGen
 */
@Table("US_DEPT_ROLE")
@PrimaryKey({"dprl_code"})
public class UsDeptRoleInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "部门角色关联表";

	/**
	 *部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 *部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";

	/**
	 *角色编码
	 */
	private String role_code;

	public static final String ROLE_CODECN = "角色编码";

	/**
	 *部门角色说明
	 */
	private String bk_expl;

	public static final String BK_EXPLCN = "部门角色说明";

	/**
	 *@return dprl_code 部门角色编码
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 *@param dprl_code 部门角色编码
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}

	/**
	 *@return dept_id 部门编码
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 *@param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 *@return role_code 角色编码
	 */
	public String getRole_code() {
		return this.role_code;
	}

	/**
	 *@param role_code 角色编码
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	/**
	 *@return bk_expl 部门角色说明
	 */
	public String getBk_expl() {
		return this.bk_expl;
	}

	/**
	 *@param bk_expl 部门角色说明
	 */
	public void setBk_expl(String bk_expl) {
		this.bk_expl = bk_expl;
	}

}
