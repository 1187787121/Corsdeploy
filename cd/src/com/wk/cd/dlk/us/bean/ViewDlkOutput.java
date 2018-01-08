/**
 * Title: QueryDlkLoginOutput.java
 * File Description: ��ѯ����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016��10��25��
 */
package com.wk.cd.dlk.us.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ��ѯ����������ӿ�
 * @author HT
 */
public class ViewDlkOutput extends ActionRootOutputBean{

	private static final long serialVersionUID = -9046353193802828264L;

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
	 *�ն˺�
	 */
	private String term_no;

	public static final String TERM_NOCN = "�ն˺�";

	/**
	 * @return userid �û�ID
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid �û�ID
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return orgid �û����������
	 */
	public String getOrgid() {
		return orgid;
	}

	/**
	 * @param orgid �û����������
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	/**
	 * @return orgname �û���������
	 */
	public String getOrgname() {
		return orgname;
	}

	/**
	 * @param orgname �û���������
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 * @return username �û�����
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username �û�����
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return term_no �ն˺�
	 */
	public String getTerm_no() {
		return term_no;
	}

	/**
	 * @param term_no �ն˺�
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}
}
