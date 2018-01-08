/**
 * Title: QueryDprlAction.java
 * File Description: 部门角色查询
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-15
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.QueryDprlInputBean;
import com.wk.cd.system.us.bean.QueryDprlOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description:部门角色查询
 * @author link
 */
public class QueryDprlAction
		extends ActionBasic<QueryDprlInputBean, QueryDprlOutputBean> {

	@Inject
	private UsDeptRoleDaoService daoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:部门角色查询
	 * @param input
	 * @return
	 */
	@Override
	protected QueryDprlOutputBean doAction(QueryDprlInputBean input) {
		QueryDprlOutputBean output = new QueryDprlOutputBean();
		List<UsDeptRoleInfo> dprl_list = new ArrayList<UsDeptRoleInfo>();
		dprl_list = daoService.queryDprl();
		output.setDprl_list(dprl_list);
		return output;
	}

	/**
	 * Description:部门角色查询日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryDprlInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("查询部门角色日志信息", lst_val);
	}

}
