/**
 * Title: QueryExtDprlListOutputBean.java
 * File Description: 查询部门角色扩展信息列表输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-17
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 查询部门角色扩展信息列表输出接口
 * @author link
 */
public class PageExtDprlListOutputBean
		extends PageQueryActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6655644323446265750L;

	private List<UsExtDprlBean> dprl_list;
	public static final String DPRL_EXT_LISTCN = "部门角色扩展信息列表";

	/**
	 * @return dprl_list 部门角色扩展信息列表
	 */
	public List<UsExtDprlBean> getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list 部门角色扩展信息列表
	 */
	public void setDprl_list(List<UsExtDprlBean> dprl_list) {
		this.dprl_list = dprl_list;
	}

}
