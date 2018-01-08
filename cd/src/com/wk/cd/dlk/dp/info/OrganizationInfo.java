/**
 * Title: OrganizationInfo.java
 * File Description: ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-24
 */

package com.wk.cd.dlk.dp.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:������
 * @author AutoGen
 */
@Table("ORGANIZATION")
@PrimaryKey({"orgid"})
public class OrganizationInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "������";

	/**
	 *����ID
	 */
	private int orgid;

	public static final String ORGIDCN = "����ID";

	/**
	 *��������
	 */
	private String orgname;

	public static final String ORGNAMECN = "��������";

	/**
	 *�ϼ����������
	 */
	private String orgpar;

	public static final String ORGPARCN = "�ϼ����������";

	/**
	 *�ϼ���������
	 */
	private String orgparname;

	public static final String ORGPARNAMECN = "�ϼ���������";

	/**
	 *����˵��
	 */
	private String orgdesc;

	public static final String ORGDESCCN = "����˵��";

	/**
	 *��������
	 */
	private String orgtype;

	public static final String ORGTYPECN = "��������";

	/**
	 *������ַ
	 */
	private String orgaddr;

	public static final String ORGADDRCN = "������ַ";

	/**
	 *�����绰
	 */
	private String orgphone;

	public static final String ORGPHONECN = "�����绰";

	/**
	 *��������
	 */
	private String orgfax;

	public static final String ORGFAXCN = "��������";

	/**
	 *������ϵ��
	 */
	private String orgcontact;

	public static final String ORGCONTACTCN = "������ϵ��";

	/**
	 *����״̬
	 */
	private String orgstatus;

	public static final String ORGSTATUSCN = "����״̬";

	/**
	 *������Ϣ
	 */
	private String orginfo;

	public static final String ORGINFOCN = "������Ϣ";

	/**
	 *���������
	 */
	private String orgsyscoding;

	public static final String ORGSYSCODINGCN = "���������";

	/**
	 *��������
	 */
	private String orgusercoding;

	public static final String ORGUSERCODINGCN = "��������";

	/**
	 *ʱ���
	 */
	private String fingerprint;

	public static final String FINGERPRINTCN = "ʱ���";

	/**
	 *����ʱ��
	 */
	private String initcreatetime;

	public static final String INITCREATETIMECN = "����ʱ��";

	/**
	 *����ʱ��
	 */
	private String lastupdatetime;

	public static final String LASTUPDATETIMECN = "����ʱ��";

	/**
	 *�������
	 */
	private String orgforshort;

	public static final String ORGFORSHORTCN = "�������";

	/**
	 *��������ֱ����λ����
	 */
	private String orgdirectlycoding;

	public static final String ORGDIRECTLYCODINGCN = "��������ֱ����λ����";

	/**
	 *�����־
	 */
	private String orgfreezeflag;

	public static final String ORGFREEZEFLAGCN = "�����־";

	/**
	 *Ԥ���ֶ�1
	 */
	private String resaveds1;

	public static final String RESAVEDS1CN = "Ԥ���ֶ�1";

	/**
	 *ֱ����������
	 */
	private String resaveds2;

	public static final String RESAVEDS2CN = "ֱ����������";

	/**
	 *��������
	 */
	private String resaveds3;

	public static final String RESAVEDS3CN = "��������";

	/**
	 *�������
	 */
	private String resaveds4;

	public static final String RESAVEDS4CN = "�������";

	/**
	 *��������
	 */
	private String resaveds5;

	public static final String RESAVEDS5CN = "��������";

	/**
	 *��λ���
	 */
	private String unittype;

	public static final String UNITTYPECN = "��λ���";

	/**
	 *@return orgid ����ID
	 */
	public int getOrgid() {
		return this.orgid;
	}

	/**
	 *@param orgid ����ID
	 */
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	/**
	 *@return orgname ��������
	 */
	public String getOrgname() {
		return this.orgname;
	}

	/**
	 *@param orgname ��������
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 *@return orgpar �ϼ����������
	 */
	public String getOrgpar() {
		return this.orgpar;
	}

	/**
	 *@param orgpar �ϼ����������
	 */
	public void setOrgpar(String orgpar) {
		this.orgpar = orgpar;
	}

	/**
	 *@return orgparname �ϼ���������
	 */
	public String getOrgparname() {
		return this.orgparname;
	}

	/**
	 *@param orgparname �ϼ���������
	 */
	public void setOrgparname(String orgparname) {
		this.orgparname = orgparname;
	}

	/**
	 *@return orgdesc ����˵��
	 */
	public String getOrgdesc() {
		return this.orgdesc;
	}

	/**
	 *@param orgdesc ����˵��
	 */
	public void setOrgdesc(String orgdesc) {
		this.orgdesc = orgdesc;
	}

	/**
	 *@return orgtype ��������
	 */
	public String getOrgtype() {
		return this.orgtype;
	}

	/**
	 *@param orgtype ��������
	 */
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	/**
	 *@return orgaddr ������ַ
	 */
	public String getOrgaddr() {
		return this.orgaddr;
	}

	/**
	 *@param orgaddr ������ַ
	 */
	public void setOrgaddr(String orgaddr) {
		this.orgaddr = orgaddr;
	}

	/**
	 *@return orgphone �����绰
	 */
	public String getOrgphone() {
		return this.orgphone;
	}

	/**
	 *@param orgphone �����绰
	 */
	public void setOrgphone(String orgphone) {
		this.orgphone = orgphone;
	}

	/**
	 *@return orgfax ��������
	 */
	public String getOrgfax() {
		return this.orgfax;
	}

	/**
	 *@param orgfax ��������
	 */
	public void setOrgfax(String orgfax) {
		this.orgfax = orgfax;
	}

	/**
	 *@return orgcontact ������ϵ��
	 */
	public String getOrgcontact() {
		return this.orgcontact;
	}

	/**
	 *@param orgcontact ������ϵ��
	 */
	public void setOrgcontact(String orgcontact) {
		this.orgcontact = orgcontact;
	}

	/**
	 *@return orgstatus ����״̬
	 */
	public String getOrgstatus() {
		return this.orgstatus;
	}

	/**
	 *@param orgstatus ����״̬
	 */
	public void setOrgstatus(String orgstatus) {
		this.orgstatus = orgstatus;
	}

	/**
	 *@return orginfo ������Ϣ
	 */
	public String getOrginfo() {
		return this.orginfo;
	}

	/**
	 *@param orginfo ������Ϣ
	 */
	public void setOrginfo(String orginfo) {
		this.orginfo = orginfo;
	}

	/**
	 *@return orgsyscoding ���������
	 */
	public String getOrgsyscoding() {
		return this.orgsyscoding;
	}

	/**
	 *@param orgsyscoding ���������
	 */
	public void setOrgsyscoding(String orgsyscoding) {
		this.orgsyscoding = orgsyscoding;
	}

	/**
	 *@return orgusercoding ��������
	 */
	public String getOrgusercoding() {
		return this.orgusercoding;
	}

	/**
	 *@param orgusercoding ��������
	 */
	public void setOrgusercoding(String orgusercoding) {
		this.orgusercoding = orgusercoding;
	}

	/**
	 *@return fingerprint ʱ���
	 */
	public String getFingerprint() {
		return this.fingerprint;
	}

	/**
	 *@param fingerprint ʱ���
	 */
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	/**
	 *@return initcreatetime ����ʱ��
	 */
	public String getInitcreatetime() {
		return this.initcreatetime;
	}

	/**
	 *@param initcreatetime ����ʱ��
	 */
	public void setInitcreatetime(String initcreatetime) {
		this.initcreatetime = initcreatetime;
	}

	/**
	 *@return lastupdatetime ����ʱ��
	 */
	public String getLastupdatetime() {
		return this.lastupdatetime;
	}

	/**
	 *@param lastupdatetime ����ʱ��
	 */
	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	/**
	 *@return orgforshort �������
	 */
	public String getOrgforshort() {
		return this.orgforshort;
	}

	/**
	 *@param orgforshort �������
	 */
	public void setOrgforshort(String orgforshort) {
		this.orgforshort = orgforshort;
	}

	/**
	 *@return orgdirectlycoding ��������ֱ����λ����
	 */
	public String getOrgdirectlycoding() {
		return this.orgdirectlycoding;
	}

	/**
	 *@param orgdirectlycoding ��������ֱ����λ����
	 */
	public void setOrgdirectlycoding(String orgdirectlycoding) {
		this.orgdirectlycoding = orgdirectlycoding;
	}

	/**
	 *@return orgfreezeflag �����־
	 */
	public String getOrgfreezeflag() {
		return this.orgfreezeflag;
	}

	/**
	 *@param orgfreezeflag �����־
	 */
	public void setOrgfreezeflag(String orgfreezeflag) {
		this.orgfreezeflag = orgfreezeflag;
	}

	/**
	 *@return resaveds1 Ԥ���ֶ�1
	 */
	public String getResaveds1() {
		return this.resaveds1;
	}

	/**
	 *@param resaveds1 Ԥ���ֶ�1
	 */
	public void setResaveds1(String resaveds1) {
		this.resaveds1 = resaveds1;
	}

	/**
	 *@return resaveds2 ֱ����������
	 */
	public String getResaveds2() {
		return this.resaveds2;
	}

	/**
	 *@param resaveds2 ֱ����������
	 */
	public void setResaveds2(String resaveds2) {
		this.resaveds2 = resaveds2;
	}

	/**
	 *@return resaveds3 ��������
	 */
	public String getResaveds3() {
		return this.resaveds3;
	}

	/**
	 *@param resaveds3 ��������
	 */
	public void setResaveds3(String resaveds3) {
		this.resaveds3 = resaveds3;
	}

	/**
	 *@return resaveds4 �������
	 */
	public String getResaveds4() {
		return this.resaveds4;
	}

	/**
	 *@param resaveds4 �������
	 */
	public void setResaveds4(String resaveds4) {
		this.resaveds4 = resaveds4;
	}

	/**
	 *@return resaveds5 ��������
	 */
	public String getResaveds5() {
		return this.resaveds5;
	}

	/**
	 *@param resaveds5 ��������
	 */
	public void setResaveds5(String resaveds5) {
		this.resaveds5 = resaveds5;
	}

	/**
	 *@return unittype ��λ���
	 */
	public String getUnittype() {
		return this.unittype;
	}

	/**
	 *@param unittype ��λ���
	 */
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

}
