/**
 * Title: QueryExtDprlListAction.java
 * File Description: 查询部门角色扩展信息列表
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-17
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.PageExtDprlListInputBean;
import com.wk.cd.system.us.bean.PageExtDprlListOutputBean;
import com.wk.cd.system.us.bean.UsExtDprlBean;
import com.wk.cd.system.us.dao.UsExtDprlDaoService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.lang.Inject;

/**
 * Class Description:查询部门角色扩展信息列表
 * @author link
 */
public class PageExtDprlListAction
		extends
		ActionBasic<PageExtDprlListInputBean, PageExtDprlListOutputBean> {

	@Inject
	private UsExtDprlDaoService daoService;
	@Inject
	private UsUserCombineDaoService combineDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:查询部门角色扩展信息列表
	 * @param input
	 * @return
	 */
	@Override
	protected PageExtDprlListOutputBean doAction(PageExtDprlListInputBean input) {
		PageExtDprlListOutputBean output = new PageExtDprlListOutputBean();
		List<UsExtDprlBean> dprl_ext_list = new ArrayList<UsExtDprlBean>();
		//查询发起用户类型
		String dept_id_str="";
		List<Integer> user_role_type_list = combineDaoService.queryRoleTypeByUserId(input.getOrg_user_id());
		if(!user_role_type_list.contains(1) && !user_role_type_list.contains(2)){
			dept_id_str = input.getOrg_dept_id();
		}
		dprl_ext_list = daoService.pageExtDprlList(input.getDept_cn_name(),dept_id_str,
				input.getStart_recd(), input.getLimit_recd());
		
		output.setDprl_list(dprl_ext_list);
		output.setAll_recd(daoService.countExtDprlList(input.getDept_cn_name(),dept_id_str));
		return output;
	}

	/**
	 * Description:查询部门角色扩展信息列表日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageExtDprlListInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("查询部门角色扩展信息列表日志信息", lst_val);
	}

}
