/**
 * Title: WkWorkSocInfo.java
 * File Description: 任务数据源配置表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.work.wk.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:任务数据源配置表
 * @author AutoGen
 */
@Table("WK_WORK_SOC")
@PrimaryKey({"work_code","soc_name"})
public class WkWorkSocInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "任务数据源配置表";

	/**
	 *任务编码
	 */
	private String work_code;

	public static final String WORK_CODECN = "任务编码";

	/**
	 *数据源名称
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "数据源名称";

	/**
	 *
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "";

	/**
	 *@return work_code 任务编码
	 */
	public String getWork_code() {
		return this.work_code;
	}

	/**
	 *@param work_code 任务编码
	 */
	public void setWork_code(String work_code) {
		this.work_code = work_code;
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
