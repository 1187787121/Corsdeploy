/**
 * Title: UsUserDprlRoleInfo.java
 * File Description: �û��ۺ���Ϣ��us_user_role,us_dept_role,us_role��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.bean;

/**
 * Class Description:�û��ۺ���Ϣ��us_user_role,us_dept_role,us_role��
 * @author link
 */
public class UsUserCombineBean {
	public static final String TABLECN = "�û��ۺ���Ϣ��";

	private String user_id;
	public static final String USER_IDCN = "�û���";

	private String dprl_code;
	public static final String DPRL_CODECN = "���Ž�ɫ����";

	private String dept_id;
	public static final String DEPT_IDCN = "���ű���";

	private String role_code;
	public static final String ROLE_CODECN = "��ɫ����";

	private String role_cn_name;
	public static final String ROLE_CN_NAMECN = "��ɫ����";

	private int role_type;
	public static final String ROLE_TYPECN = "��ɫ����";

	/**
	 * @return user_id �û���
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return dprl_code ���Ž�ɫ����
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 * @param dprl_code ���Ž�ɫ����
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}

	/**
	 * @return dept_id ���ű���
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @return role_code ��ɫ����
	 */
	public String getRole_code() {
		return this.role_code;
	}

	/**
	 * @param role_code ��ɫ����
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	/**
	 * @return role_cn_name ��ɫ����
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 * @param role_cn_name ��ɫ����
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
	}

	/**
	 * @return role_type ��ɫ����
	 */
	public int getRole_type() {
		return this.role_type;
	}

	/**
	 * @param role_type ��ɫ����
	 */
	public void setRole_type(int role_type) {
		this.role_type = role_type;
	}

}
