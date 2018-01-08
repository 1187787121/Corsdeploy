/**
 * Title: QueryBsForAppAction.java
 * File Description: ��ѯҵ��ϵͳ�б�App�ˣ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��28��
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.QueryBsForAppInputBean;
import com.wk.cd.build.en.bean.QueryBsForAppOutputBean;
import com.wk.cd.build.en.bean.SystemAppbean;
import com.wk.cd.build.en.bean.TpAppBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeSystemCfgDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.dao.CeSystemTemplateDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ѯҵ��ϵͳ�б�App�ˣ�
 * @author Xul
 */
public class QueryBsForAppAction extends ActionBasic<QueryBsForAppInputBean, QueryBsForAppOutputBean>{
	
	@Inject private UsGetUserInfoService usGetUserInfoService;
	@Inject private CeServerDaoService ceServerDaoService;
	@Inject private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject private CeSystemDaoService ceSystemDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ��ѯҵ��ϵͳ�б�App�ˣ�
	 * @param input
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected QueryBsForAppOutputBean doAction(QueryBsForAppInputBean input) {
		logger.info("----------------------QueryBsForAppAction Begin -------------");
		QueryBsForAppOutputBean output = new QueryBsForAppOutputBean();
		
		List<SystemAppbean> sys_app_list = new ArrayList<SystemAppbean>();
		//��ѯϵͳ�б�
		List<CeSystemInfo> sys_list = ceSystemDaoService.getAllSystemInfo();
		if(!Assert.isEmpty(sys_list)){
			for(CeSystemInfo info : sys_list){
				String sys_name = info.getSys_name();
				SystemAppbean bean = SystemAppbean.copy(info);
				//��ȡ�û�������
				if(!Assert.isEmpty(info.getCreate_user_id())){
					bean.setCrt_user_cname(usGetUserInfoService.getUserCnNameByUserId(info.getCreate_user_id()));
				}
				if(!Assert.isEmpty(info.getModify_user_id())){
					bean.setMod_user_cname(usGetUserInfoService.getUserCnNameByUserId(info.getModify_user_id()));
				}
				//��ȡ�����ļ��б�
				bean.setConfig_list(ceSystemCfgDaoService.queryCfgNameBySysName(sys_name).toArray(new String[ceSystemCfgDaoService.queryCfgNameBySysName(sys_name).size()]));
				//��ȡģ���б�
				List<TpAppBean> app_tp_list = new ArrayList<TpAppBean>();
				List<EnumValue> tp_list = TEMPLATE_TYPE.listValues(TEMPLATE_TYPE.class);
				if(!Assert.isEmpty(tp_list)){
					for(EnumValue tp : tp_list){
						TpAppBean tp_bean = new TpAppBean();
						tp_bean.setTemplate_type_cname(tp.getCname());
						tp_bean.setTemplate_type((TEMPLATE_TYPE)tp);
						tp_bean.setTemplate_list(ceSystemTemplateDaoService.getInfosBySysAndType(sys_name, (TEMPLATE_TYPE)tp).toArray(new String[ceSystemTemplateDaoService.getInfosBySysAndType(sys_name, (TEMPLATE_TYPE)tp).size()]));
						app_tp_list.add(tp_bean);
					}
					bean.setTp_list(app_tp_list);
				}
				//��ȡ���ط������б�
				List<String> srv_list = new ArrayList<String>();
				List<CeServerInfo> server_list = new ArrayList<CeServerInfo>();
				List<CeEnvironmentInfo> envir_list = ceEnvironmentDaoService.getEnvirList(sys_name);
				if(!Assert.isEmpty(envir_list)){
					for(CeEnvironmentInfo env_info : envir_list){
						List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(env_info.getEnv_name());
						if(!Assert.isEmpty(envir_servers)){
							for(CeEnvironmentServerInfo envir_info : envir_servers){
								String server_name = envir_info.getServer_name();
								if(!srv_list.contains(server_name))
								server_list.add(ceServerDaoService.getInfoByServerName(server_name));
								srv_list.add(server_name);
								bean.setServer_list(server_list);
							}
						}
					}
				}
				sys_app_list.add(bean);
			}
		}
		
		output.setSys_list(sys_app_list);
		logger.info("----------------------QueryBsForAppAction End -------------");
		return output;
	}

	/** 
	 * Description: ��ѯҵ��ϵͳ�б�App�ˣ�
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryBsForAppInputBean input) {
		List<String> logs = new ArrayList<String>();
		return lgsvc.getLogTxt("��ѯҵ��ϵͳ�б�App�ˣ�", logs);
	}

}
