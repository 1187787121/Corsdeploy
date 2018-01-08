/**
 * Title: AuthDprlCodeBean.java
 * File Description: ��Ȩ�б���Ϣ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.ap.bean;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.SUPER_FLAG;

/**
 * Class Description: ��Ȩ�б���Ϣ
 * @author tlw
 */
public class AuthDprlCodeBean {

	/**
	 * ��Ȩ����
	 */
	private AUTH_TYPE auth_type;

	public static final String AUTH_TYPECN = "��Ȩ����";

	/**
	 * ��Ȩ���Ž�ɫ
	 */
	private String auth_dprl_code;

	public static final String AUTH_DPRL_CODECN = "��Ȩ���Ž�ɫ";

	/**
	 * ���Ž�ɫ����
	 */
	private String role_cn_name;

	public static final String ROLE_CN_NAMECN = "��ɫ����";

	/**
	 * ��Ȩ���
	 */
	private int auth_seq;

	public static final String AUTH_SEQCN = "��Ȩ���";

	/**
	 *�������
	 */
	private APROV_CATEGORY auth_aprov_category;

	public static final String AUTH_APROV_CATEGORYCN = "�������";
	
	/**
	 * �Ƿ��ϼ�����
	 */
	private SUPER_FLAG super_flag;
	
	public static final String SUPER_FLAGCN = "�Ƿ��ϼ�����";
	
	/**
	 * @return auth_type ��Ȩ����
	 */
	public AUTH_TYPE getAuth_type() {
		return auth_type;
	}

	/**
	 * @param auth_type ��Ȩ����
	 */
	public void setAuth_type(AUTH_TYPE auth_type) {
		this.auth_type = auth_type;
	}

	/**
	 * @return auth_dprl_code ��Ȩ���Ž�ɫ
	 */
	public String getAuth_dprl_code() {
		return auth_dprl_code;
	}

	/**
	 * @param auth_dprl_code ��Ȩ���Ž�ɫ
	 */
	public void setAuth_dprl_code(String auth_dprl_code) {
		this.auth_dprl_code = auth_dprl_code;
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
	 * @return auth_seq ��Ȩ���
	 */
	public int getAuth_seq() {
		return this.auth_seq;
	}

	/**
	 * @param authSeq ��Ȩ���
	 */
	public void setAuth_seq(int auth_seq) {
		this.auth_seq = auth_seq;
	}

	/**
	 * @return auth_aprov_category �������
	 */
	public APROV_CATEGORY getAuth_aprov_category() {
		return this.auth_aprov_category;
	}

	/**
	 * @param auth_aprov_category �������
	 */
	public void setAuth_aprov_category(APROV_CATEGORY auth_aprov_category) {
		this.auth_aprov_category = auth_aprov_category;
	}
	
	/**
	 * @return �Ƿ��ϼ�����
	 */
	public SUPER_FLAG getSuper_flag() {
		return this.super_flag;
	}

	/**
	 * @param super_flag �Ƿ��ϼ�����
	 */
	public void setSuper_flag(SUPER_FLAG super_flag) {
		this.super_flag = super_flag;
	}

}
