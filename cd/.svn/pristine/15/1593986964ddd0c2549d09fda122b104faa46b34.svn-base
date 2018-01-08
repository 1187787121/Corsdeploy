/**
 * Title: CeEnvironmentServerInfo.java
 * File Description: 环境服务器表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.cd.enu.SERVER_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:环境服务器表
 * @author AutoGen
 */
@Table("CE_ENVIRONMENT_SERVER")
@PrimaryKey({"env_name","server_type","server_name"})
public class CeEnvironmentServerInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "环境服务器表";

	/**
	 *环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";

	/**
	 *服务器类型
	 */
	private SERVER_TYPE server_type;

	public static final String SERVER_TYPECN = "服务器类型";

	/**
	 *服务器名称
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务器名称";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *@return server_type 服务器类型
	 */
	public SERVER_TYPE getServer_type() {
		return this.server_type;
	}

	/**
	 *@param server_type 服务器类型
	 */
	public void setServer_type(SERVER_TYPE server_type) {
		this.server_type = server_type;
	}

	/**
	 *@return server_name 服务器名称
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name 服务器名称
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
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

	/** 
	 * Description: 
	 * @param obj
	 * @return 
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof CeEnvironmentServerInfo){
			CeEnvironmentServerInfo ceserver = (CeEnvironmentServerInfo)o;
			return env_name.equals(ceserver.env_name)&&server_name.equals(ceserver.server_name)&&server_type==(ceserver.server_type);
		}
		return false;
	}
	

}
