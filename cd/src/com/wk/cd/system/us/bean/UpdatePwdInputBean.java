/**
 * Title: UpdatePwdInputBean.java
 * File Description: �û������������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-26
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:�û������������ӿ�
 * @author link
 */
public class UpdatePwdInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -5007518484548487404L;
	private String user_passwd;
	public static final String USER_PASSWDCN = "�û�����";

	private String new_user_passwd;
	public static final String NEW_USER_PASSWDCN = "�ٴ������û�����";

	private String new_sec_passwd;
	public static final String NEW_SEC_PASSWDCN = "�ٴ�����������";

	/**
	 * @return user_passwd
	 */
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/**
	 * @param user_passwd
	 */
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	/**
	 * @return new_user_passwd
	 */
	public String getNew_user_passwd() {
		return this.new_user_passwd;
	}

	/**
	 * @param new_user_passwd
	 */
	public void setNew_user_passwd(String new_user_passwd) {
		this.new_user_passwd = new_user_passwd;
	}

	/**
	 * @return new_sec_passwd
	 */
	public String getNew_sec_passwd() {
		return this.new_sec_passwd;
	}

	/**
	 * @param new_sec_passwd
	 */
	public void setNew_sec_passwd(String new_sec_passwd) {
		this.new_sec_passwd = new_sec_passwd;
	}

}
