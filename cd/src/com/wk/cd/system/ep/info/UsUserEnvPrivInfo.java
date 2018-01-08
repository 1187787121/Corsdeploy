/**
 * Title: UsUserEnvPrivInfo.java
 * File Description: 用户应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */

package com.wk.cd.system.ep.info;

import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDateTime;
/**
 * Class description:用户应用环境权限表
 * @author AutoGen
 */
@Table("US_USER_ENV_PRIV")
@PrimaryKey({"user_id","env_name","priv_type"})
public class UsUserEnvPrivInfo {
	/**
	 *表名称
	 */
	public static final String TABLECN = "用户应用环境权限表";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";

	/**
	 *资源类型
	 */
	private PRIV_TYPE priv_type;

	public static final String PRIV_TYPECN = "资源类型";

	/**
	 *应用系统
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统";

	/**
	 *允许禁止标志
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志";

	/**
	 *临时权限开始时间
	 */
	private JaDateTime tempstart_bk_datetime;

	public static final String TEMPSTART_BK_DATETIMECN = "临时权限开始时间";

	/**
	 *临时权限结束时间
	 */
	private JaDateTime tempend_bk_datetime;

	public static final String TEMPEND_BK_DATETIMECN = "临时权限结束时间";

	/**
	 *备用字段
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
	 *@return priv_type 资源类型
	 */
	public PRIV_TYPE getPriv_type() {
		return this.priv_type;
	}

	/**
	 *@param priv_type 资源类型
	 */
	public void setPriv_type(PRIV_TYPE priv_type) {
		this.priv_type = priv_type;
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
	 *@return af_flag 允许禁止标志
	 */
	public AF_FLAG getAf_flag() {
		return this.af_flag;
	}

	/**
	 *@param af_flag 允许禁止标志
	 */
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}

	/**
	 *@return tempstart_bk_datetime 临时权限开始时间
	 */
	public JaDateTime getTempstart_bk_datetime() {
		return this.tempstart_bk_datetime;
	}

	/**
	 *@param tempstart_bk_datetime 临时权限开始时间
	 */
	public void setTempstart_bk_datetime(JaDateTime tempstart_bk_datetime) {
		this.tempstart_bk_datetime = tempstart_bk_datetime;
	}

	/**
	 *@return tempend_bk_datetime 临时权限结束时间
	 */
	public JaDateTime getTempend_bk_datetime() {
		return this.tempend_bk_datetime;
	}

	/**
	 *@param tempend_bk_datetime 临时权限结束时间
	 */
	public void setTempend_bk_datetime(JaDateTime tempend_bk_datetime) {
		this.tempend_bk_datetime = tempend_bk_datetime;
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
