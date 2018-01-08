/**
 * Title: UsUserSocPrivInfo.java
 * File Description: 用户数据源权限表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-9-23
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:用户数据源权限表
 * @author AutoGen
 */
@Table("US_USER_SOC_PRIV")
@PrimaryKey({"user_id","soc_name","priv_type"})
public class UsUserSocPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户数据源权限表";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *数据源名称
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "数据源名称";

	/**
	 *允许禁止标志
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志";

	/**
	 *资源类型
	 */
	private PRIV_TYPE priv_type;

	public static final String PRIV_TYPECN = "资源类型";

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
