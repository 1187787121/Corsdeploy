/**
 * Title: DpDeptOptPrivInfo.java
 * File Description: 部门操作权限配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-28
 */

package com.wk.cd.system.dp.info;

import java.io.Serializable;

import com.wk.cd.enu.PRIV_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:部门操作权限配置表
 * @author AutoGen
 */
@Table("DP_DEPT_OPT_PRIV")
@PrimaryKey({"opt_code","dept_id"})
public class DpDeptOptPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "部门操作权限配置表";

	/**
	 *操作编码
	 */
	private String opt_code;

	public static final String OPT_CODECN = "操作编码";

	/**
	 *部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";

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
