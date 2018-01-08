/**
 * Title: QueryUserTermListInputBean.java
 * File Description: 查询用户接入终端列表输入接口 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月14日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: 查询用户接入终端列表输入接口 
 * @author HT
 */
public class PageUserTermInputBean extends PageQueryActionRootInputBean{

	private static final long serialVersionUID = -2809475400555353563L;
	
	/**
	 * 用户姓名
	 */
	private String user_cn_name;
	
	public final static String USER_CN_NAMECN="用户姓名";

	/**
	 * @return user_cn_name 用户姓名
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name 用户姓名
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}
}
