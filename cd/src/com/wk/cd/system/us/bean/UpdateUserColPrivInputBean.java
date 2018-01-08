/**
 * Title: UpdateUserColPrivInputBean.java
 * File Description: 修改用户Col权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月9日
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsUserColPrivInfo;

/**
 * Class Description: 修改用户Col权限输入接口
 * @author HT
 */
public class UpdateUserColPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -2499076444932061994L;

	/**
	 * 用户名
	 */
	private String user_id;
	
	public static final String USER_IDCN = "用户名";
	
	/**
	 * col权限列表
	 */
	private List<UsUserColPrivInfo> col_list;
	
	public static final String COL_LISTCN = "col权限列表";

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

	/**
	 * @return col_list col权限列表
	 */
	public List<UsUserColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list col权限列表
	 */
	public void setCol_list(List<UsUserColPrivInfo> col_list) {
		this.col_list = col_list;
	}
}
