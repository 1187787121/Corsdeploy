/**
 * Title: PageExtDprlListByDeptIdAction.java
 * File Description: 根据部门编码查询部门角色编码的相关详细信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.QueryExtDprlListByDeptIdInputBean;
import com.wk.cd.system.us.bean.QueryExtDprlListByDeptIdOutputBean;
import com.wk.cd.system.us.bean.UsExtDprlBean;
import com.wk.cd.system.us.dao.UsExtDprlDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description: 根据部门编码查询部门角色编码的相关详细信息
 * @author link
 */
public class QueryExtDprlListByDeptIdAction
		extends
		ActionBasic<QueryExtDprlListByDeptIdInputBean, QueryExtDprlListByDeptIdOutputBean> {

	@Inject
	private UsExtDprlDaoService daoService;
	@Inject
	private UsUserRoleDaoService usRoleDaoSrv;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:根据部门编码查询部门角色编码的相关详细信息
	 * @param input
	 * @return
	 */
	@Override
	protected QueryExtDprlListByDeptIdOutputBean doAction(
			QueryExtDprlListByDeptIdInputBean input) {
		QueryExtDprlListByDeptIdOutputBean output = new QueryExtDprlListByDeptIdOutputBean();
		String[] dept_arr = input.getDept_arr();
		Assert.assertNotEmpty(dept_arr, "所属部门");
		List<UsExtDprlBean> dprl_bean_list = new ArrayList<UsExtDprlBean>();
		List<UsUserRoleInfo> us_roles = new ArrayList<UsUserRoleInfo>();
		for (String dept_id : dept_arr) {
			if (dept_id != null) {
				List<UsExtDprlBean> temp_dprl_bean_list = daoService
						.queryExtDprlListByDept(dept_id);
				dprl_bean_list.addAll(temp_dprl_bean_list);
			}
		}
		if(!Assert.isEmpty(dprl_bean_list)){
			for(UsExtDprlBean u : dprl_bean_list){
				us_roles.addAll(usRoleDaoSrv.queryUserRoleByDprlCode(u.getDprl_code()));
			}
		}
		output.setDprl_bean_list(dprl_bean_list);
		output.setUs_roles(us_roles);
		return output;
	}

	/**
	 * Description:根据部门编码查询部门角色编码的相关详细信息日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryExtDprlListByDeptIdInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		String[] dept_arr = input.getDept_arr();
		Assert.assertNotEmpty(dept_arr, "所属部门");
		lst_val.add(dept_arr.toString());
		return lgsvc.getLogTxt("根据部门编码查询部门角色编码的相关详细信息日志信息", lst_val);
	}

}
