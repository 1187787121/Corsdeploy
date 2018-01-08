/**
 * Title: QueryDprlOutputBean.java
 * File Description: 查询部门角色输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-15
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsDeptRoleInfo;

/**
 * Class Description:查询部门角色输出接口
 * @author link
 */
public class QueryDprlOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 5645769350046839090L;

	private List<UsDeptRoleInfo> dprl_list;
	public static final String DPRL_LISTCN = "部门角色列表";

	/**
	 * @return dprl_list 部门角色列表
	 */
	public List<UsDeptRoleInfo> getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list 部门角色列表
	 */
	public void setDprl_list(List<UsDeptRoleInfo> dprl_list) {
		this.dprl_list = dprl_list;
	}

}
