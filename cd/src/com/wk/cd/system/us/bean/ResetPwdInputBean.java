/**
 * Title: ResetPwdInputBean.java
 * File Description: ������������ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ������������ӿ�
 * @author link
 */
public class ResetPwdInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 9023936511365934307L;
	private String user_id;
	public static final String USER_IDCN = "�û���";

	/**
	 * @return user_id �û���
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
