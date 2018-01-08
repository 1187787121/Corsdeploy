/**
 * Title: UpdateDeptEnvPrivAction.java
 * File Description: 修改部门应用环境权限
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017年1月4日
 */
package com.wk.cd.system.ep.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.bean.UpdateDeptEnvPrivInputBean;
import com.wk.cd.system.ep.bean.UpdateDeptEnvPrivOutputBean;
import com.wk.cd.system.ep.dao.DpDeptEnvPrivDaoService;
import com.wk.cd.system.ep.info.DpDeptEnvPrivInfo;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门应用环境权限
 * @author HT
 */
public class UpdateDeptEnvPrivAction
		extends ActionBasic<UpdateDeptEnvPrivInputBean, UpdateDeptEnvPrivOutputBean> {

	@Inject
	private EnvPrivService envPrivService;
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private DpDeptEnvPrivDaoService dpDeptEnvPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 修改部门应用环境权限
	 * @param arg0
	 * @return
	 */
	@Override
	protected UpdateDeptEnvPrivOutputBean doAction(UpdateDeptEnvPrivInputBean input) {
		logger.info("------UpdateDeptEnvPrivAction begin------");
		logger.debug("------dept_id=[{}]", input.getDept_id());

		UpdateDeptEnvPrivOutputBean output = new UpdateDeptEnvPrivOutputBean();
		String dept_id = input.getDept_id();
		List<EnvPrivBean> env_list = input.getEnv_list();
		Assert.assertNotEmpty(dept_id, UpdateDeptEnvPrivInputBean.DEPT_IDCN);

		List<String> env_strs = new ArrayList<String>();
		for (EnvPrivBean env_priv : env_list) {
			env_strs.add(env_priv.getEnv_name());
		}
		// 根据部门编码查找部门角色
		List<String> dprl_list = usDeptRoleDaoService.queryRoleByDept(dept_id);

		// 查找部门下所有角色的环境权限
		List<String> dprl_env_list = envPrivService.queryEnvPrivByDprls(dprl_list);

		// 要更改的环境列表里没有所有的该部门下角色环境 则报错
		for (String env_name : dprl_env_list) {
			if (Assert.isEmpty(env_list)) {
				throw new IllegalOperaterException().addScene("PARM1", "部门应用环境权限[" + env_name + "]").addScene("PARM2", ",存在部门角色分配该应用环境权限");
			} else {
				if (!env_strs.contains(env_name.trim())) {
					throw new IllegalOperaterException().addScene("PARM1", "部门应用环境权限[" + env_name + "]").addScene("PARM2", "存在部门角色分配该应用环境权限");
				}
			}
		}

		// 查询下级部门列表
		List<String> sub_dept_ids = dpPublicService.querySubDeptIdsByDeptId(dept_id);

		// *查询下级部门的环境权限，若要更改的环境权限有被使用则报错
		List<String> dept_env_list = envPrivService.queryEnvPrivByDepts(sub_dept_ids);
		for (String env_name : dept_env_list) {
			if (Assert.isEmpty(env_list)) {
				throw new IllegalOperaterException().addScene("PARM1", "部门应用环境权限[" + env_name + "]").addScene("PARM2", ",存在下级部门分配该应用环境权限");
			}
			if (!env_strs.contains(env_name.trim())) {
				throw new IllegalOperaterException().addScene("PARM1", "部门应用环境权限[" + env_name + "]").addScene("PARM2", ",存在下级部门分配该应用环境权限");
			}
		}

		// 根据部门编码查找部门下用户
		List<String> user_list = usUserDaoService.queryUserByBlDeptId(dept_id);

		// 查询部门用户的环境权限，若要更改的环境权限有被使用则报错
		List<String> user_env_list = envPrivService.queryEnvPrivByUserId(user_list);
		for (String env_name : user_env_list) {
			if (Assert.isEmpty(env_list)) {
				throw new IllegalOperaterException().addScene("PARM1", "部门应用环境权限[" + env_name + "]").addScene("PARM2", ",存在用户分配该应用环境权限");
			}
			if (!env_strs.contains(env_name.trim())) {
				throw new IllegalOperaterException().addScene("PARM1", "部门应用环境权限[" + env_name + "]").addScene("PARM2", ",存在用户分配该应用环境权限");
			}
		}

		envPrivService.deleteDeptEnvPriv(dept_id);
		// 修改
		if (!Assert.isEmpty(env_list) && env_list.size() != 0) {
			for (EnvPrivBean priv : env_list) {
				DpDeptEnvPrivInfo envPrivInfo = new DpDeptEnvPrivInfo();
				envPrivInfo.setDept_id(dept_id);
				envPrivInfo.setEnv_name(priv.getEnv_name());
				envPrivInfo.setSys_name(priv.getSys_name());
				dpDeptEnvPrivDaoService.insertInfo(envPrivInfo);
			}
		}

		logger.info("------UpdateDeptEnvPrivAction begin------");
		return output;
	}

	/**
	 * Description: 记录日志
	 * @param arg0
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateDeptEnvPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		List<EnvPrivBean> env_list = input.getEnv_list();
		if (!Assert.isEmpty(env_list)) {
			for (EnvPrivBean priv : env_list) {
				log_lst.add(priv.getEnv_name());
			}
		}
		return lgsvc.getLogTxt("修改部门应用环境权限信息", log_lst);
	}
}
