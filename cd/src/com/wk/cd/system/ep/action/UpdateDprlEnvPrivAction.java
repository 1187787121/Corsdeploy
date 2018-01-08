/**
 * Title: UpdateDprlEnvPrivAction.java
 * File Description: 修改部门角色应用环境权限
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
import com.wk.cd.system.ep.bean.UpdateDprlEnvPrivInputBean;
import com.wk.cd.system.ep.bean.UpdateDprlEnvPrivOutputBean;
import com.wk.cd.system.ep.dao.UsRoleEnvPrivDaoService;
import com.wk.cd.system.ep.info.UsRoleEnvPrivInfo;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门角色应用环境权限
 * @author HT
 */
public class UpdateDprlEnvPrivAction extends ActionBasic<UpdateDprlEnvPrivInputBean, UpdateDprlEnvPrivOutputBean>{
	@Inject
	private EnvPrivService envPrivService;
	@Inject
	private UsRoleEnvPrivDaoService usRoleEnvPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门角色应用环境权限
	 * @param arg0
	 * @return 
	 */
	@Override
	protected UpdateDprlEnvPrivOutputBean doAction(UpdateDprlEnvPrivInputBean input) {
		logger.info("------UpdateDprlEnvPrivAction begin------");
		logger.debug("------dprl_code=[{}]", input.getDprl_code());
		UpdateDprlEnvPrivOutputBean output =new UpdateDprlEnvPrivOutputBean();
		String dprl_code = input.getDprl_code();
		List<EnvPrivBean> env_list = input.getEnv_list();
		Assert.assertNotEmpty(dprl_code, UpdateDprlEnvPrivInputBean.DPRL_CODECN);

		
		envPrivService.deleteDprlEnvPriv(dprl_code);
		// 修改
		if (!Assert.isEmpty(env_list) && env_list.size() != 0) {
			for (EnvPrivBean env_priv : env_list) {
				UsRoleEnvPrivInfo envPrivInfo = new UsRoleEnvPrivInfo();
				envPrivInfo.setDprl_code(dprl_code);
				envPrivInfo.setEnv_name(env_priv.getEnv_name());
				envPrivInfo.setSys_name(env_priv.getSys_name());
				usRoleEnvPrivDaoService.insertInfo(envPrivInfo);
			}
		}
		
		logger.info("------UpdateDprlEnvPrivAction begin------");
		return output;
	}

	/** 
	 * Description: 写日志
	 * @param arg0
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDprlEnvPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		List<EnvPrivBean> env_list = input.getEnv_list();
		if (!Assert.isEmpty(env_list)) {
			for (EnvPrivBean priv : env_list) {
				log_lst.add(priv.getEnv_name());
			}
		}
		return lgsvc.getLogTxt("修改部门角色应用环境权限信息", log_lst);
	}
}
