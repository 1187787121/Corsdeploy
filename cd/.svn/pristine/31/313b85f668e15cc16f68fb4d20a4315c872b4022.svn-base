/**
 * Title: ViewEnvirAction.java
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

import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.bean.ViewEnvirInputBean;
import com.wk.cd.build.en.bean.ViewEnvirOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.SERVER_TYPE;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.service.IViewActionBasic;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.BeanCopy;
import com.wk.util.DeepBeanCopier;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 应用环境
 * @author xuph
 */
public class ViewEnvirAction
		extends IViewActionBasic<ViewEnvirInputBean, ViewEnvirOutputBean> {
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeServerDaoService ceServiceDaoService;
	@Inject
	private EnvironmentPublicService cePublicService;
	@Inject
	private EnvPrivService envPrivService;
	private static final Log logger = LogFactory.getLog();

	@Inject
	private DeepBeanCopier<CeServerInfo, CeServerInfo> deepBeanCopier;
	
	/**
	 * Description:查看单个应用环境信息
	 * @param input
	 * @return
	 */
	public ViewEnvirOutputBean getEnvironmentMsg(ViewEnvirInputBean input) {
		ViewEnvirOutputBean output = new ViewEnvirOutputBean();
		String env_name = input.getEnv_name();
		String user_id = input.getOrg_user_id();
		// 非空校检
		Assert.assertNotEmpty(env_name, ViewEnvirInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(user_id, ViewEnvirInputBean.ORG_USER_IDCN);
		// 合法性校检
		cePublicService.checkEnvNameIsExist(env_name);
		CeEnvironmentInfo envir_info = ceEnvironmentDaoService.getInfoByEnvName(env_name);
		output.setEnv_name(env_name);
		output.setEnv_cn_name(envir_info.getEnv_cn_name());
		output.setEle_type(cePublicService.generateEleTypeList(envir_info.getEle_type()));
		output.setEnv_bk_desc(envir_info.getEnv_bk_desc());
		output.setEnv_type(envir_info.getEnv_type());
		output.setDt_range(envir_info.getDt_range());
		output.setSys_name(envir_info.getSys_name());
		List<ServerBean> server_list = new ArrayList<ServerBean>();
		List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(env_name);
		if (!Assert.isEmpty(envir_servers)) {
			for (CeEnvironmentServerInfo serv : envir_servers) {
				ServerBean server = new ServerBean();
				server.setServer_name(serv.getServer_name());
				CeServerInfo servers = ceServiceDaoService.getInfoByServerName(serv.getServer_name());
				if (!Assert.isEmpty(servers)) {
					server.setServer_ip(servers.getServer_ip());
					server.setServer_cn_name(servers.getServer_cn_name());
				}
				server.setServer_type(serv.getServer_type());
				server_list.add(server);
			}
		}
		if (envPrivService.hasUserEnvPriv(user_id, env_name)) {
			output.setAf_flag(AF_FLAG.ALLOW);
		} else {
			output.setAf_flag(AF_FLAG.FORBID);
		}
		output.setServer_list(server_list);
		return output;
	}
	
	/**
	 * Description: 获取环境下服务器列表
	 * @param input
	 * @return
	 */
	public ViewEnvirOutputBean getEnvServerList(ViewEnvirInputBean input) {
		ViewEnvirOutputBean output = new ViewEnvirOutputBean();
		String env_name = input.getEnv_name();
		// 非空校检
		Assert.assertNotEmpty(env_name, ViewEnvirInputBean.ENV_NAMECN);
		// 合法性校检
		cePublicService.checkEnvNameIsExist(env_name);
		List<ServerBean> server_list = new ArrayList<ServerBean>();
		List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(env_name);
		if (!Assert.isEmpty(envir_servers)) {
			for (CeEnvironmentServerInfo serv : envir_servers) {
				ServerBean server = new ServerBean();
				server.setServer_name(serv.getServer_name());
				CeServerInfo servers = ceServiceDaoService.getInfoByServerName(serv.getServer_name());
				if (!Assert.isEmpty(servers)) {
					server.setServer_ip(servers.getServer_ip());
					server.setServer_cn_name(servers.getServer_cn_name());
				}
				server.setServer_type(serv.getServer_type());
				server_list.add(server);
			}
		}
		output.setServer_list(server_list);
		return output;
	}
	
	/**
	 * Description: 根据环境名获取环境信息
	 * @param input
	 * @return
	 */
	public ViewEnvirOutputBean queryEnvInfoList(ViewEnvirInputBean input) {
		logger.debug("queryEnvInfoList begin");
		ViewEnvirOutputBean output = new ViewEnvirOutputBean();
		List<EnvInfoBean> env_lists = new ArrayList<EnvInfoBean>();
		String[] env_list = input.getEnv_list();
		Assert.assertNotEmpty(env_list, ViewEnvirInputBean.ENV_LISTCN);
		for (String env_name : env_list) {
			logger.debug("环境名：" + env_name);
			// 合法性校检
			cePublicService.checkEnvNameIsExist(env_name);
			CeEnvironmentInfo envs = ceEnvironmentDaoService.getInfoByEnvName(env_name);
			EnvInfoBean envbean = new EnvInfoBean();
			// 构成要素
			List<ELE_TYPE> ele_type = new ArrayList<ELE_TYPE>();
			ele_type = cePublicService.generateEleTypeList(envs.getEle_type());
			// 环境信息封装
			envbean.setEnv_name(env_name);
			envbean.setEnv_cn_name(envs.getEnv_cn_name());
			envbean.setEnv_bk_desc(envs.getEnv_bk_desc());
			envbean.setEnv_type(envs.getEnv_type());
			envbean.setEle_type(ele_type.toArray(new ELE_TYPE[ele_type.size()]));
			envbean.setDt_range(envs.getDt_range());
			envbean.setModify_user_id(envs.getModify_user_id());
			envbean.setCreate_user_id(envs.getCreate_user_id());
			envbean.setSys_name(envs.getSys_name());
			// 环境关联服务器信息
			List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(env_name);
			if (!Assert.isEmpty(envir_servers)) {
				List<String> ver_servers = new ArrayList<String>();
				List<String> sys_servers = new ArrayList<String>();
				List<String> db_servers = new ArrayList<String>();
				for (CeEnvironmentServerInfo server : envir_servers) {
					SERVER_TYPE type = server.getServer_type();
					String name = server.getServer_name();
					if (type == SERVER_TYPE.APPLY) {
						sys_servers.add(name);
					}
					if (type == SERVER_TYPE.DATABASE) {
						db_servers.add(name);
					}
					if (type == SERVER_TYPE.VERSION) {
						ver_servers.add(name);
					} else {
						continue;
					}
				}
				envbean.setDb_server_list(db_servers);
				envbean.setVer_server_list(ver_servers);
				envbean.setSys_server_list(sys_servers);
			}
			JaDate create_date = envs.getCreate_bk_date();
			JaTime crate_time = envs.getCreate_bk_time();
			JaDate modify_date = envs.getModify_bk_date();
			JaTime modify_time = envs.getModify_bk_time();
			if (!Assert.isEmpty(create_date) && !Assert.isEmpty(crate_time)) {
				envbean.setCreate_date_time(JaDateTime.valueOf(create_date.toString() + " " + crate_time.toString()));
			}
			if (!Assert.isEmpty(modify_date) && !Assert.isEmpty(modify_time)) {
				envbean.setModify_date_time(JaDateTime.valueOf(modify_date.toString() + " " + modify_time.toString()));
			}
			env_lists.add(envbean);
		}
		output.setEnv_list(env_lists);
		logger.debug("queryEnvInfoList end");
		return output;
	}
	
	public ViewEnvirOutputBean queryServerListByEnv(ViewEnvirInputBean input) {
		logger.debug("queryServerListByEnv begin");
		ViewEnvirOutputBean output = new ViewEnvirOutputBean();
		
		// 非空校验
		String env_name = input.getEnv_name();
		Assert.assertNotEmpty(env_name, ViewEnvirInputBean.ENV_NAMECN);
		
		// 环境关联服务器信息
		List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(env_name);
		if(!Assert.isEmpty(envir_servers)){
			List<CeServerInfo> ver_servers = new ArrayList<CeServerInfo>();
			List<CeServerInfo> sys_servers = new ArrayList<CeServerInfo>();
			List<CeServerInfo> db_servers = new ArrayList<CeServerInfo>();
			for (CeEnvironmentServerInfo server : envir_servers) {
				SERVER_TYPE type = server.getServer_type();
				String name = server.getServer_name();
				CeServerInfo info = ceServiceDaoService.getInfoByServerName(name);
				if(type == SERVER_TYPE.APPLY){
					CeServerInfo copyInfo = new CeServerInfo();
					deepBeanCopier.copy(info, copyInfo);
					sys_servers.add(copyInfo);
				}
				if(type == SERVER_TYPE.DATABASE){
					CeServerInfo copyInfo = new CeServerInfo();
					deepBeanCopier.copy(info, copyInfo);
					db_servers.add(copyInfo);
				}
				if(type == SERVER_TYPE.VERSION){
					CeServerInfo copyInfo = new CeServerInfo();
					deepBeanCopier.copy(info, copyInfo);
					ver_servers.add(copyInfo);
				}else{
					continue;
				}
			}
			output.setDb_server_list(db_servers);
			output.setVersion_server_list(ver_servers);
			output.setApp_server_list(sys_servers);
			
		}
		logger.debug("queryServerListByEnv begin");
		return output;
	}
}
