/**
 * Title: SystemService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wk.cd.build.en.bean.EnvInfoBean;
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
import com.wk.cd.build.en.info.CeSystemTemplateInfo;
import com.wk.cd.build.exc.AddSystemIsExistException;
import com.wk.cd.build.exc.UpdateSystemIsNotExistException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author Administrator
 */
public class SystemService {

	@Inject
	private CeSystemDaoService ceSystemDaoService;
	@Inject 
	private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject 
	private UsGetUserInfoService usGetUserInfoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description:检查应用系统是否存在，不存在抛出异常
	 * @param sys_name
	 */
	public void checkSystemNameIsNotExist(String sys_name) {
		logger.info("检测应用系统名[{}]是否存在，不存存在就抛异常", sys_name);
		if (ceSystemDaoService.countBySystemName(sys_name) <= 0) {
		   throw new UpdateSystemIsNotExistException().addScene("SYSTEM", sys_name);
		}
	}

	/**
	 * Description: 根据模板类型获取模板名
	 * @param business_sys_name
	 * @param project_name
	 * @return
	 */
	public List<CeSystemTemplateInfo> getTemplateByType(
			TEMPLATE_TYPE template_type) {
		return null;

	}

	/**
	 * Description: 如果系统存在就抛异常
	 * @param sys_name
	 */
	public void checkSystemNameIsExist(String sys_name) {
		logger.info("检测应用系统名[{}]是否不存在，存在就抛异常", sys_name);
		if (ceSystemDaoService.countBySystemName(sys_name) > 0) {
			throw new AddSystemIsExistException().addScene("SYSTEM", sys_name);
		}

	}

	/**
	 * Description: 获得应用系统中文名
	 * @param sys_name
	 * @return
	 */
	public String getSystemCnName(String sys_name) {
		String sys_cn_name = "";
		CeSystemInfo sysinfo = new CeSystemInfo();
		sysinfo.setSys_name(sys_name);
		CeSystemInfo sys = ceSystemDaoService.getInfoByKey(sysinfo);
		if(!Assert.isEmpty(sys)){
			sys_cn_name = sys.getSys_cn_name();
		}
		return sys_cn_name;
	}
	
	/**
	 * Description: 根据系统名和模板类型 获取模板名列表
	 * @param sys_name
	 * @return
	 */
	public  List<String> getInfoBySysName(String sys_name,TEMPLATE_TYPE template_type){
		checkSystemNameIsNotExist(sys_name);
		List<String> tp_names = new ArrayList<String>();
		List<CeSystemTemplateInfo> list = ceSystemTemplateDaoService.getInfoBySysName(sys_name);
		if(!Assert.isEmpty(list)){
			for(CeSystemTemplateInfo info : list){
				if(template_type.equals(info.getTemplate_type())){
					tp_names.add(info.getTemplate_name());
				}
			}
		}
		return tp_names;
	}
	
	/**
	 * Description: 获得系统列表信息ForAPP
	 * @return
	 */
	public List<SystemAppbean> getSysListForApp(List<CeSystemInfo> sys_list){
		List<SystemAppbean> sys_app_list = new ArrayList<SystemAppbean>();
		if(!Assert.isEmpty(sys_list)){
			for(CeSystemInfo info : sys_list){
				String sys_name = info.getSys_name();
				SystemAppbean bean = SystemAppbean.copy(info);
				//获取用户中文名
				if(!Assert.isEmpty(info.getCreate_user_id())){
					bean.setCrt_user_cname(usGetUserInfoService.getUserCnNameByUserId(info.getCreate_user_id()));
				}
				if(!Assert.isEmpty(info.getModify_user_id())){
					bean.setMod_user_cname(usGetUserInfoService.getUserCnNameByUserId(info.getModify_user_id()));
				}
				//获取配置文件列表
				bean.setConfig_list(ceSystemCfgDaoService.queryCfgNameBySysName(sys_name).toArray(new String[ceSystemCfgDaoService.queryCfgNameBySysName(sys_name).size()]));
				//获取模板列表
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
				//获取挂载服务器列表
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
			sortSysWithSysCnName(sys_app_list);
		}
		return sys_app_list;
	}
	
	/**
	 * Description:根据系统中文名排序
	 */
	private void sortSysWithSysCnName(List<SystemAppbean> sys_list){
		Collections.sort(sys_list, new Comparator<SystemAppbean>() {
			@Override
			public int compare(SystemAppbean bean1, SystemAppbean bean2) {
				return (bean1.getSys_cn_name()).compareTo(bean2.getSys_cn_name());
			}
		});
	}
}
