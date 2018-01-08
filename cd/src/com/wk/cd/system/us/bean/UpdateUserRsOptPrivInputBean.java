/**
 * Title: UpdateUserRsOptPrivInputBean.java
 * File Description: 修改用户资源及操作权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月9日
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;

/**
 * Class Description: 修改用户资源及操作权限输入接口
 * @author HT
 */
public class UpdateUserRsOptPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -7812087656855792397L;
	
	/**
	 * 用户名
	 */
	private String user_id;

	public static final String USER_IDCN = "用户名_V";

	/**
	 * 资源权限列表
	 */
	private List<UsUserRsPrivInfo> rs_list;

	public static final String RS_LISTCN = "资源权限列表_V";
	
	/**
	 * 用户操作权限列表
	 */
	private List<UsUserOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "用户操作权限列表_V";

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
	 * @return rs_list 资源权限列表
	 */
	public List<UsUserRsPrivInfo> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list 资源权限列表
	 */
	public void setRs_list(List<UsUserRsPrivInfo> rs_list) {
		this.rs_list = rs_list;
	}

	/**
	 * @return opt_priv 资源编码数组列表
	 */
	public List<UsUserOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv资源编码数组列表
	 */
	public void setOpt_priv(List<UsUserOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}
}
