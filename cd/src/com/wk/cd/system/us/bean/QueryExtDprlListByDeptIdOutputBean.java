/**
 * Title: PageExtDprlListByDeptIdOutputBean.java
 * File Description: 根据部门编码查询部门角色编码的相关详细信息输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsUserRoleInfo;

/**
 * Class Description:根据部门编码查询部门角色编码的相关详细信息输出接口
 * @author link
 */
public class QueryExtDprlListByDeptIdOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = -7662747713781100952L;
	
	/**
	 * 部门角色详细信息列表
	 */
	private List<UsExtDprlBean> dprl_bean_list;
	
	public static final String DPRL_BEAN_LISTCN = "部门角色详细信息列表";
	
	/**
	 * 用户角色信息
	 */
	private List<UsUserRoleInfo> us_roles;
	
	public static final String US_ROLESCN = "用户角色信息";

	/**
	 * @return dprl_bean_list 部门角色详细信息列表
	 */
	public List<UsExtDprlBean> getDprl_bean_list() {
		return this.dprl_bean_list;
	}

	/**
	 * @param dprl_bean_list 部门角色详细信息列表
	 */
	public void setDprl_bean_list(List<UsExtDprlBean> dprl_bean_list) {
		this.dprl_bean_list = dprl_bean_list;
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
