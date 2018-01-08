/**
 * Title: IgUserInfo.java
 * File Description: 用户表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-24
 */

package com.wk.cd.dlk.us.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:用户表
 * @author AutoGen
 */
@Table("IGUSER")
@PrimaryKey({"userid"})
public class IgUserInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "用户表";

	/**
	 *用户ID
	 */
	private String userid;

	public static final String USERIDCN = "用户ID";

	/**
	 *用户机构层次码
	 */
	private String orgid;

	public static final String ORGIDCN = "用户机构层次码";

	/**
	 *用户机构名称
	 */
	private String orgname;

	public static final String ORGNAMECN = "用户机构名称";

	/**
	 *用户名称
	 */
	private String username;

	public static final String USERNAMECN = "用户名称";

	/**
	 *用户说明
	 */
	private String userdesc;

	public static final String USERDESCCN = "用户说明";

	/**
	 *用户信息
	 */
	private String userinfo;

	public static final String USERINFOCN = "用户信息";

	/**
	 *用户登录密码
	 */
	private String password;

	public static final String PASSWORDCN = "用户登录密码";

	/**
	 *用户状态
	 */
	private String userstatus;

	public static final String USERSTATUSCN = "用户状态";

	/**
	 *用户类型
	 */
	private String usertype;

	public static final String USERTYPECN = "用户类型";

	/**
	 *用户电话
	 */
	private String userphone;

	public static final String USERPHONECN = "用户电话";

	/**
	 *用户手机
	 */
	private String usermobile;

	public static final String USERMOBILECN = "用户手机";

	/**
	 *用户邮箱
	 */
	private String useremail;

	public static final String USEREMAILCN = "用户邮箱";

	/**
	 *时间戳
	 */
	private String fingerprint;

	public static final String FINGERPRINTCN = "时间戳";

	/**
	 *逻辑删除标志位
	 */
	private String deleteflag;

	public static final String DELETEFLAGCN = "逻辑删除标志位";

	/**
	 *@return userid 用户ID
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 *@param userid 用户ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 *@return orgid 用户机构层次码
	 */
	public String getOrgid() {
		return this.orgid;
	}

	/**
	 *@param orgid 用户机构层次码
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	/**
	 *@return orgname 用户机构名称
	 */
	public String getOrgname() {
		return this.orgname;
	}

	/**
	 *@param orgname 用户机构名称
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 *@return username 用户名称
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 *@param username 用户名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *@return userdesc 用户说明
	 */
	public String getUserdesc() {
		return this.userdesc;
	}

	/**
	 *@param userdesc 用户说明
	 */
	public void setUserdesc(String userdesc) {
		this.userdesc = userdesc;
	}

	/**
	 *@return userinfo 用户信息
	 */
	public String getUserinfo() {
		return this.userinfo;
	}

	/**
	 *@param userinfo 用户信息
	 */
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	/**
	 *@return password 用户登录密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 *@param password 用户登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *@return userstatus 用户状态
	 */
	public String getUserstatus() {
		return this.userstatus;
	}

	/**
	 *@param userstatus 用户状态
	 */
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	/**
	 *@return usertype 用户类型
	 */
	public String getUsertype() {
		return this.usertype;
	}

	/**
	 *@param usertype 用户类型
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 *@return userphone 用户电话
	 */
	public String getUserphone() {
		return this.userphone;
	}

	/**
	 *@param userphone 用户电话
	 */
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	/**
	 *@return usermobile 用户手机
	 */
	public String getUsermobile() {
		return this.usermobile;
	}

	/**
	 *@param usermobile 用户手机
	 */
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	/**
	 *@return useremail 用户邮箱
	 */
	public String getUseremail() {
		return this.useremail;
	}

	/**
	 *@param useremail 用户邮箱
	 */
	public void setUseremail(String useremail) {
		this.useremail = useremail;
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
	 *@return deleteflag 逻辑删除标志位
	 */
	public String getDeleteflag() {
		return this.deleteflag;
	}

	/**
	 *@param deleteflag 逻辑删除标志位
	 */
	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

}
