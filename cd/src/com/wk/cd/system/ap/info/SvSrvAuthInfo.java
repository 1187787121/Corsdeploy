/**
 * Title: SvSrvAuthInfo.java
 * File Description: ������Ȩ�����
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.ap.info;

import java.io.Serializable;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.SUPER_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:������Ȩ�����
 * @author AutoGen
 */
@Table("SV_SRV_AUTH")
@PrimaryKey({"auth_dept_id","srv_name","auth_seq"})
public class SvSrvAuthInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "������Ȩ�����";

	/**
	 * ���ò��ű���
	 */
	private String auth_dept_id;
	
	public static final String AUTH_DEPT_IDCN = "���ò��ű���";
	
	/**
	 *��������
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "��������";

	/**
	 *��Ȩ���
	 */
	private int auth_seq;

	public static final String AUTH_SEQCN = "��Ȩ���";

	/**
	 *��Ȩ����
	 */
	private AUTH_TYPE auth_type;

	public static final String AUTH_TYPECN = "��Ȩ����";
	
	/**
	 *�������
	 */
	private APROV_CATEGORY auth_aprov_category;

	public static final String AUTH_APROV_CATEGORYCN = "�������";

	/**
	 *��Ȩ���Ž�ɫ
	 */
	private String auth_dprl_code;

	public static final String AUTH_DPRL_CODECN = "��Ȩ���Ž�ɫ";
	
	/**
	 * ��ɫ����
	 */
	private String role_cn_name;
	
	public static final String ROLE_CN_NAMECN = "��ɫ����";
	
	/**
	 * �Ƿ��ϼ�����
	 */
	private SUPER_FLAG super_flag;
	
	public static final String SUPER_FLAGCN = "�Ƿ��ϼ�����";

	/**
	 * @return auth_dept_id ���ò��ű���
	 */
	public String getAuth_dept_id() {
		return this.auth_dept_id;
	}

	/**
	 * @param auth_dept_id ���ò��ű���
	 */
	public void setAuth_dept_id(String auth_dept_id) {
		this.auth_dept_id = auth_dept_id;
	}

	/**
	 *@return srv_name ��������
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 *@param srv_name ��������
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 *@return auth_seq ��Ȩ���
	 */
	public int getAuth_seq() {
		return this.auth_seq;
	}

	/**
	 *@param auth_seq ��Ȩ���
	 */
	public void setAuth_seq(int auth_seq) {
		this.auth_seq = auth_seq;
	}

	/**
	 *@return auth_type ��Ȩ����
	 */
	public AUTH_TYPE getAuth_type() {
		return this.auth_type;
	}

	/**
	 *@param auth_type ��Ȩ����
	 */
	public void setAuth_type(AUTH_TYPE auth_type) {
		this.auth_type = auth_type;
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
	 *@return auth_dprl_code ��Ȩ���Ž�ɫ
	 */
	public String getAuth_dprl_code() {
		return this.auth_dprl_code;
	}

	/**
	 *@param auth_dprl_code ��Ȩ���Ž�ɫ
	 */
	public void setAuth_dprl_code(String auth_dprl_code) {
		this.auth_dprl_code = auth_dprl_code;
	}
	
	/**
	 *@return role_cn_name ��ɫ����
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 *@param role_cn_name ��ɫ����
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
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
