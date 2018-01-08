/**
 * Title: QueryAllUserInfosOutputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月10日
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsUserInfo;

/**
 * Class Description: 查询所有用户信息输出接口
 * @author HT
 */
public class QueryAllUserInfosOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 7393644854562168894L;
	
	/**
	 * 用户列表
	 */
	private List<UsUserInfo> user_list;
	
	public static final String USER_LISTCN="用户列表";

	/**
	 * @return user_list 用户列表
	 */
	public List<UsUserInfo> getUser_list() {
		return this.user_list;
	}

	/**
	 * @param user_list 用户列表
	 */
	public void setUser_list(List<UsUserInfo> user_list) {
		this.user_list = user_list;
	}
	
	
}
