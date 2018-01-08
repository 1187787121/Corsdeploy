/**
 * Title: PageExtUserListAction.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-20
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.PageExtUserListInputBean;
import com.wk.cd.system.us.bean.PageExtUserListOutputBean;
import com.wk.cd.system.us.bean.UsExtUserBean;
import com.wk.cd.system.us.dao.UsExtUserDaoService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author link
 */
public class PageExtUserListAction
		extends
		ActionBasic<PageExtUserListInputBean, PageExtUserListOutputBean> {
	@Inject
	private UsExtUserDaoService daoService;
	@Inject
	private UsUserDaoService usrdaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private UsUserCombineDaoService combineDaoService;
	@Inject
	DpDeptDaoService deptDaoService;
	

	/**
	 * Description:查询所属部门的用户信息，以及根据用户名模糊查询用户
	 * @param input
	 * @return
	 */
	@Override
	protected PageExtUserListOutputBean doAction(PageExtUserListInputBean input) {
		PageExtUserListOutputBean output = new PageExtUserListOutputBean();
		List<UsExtUserBean> user_list = new ArrayList<UsExtUserBean>();
		//查询用户类型列表
		String dept_id_str="";
		List<Integer> user_role_type_list = combineDaoService.queryRoleTypeByUserId(input.getOrg_user_id());
		if(!user_role_type_list.contains(1) && !user_role_type_list.contains(2)){
			List<String> dept_list = deptDaoService.querySubDeptIdByKey(input.getOrg_user_id());
			UsUserInfo user_info1 = usrdaoService.getInfoByKey(input.getOrg_user_id());
			if(!Assert.isEmpty(user_info1.getFirst_dept_id())){
				dept_list.add(user_info1.getFirst_dept_id());
			}
			if(!Assert.isEmpty(user_info1.getSecd_dept_id())){
				dept_list.add(user_info1.getSecd_dept_id());
			}
			if(!Assert.isEmpty(user_info1.getThird_dept_id())){
				dept_list.add(user_info1.getThird_dept_id());
			}
			dept_list.add(input.getOrg_dept_id());
			dept_id_str = listToString(dept_list);
		}
		user_list = daoService.pageUserByBlDeptId(dept_id_str,input.getUser_cn_name(),
				input.getStart_recd(), input.getLimit_recd());
		int all_recd = daoService.countUserByBlDeptId(dept_id_str,input.getUser_cn_name());
		output.setUser_list(user_list);
		output.setAll_recd(all_recd);
		return output;
	}

	/**
	 * Description:查询所属部门及下级部门的用户信息，以及根据用户名模糊查询用户日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageExtUserListInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_dept_id());
		if (!Assert.isEmpty(input.getUser_cn_name())) {
			lst_val.add(input.getUser_cn_name());
		}
		return lgsvc.getLogTxt("查询所属部门及下级部门的用户信息，以及根据用户名模糊查询用户日志信息", lst_val);
	}
	
	/**
	 * 将输入的列表转换为字符串，例如列表中为a b c，转换后则为('a','b','c')
	 * @param list 输入列表
	 * @return 字符串
	 */
	private String listToString(List<String> list) {
		String str = "";
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "输入信息");
		}
		for (String s : list) {
			str = str + s + "','";
		}
		str = "('" + str.substring(0, str.length() - 2) + ")";
		return str;
	}

}
