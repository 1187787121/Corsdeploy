/**
 * Title: UsUserInfo.java
 * File Description: 用户表
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
import com.wk.cd.enu.USER_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:用户表
 * @author AutoGen
 */
@Table("US_USER")
@PrimaryKey({"user_id"})
public class UsUserInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户表";

	/**
	 *用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 *用户密码
	 */
	private String user_passwd;

	public static final String USER_PASSWDCN = "用户密码";

	/**
	 *密码到期日
	 */
	private JaDate pwdexp_bk_date;

	public static final String PWDEXP_BK_DATECN = "密码到期日";

	/**
	 *用户姓名
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "用户姓名";

	/**
	 *邮箱
	 */
	private String email_add;

	public static final String EMAIL_ADDCN = "邮箱";

	/**
	 *电话号码
	 */
	private String phone_no;

	public static final String PHONE_NOCN = "电话号码";

	/**
	 *用户登录数量
	 */
	private int login_bk_num;

	public static final String LOGIN_BK_NUMCN = "用户登录数量";

	/**
	 *所属部门号
	 */
	private String bl_dept_id;

	public static final String BL_DEPT_IDCN = "所属部门号";

	/**
	 *用户类型
	 */
	private USER_TYPE user_type;

	public static final String USER_TYPECN = "用户类型";

	/**
	 * 柜员号
	 */
	private String teller_no;
	
	public static final String TELLER_NOCN = "柜员号";
	
	/**
	 *兼职部门1
	 */
	private String first_dept_id;

	public static final String FIRST_DEPT_IDCN = "兼职部门1";

	/**
	 *兼职部门2
	 */
	private String secd_dept_id;

	public static final String SECD_DEPT_IDCN = "兼职部门2";

	/**
	 *兼职部门3
	 */
	private String third_dept_id;

	public static final String THIRD_DEPT_IDCN = "兼职部门3";

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
	 *@return user_passwd 用户密码
	 */
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/**
	 *@param user_passwd 用户密码
	 */
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	/**
	 *@return pwdexp_bk_date 密码到期日
	 */
	public JaDate getPwdexp_bk_date() {
		return this.pwdexp_bk_date;
	}

	/**
	 *@param pwdexp_bk_date 密码到期日
	 */
	public void setPwdexp_bk_date(JaDate pwdexp_bk_date) {
		this.pwdexp_bk_date = pwdexp_bk_date;
	}

	/**
	 *@return user_cn_name 用户姓名
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 *@param user_cn_name 用户姓名
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 *@return email_add 邮箱
	 */
	public String getEmail_add() {
		return this.email_add;
	}

	/**
	 *@param email_add 邮箱
	 */
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}

	/**
	 *@return phone_no 电话号码
	 */
	public String getPhone_no() {
		return this.phone_no;
	}

	/**
	 *@param phone_no 电话号码
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 *@return login_bk_num 用户登录数量
	 */
	public int getLogin_bk_num() {
		return this.login_bk_num;
	}

	/**
	 *@param login_bk_num 用户登录数量
	 */
	public void setLogin_bk_num(int login_bk_num) {
		this.login_bk_num = login_bk_num;
	}

	/**
	 *@return bl_dept_id 所属部门号
	 */
	public String getBl_dept_id() {
		return this.bl_dept_id;
	}

	/**
	 *@param bl_dept_id 所属部门号
	 */
	public void setBl_dept_id(String bl_dept_id) {
		this.bl_dept_id = bl_dept_id;
	}

	/**
	 *@return user_type 用户类型
	 */
	public USER_TYPE getUser_type() {
		return this.user_type;
	}

	/**
	 *@param user_type 用户类型
	 */
	public void setUser_type(USER_TYPE user_type) {
		this.user_type = user_type;
	}
	
	

	/**
	 * @return teller_no 柜员号
	 */
	public String getTeller_no() {
		return this.teller_no;
	}

	/**
	 * @param teller_no 柜员号
	 */
	public void setTeller_no(String teller_no) {
		this.teller_no = teller_no;
	}

	/**
	 *@return first_dept_id 兼职部门1
	 */
	public String getFirst_dept_id() {
		return this.first_dept_id;
	}

	/**
	 *@param first_dept_id 兼职部门1
	 */
	public void setFirst_dept_id(String first_dept_id) {
		this.first_dept_id = first_dept_id;
	}

	/**
	 *@return secd_dept_id 兼职部门2
	 */
	public String getSecd_dept_id() {
		return this.secd_dept_id;
	}

	/**
	 *@param secd_dept_id 兼职部门2
	 */
	public void setSecd_dept_id(String secd_dept_id) {
		this.secd_dept_id = secd_dept_id;
	}

	/**
	 *@return third_dept_id 兼职部门3
	 */
	public String getThird_dept_id() {
		return this.third_dept_id;
	}

	/**
	 *@param third_dept_id 兼职部门3
	 */
	public void setThird_dept_id(String third_dept_id) {
		this.third_dept_id = third_dept_id;
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
