/**
 * Title: CeServerDsInfo.java
 * File Description: 服务器数据源表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-3
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:服务器数据源表
 * @author AutoGen
 */
@Table("CE_SERVER_DS")
@PrimaryKey({"server_name","soc_name"})
public class CeServerDsInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "服务器数据源表";

	/**
	 *服务器名称
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "服务器名称";

	/**
	 *数据源名称
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "数据源名称";

	/**
	 *数据源用途
	 */
	private String apply_type;

	public static final String APPLY_TYPECN = "数据源用途";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

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
	 *@return soc_name 数据源名称
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 *@param soc_name 数据源名称
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 *@return apply_type 数据源用途
	 */
	public String getApply_type() {
		return this.apply_type;
	}

	/**
	 *@param apply_type 数据源用途
	 */
	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
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
