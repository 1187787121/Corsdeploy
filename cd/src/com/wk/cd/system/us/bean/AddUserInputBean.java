/**
 * Title: AddUserInputBean.java
 * File Description: 新增用户输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-3
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.USER_TYPE;
import com.wk.util.JaDate;

/**
 * Class Description:新增用户输入接口
 * @author link
 */
public class AddUserInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -2855906074260942852L;
	
	/**
	 * 用户名
	 */
	private String user_id;
	
	public static final String USER_IDCN = "用户名";

	/**
	 * 用户密码
	 */
	private transient String user_passwd;
	
	public static final String USER_PASSWDCN = "用户密码";

	/**
	 * 密码到期日
	 */
	private JaDate pwdexp_bk_date;
	
	public static final String PWDEXP_BK_DATECN = "密码到期日";

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
	 * 用户类型
	 */
	private USER_TYPE user_type;
	
	public static final String USER_TYPECN = "用户类型";
	
	/**
	 * 柜员号
	 */
	private String teller_no;
	
	public static final String 	TELLER_NOCN = "柜员号";

	/**
	 * 兼职部门1
	 */
	private String first_dept_id;
	
	public static final String FIRST_DEPT_IDCN = "兼职部门1";

	/**
	 * 兼职部门2
	 */
	private String secd_dept_id;
	
	public static final String SECD_DEPT_IDCN = "兼职部门2";

	/**
	 * 兼职部门3
	 */
	private String third_dept_id;
	
	public static final String THIRD_DEPT_IDCN = "兼职部门3";

	/**
	 * 部门角色编码列表
	 */
	private String[] dprl_list;
	
	public static final String DPRL_LISTCN = "部门角色编码列表";
	
	/**
	 * 用户权重列表
	 * 此权重列表信息应该和部门角色编码列表按照顺序一一对应
	 */
	private int[] user_weight_list;
	
	public static final String USER_WEIGHT_LISTCN = "用户权重列表";

	/**
	 * @return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return user_passwd 用户密码
	 */
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/**
	 * @param user_passwd 用户密码
	 */
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	/**
	 * @return pwdexp_bk_date 密码到期日
	 */
	public JaDate getPwdexp_bk_date() {
		return this.pwdexp_bk_date;
	}

	/**
	 * @param pwdexp_bk_date 密码到期日
	 */
	public void setPwdexp_bk_date(JaDate pwdexp_bk_date) {
		this.pwdexp_bk_date = pwdexp_bk_date;
	}

	/**
	 * @return user_cn_name 用户姓名
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name 用户姓名
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return email_add 邮箱
	 */
	public String getEmail_add() {
		return this.email_add;
	}

	/**
	 * @param email_add 邮箱
	 */
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}

	/**
	 * @return phone_no 电话号码
	 */
	public String getPhone_no() {
		return this.phone_no;
	}

	/**
	 * @param phone_no 电话号码
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 * @return bl_dept_id 所属部门号
	 */
	public String getBl_dept_id() {
		return this.bl_dept_id;
	}

	/**
	 * @param bl_dept_id 所属部门号
	 */
	public void setBl_dept_id(String bl_dept_id) {
		this.bl_dept_id = bl_dept_id;
	}

	/**
	 * @return user_type 用户类型
	 */
	public USER_TYPE getUser_type() {
		return this.user_type;
	}

	/**
	 * @param user_type 用户类型
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
	 * @return first_dept_id 兼职部门1
	 */
	public String getFirst_dept_id() {
		return this.first_dept_id;
	}

	/**
	 * @param first_dept_id 兼职部门1
	 */
	public void setFirst_dept_id(String first_dept_id) {
		this.first_dept_id = first_dept_id;
	}

	/**
	 * @return secd_dept_id 兼职部门2
	 */
	public String getSecd_dept_id() {
		return this.secd_dept_id;
	}

	/**
	 * @param secd_dept_id 兼职部门2
	 */
	public void setSecd_dept_id(String secd_dept_id) {
		this.secd_dept_id = secd_dept_id;
	}

	/**
	 * @return third_dept_id 兼职部门3
	 */
	public String getThird_dept_id() {
		return this.third_dept_id;
	}

	/**
	 * @param third_dept_id 兼职部门3
	 */
	public void setThird_dept_id(String third_dept_id) {
		this.third_dept_id = third_dept_id;
	}

	/**
	 * @return dprl_list 部门角色编码列表
	 */
	public String[] getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list 部门角色编码列表
	 */
	public void setDprl_list(String[] dprl_list) {
		this.dprl_list = dprl_list;
	}
	
	/**
	 * @return user_weight_list 用户权重列表
	 */
	public int[] getUser_weight_list() {
		return this.user_weight_list;
	}

	/**
	 * @param user_weight_list 用户权重列表
	 */
	public void setUser_weight_list(int[] user_weight_list) {
		this.user_weight_list = user_weight_list;
	}
}
