/**
 * Title: CeSystemCfgInfo.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-6
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:
 * @author AutoGen
 */
@Table("CE_SYSTEM_CFG")
@PrimaryKey({"sys_name","cfg_bk_fname"})
public class CeSystemCfgInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "";

	/**
	 *系统名
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "系统名";

	/**
	 *文件名
	 */
	private String cfg_bk_fname;

	public static final String CFG_BK_FNAMECN = "文件名";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return sys_name 系统名
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 系统名
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return cfg_bk_fname 文件名
	 */
	public String getCfg_bk_fname() {
		return this.cfg_bk_fname;
	}

	/**
	 *@param cfg_bk_fname 文件名
	 */
	public void setCfg_bk_fname(String cfg_bk_fname) {
		this.cfg_bk_fname = cfg_bk_fname;
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
