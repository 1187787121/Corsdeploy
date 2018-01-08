/**
 * Title: AddCeEnvironmentAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.AddEnvironmentInputBean;
import com.wk.cd.build.en.bean.AddEnvironmentOutputBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.system.ep.dao.DpDeptEnvPrivDaoService;
import com.wk.cd.system.ep.dao.UsUserEnvPrivDaoService;
import com.wk.cd.system.ep.info.DpDeptEnvPrivInfo;
import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 添加应用环境
 * @author xuph
 */
public class AddEnvironmentAction
		extends ActionBasic<AddEnvironmentInputBean, AddEnvironmentOutputBean> {
	@Inject
	private EnvironmentPublicService cePublishService;
	@Inject
	private ServerCommonService serverCommonService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private UsUserEnvPrivDaoService userEnvPrivDaoService;
	@Inject
	private DpDeptEnvPrivDaoService dpDeptEnvPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 添加应用环境操作
	 * @param input
	 * @return
	 */
	@Override
	protected AddEnvironmentOutputBean doAction(AddEnvironmentInputBean input) {
		logger.info("---------------------AddCeEnvironmentAction Begin-------------");
		AddEnvironmentOutputBean output = new AddEnvironmentOutputBean();
		String user_id = input.getOrg_user_id();
		String dept_id = input.getOrg_dept_id();

		String env_name = input.getEnv_name();
		String sys_name = input.getSys_name();
		String env_cn_name = input.getEnv_cn_name();
		String env_bk_desc = input.getEnv_bk_desc();
		DT_RANGE dt_range = input.getDt_range();
		String[] ele_types = input.getEle_type();
		ENV_TYPE env_type = input.getEnv_type();
		List<ServerBean> server_list = input.getServer_list();
		// 非空校检
		Assert.assertNotEmpty(user_id, AddEnvironmentInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(dept_id, AddEnvironmentInputBean.ORG_DEPT_IDCN);
		Assert.assertNotEmpty(env_name, AddEnvironmentInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(sys_name, AddEnvironmentInputBean.SYS_NAMECN);
		Assert.assertNotEmpty(env_cn_name, AddEnvironmentInputBean.ENV_CN_NAMECN);
		Assert.assertNotEmpty(env_bk_desc, AddEnvironmentInputBean.ENV_BK_DESCCN);
		Assert.assertNotEmpty(ele_types, AddEnvironmentInputBean.ELE_TYPECN);
		Assert.assertNotEmpty(env_type.getValue() == 0 ? null : env_type.getValue(), AddEnvironmentInputBean.ENV_TYPECN);
		// 校检数据范围字段
		cePublishService.checkDtRangeIsNotEmpty(dt_range, ele_types);
		// 合法性校检
		cePublishService.checkEnvNameNotExist(env_name);
		// 构成要素
		String ele_type = cePublishService.getEleTypeStr(ele_types);
		// 应用环境数据
		CeEnvironmentInfo envir_info = new CeEnvironmentInfo();
		envir_info.setEnv_name(env_name);
		envir_info.setEnv_cn_name(env_cn_name);
		envir_info.setEle_type(ele_type);
		envir_info.setEnv_bk_desc(env_bk_desc);
		envir_info.setDt_range(dt_range);
		envir_info.setEnv_type(env_type);
		envir_info.setSys_name(sys_name);
		envir_info.setCreate_bk_date(input.getDtbs_bk_date());
		envir_info.setCreate_bk_time(input.getDtbs_bk_time());
		envir_info.setCreate_user_id(input.getOrg_user_id());
		logger.info("新增应用环境 Env_name=[{}]", env_name);
		// 环境服务器关联
		List<CeEnvironmentServerInfo> envir_servers = cePublishService.getCeEnvServerList(server_list, env_name);
		// 插入应用环境表
		ceEnvironmentDaoService.insertInfo(envir_info);
		// 插入环境服务关联表
		ceEnvironmentServerDaoService.insertListInfo(envir_servers);

		// 添加环境用户部门权限
		DpDeptEnvPrivInfo deptEnvPrivInfo = new DpDeptEnvPrivInfo();
		deptEnvPrivInfo.setDept_id(dept_id);
		deptEnvPrivInfo.setEnv_name(env_name);
		deptEnvPrivInfo.setSys_name(sys_name);
		dpDeptEnvPrivDaoService.insertInfo(deptEnvPrivInfo);
		// 添加环境用户权限
		UsUserEnvPrivInfo userEnvPrivInfo = new UsUserEnvPrivInfo();
		userEnvPrivInfo.setUser_id(user_id);
		userEnvPrivInfo.setEnv_name(env_name);
		userEnvPrivInfo.setSys_name(sys_name);
		userEnvPrivInfo.setPriv_type(PRIV_TYPE.PERPETUAL);
		userEnvPrivInfo.setAf_flag(AF_FLAG.ALLOW);
		userEnvPrivDaoService.insertInfo(userEnvPrivInfo);
		logger.info("---------------------AddCeEnvironmentAction End---------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddEnvironmentInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("Env_name=" + input.getEnv_name());
		lst_val.add("Env_cn_name=" + input.getEnv_cn_name());
		return lgsvc.getLogTxt("新增应用环境", lst_val);
	}

}
