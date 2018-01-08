/**
 * Title: AddUserInputBean.java
 * File Description: �����û�����ӿ�
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
 * Class Description:�����û�����ӿ�
 * @author link
 */
public class AddUserInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -2855906074260942852L;
	
	/**
	 * �û���
	 */
	private String user_id;
	
	public static final String USER_IDCN = "�û���";

	/**
	 * �û�����
	 */
	private transient String user_passwd;
	
	public static final String USER_PASSWDCN = "�û�����";

	/**
	 * ���뵽����
	 */
	private JaDate pwdexp_bk_date;
	
	public static final String PWDEXP_BK_DATECN = "���뵽����";

	/**
	 * �û�����
	 */
	private String user_cn_name;
	
	public static final String USER_CN_NAMECN = "�û�����";

	/**
	 * ����
	 */
	private String email_add;
	
	public static final String EMAIL_ADDCN = "����";

	/**
	 * �绰����
	 */
	private String phone_no;
	
	public static final String PHONE_NOCN = "�绰����";

	/**
	 * �������ź�
	 */
	private String bl_dept_id;
	
	public static final String BL_DEPT_IDCN = "�������ź�";

	/**
	 * �û�����
	 */
	private USER_TYPE user_type;
	
	public static final String USER_TYPECN = "�û�����";
	
	/**
	 * ��Ա��
	 */
	private String teller_no;
	
	public static final String 	TELLER_NOCN = "��Ա��";

	/**
	 * ��ְ����1
	 */
	private String first_dept_id;
	
	public static final String FIRST_DEPT_IDCN = "��ְ����1";

	/**
	 * ��ְ����2
	 */
	private String secd_dept_id;
	
	public static final String SECD_DEPT_IDCN = "��ְ����2";

	/**
	 * ��ְ����3
	 */
	private String third_dept_id;
	
	public static final String THIRD_DEPT_IDCN = "��ְ����3";

	/**
	 * ���Ž�ɫ�����б�
	 */
	private String[] dprl_list;
	
	public static final String DPRL_LISTCN = "���Ž�ɫ�����б�";
	
	/**
	 * �û�Ȩ���б�
	 * ��Ȩ���б���ϢӦ�úͲ��Ž�ɫ�����б�����˳��һһ��Ӧ
	 */
	private int[] user_weight_list;
	
	public static final String USER_WEIGHT_LISTCN = "�û�Ȩ���б�";

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
	 * @return user_passwd �û�����
	 */
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/**
	 * @param user_passwd �û�����
	 */
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	/**
	 * @return pwdexp_bk_date ���뵽����
	 */
	public JaDate getPwdexp_bk_date() {
		return this.pwdexp_bk_date;
	}

	/**
	 * @param pwdexp_bk_date ���뵽����
	 */
	public void setPwdexp_bk_date(JaDate pwdexp_bk_date) {
		this.pwdexp_bk_date = pwdexp_bk_date;
	}

	/**
	 * @return user_cn_name �û�����
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name �û�����
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return email_add ����
	 */
	public String getEmail_add() {
		return this.email_add;
	}

	/**
	 * @param email_add ����
	 */
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}

	/**
	 * @return phone_no �绰����
	 */
	public String getPhone_no() {
		return this.phone_no;
	}

	/**
	 * @param phone_no �绰����
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 * @return bl_dept_id �������ź�
	 */
	public String getBl_dept_id() {
		return this.bl_dept_id;
	}

	/**
	 * @param bl_dept_id �������ź�
	 */
	public void setBl_dept_id(String bl_dept_id) {
		this.bl_dept_id = bl_dept_id;
	}

	/**
	 * @return user_type �û�����
	 */
	public USER_TYPE getUser_type() {
		return this.user_type;
	}

	/**
	 * @param user_type �û�����
	 */
	public void setUser_type(USER_TYPE user_type) {
		this.user_type = user_type;
	}

	
	
	/**
	 * @return teller_no ��Ա��
	 */
	public String getTeller_no() {
		return this.teller_no;
	}

	/**
	 * @param teller_no ��Ա��
	 */
	public void setTeller_no(String teller_no) {
		this.teller_no = teller_no;
	}

	/**
	 * @return first_dept_id ��ְ����1
	 */
	public String getFirst_dept_id() {
		return this.first_dept_id;
	}

	/**
	 * @param first_dept_id ��ְ����1
	 */
	public void setFirst_dept_id(String first_dept_id) {
		this.first_dept_id = first_dept_id;
	}

	/**
	 * @return secd_dept_id ��ְ����2
	 */
	public String getSecd_dept_id() {
		return this.secd_dept_id;
	}

	/**
	 * @param secd_dept_id ��ְ����2
	 */
	public void setSecd_dept_id(String secd_dept_id) {
		this.secd_dept_id = secd_dept_id;
	}

	/**
	 * @return third_dept_id ��ְ����3
	 */
	public String getThird_dept_id() {
		return this.third_dept_id;
	}

	/**
	 * @param third_dept_id ��ְ����3
	 */
	public void setThird_dept_id(String third_dept_id) {
		this.third_dept_id = third_dept_id;
	}

	/**
	 * @return dprl_list ���Ž�ɫ�����б�
	 */
	public String[] getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list ���Ž�ɫ�����б�
	 */
	public void setDprl_list(String[] dprl_list) {
		this.dprl_list = dprl_list;
	}
	
	/**
	 * @return user_weight_list �û�Ȩ���б�
	 */
	public int[] getUser_weight_list() {
		return this.user_weight_list;
	}

	/**
	 * @param user_weight_list �û�Ȩ���б�
	 */
	public void setUser_weight_list(int[] user_weight_list) {
		this.user_weight_list = user_weight_list;
	}
}