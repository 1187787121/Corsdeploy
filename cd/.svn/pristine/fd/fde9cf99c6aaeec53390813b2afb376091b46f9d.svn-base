/**
 * Title: ViewEnvPrivAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017年1月4日
 */
package com.wk.cd.system.ep.action;

import java.util.List;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.bean.ViewEnvPrivInputBean;
import com.wk.cd.system.ep.bean.ViewEnvPrivOutputBean;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author HT
 */
public class ViewEnvPrivAction
		extends IViewActionBasic<ViewEnvPrivInputBean, ViewEnvPrivOutputBean> {

	@Inject
	private EnvPrivService envPrivService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;

	/**
	 * Description: 查询部门应用环境权限
	 * @param input
	 * @return
	 */
	public ViewEnvPrivOutputBean getDeptEnvPriv(ViewEnvPrivInputBean input) {
		ViewEnvPrivOutputBean output = new ViewEnvPrivOutputBean();
		String dept_id = input.getDept_id();
		Assert.assertNotEmpty(dept_id, ViewEnvPrivInputBean.DEPT_IDCN);
		List<EnvPrivBean> env_list = envPrivService.queryDeptEnvPriv(dept_id);

		DpDeptInfo info = new DpDeptInfo();
		info.setDept_id(dept_id);
		DpDeptInfo dept = dpDeptDaoService.getInfoByKey(info);
		if (dept.getDept_level() != 1) {// 有上级
			String super_dept_id = dept.getSuper_dept_id();
			// 查询上级部门应用环境权限
			List<EnvPrivBean> sup_env_list = envPrivService.queryDeptEnvPriv(super_dept_id);
			output.setSup_env_list(sup_env_list);
		} else {// 无上级 查询所有权限
			// 查询所有应用环境
			List<EnvPrivBean> sup_env_list = envPrivService.queryAllEnvPriv();
			output.setSup_env_list(sup_env_list);
		}
		output.setEnv_list(env_list);
		return output;
	}

	/**
	 * Description: 查询部门角色应用环境权限
	 * @param input
	 * @return
	 */
	public ViewEnvPrivOutputBean getDprlEnvPriv(ViewEnvPrivInputBean input) {
		ViewEnvPrivOutputBean output = new ViewEnvPrivOutputBean();
		String dprl_code = input.getDprl_code();
		Assert.assertNotEmpty(dprl_code, ViewEnvPrivInputBean.DPRL_CODECN);
		List<EnvPrivBean> env_list = envPrivService.queryDprlEnvPriv(dprl_code);

		UsDeptRoleInfo info = new UsDeptRoleInfo();
		info.setDprl_code(dprl_code);
		info = usDeptRoleDaoService.getInfoByKey(info);
		String super_dept_id = info.getDept_id();
		// 查询上级部门应用环境权限
		List<EnvPrivBean> sup_env_list = envPrivService.queryDeptEnvPriv(super_dept_id);
		output.setSup_env_list(sup_env_list);
		output.setEnv_list(env_list);
		return output;
	}

	/**
	 * Description: 查询用户应用环境权限
	 * @param input
	 * @return
	 */
	public ViewEnvPrivOutputBean getUserEnvPriv(ViewEnvPrivInputBean input) {
		ViewEnvPrivOutputBean output = new ViewEnvPrivOutputBean();
		String user_id = input.getUser_id();
		Assert.assertNotEmpty(user_id, ViewEnvPrivInputBean.USER_IDCN);

		// 查询用户应用环境权限
		List<EnvPrivBean> env_list = envPrivService.getUserEnvPriv(user_id);
		output.setEnv_list(env_list);
		// 查询部门角色应用环境权限
		List<EnvPrivBean> dr_env_list = envPrivService.getDprlEnvPrivByUser(user_id);
		output.setDr_env_list(dr_env_list);

		UsUserInfo info = new UsUserInfo();
		info.setUser_id(user_id);
		info = usUserDaoService.getInfoByKey(info);
		String super_dept_id = info.getBl_dept_id();

		// 查询上级部门应用环境权限
		List<EnvPrivBean> sup_env_list = envPrivService.queryDeptEnvPriv(super_dept_id);
		output.setSup_env_list(sup_env_list);

		return output;
	}
}
