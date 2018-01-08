/**
 * Title: UserLoginInOutputBean.java
 * File Description: �û���¼����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.USER_TYPE;


/**
 * Class Description: �û���¼����ӿ�
 * @author link
 */
public class UserLoginInOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 2273831298073610185L;

	private String term_no;

	/**
	 * �û�����
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "�û�����";

	/**
	 * �û���¼����
	 */
	private int login_bk_num;

	public static final String LOGIN_BK_NUMCN = "�û���¼����";

	/**
	 * �������ź�
	 */
	private String bl_dept_id;

	public static final String BL_DEPT_IDCN = "�������ź�";
	
	/**
	 * ���ż��
	 */
	private String dept_cn_name;
	
	public static final String DEPT_CN_NAMECN = "���ż��";

	/**
	 * �û�����
	 */
	private USER_TYPE user_type;

	public static final String USER_TYPECN = "�û�����";

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
	 * ���Ž�ɫ���� ��ʽ"01:02"
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����";
	
	/**
	 * ���������²��Ž�ɫ˵��  ��ʽ"XX:XX"
	 */
	private String bl_dprl_expls;

	public static final String BL_DPRL_EXPLSCN = "���������²��Ž�ɫ˵��";

	/**
	 * ��Ա��
	 */
	private String tl_tltelr;
	
	public static final String TL_TLTELRCN = "��Ա��";
	
	/**
	 * ��������
	 */
	private String br_sbsbno;
	
	public static final String BR_SBSBNOCN = "��������";
	
	/**
	 * ��������
	 */
	private String br_sbsbch;
	
	public static final String BR_SBSBCHCN = "��������";

	/**
	 * @return user_cn_name�û�����
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name�û�����
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

	/**
	 * @return login_bk_num ��ǰ��¼����
	 */
	public int getLogin_bk_num() {
		return this.login_bk_num;
	}

	/**
	 * @param login_bk_num ��ǰ��¼����
	 */
	public void setLogin_bk_num(int login_bk_num) {
		this.login_bk_num = login_bk_num;
	}

	/**
	 * @return bl_dept_id ��������
	 */
	public String getBl_dept_id() {
		return this.bl_dept_id;
	}

	/**
	 * @param bl_dept_id��������
	 */
	public void setBl_dept_id(String bl_dept_id) {
		this.bl_dept_id = bl_dept_id;
	}

	/**
	 * @return dept_cn_name ���ż��
	 */
	public String getDept_cn_name() {
		return this.dept_cn_name;
	}

	/**
	 * @param dept_cn_name ���ż��
	 */
	public void setDept_cn_name(String dept_cn_name) {
		this.dept_cn_name = dept_cn_name;
	}

	/**
	 * @return user_type�û�����
	 */
	public USER_TYPE getUser_type() {
		return this.user_type;
	}

	/**
	 * @param user_type�û�����
	 */
	public void setUser_type(USER_TYPE user_type) {
		this.user_type = user_type;
	}

	/**
	 * @return first_dept_idְ�沿�ţ�
	 */
	public String getFirst_dept_id() {
		return this.first_dept_id;
	}

	/**
	 * @param first_dept_idְ�沿�ţ�
	 */
	public void setFirst_dept_id(String first_dept_id) {
		this.first_dept_id = first_dept_id;
	}

	/**
	 * @return secd_dept_id��ְ���ţ� 
	 */
	public String getSecd_dept_id() {
		return this.secd_dept_id;
	}

	/**
	 * @param secd_dept_id ��ְ���ţ�
	 */
	public void setSecd_dept_id(String secd_dept_id) {
		this.secd_dept_id = secd_dept_id;
	}

	/**
	 * @return third_dept_id ��ְ���ţ�
	 */
	public String getThird_dept_id() {
		return this.third_dept_id;
	}

	/**
	 * @param third_dept_id��ְ���ţ�
	 */
	public void setThird_dept_id(String third_dept_id) {
		this.third_dept_id = third_dept_id;
	}

	/**
	 * @return dprl_code ���Ž�ɫ���� ��ʽ"01:02"
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 * @param dprl_code���Ž�ɫ���� ��ʽ"01:02"
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}
	
	/**
	 * @return bl_dprl_expls ���������²��Ž�ɫ˵��  ��ʽ"XX:XX"
	 */
	public String getBl_dprl_expls() {
		return this.bl_dprl_expls;
	}

	/**
	 * @param bl_dprl_expls ���������²��Ž�ɫ˵��  ��ʽ"XX:XX"
	 */
	public void setBl_dprl_expls(String bl_dprl_expls) {
		this.bl_dprl_expls = bl_dprl_expls;
	}

	/**
	 * @return term_no
	 */
	public String getTerm_no() {
		return this.term_no;
	}

	/**
	 * @param term_no
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	/**
	 * @return tl_tltelr ��Ա��
	 */
	public String getTl_tltelr() {
		return this.tl_tltelr;
	}

	/**
	 * @param tl_tltelr ��Ա��
	 */
	public void setTl_tltelr(String tl_tltelr) {
		this.tl_tltelr = tl_tltelr;
	}

	/**
	 * @return br_sbsbno ��������
	 */
	public String getBr_sbsbno() {
		return this.br_sbsbno;
	}

	/**
	 * @param br_sbsbno ��������
	 */
	public void setBr_sbsbno(String br_sbsbno) {
		this.br_sbsbno = br_sbsbno;
	}

	/**
	 * @return br_sbsbch ��������
	 */
	public String getBr_sbsbch() {
		return this.br_sbsbch;
	}

	/**
	 * @param br_sbsbch ��������
	 */
	public void setBr_sbsbch(String br_sbsbch) {
		this.br_sbsbch = br_sbsbch;
	}

	
}
