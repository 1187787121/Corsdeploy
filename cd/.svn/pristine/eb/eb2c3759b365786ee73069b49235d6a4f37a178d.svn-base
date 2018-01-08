/**
 * Title: UpdateDeptRsOptPrivInputBean.java
 * File Description: 修改部门资源及操作权限信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月27日
 */
package com.wk.cd.system.dp.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;

/**
 * Class Description: 修改部门资源及操作权限信息输入接口
 * @author HT
 */
public class UpdateDeptRsOptPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -2974603586156282241L;
	
	/**
	 * 部门编码
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "部门编码";

	/**
	 * 资源编码数组列表
	 */
	private String[] rs_ary;

	public static final String RS_ARYCN = "资源编码数组列表";
	
	/**
	 * 部门操作权限列表
	 */
	private List<DpDeptOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "部门操作权限列表";

	/** 
	 * @return dept_id 部门编码
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id 部门编码
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
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
	 * @return opt_priv 部门操作权限列表
	 */
	public List<DpDeptOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv 部门操作权限列表
	 */
	public void setOpt_priv(List<DpDeptOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}
}
