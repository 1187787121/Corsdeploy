/**
 * Title: UpdateUserAction.java
 * File Description: 更改用户信息
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
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.InputParamIsNullException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.exc.CanNotAddTellerException;
import com.wk.cd.system.exc.DprlAndUserWeightNotMatchException;
import com.wk.cd.system.exc.ValueBeyondLimitException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserInputBean;
import com.wk.cd.system.us.bean.UpdateUserOutputBean;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserOptPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.cd.system.us.service.UsDelUserPrivService;
import com.wk.lang.Inject;

/**
 * Class Description:更改用户信息
 * @author link
 */
public class UpdateUserAction
		extends ActionBasic<UpdateUserInputBean, UpdateUserOutputBean> {

	@Inject
	private UsUserDaoService daoService;
	@Inject
	private UsUserRoleDaoService daoUserRoleService;
	@Inject
	private UsDelUserPrivService delUserPrivService;
	@Inject
	private DpDeptDaoService deptDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private UsUserOptPrivDaoService usUserOptPrivDaoService;
	@Inject
	private UsUserRoleDaoService userRoleDaoService;

	/**
	 * Description:更改用户信息
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateUserOutputBean doAction(UpdateUserInputBean input) {
		UpdateUserOutputBean output = new UpdateUserOutputBean();
		UsUserInfo info = new UsUserInfo();
		Assert
				.assertNotEmpty(input.getUser_id(),
						UpdateUserInputBean.USER_IDCN);
		daoService.checkExistUserIdExist(input.getUser_id());
		// 检查部门角色编码列表和用户权重列表是否一一对应
		String[] dprl_list = input.getDprl_list();
		int[] user_weight_list = input.getUser_weight_list();
		int len = 0;
		if (!Assert.isEmpty(user_weight_list) && !Assert.isEmpty(dprl_list)) {

			if (dprl_list.length != user_weight_list.length) {
				throw new DprlAndUserWeightNotMatchException();
			} else {
				len = dprl_list.length;
			}
			for (int i = 0; i < len; i++) {
				if (Assert.isEmpty(dprl_list[i])) {
					throw new InputParamIsNullException().addScene("PARM",
							"用户部门角色列表");
				}
				if (user_weight_list[i] < 1 || user_weight_list[i] > 100) {
					throw new ValueBeyondLimitException().addScene("PARM1",
							"用户权重").addScene("PARM2", "1-100");
				}
				// 检查部门角色对应的权重是否在用户部门角色对应表中存在，如果存在，则需要报错
				userRoleDaoService.checkDprlWeghtExsit(input.getUser_id(),
						dprl_list[i], user_weight_list[i]);
			}
		}

		info.setUser_id(input.getUser_id());
		info.setUser_cn_name(input.getUser_cn_name());
		info.setEmail_add(input.getEmail_add());
		info.setPhone_no(input.getPhone_no());
		info.setBl_dept_id(input.getBl_dept_id());
		info.setUser_type(input.getUser_type());
		info.setTeller_no(input.getTeller_no());
		String first_dept_id = input.getFirst_dept_id();
		if (!Assert.isEmpty(first_dept_id)) {
			info.setFirst_dept_id(first_dept_id);
		} else {
			info.setFirst_dept_id("");
		}
		String secd_dept_id = input.getSecd_dept_id();
		if (!Assert.isEmpty(secd_dept_id)) {
			info.setSecd_dept_id(secd_dept_id);
		} else {
			info.setSecd_dept_id("");
		}
		String third_dept_id = input.getThird_dept_id();
		if (!Assert.isEmpty(third_dept_id)) {
			info.setThird_dept_id(third_dept_id);
		} else {
			info.setThird_dept_id("");
		}
		info.setModify_bk_date(input.getDtbs_bk_date());
		info.setModify_bk_time(input.getDtbs_bk_time());
		info.setModify_user_id(input.getOrg_user_id());
		info.setRcd_state(RCD_STATE.NORMAL);
		// 查询所有部门的机构号
		List<String> dept_list = new ArrayList<String>();
		DpDeptInfo bl_dept_info = deptDaoService.getDeptInfo(input
				.getBl_dept_id());
		String bl_dept_id_sbno = bl_dept_info.getBranch_no();
		String first_dept_id_sbno = "";
		DpDeptInfo first_dept_info = null;
		if (!Assert.isEmpty(input.getFirst_dept_id())) {
			first_dept_info = deptDaoService.getDeptInfo(input
					.getFirst_dept_id());
			first_dept_id_sbno = first_dept_info.getBranch_no();
		}
		if (Assert.isEmpty(bl_dept_id_sbno)
				&& Assert.isEmpty(first_dept_id_sbno)
				&& !Assert.isEmpty(input.getTeller_no())) {
			if (Assert.isEmpty(first_dept_info)) {
				throw new CanNotAddTellerException().addScene("PARM",
						bl_dept_info.getDept_cn_name() + "["
								+ input.getBl_dept_id() + "]");
			} else {
				throw new CanNotAddTellerException().addScene("PARM",
						bl_dept_info.getDept_cn_name() + "["
								+ input.getBl_dept_id() + "]" + " "
								+ first_dept_info.getDept_cn_name() + "["
								+ input.getFirst_dept_id() + "]");
			}
		}
		dept_list.add(bl_dept_id_sbno);
		if (!Assert.isEmpty(input.getFirst_dept_id())) {
			dept_list.add(first_dept_id_sbno);
		}
		daoService.updateUserByKey(info);
		if (Assert.isEmpty(input.getDept_flag()) && input.getDept_flag() == 1) {
			// 删除用户自己权限表中相应的权限
			delUserPrivService.deleteAllRsByUserId(input.getUser_id());
			delUserPrivService.deleteAllSocByUserId(input.getUser_id());
			delUserPrivService.deleteAllSqlByUserId(input.getUser_id());
			delUserPrivService.deleteAllColByUserId(input.getUser_id());
			usUserOptPrivDaoService.deleteOptPrivByUser(input.getUser_id());
		}
		
		if(len > 0){
			daoUserRoleService.deleteUserDprlByUserId(input.getUser_id());
		}
		for (int i = 0; i < len; i++) {
			UsUserRoleInfo dprl_info = new UsUserRoleInfo();
			dprl_info.setDprl_code(dprl_list[i]);
			dprl_info.setUser_id(input.getUser_id());
			dprl_info.setUser_bk_weight(user_weight_list[i]);
			userRoleDaoService.insertInfo(dprl_info);
		}
		return output;
	}

	/**
	 * Description:更改用户信息日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateUserInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getUser_id());
		lst_val.add(input.getUser_cn_name());
		lst_val.add(input.getUser_type().getCname());
		if (!Assert.isEmpty(input.getDprl_list())) {
			lst_val.add(input.getDprl_list().toString());
		}
		return lgsvc.getLogTxt("更新用户信息", lst_val);
	}

}
