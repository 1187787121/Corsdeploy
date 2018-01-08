/**
 * Title: IgUserInfo.java
 * File Description: �û���
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
 * Class description:�û���
 * @author AutoGen
 */
@Table("IGUSER")
@PrimaryKey({"userid"})
public class IgUserInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�û���";

	/**
	 *�û�ID
	 */
	private String userid;

	public static final String USERIDCN = "�û�ID";

	/**
	 *�û����������
	 */
	private String orgid;

	public static final String ORGIDCN = "�û����������";

	/**
	 *�û���������
	 */
	private String orgname;

	public static final String ORGNAMECN = "�û���������";

	/**
	 *�û�����
	 */
	private String username;

	public static final String USERNAMECN = "�û�����";

	/**
	 *�û�˵��
	 */
	private String userdesc;

	public static final String USERDESCCN = "�û�˵��";

	/**
	 *�û���Ϣ
	 */
	private String userinfo;

	public static final String USERINFOCN = "�û���Ϣ";

	/**
	 *�û���¼����
	 */
	private String password;

	public static final String PASSWORDCN = "�û���¼����";

	/**
	 *�û�״̬
	 */
	private String userstatus;

	public static final String USERSTATUSCN = "�û�״̬";

	/**
	 *�û�����
	 */
	private String usertype;

	public static final String USERTYPECN = "�û�����";

	/**
	 *�û��绰
	 */
	private String userphone;

	public static final String USERPHONECN = "�û��绰";

	/**
	 *�û��ֻ�
	 */
	private String usermobile;

	public static final String USERMOBILECN = "�û��ֻ�";

	/**
	 *�û�����
	 */
	private String useremail;

	public static final String USEREMAILCN = "�û�����";

	/**
	 *ʱ���
	 */
	private String fingerprint;

	public static final String FINGERPRINTCN = "ʱ���";

	/**
	 *�߼�ɾ����־λ
	 */
	private String deleteflag;

	public static final String DELETEFLAGCN = "�߼�ɾ����־λ";

	/**
	 *@return userid �û�ID
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 *@param userid �û�ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 *@return orgid �û����������
	 */
	public String getOrgid() {
		return this.orgid;
	}

	/**
	 *@param orgid �û����������
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	/**
	 *@return orgname �û���������
	 */
	public String getOrgname() {
		return this.orgname;
	}

	/**
	 *@param orgname �û���������
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 *@return username �û�����
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 *@param username �û�����
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *@return userdesc �û�˵��
	 */
	public String getUserdesc() {
		return this.userdesc;
	}

	/**
	 *@param userdesc �û�˵��
	 */
	public void setUserdesc(String userdesc) {
		this.userdesc = userdesc;
	}

	/**
	 *@return userinfo �û���Ϣ
	 */
	public String getUserinfo() {
		return this.userinfo;
	}

	/**
	 *@param userinfo �û���Ϣ
	 */
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	/**
	 *@return password �û���¼����
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 *@param password �û���¼����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *@return userstatus �û�״̬
	 */
	public String getUserstatus() {
		return this.userstatus;
	}

	/**
	 *@param userstatus �û�״̬
	 */
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	/**
	 *@return usertype �û�����
	 */
	public String getUsertype() {
		return this.usertype;
	}

	/**
	 *@param usertype �û�����
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 *@return userphone �û��绰
	 */
	public String getUserphone() {
		return this.userphone;
	}

	/**
	 *@param userphone �û��绰
	 */
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	/**
	 *@return usermobile �û��ֻ�
	 */
	public String getUsermobile() {
		return this.usermobile;
	}

	/**
	 *@param usermobile �û��ֻ�
	 */
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	/**
	 *@return useremail �û�����
	 */
	public String getUseremail() {
		return this.useremail;
	}

	/**
	 *@param useremail �û�����
	 */
	public void setUseremail(String useremail) {
		this.useremail = useremail;
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
	 *@return deleteflag �߼�ɾ����־λ
	 */
	public String getDeleteflag() {
		return this.deleteflag;
	}

	/**
	 *@param deleteflag �߼�ɾ����־λ
	 */
	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

}
