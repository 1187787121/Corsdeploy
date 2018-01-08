/**
 * Title: WkWorkSrvInfo.java
 * File Description: 任务服务配置表
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
 * Class description:任务服务配置表
 * @author AutoGen
 */
@Table("WK_WORK_SRV")
@PrimaryKey({"work_code","srv_name"})
public class WkWorkSrvInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "任务服务配置表";

	/**
	 *任务编码
	 */
	private String work_code;

	public static final String WORK_CODECN = "任务编码";

	/**
	 *服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

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
	 *@return srv_name 服务名称
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 *@param srv_name 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
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
