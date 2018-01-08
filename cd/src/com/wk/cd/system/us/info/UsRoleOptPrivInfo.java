/**
 * Title: UsRoleOptPrivInfo.java
 * File Description: 部门角色操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.cd.enu.PRIV_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:部门角色操作权限配置表
 * @author AutoGen
 */
@Table("US_ROLE_OPT_PRIV")
@PrimaryKey({"opt_code","dprl_code"})
public class UsRoleOptPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "部门角色操作权限配置表";

	/**
	 *操作编码
	 */
	private String opt_code;

	public static final String OPT_CODECN = "操作编码";

	/**
	 *部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 *权限
	 */
	private PRIV_FLAG priv_flag;

	public static final String PRIV_FLAGCN = "权限";

	/**
	 *@return opt_code 操作编码
	 */
	public String getOpt_code() {
		return this.opt_code;
	}

	/**
	 *@param opt_code 操作编码
	 */
	public void setOpt_code(String opt_code) {
		this.opt_code = opt_code;
	}

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
	 *@return priv_flag 权限
	 */
	public PRIV_FLAG getPriv_flag() {
		return this.priv_flag;
	}

	/**
	 *@param priv_flag 权限
	 */
	public void setPriv_flag(PRIV_FLAG priv_flag) {
		this.priv_flag = priv_flag;
	}

}
