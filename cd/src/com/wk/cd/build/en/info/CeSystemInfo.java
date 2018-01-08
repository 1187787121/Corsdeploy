/**
 * Title: CeSystemInfo.java
 * File Description: 应用系统表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.cd.enu.SYS_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
/**
 * Class description:应用系统表
 * @author AutoGen
 */
@Table("CE_SYSTEM")
@PrimaryKey({"sys_name"})
public class CeSystemInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "应用系统表";

	/**
	 *应用系统名称
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统名称";

	/**
	 *应用系统简称
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "应用系统简称";

	/**
	 *应用系统类型
	 */
	private SYS_TYPE sys_type;

	public static final String SYS_TYPECN = "应用系统类型";

	/**
	 *应用系统描述
	 */
	private String sys_bk_desc;

	public static final String SYS_BK_DESCCN = "应用系统描述";

	/**
	 *创建日期
	 */
	private JaDate create_bk_date;

	public static final String CREATE_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime create_bk_time;

	public static final String CREATE_BK_TIMECN = "创建时间";

	/**
	 *创建用户
	 */
	private String create_user_id;

	public static final String CREATE_USER_IDCN = "创建用户";

	/**
	 *修改日期
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "修改日期";

	/**
	 *修改时间
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "修改时间";

	/**
	 *修改用户
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "修改用户";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *@return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return sys_cn_name 应用系统简称
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 *@param sys_cn_name 应用系统简称
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 *@return sys_type 应用系统类型
	 */
	public SYS_TYPE getSys_type() {
		return this.sys_type;
	}

	/**
	 *@param sys_type 应用系统类型
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 *@return sys_bk_desc 应用系统描述
	 */
	public String getSys_bk_desc() {
		return this.sys_bk_desc;
	}

	/**
	 *@param sys_bk_desc 应用系统描述
	 */
	public void setSys_bk_desc(String sys_bk_desc) {
		this.sys_bk_desc = sys_bk_desc;
	}

	/**
	 *@return create_bk_date 创建日期
	 */
	public JaDate getCreate_bk_date() {
		return this.create_bk_date;
	}

	/**
	 *@param create_bk_date 创建日期
	 */
	public void setCreate_bk_date(JaDate create_bk_date) {
		this.create_bk_date = create_bk_date;
	}

	/**
	 *@return create_bk_time 创建时间
	 */
	public JaTime getCreate_bk_time() {
		return this.create_bk_time;
	}

	/**
	 *@param create_bk_time 创建时间
	 */
	public void setCreate_bk_time(JaTime create_bk_time) {
		this.create_bk_time = create_bk_time;
	}

	/**
	 *@return create_user_id 创建用户
	 */
	public String getCreate_user_id() {
		return this.create_user_id;
	}

	/**
	 *@param create_user_id 创建用户
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 *@return modify_bk_date 修改日期
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date 修改日期
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time 修改时间
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time 修改时间
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return modify_user_id 修改用户
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id 修改用户
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
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
