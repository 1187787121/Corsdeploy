/**
 * Title: UsUserRoleInfo.java
 * File Description: 用户角色关联表
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
 * Class description:用户角色关联表
 * @author AutoGen
 */
@Table("US_USER_ROLE")
@PrimaryKey({"user_id","dprl_code"})
public class UsUserRoleInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户角色关联表";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 * 用户权重
	 */
	private int user_bk_weight;
	
	public static final String USER_BK_WEIGHTCN = "用户权重";
	/**
	 * 备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	 *@return user_bk_weight 用户权重
	 */
	public int getUser_bk_weight() {
		return this.user_bk_weight;
	}

	/**
	 *@param user_bk_weight 用户权重
	 */
	public void setUser_bk_weight(int user_bk_weight) {
		this.user_bk_weight = user_bk_weight;
	}

	/**
	 *@return backup_fld 备用字段
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用字段
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
