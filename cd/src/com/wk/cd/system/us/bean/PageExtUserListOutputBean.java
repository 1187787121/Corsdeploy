/**
 * Title: PageExtUserListOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-20
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description:
 * @author link
 */
public class PageExtUserListOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = 8483182659263804040L;
	private List<UsExtUserBean> user_list;
	public static final String USER_LISTCN = "用户扩展信息列表";

	/**
	 * @return user_list 用户扩展信息列表
	 */
	public List<UsExtUserBean> getUser_list() {
		return this.user_list;
	}

	/**
	 * @param user_list 用户扩展信息列表
	 */
	public void setUser_list(List<UsExtUserBean> user_list) {
		this.user_list = user_list;
	}

}
