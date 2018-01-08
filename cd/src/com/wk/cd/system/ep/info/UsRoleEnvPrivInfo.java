/**
 * Title: UsRoleEnvPrivInfo.java
 * File Description: 部门角色应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */

package com.wk.cd.system.ep.info;

import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:部门角色应用环境权限表
 * @author AutoGen
 */
@Table("US_ROLE_ENV_PRIV")
@PrimaryKey({"dprl_code","env_name"})
public class UsRoleEnvPrivInfo {
	/**
	 *表名称
	 */
	public static final String TABLECN = "部门角色应用环境权限表";

	/**
	 *部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 *环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";

	/**
	 *应用系统
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *@return env_name 环境名称
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return sys_name 应用系统
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 应用系统
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
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
