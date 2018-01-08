/**
 * Title: QueryUserByUserIdOutputBean.java
 * File Description: 根据用户名查询用户详细信息输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsUserRoleInfo;

/**
 * Class Description:根据用户名查询用户详细信息输出接口
 * @author link
 */
public class QueryUserByUserIdOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 2626601738837554185L;
	
	/**
	 * 用户详细信息
	 */
	private UsExtUserBean user_bean;
	
	public static final String USER_BEANCN = "用户详细信息";
	
	/**
	 * 用户角色信息
	 */
	private List<UsUserRoleInfo> us_roles;
	
	public static final String US_ROLESCN = "用户角色信息";

	/**
	 * @return user_bean 用户详细信息
	 */
	public UsExtUserBean getUser_bean() {
		return this.user_bean;
	}

	/**
	 * @param user_bean 用户详细信息
	 */
	public void setUser_bean(UsExtUserBean user_bean) {
		this.user_bean = user_bean;
	}
	
	/**
	 * @return us_roles 用户角色信息
	 */
	public List<UsUserRoleInfo> getUs_roles() {
		return this.us_roles;
	}

	/**
	 * @param us_roles 用户角色信息
	 */
	public void setUs_roles(List<UsUserRoleInfo> us_roles) {
		this.us_roles = us_roles;
	}

}
