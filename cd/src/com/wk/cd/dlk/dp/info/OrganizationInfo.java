/**
 * Title: OrganizationInfo.java
 * File Description: 机构表
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
 * Class description:机构表
 * @author AutoGen
 */
@Table("ORGANIZATION")
@PrimaryKey({"orgid"})
public class OrganizationInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "机构表";

	/**
	 *机构ID
	 */
	private int orgid;

	public static final String ORGIDCN = "机构ID";

	/**
	 *机构名称
	 */
	private String orgname;

	public static final String ORGNAMECN = "机构名称";

	/**
	 *上级机构层次码
	 */
	private String orgpar;

	public static final String ORGPARCN = "上级机构层次码";

	/**
	 *上级机构名称
	 */
	private String orgparname;

	public static final String ORGPARNAMECN = "上级机构名称";

	/**
	 *机构说明
	 */
	private String orgdesc;

	public static final String ORGDESCCN = "机构说明";

	/**
	 *机构类型
	 */
	private String orgtype;

	public static final String ORGTYPECN = "机构类型";

	/**
	 *机构地址
	 */
	private String orgaddr;

	public static final String ORGADDRCN = "机构地址";

	/**
	 *机构电话
	 */
	private String orgphone;

	public static final String ORGPHONECN = "机构电话";

	/**
	 *机构传真
	 */
	private String orgfax;

	public static final String ORGFAXCN = "机构传真";

	/**
	 *机构联系人
	 */
	private String orgcontact;

	public static final String ORGCONTACTCN = "机构联系人";

	/**
	 *机构状态
	 */
	private String orgstatus;

	public static final String ORGSTATUSCN = "机构状态";

	/**
	 *机构信息
	 */
	private String orginfo;

	public static final String ORGINFOCN = "机构信息";

	/**
	 *机构层次码
	 */
	private String orgsyscoding;

	public static final String ORGSYSCODINGCN = "机构层次码";

	/**
	 *机构编码
	 */
	private String orgusercoding;

	public static final String ORGUSERCODINGCN = "机构编码";

	/**
	 *时间戳
	 */
	private String fingerprint;

	public static final String FINGERPRINTCN = "时间戳";

	/**
	 *插入时间
	 */
	private String initcreatetime;

	public static final String INITCREATETIMECN = "插入时间";

	/**
	 *更新时间
	 */
	private String lastupdatetime;

	public static final String LASTUPDATETIMECN = "更新时间";

	/**
	 *机构简称
	 */
	private String orgforshort;

	public static final String ORGFORSHORTCN = "机构简称";

	/**
	 *人事隶属直属单位代码
	 */
	private String orgdirectlycoding;

	public static final String ORGDIRECTLYCODINGCN = "人事隶属直属单位代码";

	/**
	 *冻结标志
	 */
	private String orgfreezeflag;

	public static final String ORGFREEZEFLAGCN = "冻结标志";

	/**
	 *预留字段1
	 */
	private String resaveds1;

	public static final String RESAVEDS1CN = "预留字段1";

	/**
	 *直属机构编码
	 */
	private String resaveds2;

	public static final String RESAVEDS2CN = "直属机构编码";

	/**
	 *机构城市
	 */
	private String resaveds3;

	public static final String RESAVEDS3CN = "机构城市";

	/**
	 *排序编码
	 */
	private String resaveds4;

	public static final String RESAVEDS4CN = "排序编码";

	/**
	 *机构级别
	 */
	private String resaveds5;

	public static final String RESAVEDS5CN = "机构级别";

	/**
	 *单位类别
	 */
	private String unittype;

	public static final String UNITTYPECN = "单位类别";

	/**
	 *@return orgid 机构ID
	 */
	public int getOrgid() {
		return this.orgid;
	}

	/**
	 *@param orgid 机构ID
	 */
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	/**
	 *@return orgname 机构名称
	 */
	public String getOrgname() {
		return this.orgname;
	}

	/**
	 *@param orgname 机构名称
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 *@return orgpar 上级机构层次码
	 */
	public String getOrgpar() {
		return this.orgpar;
	}

	/**
	 *@param orgpar 上级机构层次码
	 */
	public void setOrgpar(String orgpar) {
		this.orgpar = orgpar;
	}

	/**
	 *@return orgparname 上级机构名称
	 */
	public String getOrgparname() {
		return this.orgparname;
	}

	/**
	 *@param orgparname 上级机构名称
	 */
	public void setOrgparname(String orgparname) {
		this.orgparname = orgparname;
	}

	/**
	 *@return orgdesc 机构说明
	 */
	public String getOrgdesc() {
		return this.orgdesc;
	}

	/**
	 *@param orgdesc 机构说明
	 */
	public void setOrgdesc(String orgdesc) {
		this.orgdesc = orgdesc;
	}

	/**
	 *@return orgtype 机构类型
	 */
	public String getOrgtype() {
		return this.orgtype;
	}

	/**
	 *@param orgtype 机构类型
	 */
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	/**
	 *@return orgaddr 机构地址
	 */
	public String getOrgaddr() {
		return this.orgaddr;
	}

	/**
	 *@param orgaddr 机构地址
	 */
	public void setOrgaddr(String orgaddr) {
		this.orgaddr = orgaddr;
	}

	/**
	 *@return orgphone 机构电话
	 */
	public String getOrgphone() {
		return this.orgphone;
	}

	/**
	 *@param orgphone 机构电话
	 */
	public void setOrgphone(String orgphone) {
		this.orgphone = orgphone;
	}

	/**
	 *@return orgfax 机构传真
	 */
	public String getOrgfax() {
		return this.orgfax;
	}

	/**
	 *@param orgfax 机构传真
	 */
	public void setOrgfax(String orgfax) {
		this.orgfax = orgfax;
	}

	/**
	 *@return orgcontact 机构联系人
	 */
	public String getOrgcontact() {
		return this.orgcontact;
	}

	/**
	 *@param orgcontact 机构联系人
	 */
	public void setOrgcontact(String orgcontact) {
		this.orgcontact = orgcontact;
	}

	/**
	 *@return orgstatus 机构状态
	 */
	public String getOrgstatus() {
		return this.orgstatus;
	}

	/**
	 *@param orgstatus 机构状态
	 */
	public void setOrgstatus(String orgstatus) {
		this.orgstatus = orgstatus;
	}

	/**
	 *@return orginfo 机构信息
	 */
	public String getOrginfo() {
		return this.orginfo;
	}

	/**
	 *@param orginfo 机构信息
	 */
	public void setOrginfo(String orginfo) {
		this.orginfo = orginfo;
	}

	/**
	 *@return orgsyscoding 机构层次码
	 */
	public String getOrgsyscoding() {
		return this.orgsyscoding;
	}

	/**
	 *@param orgsyscoding 机构层次码
	 */
	public void setOrgsyscoding(String orgsyscoding) {
		this.orgsyscoding = orgsyscoding;
	}

	/**
	 *@return orgusercoding 机构编码
	 */
	public String getOrgusercoding() {
		return this.orgusercoding;
	}

	/**
	 *@param orgusercoding 机构编码
	 */
	public void setOrgusercoding(String orgusercoding) {
		this.orgusercoding = orgusercoding;
	}

	/**
	 *@return fingerprint 时间戳
	 */
	public String getFingerprint() {
		return this.fingerprint;
	}

	/**
	 *@param fingerprint 时间戳
	 */
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	/**
	 *@return initcreatetime 插入时间
	 */
	public String getInitcreatetime() {
		return this.initcreatetime;
	}

	/**
	 *@param initcreatetime 插入时间
	 */
	public void setInitcreatetime(String initcreatetime) {
		this.initcreatetime = initcreatetime;
	}

	/**
	 *@return lastupdatetime 更新时间
	 */
	public String getLastupdatetime() {
		return this.lastupdatetime;
	}

	/**
	 *@param lastupdatetime 更新时间
	 */
	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	/**
	 *@return orgforshort 机构简称
	 */
	public String getOrgforshort() {
		return this.orgforshort;
	}

	/**
	 *@param orgforshort 机构简称
	 */
	public void setOrgforshort(String orgforshort) {
		this.orgforshort = orgforshort;
	}

	/**
	 *@return orgdirectlycoding 人事隶属直属单位代码
	 */
	public String getOrgdirectlycoding() {
		return this.orgdirectlycoding;
	}

	/**
	 *@param orgdirectlycoding 人事隶属直属单位代码
	 */
	public void setOrgdirectlycoding(String orgdirectlycoding) {
		this.orgdirectlycoding = orgdirectlycoding;
	}

	/**
	 *@return orgfreezeflag 冻结标志
	 */
	public String getOrgfreezeflag() {
		return this.orgfreezeflag;
	}

	/**
	 *@param orgfreezeflag 冻结标志
	 */
	public void setOrgfreezeflag(String orgfreezeflag) {
		this.orgfreezeflag = orgfreezeflag;
	}

	/**
	 *@return resaveds1 预留字段1
	 */
	public String getResaveds1() {
		return this.resaveds1;
	}

	/**
	 *@param resaveds1 预留字段1
	 */
	public void setResaveds1(String resaveds1) {
		this.resaveds1 = resaveds1;
	}

	/**
	 *@return resaveds2 直属机构编码
	 */
	public String getResaveds2() {
		return this.resaveds2;
	}

	/**
	 *@param resaveds2 直属机构编码
	 */
	public void setResaveds2(String resaveds2) {
		this.resaveds2 = resaveds2;
	}

	/**
	 *@return resaveds3 机构城市
	 */
	public String getResaveds3() {
		return this.resaveds3;
	}

	/**
	 *@param resaveds3 机构城市
	 */
	public void setResaveds3(String resaveds3) {
		this.resaveds3 = resaveds3;
	}

	/**
	 *@return resaveds4 排序编码
	 */
	public String getResaveds4() {
		return this.resaveds4;
	}

	/**
	 *@param resaveds4 排序编码
	 */
	public void setResaveds4(String resaveds4) {
		this.resaveds4 = resaveds4;
	}

	/**
	 *@return resaveds5 机构级别
	 */
	public String getResaveds5() {
		return this.resaveds5;
	}

	/**
	 *@param resaveds5 机构级别
	 */
	public void setResaveds5(String resaveds5) {
		this.resaveds5 = resaveds5;
	}

	/**
	 *@return unittype 单位类别
	 */
	public String getUnittype() {
		return this.unittype;
	}

	/**
	 *@param unittype 单位类别
	 */
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

}
