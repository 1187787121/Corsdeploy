/**
 * Title: UpdateDprlSocPrivInputBean.java
 * File Description: 修改部门角色数据源权限信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月7日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改部门角色数据源权限信息输入接口
 * @author HT
 */
public class UpdateDprlSocPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -1630735601776084048L;
	
	/**
	 * 部门角色编码
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "部门角色编码_V";

	/**
	 * 数据源编码列表
	 */
	private String[] soc_ary;

	public static final String SOC_ARYCN = "数据源编码列表_V";

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
	 * @return soc_ary 数据源编码列表
	 */
	public String[] getSoc_ary() {
		return this.soc_ary;
	}

	/**
	 * @param soc_ary 数据源编码列表
	 */
	public void setSoc_ary(String[] soc_ary) {
		this.soc_ary = soc_ary;
	}
}
