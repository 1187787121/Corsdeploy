/**
 * Title: QueryBsSerEnvListAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年3月27日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.build.en.bean.QueryBsSerEnvForAppInputBean;
import com.wk.cd.build.en.bean.QueryBsSerEnvForAppOutputBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.bean.SystemAppbean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询系统环境服务器列表
 * @author xuph
 */
public class QueryBsSerEnvForAppAction
		extends ActionBasic<QueryBsSerEnvForAppInputBean, QueryBsSerEnvForAppOutputBean> {
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private EnvironmentPublicService envPublicSrv;
	@Inject
	private CeEnvironmentDaoService ceEnvDaoSrv;
	@Inject
	private CeSystemDaoService ceSysDaoSrv;
	@Inject
	private CeServerDaoService ceSerDaoSrv;
	@Inject
	private SystemService systemService;
	@Inject
	private ServerCommonService serCommonSrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 查询系统环境服务器列表
	 * @param input
	 * @return
	 */
	@Override
	protected QueryBsSerEnvForAppOutputBean doAction(QueryBsSerEnvForAppInputBean input) {
		logger.info("---------------QueryBsSerEnvForAppAction begin----------------");
		QueryBsSerEnvForAppOutputBean output = new QueryBsSerEnvForAppOutputBean();
		// 环境
		List<CeEnvironmentInfo> env_lists = ceEnvDaoSrv.queryAllEnvMsg();
		List<EnvInfoBean> env_list = envPublicSrv.getEnvBeanListForApp(env_lists);
		// 系统
		List<CeSystemInfo> sys_lists = ceSysDaoSrv.getAllSystemInfo();
		List<SystemAppbean> sys_list = systemService.getSysListForApp(sys_lists);
		// 服务器
		List<CeServerInfo> ser_lists = ceSerDaoSrv.queryAllInfo();
		List<ServerBean> ser_list = serCommonSrv.getSerListForApp(ser_lists);
		output.setEnv_list(env_list);
		output.setSys_list(sys_list);
		output.setServer_list(ser_list);
		logger.info("---------------QueryBsSerEnvForAppAction end----------------");
		return output;
	}

	/**
	 * Description: log write
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryBsSerEnvForAppInputBean input) {
		List<String> log_msg = new ArrayList<String>();

		return lgsvc.getLogTxt("获得环境|系统|服务器列表", log_msg);
	}

}
