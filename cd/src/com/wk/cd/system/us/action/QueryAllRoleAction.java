/**
 * Title: QueryAllRoleAction.java
 * File Description:查询所有角色信息列表 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-2-3
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.QueryAllRoleInputBean;
import com.wk.cd.system.us.bean.QueryAllRoleOutputBean;
import com.wk.cd.system.us.dao.UsRoleDaoService;
import com.wk.cd.system.us.info.UsRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description:查询所有角色信息列表
 * @author link
 */
public class QueryAllRoleAction
		extends ActionBasic<QueryAllRoleInputBean, QueryAllRoleOutputBean> {
	@Inject
	private UsRoleDaoService daoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:查询所有角色信息列表
	 * @param input
	 * @return
	 */
	@Override
	protected QueryAllRoleOutputBean doAction(QueryAllRoleInputBean input) {
		QueryAllRoleOutputBean output = new QueryAllRoleOutputBean();
		List<UsRoleInfo> role_list = new ArrayList<UsRoleInfo>();
		role_list = daoService.queryAllRole();
		output.setRole_list(role_list);
		return output;
	}

	/**
	 * Description:查询所有角色信息列表日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryAllRoleInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsvc.getLogTxt("查询所有角色实体列表", lst_val);
	}

}
