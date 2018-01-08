/**
 * Title: UpdateUserEnvPrivAction.java
 * File Description: 修改用户应用环境权限
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017年1月4日
 */
package com.wk.cd.system.ep.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.ep.bean.UpdateUserEnvPrivInputBean;
import com.wk.cd.system.ep.bean.UpdateUserEnvPrivOutputBean;
import com.wk.cd.system.ep.dao.UsUserEnvPrivDaoService;
import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户应用环境权限
 * @author HT
 */
public class UpdateUserEnvPrivAction
		extends ActionBasic<UpdateUserEnvPrivInputBean, UpdateUserEnvPrivOutputBean> {

	@Inject
	private EnvPrivService envPrivService;
	@Inject UsUserEnvPrivDaoService usUserEnvPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 修改用户应用环境权限
	 * @param arg0
	 * @return
	 */
	@Override
	protected UpdateUserEnvPrivOutputBean doAction(UpdateUserEnvPrivInputBean input) {
		logger.info("------UpdateUserEnvPrivAction begin------");
		logger.debug("------user_id=[{}]", input.getUser_id());
		UpdateUserEnvPrivOutputBean output =new UpdateUserEnvPrivOutputBean();
		String user_id = input.getUser_id();
		List<UsUserEnvPrivInfo> env_list = input.getEnv_list();
		Assert.assertNotEmpty(user_id, UpdateUserEnvPrivInputBean.USER_IDCN);

		
		envPrivService.deleteUserEnvPriv(user_id);
		// 修改
		if (!Assert.isEmpty(env_list) && env_list.size() != 0) {
			for (UsUserEnvPrivInfo env_priv : env_list) {
				Assert.assertNotEmpty(env_priv.getEnv_name(), UsUserEnvPrivInfo.ENV_NAMECN);
				Assert.assertNotEmpty(env_priv.getSys_name(), UsUserEnvPrivInfo.SYS_NAMECN);
				Assert.assertNotEmpty(env_priv.getAf_flag(), UsUserEnvPrivInfo.AF_FLAGCN);
				env_priv.setUser_id(user_id);
				env_priv.setPriv_type(PRIV_TYPE.PERPETUAL);
				usUserEnvPrivDaoService.insertInfo(env_priv);
			}
		}
		
		logger.info("------UpdateUserEnvPrivAction begin------");
		return output;
	}

	/**
	 * Description: 写日志
	 * @param arg0
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateUserEnvPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		List<UsUserEnvPrivInfo> env_list = input.getEnv_list();
		if (!Assert.isEmpty(env_list)) {
			for (UsUserEnvPrivInfo priv : env_list) {
				log_lst.add(priv.getEnv_name() + " af_flag:" + priv.getAf_flag());
			}
		}
		return lgsvc.getLogTxt("修改用户应用环境权限信息", log_lst);
	}

}
