/**
 * Title: DcDictInfo.java
 * File Description: �����ֵ���Ϣ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.dc.info;

import java.io.Serializable;

import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:�����ֵ���Ϣ��
 * @author AutoGen
 */
@Table("DC_DICT")
@PrimaryKey({"domain_name"})
public class DcDictInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�����ֵ���Ϣ��";

	/**
	 *�����
	 */
	private String domain_name;

	public static final String DOMAIN_NAMECN = "�����";

	/**
	 *����������
	 */
	private String domain_cn_name;

	public static final String DOMAIN_CN_NAMECN = "����������";

	/**
	 *����
	 */
	private String fld_type;

	public static final String FLD_TYPECN = "����";

	/**
	 *�ܳ���
	 */
	private int fld_length;

	public static final String FLD_LENGTHCN = "�ܳ���";

	/**
	 *С��λ
	 */
	private int fld_scale;

	public static final String FLD_SCALECN = "С��λ";

	/**
	 *�Ƿ�ö��
	 */
	private YN_FLAG enu_yn_flag;

	public static final String ENU_YN_FLAGCN = "�Ƿ�ö��";

	/**
	 *@return domain_name �����
	 */
	public String getDomain_name() {
		return this.domain_name;
	}

	/**
	 *@param domain_name �����
	 */
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	/**
	 *@return domain_cn_name ����������
	 */
	public String getDomain_cn_name() {
		return this.domain_cn_name;
	}

	/**
	 *@param domain_cn_name ����������
	 */
	public void setDomain_cn_name(String domain_cn_name) {
		this.domain_cn_name = domain_cn_name;
	}

	/**
	 *@return fld_type ����
	 */
	public String getFld_type() {
		return this.fld_type;
	}

	/**
	 *@param fld_type ����
	 */
	public void setFld_type(String fld_type) {
		this.fld_type = fld_type;
	}

	/**
	 *@return fld_length �ܳ���
	 */
	public int getFld_length() {
		return this.fld_length;
	}

	/**
	 *@param fld_length �ܳ���
	 */
	public void setFld_length(int fld_length) {
		this.fld_length = fld_length;
	}

	/**
	 *@return fld_scale С��λ
	 */
	public int getFld_scale() {
		return this.fld_scale;
	}

	/**
	 *@param fld_scale С��λ
	 */
	public void setFld_scale(int fld_scale) {
		this.fld_scale = fld_scale;
	}

	/**
	 *@return enu_yn_flag �Ƿ�ö��
	 */
	public YN_FLAG getEnu_yn_flag() {
		return this.enu_yn_flag;
	}

	/**
	 *@param enu_yn_flag �Ƿ�ö��
	 */
	public void setEnu_yn_flag(YN_FLAG enu_yn_flag) {
		this.enu_yn_flag = enu_yn_flag;
	}

}
