/**
 * Title: UsExtUserBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-20
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.enu.USER_TYPE;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.util.JaDate;

/**
 * Class Description: 用户扩展信息查询
 * @author link
 */
public class UsExtUserBean {

	/**
	 * 用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名";

	/**
	 * 用户姓名
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "用户姓名";

	/**
	 * 邮箱
	 */
	private String email_add;

	public static final String EMAIL_ADDCN = "邮箱";

	/**
	 * 电话号码
	 */
	private String phone_no;

	public static final String PHONE_NOCN = "电话号码";

	/**
	 * 所属部门号
	 */
	private String bl_dept_id;

	public static final String BL_DEPT_IDCN = "所属部门号";

	/**
	 * 部门名称
	 */
	private String dept_cn_name;

	public static final String DEPT_CN_NAMECN = "部门名称";

	/**
	 * 兼职部门1
	 */
	private String first_dept_id;

	public static final String FIRST_DEPT_IDCN = "兼职部门1";

	/**
	 * 兼职部门1中文名称
	 */
	private String first_dept_cn_name;

	public static final String FIRST_DEPT_CN_NAMECN = "第一兼职部门中文名称";

	/**
	 * 用户类型
	 */
	private USER_TYPE user_type;

	public static final String USER_TYPECN = "用户类型";
	
	/**
	 * 柜员号
	 */
	private String teller_no;
	
	public static final String TELLER_NOCN = "柜员号";

	/**
	 * 部门角色实体列表
	 */
	private List<UsDeptRoleInfo> dprl_list;

	public static final String DPRL_LISTCN = "部门角色实体列表";

	private JaDate pwdexp_bk_date;
	public static final String PWDEXP_BK_DATECN = "密码到期日";

	/**
	 * @return user_id
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return user_cn_name
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return email_add
	 */
	public String getEmail_add() {
		return this.email_add;
	}

	/**
	 * @param email_add
	 */
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}

	/**
	 * @return phone_no
	 */
	public String getPhone_no() {
		return this.phone_no;
	}

	/**
	 * @param phone_no
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 * @return bl_dept_id
	 */
	public String getBl_dept_id() {
		return this.bl_dept_id;
	}

	/**
	 * @param bl_dept_id
	 */
	public void setBl_dept_id(String bl_dept_id) {
		this.bl_dept_id = bl_dept_id;
	}

	/**
	 * @return dept_cn_name
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 * @param dept_cn_name
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}

	/**
	 * @return user_type
	 */
	public USER_TYPE getUser_type() {
		return this.user_type;
	}

	/**
	 * @param user_type
	 */
	public void setUser_type(USER_TYPE user_type) {
		this.user_type = user_type;
	}

	/**
	 * @return teller_no
	 */
	public String getTeller_no() {
		return this.teller_no;
	}

	/**
	 * @param teller_no
	 */
	public void setTeller_no(String teller_no) {
		this.teller_no = teller_no;
	}

	/**
	 * @return first_dept_id
	 */
	public String getFirst_dept_id() {
		return this.first_dept_id;
	}

	/**
	 * @param first_dept_id
	 */
	public void setFirst_dept_id(String first_dept_id) {
		this.first_dept_id = first_dept_id;
	}

	/**
	 * @return first_dept_cn_name
	 */
	public String getFirst_dept_cn_name() {
		return this.first_dept_cn_name;
	}

	/**
	 * @param first_dept_cn_name
	 */
	public void setFirst_dept_cn_name(String first_dept_cn_name) {
		this.first_dept_cn_name = first_dept_cn_name;
	}

	/**
	 * @return dprl_list
	 */
	public List<UsDeptRoleInfo> getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list
	 */
	public void setDprl_list(List<UsDeptRoleInfo> dprl_list) {
		this.dprl_list = dprl_list;
	}

	/**
	 * @return pwdexp_bk_date
	 */
	public JaDate getPwdexp_bk_date() {
		return this.pwdexp_bk_date;
	}

	/**
	 * @param pwdexp_bk_date
	 */
	public void setPwdexp_bk_date(JaDate pwdexp_bk_date) {
		this.pwdexp_bk_date = pwdexp_bk_date;
	}

}
