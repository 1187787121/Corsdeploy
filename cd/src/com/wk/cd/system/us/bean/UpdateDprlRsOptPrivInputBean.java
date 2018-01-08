/**
 * Title: UpdateDprlRsOptPrivInputBean.java
 * File Description: 修改部门角色资源及操作权限信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月7日
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsRoleOptPrivInfo;

/**
 * Class Description: 修改部门角色资源及操作权限信息输入接口
 * @author HT
 */
public class UpdateDprlRsOptPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -822565438298002799L;
	
	/**
	 * 部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 * 资源编码数组列表
	 */
	private String[] rs_ary;

	public static final String RS_ARYCN = "资源编码数组列表";
	
	/**
	 * 部门角色操作权限列表
	 */
	private List<UsRoleOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "部门角色操作权限列表";

	/**
	 * @return dprl_code 部门角色编码
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 * @param dprl_code 部门角色编码
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}

	/**
	 * @return rs_ary 资源编码数组列表
	 */
	public String[] getRs_ary() {
		return this.rs_ary;
	}

	/**
	 * @param rs_ary 资源编码数组列表
	 */
	public void setRs_ary(String[] rs_ary) {
		this.rs_ary = rs_ary;
	}

	/**
	 * @return opt_priv 部门角色操作权限列表
	 */
	public List<UsRoleOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv 部门角色操作权限列表
	 */
	public void setOpt_priv(List<UsRoleOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}
}
