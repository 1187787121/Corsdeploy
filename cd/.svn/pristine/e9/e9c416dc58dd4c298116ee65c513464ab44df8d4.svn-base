/**
 * Title: QueryEnvForAppAction.java
 * File Description: 环境列表信息查询
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.build.en.bean.QueryEnvForAppInputBean;
import com.wk.cd.build.en.bean.QueryEnvForAppOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.SERVER_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 环境列表信息查询
 * @author xuph
 */
public class QueryEnvForAppAction extends ActionBasic<QueryEnvForAppInputBean, QueryEnvForAppOutputBean>{
	@Inject private CeEnvironmentServerDaoService ceEnvironmentServerDaoSrv;
	@Inject private EnvironmentPublicService environmentPublicSrv;
	@Inject private CeSystemDaoService ceSystemDaosrv;
	@Inject private SystemService systemSrv;
	@Inject private CeEnvironmentDaoService ceEnvironmentDaoSrv;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: core do
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryEnvForAppOutputBean doAction(QueryEnvForAppInputBean input) {
		logger.info("----------------------QueryEnvForAppAction Begin--------------");
		QueryEnvForAppOutputBean output = new QueryEnvForAppOutputBean();
		List<EnvInfoBean> env_lists = new ArrayList<EnvInfoBean>();
		//所有环境信息
		List<CeEnvironmentInfo> env_list = ceEnvironmentDaoSrv.queryAllEnvMsg();
		if(!Assert.isEmpty(env_list)){
			for (CeEnvironmentInfo envs : env_list) {
				EnvInfoBean envbean = new EnvInfoBean();
				//构成要素
				List<ELE_TYPE> ele_type = new ArrayList<ELE_TYPE>();
				ele_type = environmentPublicSrv.generateEleTypeList(envs.getEle_type());
				//环境信息封装
				String env_name = envs.getEnv_name();
				envbean.setEnv_name(env_name);
				envbean.setEnv_cn_name(envs.getEnv_cn_name());
				envbean.setEnv_bk_desc(envs.getEnv_bk_desc());
				envbean.setEnv_type(envs.getEnv_type());
				envbean.setEle_type(ele_type.toArray(new ELE_TYPE[ele_type.size()]));
				envbean.setDt_range(envs.getDt_range());
				envbean.setModify_user_id(envs.getModify_user_id());
				envbean.setCreate_user_id(envs.getCreate_user_id());
				envbean.setSys_name(envs.getSys_name());
				//获得系统类型
				if(!Assert.isEmpty(envs.getSys_name())){
					envbean.setSys_cn_name(systemSrv.getSystemCnName(envs.getSys_name()));
					CeSystemInfo sys = ceSystemDaosrv.getInfoBySysName(envs.getSys_name());
					if(!Assert.isEmpty(sys))
					envbean.setSys_type(sys.getSys_type());
				}
				// 环境关联服务器信息
				List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoSrv.queryInfoByEnvName(env_name);
				if(!Assert.isEmpty(envir_servers)){
					List<String> ver_servers = new ArrayList<String>();
					List<String> sys_servers = new ArrayList<String>();
					List<String> db_servers = new ArrayList<String>();
					for (CeEnvironmentServerInfo server : envir_servers) {
						SERVER_TYPE type = server.getServer_type();
						String name = server.getServer_name();
						if(type == SERVER_TYPE.APPLY){
							sys_servers.add(name);
						}
						if(type == SERVER_TYPE.DATABASE){
							db_servers.add(name);
						}
						if(type == SERVER_TYPE.VERSION){
							ver_servers.add(name);
						}else{
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
				if(!Assert.isEmpty(create_date)&&!Assert.isEmpty(crate_time)){
					envbean.setCreate_date_time(JaDateTime.valueOf(create_date.toString()
							+ " " + crate_time.toString()));
				}
				if(!Assert.isEmpty(modify_date)&&!Assert.isEmpty(modify_time)){
					envbean.setModify_date_time(JaDateTime.valueOf(modify_date.toString()
							+ " " + modify_time.toString()));
				}
				env_lists.add(envbean);
			}
			output.setEnv_list(env_lists);
		}
		logger.info("----------------------QueryEnvForAppAction End--------------");
		return output;
	}

	/** 
	 * Description: write log
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryEnvForAppInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		return lgsvc.getLogTxt("环境信息列表ForApp", log_lst);
	}

}
