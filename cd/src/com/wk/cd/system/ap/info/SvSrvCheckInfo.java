/**
 * Title: SvSrvCheckInfo.java
 * File Description: ���񸴺˶����
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.ap.info;

import java.io.Serializable;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.SUPER_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:���񸴺˶����
 * @author AutoGen
 */
@Table("SV_SRV_CHECK")
@PrimaryKey({"check_dept_id","srv_name","check_seq"})
public class SvSrvCheckInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���񸴺˶����";

	/**
	 * ���ò��ű���
	 */
	private String check_dept_id;
	
	public static final String CHECK_DEPT_IDCN = "���ò��ű���";
	
	/**
	 *��������
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "��������";

	/**
	 *�������
	 */
	private int check_seq;

	public static final String CHECK_SEQCN = "�������";
	
	/**
	 *�������
	 */
	private APROV_CATEGORY chk_aprov_category;

	public static final String CHK_APROV_CATEGORYCN = "�������";

	/**
	 *���˲��Ž�ɫ
	 */
	private String chk_dprl_code;

	public static final String CHK_DPRL_CODECN = "���˲��Ž�ɫ";
	
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
	 * @return check_dept_id ���ò��ű���
	 */
	public String getCheck_dept_id() {
		return this.check_dept_id;
	}

	/**
	 * @param check_dept_id ���ò��ű���
	 */
	public void setCheck_dept_id(String check_dept_id) {
		this.check_dept_id = check_dept_id;
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
	 *@return check_seq �������
	 */
	public int getCheck_seq() {
		return this.check_seq;
	}

	/**
	 *@param check_seq �������
	 */
	public void setCheck_seq(int check_seq) {
		this.check_seq = check_seq;
	}

	/**
	 * @return chk_aprov_category �������
	 */
	public APROV_CATEGORY getChk_aprov_category() {
		return this.chk_aprov_category;
	}

	/**
	 * @param chk_aprov_category �������
	 */
	public void setChk_aprov_category(APROV_CATEGORY chk_aprov_category) {
		this.chk_aprov_category = chk_aprov_category;
	}
	
	/**
	 *@return chk_dprl_code ���˲��Ž�ɫ
	 */
	public String getChk_dprl_code() {
		return this.chk_dprl_code;
	}

	/**
	 *@param chk_dprl_code ���˲��Ž�ɫ
	 */
	public void setChk_dprl_code(String chk_dprl_code) {
		this.chk_dprl_code = chk_dprl_code;
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
