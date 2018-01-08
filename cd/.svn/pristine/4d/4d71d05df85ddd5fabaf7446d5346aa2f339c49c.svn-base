/**
 * Title: QueryUserByUserIdAction.java
 * File Description: 根据用户名查询用户的详细信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.exc.NotSupportDiffDeptException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.QueryUserByUserIdInputBean;
import com.wk.cd.system.us.bean.QueryUserByUserIdOutputBean;
import com.wk.cd.system.us.bean.UsExtUserBean;
import com.wk.cd.system.us.dao.UsExtUserDaoService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description:根据用户名查询用户的详细信息
 * @author link
 */
public class QueryUserByUserIdAction
		extends
		ActionBasic<QueryUserByUserIdInputBean, QueryUserByUserIdOutputBean> {

	@Inject
	private UsUserDaoService daoService;
	@Inject
	private UsExtUserDaoService daoExtService;
	@Inject
	private UsUserCombineDaoService combineDaoService;
	@Inject
	DpDeptDaoService deptDaoService;
	@Inject
	private UsUserRoleDaoService usRoleDaoSrv;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: 根据用户名查询用户的详细信息
	 * @param input
	 * @return
	 */
	@Override
	protected QueryUserByUserIdOutputBean doAction(
			QueryUserByUserIdInputBean input) {
		QueryUserByUserIdOutputBean output = new QueryUserByUserIdOutputBean();
		UsUserInfo info = new UsUserInfo();
		List<UsUserRoleInfo> us_roles = new ArrayList<UsUserRoleInfo>();
		Assert.assertNotEmpty(input.getUser_id(),
				QueryUserByUserIdInputBean.USER_IDCN);
		info = daoService.queryDetailByUserId(input.getUser_id());
		//如果不是超级管理员和信息管理员，需要验证上下级关系
		List<Integer> role_list = combineDaoService.queryRoleTypeByUserId(input.getOrg_user_id());
		if(!role_list.contains(1) && !role_list.contains(2) && !"3400008851".equals(input.getBr_sbsbno())){
			//所有的下级部门编号
			List<String> dept_list = deptDaoService.querySubDeptIdByKey(input.getOrg_user_id());
			UsUserInfo user_info = daoService.getInfoByKey(input.getUser_id());
			UsUserInfo user_info1 = daoService.getInfoByKey(input.getOrg_user_id());
			dept_list.add(input.getOrg_dept_id());
			if(!Assert.isEmpty(user_info1.getFirst_dept_id())){
				dept_list.add(user_info1.getFirst_dept_id());
			}
			if(!Assert.isEmpty(user_info1.getSecd_dept_id())){
				dept_list.add(user_info1.getSecd_dept_id());
			}
			if(!Assert.isEmpty(user_info1.getThird_dept_id())){
				dept_list.add(user_info1.getThird_dept_id());
			}
			if(!dept_list.contains(user_info.getBl_dept_id())){
				throw new NotSupportDiffDeptException(); 
			}
		}
		UsExtUserBean user_bean = new UsExtUserBean();
		List<UsDeptRoleInfo> dprl_list = new ArrayList<UsDeptRoleInfo>();
		dprl_list = daoExtService.queryExtUserDprlListByUserId(input
				.getUser_id());
		if(!Assert.isEmpty(dprl_list)){
			for(UsDeptRoleInfo o : dprl_list){
				us_roles.addAll(usRoleDaoSrv.queryUserRoleByDprlCode(o.getDprl_code()));
			}
		}
		user_bean.setUser_id(info.getUser_id());
		user_bean.setUser_cn_name(info.getUser_cn_name());
		user_bean.setPwdexp_bk_date(info.getPwdexp_bk_date());
		user_bean.setEmail_add(info.getEmail_add());
		user_bean.setPhone_no(info.getPhone_no());
		user_bean.setBl_dept_id(info.getBl_dept_id());
		user_bean.setDept_cn_name(daoExtService
				.queryExtUserDeptNameByUserId(input.getUser_id()));
		user_bean.setTeller_no(info.getTeller_no());
		user_bean.setUser_type(info.getUser_type());
		user_bean.setFirst_dept_id(info.getFirst_dept_id());
		user_bean.setFirst_dept_cn_name(daoExtService
				.queryExtUserFirstDeptByUserId(input.getUser_id()));
		user_bean.setDprl_list(dprl_list);
		
		output.setUser_bean(user_bean);
		output.setUs_roles(us_roles);
		return output;
	}

	/**
	 * Description: 根据用户名查询用户的详细信息日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryUserByUserIdInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getUser_id());
		return lgsvc.getLogTxt("根据用户名查询用户的详细信息", lst_val);
	}

}
