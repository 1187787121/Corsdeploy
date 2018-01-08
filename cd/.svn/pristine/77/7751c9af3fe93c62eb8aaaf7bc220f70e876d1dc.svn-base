/**
 * Title: UsUserColPrivInfo.java
 * File Description: 用户SQL字段操作权限表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-9-23
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDateTime;
/**
 * Class description:用户SQL字段操作权限表
 * @author AutoGen
 */
@Table("US_USER_COL_PRIV")
@PrimaryKey({"user_id","soc_name","tbl_name","col_name","priv_type"})
public class UsUserColPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户SQL字段操作权限表";

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
	 *表名
	 */
	private String tbl_name;

	public static final String TBL_NAMECN = "表名";

	/**
	 *字段名称
	 */
	private String col_name;

	public static final String COL_NAMECN = "字段名称";

	/**
	 *Insert权限
	 */
	private PRIV_FLAG ins_priv_flag;

	public static final String INS_PRIV_FLAGCN = "Insert权限";

	/**
	 *Delete权限
	 */
	private PRIV_FLAG del_priv_flag;

	public static final String DEL_PRIV_FLAGCN = "Delete权限";

	/**
	 *Update权限
	 */
	private PRIV_FLAG upd_priv_flag;

	public static final String UPD_PRIV_FLAGCN = "Update权限";

	/**
	 *Select权限
	 */
	private PRIV_FLAG sel_priv_flag;

	public static final String SEL_PRIV_FLAGCN = "Select权限";

	/**
	 *允许禁止标志位
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志位";

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
	 *@return tbl_name 表名
	 */
	public String getTbl_name() {
		return this.tbl_name;
	}

	/**
	 *@param tbl_name 表名
	 */
	public void setTbl_name(String tbl_name) {
		this.tbl_name = tbl_name;
	}

	/**
	 *@return col_name 字段名称
	 */
	public String getCol_name() {
		return this.col_name;
	}

	/**
	 *@param col_name 字段名称
	 */
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	/**
	 *@return ins_priv_flag Insert权限
	 */
	public PRIV_FLAG getIns_priv_flag() {
		return this.ins_priv_flag;
	}

	/**
	 *@param ins_priv_flag Insert权限
	 */
	public void setIns_priv_flag(PRIV_FLAG ins_priv_flag) {
		this.ins_priv_flag = ins_priv_flag;
	}

	/**
	 *@return del_priv_flag Delete权限
	 */
	public PRIV_FLAG getDel_priv_flag() {
		return this.del_priv_flag;
	}

	/**
	 *@param del_priv_flag Delete权限
	 */
	public void setDel_priv_flag(PRIV_FLAG del_priv_flag) {
		this.del_priv_flag = del_priv_flag;
	}

	/**
	 *@return upd_priv_flag Update权限
	 */
	public PRIV_FLAG getUpd_priv_flag() {
		return this.upd_priv_flag;
	}

	/**
	 *@param upd_priv_flag Update权限
	 */
	public void setUpd_priv_flag(PRIV_FLAG upd_priv_flag) {
		this.upd_priv_flag = upd_priv_flag;
	}

	/**
	 *@return sel_priv_flag Select权限
	 */
	public PRIV_FLAG getSel_priv_flag() {
		return this.sel_priv_flag;
	}

	/**
	 *@param sel_priv_flag Select权限
	 */
	public void setSel_priv_flag(PRIV_FLAG sel_priv_flag) {
		this.sel_priv_flag = sel_priv_flag;
	}

	/**
	 *@return af_flag 允许禁止标志位
	 */
	public AF_FLAG getAf_flag() {
		return this.af_flag;
	}

	/**
	 *@param af_flag 允许禁止标志位
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
