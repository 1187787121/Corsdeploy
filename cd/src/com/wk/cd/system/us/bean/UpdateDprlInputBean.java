/**
 * Title: UpdateDprlInputBean.java
 * File Description: 修改部门角色基础信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月7日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改部门角色基础信息输入接口
 * @author HT
 */
public class UpdateDprlInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 7039954040196484584L;

	/**
	 * 部门角色编码
	 */
	private String dprl_code;
	
	public static final String DPRL_CODECN = "部门角色编码";

	/**
	 * 部门角色说明
	 */
	private String bk_expl;
	
	public static final String BK_EXPLCN = "部门角色说明";

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
	 * @return bk_expl 部门角色说明
	 */
	public String getBk_expl() {
		return this.bk_expl;
	}

	/**
	 * @param bk_expl 部门角色说明
	 */
	public void setBk_expl(String bk_expl) {
		this.bk_expl = bk_expl;
	}
}
