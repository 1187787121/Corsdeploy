/**
 * Title: CheckLocalAuthInputBean.java
 * File Description: 检查本地授权权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年6月26日
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * @author HT
 */
public class CheckLocalAuthInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 5105354264513043811L;
	
	/**
	 * 授权序号
	 */
	private Integer auth_seq;
	
	public static final String AUTH_SEQCN="授权序号";
	
	/**
	 * 授权部门角色编码
	 */
	private String auth_dprl_code;
	
	public static final String AUTH_DPRL_CODECN="授权部门角色编码";
	
	/**
	 * 授权用户名
	 */
	private String auth_user_id;
	
	public static final String AUTH_USER_IDCN="授权用户名";
	
	/**
	 * 授权密码
	 */
	private String user_passwd;

	public static final String USER_PASSWDCN="授权密码";
	
	/**
	 * 授权关键字
	 */
	private String auth_key;
	
	public static final String AUTH_KEYCN = "授权关键字";
	
	/**
	 * @return auth_seq 授权序号
	 */
	public Integer getAuth_seq() {
		return this.auth_seq;
	}

	/**
	 * @param auth_seq 授权序号
	 */
	public void setAuth_seq(Integer auth_seq) {
		this.auth_seq = auth_seq;
	}

	/**
	 * @return auth_dprl_code 授权部门角色编码
	 */
	public String getAuth_dprl_code() {
		return this.auth_dprl_code;
	}

	/**
	 * @param auth_dprl_code 授权部门角色编码
	 */
	public void setAuth_dprl_code(String auth_dprl_code) {
		this.auth_dprl_code = auth_dprl_code;
	}

	/**
	 * @return auth_user_id 授权用户名
	 */
	public String getAuth_user_id() {
		return this.auth_user_id;
	}

	/**
	 * @param auth_user_id 授权用户名
	 */
	public void setAuth_user_id(String auth_user_id) {
		this.auth_user_id = auth_user_id;
	}

	/**
	 * @return user_passwd 授权密码
	 */
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/**
	 * @param user_passwd 授权密码
	 */
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	/**
	 * @return auth_key 授权关键字
	 */
	public String getAuth_key() {
		return auth_key;
	}

	/**
	 * @param auth_key 授权关键字
	 */
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
}
