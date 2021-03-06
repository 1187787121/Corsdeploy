/**
 * Title: ResetPwdInputBean.java
 * File Description: 重置密码输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 重置密码输入接口
 * @author link
 */
public class ResetPwdInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 9023936511365934307L;
	private String user_id;
	public static final String USER_IDCN = "用户名";

	/**
	 * @return user_id 用户名
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id 用户名
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
