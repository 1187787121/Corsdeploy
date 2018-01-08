/**
 * Title: QueryRsOptPrivOutputBean.java
 * File Description: 查询用户资源操作权限输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年5月15日
 */
package com.wk.cd.system.rs.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * @author HT
 */
public class QueryRsOptPrivOutputBean extends ActionRootOutputBean{
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3949277571218393140L;
	
	/**
	 * 资源操作权限列表
	 */
	private List<RsOptPrivBean> opt_list;
	
	public static final String OPT_LISTCN = "资源操作权限列表";

	/**
	 * @return opt_list 资源操作权限列表
	 */
	public List<RsOptPrivBean> getOpt_list() {
		return this.opt_list;
	}

	/**
	 * @param opt_list 资源操作权限列表
	 */
	public void setOpt_list(List<RsOptPrivBean> opt_list) {
		this.opt_list = opt_list;
	}
}
