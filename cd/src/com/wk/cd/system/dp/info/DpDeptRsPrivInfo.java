/**
 * Title: DpDeptRsPrivInfo.java
 * File Description: 部门资源权限表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.dp.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:部门资源权限表
 * @author AutoGen
 */
@Table("DP_DEPT_RS_PRIV")
@PrimaryKey({"dept_id","rs_code"})
public class DpDeptRsPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "部门资源权限表";

	/**
	 *部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";

	/**
	 *资源编码
	 */
	private String rs_code;

	public static final String RS_CODECN = "资源编码";

	/**
	 *
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "";

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
	 *@return rs_code 资源编码
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/**
	 *@param rs_code 资源编码
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	/**
	 *@return backup_fld 
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
