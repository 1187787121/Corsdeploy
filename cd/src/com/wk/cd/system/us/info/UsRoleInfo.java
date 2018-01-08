/**
 * Title: UsRoleInfo.java
 * File Description: 角色信表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:角色信表
 * @author AutoGen
 */
@Table("US_ROLE")
@PrimaryKey({"role_code"})
public class UsRoleInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "角色信表";

	/**
	 *角色编码
	 */
	private String role_code;

	public static final String ROLE_CODECN = "角色编码";

	/**
	 *角色名称
	 */
	private String role_cn_name;

	public static final String ROLE_CN_NAMECN = "角色名称";

	/**
	 *角色类型
	 */
	private int role_type;

	public static final String ROLE_TYPECN = "角色类型";

	/**
	 *角色说明
	 */
	private String role_bk_desc;

	public static final String ROLE_BK_DESCCN = "角色说明";

	/**
	 *备用字段
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用字段";

	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "创建时间";

	/**
	 *创建用户
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建用户";

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
	 *删除日期
	 */
	private JaDate del_bk_date;

	public static final String DEL_BK_DATECN = "删除日期";

	/**
	 *删除时间
	 */
	private JaTime del_bk_time;

	public static final String DEL_BK_TIMECN = "删除时间";

	/**
	 *删除用户
	 */
	private String del_user_id;

	public static final String DEL_USER_IDCN = "删除用户";

	/**
	 *记录状态
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "记录状态";

	/**
	 *@return role_code 角色编码
	 */
	public String getRole_code() {
		return this.role_code;
	}

	/**
	 *@param role_code 角色编码
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	/**
	 *@return role_cn_name 角色名称
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 *@param role_cn_name 角色名称
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
	}

	/**
	 *@return role_type 角色类型
	 */
	public int getRole_type() {
		return this.role_type;
	}

	/**
	 *@param role_type 角色类型
	 */
	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}

	/**
	 *@return role_bk_desc 角色说明
	 */
	public String getRole_bk_desc() {
		return this.role_bk_desc;
	}

	/**
	 *@param role_bk_desc 角色说明
	 */
	public void setRole_bk_desc(String role_bk_desc) {
		this.role_bk_desc = role_bk_desc;
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
	 *@return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return crt_user_id 创建用户
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id 创建用户
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
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
	 *@return del_bk_date 删除日期
	 */
	public JaDate getDel_bk_date() {
		return this.del_bk_date;
	}

	/**
	 *@param del_bk_date 删除日期
	 */
	public void setDel_bk_date(JaDate del_bk_date) {
		this.del_bk_date = del_bk_date;
	}

	/**
	 *@return del_bk_time 删除时间
	 */
	public JaTime getDel_bk_time() {
		return this.del_bk_time;
	}

	/**
	 *@param del_bk_time 删除时间
	 */
	public void setDel_bk_time(JaTime del_bk_time) {
		this.del_bk_time = del_bk_time;
	}

	/**
	 *@return del_user_id 删除用户
	 */
	public String getDel_user_id() {
		return this.del_user_id;
	}

	/**
	 *@param del_user_id 删除用户
	 */
	public void setDel_user_id(String del_user_id) {
		this.del_user_id = del_user_id;
	}

	/**
	 *@return rcd_state 记录状态
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state 记录状态
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

}
