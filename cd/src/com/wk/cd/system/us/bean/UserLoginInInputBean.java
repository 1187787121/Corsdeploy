/**
 * Title: UserLoginInInputBean.java
 * File Description: 用户登录输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 用户登录输入接口
 * @author link
 */
public class UserLoginInInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -1014001385346845596L;

	private String user_passwd;
	public static final String USER_PASSWDCN = "用户密码";

	/**
	 * 客户端IP
	 */
	private String remote_ip;

	/**
	 * @return user_passwd 用户密码
	 */
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/**
	 * @param user_passwd 用户密码
	 */
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	/**
	 * @return String 客户端IP
	 */
	public String getRemote_ip(){
		return this.remote_ip;
	}

	/**
	 * @param remote_ip  客户端IP
	 */
	public void setRemote_ip(String remote_ip){
		this.remote_ip = remote_ip;
	}

}
