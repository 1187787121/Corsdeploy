/**
 * Title: QueryAllRoleOutputBean.java
 * File Description:查询所有角色信息输出接口 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-2-3
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsRoleInfo;

/**
 * Class Description:查询所有角色信息输出接口
 * @author link
 */
public class QueryAllRoleOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 3389876892855208654L;

	public List<UsRoleInfo> role_list;
	public static final String ROLE_LISTCN = "角色列表";

	/**
	 * @return role_list 角色列表
	 */
	public List<UsRoleInfo> getRole_list() {
		return this.role_list;
	}

	/**
	 * @param role_list 角色列表
	 */
	public void setRole_list(List<UsRoleInfo> role_list) {
		this.role_list = role_list;
	}

}
