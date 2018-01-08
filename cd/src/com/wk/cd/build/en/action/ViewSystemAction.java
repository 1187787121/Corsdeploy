/**
 * Title: ViewCeAction.java
 * File Description: Ӧ��ϵͳ�鿴����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.build.en.bean.PageSystemListBean;
import com.wk.cd.build.en.bean.SysEnvAndTaskBean;
import com.wk.cd.build.en.bean.SystemEnvirBean;
import com.wk.cd.build.en.bean.SystemTemplateBean;
import com.wk.cd.build.en.bean.ViewSystemInputBean;
import com.wk.cd.build.en.bean.ViewSystemOutputBean;
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
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.SERVER_TYPE;
import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.system.ep.service.EnvPrivService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.service.IViewActionBasic;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: Ӧ��ϵͳ�鿴����
 * @author chiss
 */
public class ViewSystemAction
		extends IViewActionBasic<ViewSystemInputBean, ViewSystemOutputBean> {

	@Inject
	private SystemService systemservice;
	@Inject
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private MoTemplateDaoService tpTemplateDaoService;
	@Inject
	private CeSystemDaoService ceSystemDaoService;
	@Inject
	private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject
	private EnvPrivService envPrivService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: �鿴Ӧ��ϵͳģ��
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean getTemplate(ViewSystemInputBean input) {
		ViewSystemOutputBean output = new ViewSystemOutputBean();

		TEMPLATE_TYPE template_type = input.getTemplate_type();
		// �ǿ�У��
		Assert.assertNotEmpty(template_type.getValue() == 0 ? null : template_type.getValue(), ViewSystemInputBean.TEMPLATE_TYPECN);
		CeSystemTemplateInfo sys_info = new CeSystemTemplateInfo();
		sys_info.setTemplate_type(template_type);
		// �ݶ�
		String[] temp_name = new String[2];
		temp_name[0] = "template1";
		temp_name[1] = "template2";
		output.setTemplate_name(temp_name);
		return output;
	}

	/**
	 * Description: �鿴ģ���б�
	 * @param input
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ViewSystemOutputBean getTemplateList(ViewSystemInputBean input) {
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		List<SystemTemplateBean> bean_list = new ArrayList<SystemTemplateBean>();
		// ��ȡö���б�
		List<EnumValue> lis = TEMPLATE_TYPE.listValues(TEMPLATE_TYPE.class);
		for (EnumValue enumValue : lis) {
			SystemTemplateBean bean = new SystemTemplateBean();
			bean.setTemplate_type((TEMPLATE_TYPE) enumValue);
			// ��ͬ��ģ�����Ͷ�Ӧ��ͬ��ģ����
			List<String> template_names = tpTemplateDaoService.queryTemplateNameByType((TEMPLATE_TYPE) enumValue);
			if (!Assert.isEmpty(template_names)) {
				bean.setTemplate_name(template_names.toArray(new String[template_names.size()]));
			}
			bean_list.add(bean);
		}
		output.setSys_bean(bean_list);
		return output;
	}

	/**
	 * Description: �鿴����Ӧ��ϵͳ
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean getSystem(ViewSystemInputBean input) {
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		String sys_name = input.getSys_name();
		// �ǿ�У��
		Assert.assertNotEmpty(sys_name, ViewSystemInputBean.SYS_NAMECN);
		// �Ϸ���У��
		systemservice.checkSystemNameIsNotExist(sys_name);
		CeSystemInfo sys_info = new CeSystemInfo();
		sys_info.setSys_name(sys_name);
		CeSystemInfo sysinfo = ceSystemDaoService.getInfoByKey(sys_info);
		List<CeSystemTemplateInfo> systemp_list = ceSystemTemplateDaoService.getInfoBySysName(sys_name);
		List<TEMPLATE_TYPE> type_list = new ArrayList<TEMPLATE_TYPE>();
		// ����ģ����
		if (!Assert.isEmpty(systemp_list)) {
			for (CeSystemTemplateInfo info : systemp_list) {
				if (!type_list.contains(info.getTemplate_type())) {
					type_list.add(info.getTemplate_type());
				}
			}
		}
		// ��ȡģ������
		List<SystemTemplateBean> bean_list = new ArrayList<SystemTemplateBean>();
		if (!Assert.isEmpty(type_list)) {
			for (TEMPLATE_TYPE type : type_list) {
				SystemTemplateBean bean = new SystemTemplateBean();
				bean.setTemplate_type(type);
				bean_list.add(bean);
			}
		}
		// ��ģ�����ͺ�ģ��������info
		if (!Assert.isEmpty(bean_list)) {
			for (SystemTemplateBean temp : bean_list) {
				TEMPLATE_TYPE types = temp.getTemplate_type();
				List<String> temp_name = new ArrayList<String>();
				for (CeSystemTemplateInfo info : systemp_list) {
					if (types == info.getTemplate_type()) {
						if (!temp_name.contains(info.getTemplate_name())) {
							temp_name.add(info.getTemplate_name());
						}
					}
				}
				if (!Assert.isEmpty(temp_name)) {
					temp.setTemplate_name(temp_name.toArray(new String[temp_name.size()]));
				}
			}
		}
		output.setSys_name(sysinfo.getSys_name());
		output.setSys_cn_name(sysinfo.getSys_cn_name());
		output.setSys_bk_desc(sysinfo.getSys_bk_desc());
		output.setSys_type(sysinfo.getSys_type());
		// ģ���б�
		output.setSystemp_list(bean_list);
		// ��ȡ�ļ����б�
		List<String> cfg_list = ceSystemCfgDaoService.queryCfgNameBySysName(sys_name);
		output.setFile_list(cfg_list.toArray(new String[cfg_list.size()]));
		return output;
	}

	/**
	 * Description: ��ѯ���������б�
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean getSystemEnvirList(ViewSystemInputBean input) {
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		String sys_name = input.getSys_name();
		// �ǿ�У��
		Assert.assertNotEmpty(sys_name, ViewSystemInputBean.SYS_NAMECN);
		// �Ϸ���У��
		systemservice.checkSystemNameIsNotExist(sys_name);
		// ��ȡ�����б���Ϣ
		List<CeEnvironmentInfo> envir_list = ceEnvironmentDaoService.getEnvirList(sys_name);
		List<SystemEnvirBean> sysenvir = new ArrayList<SystemEnvirBean>();
		if (!Assert.isEmpty(envir_list)) {
			for (CeEnvironmentInfo envir : envir_list) {
				SystemEnvirBean system = new SystemEnvirBean();
				system.setDt_range(envir.getDt_range());
				system.setEle_type(envir.getEle_type());
				system.setEnv_cn_name(envir.getEnv_cn_name());
				system.setEnv_type(envir.getEnv_type());
				sysenvir.add(system);
			}
			output.setEnvir_list(sysenvir);
		}
		return output;
	}
	
	/**
	 * Description: ��ѯ���������б�(APP)
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean queryEnvListForApp(ViewSystemInputBean input){
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		String sys_name = input.getSys_name();
		// �ǿ�У��
		Assert.assertNotEmpty(sys_name, ViewSystemInputBean.SYS_NAMECN);
		// �Ϸ���У��
		systemservice.checkSystemNameIsNotExist(sys_name);
		// ������Ϣ�б�
		List<EnvInfoBean> env_lists = new ArrayList<EnvInfoBean>();
		// ��ȡ�����б���Ϣ
		List<CeEnvironmentInfo> env_list = ceEnvironmentDaoService.getEnvirList(sys_name);
		if(!Assert.isEmpty(env_list)){
			for (CeEnvironmentInfo envs : env_list) {
				EnvInfoBean envbean = new EnvInfoBean();
				//����Ҫ��
				List<ELE_TYPE> ele_type = new ArrayList<ELE_TYPE>();
				ele_type = environmentPublicService.generateEleTypeList(envs.getEle_type());
				//������Ϣ��װ
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
				// ����������������Ϣ
				List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(env_name);
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
		return output;
	}

	/**
	 * Description: �������Ӧ��ϵͳ�������ƺͼ��
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean getSystemNameList(ViewSystemInputBean input) {
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		List<CeSystemInfo> sys_list = ceSystemDaoService.getAllSystemInfo();
		output.setSys_list(sys_list);
		return output;
	}

	/**
	 * Description: Ӧ��ϵͳ�б�
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean getSystemList(ViewSystemInputBean input) {
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		String sys_name = input.getSys_name();
		// �ǿ�У��
		if (!Assert.isEmpty(sys_name)) {
			sys_name = sys_name.trim();
		}
		// ��ѯ����Ӧ��ϵͳ������
		List<PageSystemListBean> sys_list = ceSystemDaoService.getAllSystemList(sys_name, sys_name);
		if (!Assert.isEmpty(sys_list)) {
			for (PageSystemListBean sysInfo : sys_list) {
				int template_num = ceSystemTemplateDaoService.countSystemTemplateBySysName(sysInfo.getSys_name());
				sysInfo.setTemplate_num(template_num);
			}
		}
		output.setPage_sys_List(sys_list);
		return output;
	}

	/**
	 * Description: ����Ӧ��ϵͳ��ѯ����������������ϸ
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean queryEnvAndTask(ViewSystemInputBean input) {
		logger.debug("--------------------queryEnvAndTask begin---------------------");
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		// �ǿ�У��
		String sys_name = input.getSys_name();
		String user_id = input.getOrg_user_id();
		Assert.assertNotEmpty(sys_name, ViewSystemInputBean.SYS_NAMECN);
		Assert.assertNotEmpty(user_id, ViewSystemInputBean.ORG_USER_IDCN);
		// ������У��
		systemservice.checkSystemNameIsNotExist(sys_name);
		logger.debug("Ӧ��ϵͳ��Ϊ[{}]", sys_name);
		List<SysEnvAndTaskBean> sys_detail_list = new ArrayList<SysEnvAndTaskBean>();
		// ��ѯϵͳ�»����б�
		List<CeEnvironmentInfo> env_list = ceEnvironmentDaoService.queryEnvInfosBySys(sys_name);
		if (!Assert.isEmpty(env_list)) {
			logger.debug("-----------------����������---------------");
			for (CeEnvironmentInfo info : env_list) {
				SysEnvAndTaskBean bean = new SysEnvAndTaskBean();
				String env_name = info.getEnv_name();
				bean.setEnv_name(env_name);
				bean.setEnv_cn_name(info.getEnv_cn_name());
				bean.setEnv_type(info.getEnv_type());
				String task_seq = "";
				logger.debug("--------��ǰ������" + env_name + "----------");
				// ��ȡ����������ִ�е�������Ϣ
				List<EnvTaskInfo> task_info_list = envTaskDaoService.getIdByEnv(env_name);

				if (!Assert.isEmpty(task_info_list)) {
					task_seq = task_info_list.get(0).getWork_id();
					if (TASK_TYPE.BUILD.equals(task_info_list.get(0).getTask_type())) {
						bean.setTask_all_type(TASK_ALL_TYPE.BUILD);
					} else if (TASK_TYPE.INTEGRATION.equals(task_info_list.get(0).getTask_type())) {
						bean.setTask_all_type(TASK_ALL_TYPE.INTEGRATION);
					} else if (TASK_TYPE.PUBLISH.equals(task_info_list.get(0).getTask_type())) {
						bean.setTask_all_type(TASK_ALL_TYPE.PUBLISH);
					}
				}
				// ��ȡ����������ִ�е������Ϣ
				List<EnvTagStorageInfo> storage_list = envTagStorageDaoService.getIdByEnv(env_name);
				if (!Assert.isEmpty(storage_list)) {
					task_seq = storage_list.get(0).getStorage_id();
					bean.setTask_all_type(TASK_ALL_TYPE.STORAGE);
					bean.setInstance_id(storage_list.get(0).getInstance_id());
				}
				if (envPrivService.hasUserEnvPriv(user_id, env_name)) {
					bean.setAf_flag(AF_FLAG.ALLOW);
				}else{
					bean.setAf_flag(AF_FLAG.FORBID);
				}
				bean.setTask_seq(task_seq);
				sys_detail_list.add(bean);
			}
		}
		output.setSys_detail_list(sys_detail_list);
		logger.debug("--------------------queryEnvAndTask end---------------------");
		return output;
	}
	
	/**
	 * Description: ����ϵͳ���鿴���ط������б�
	 * @param input
	 * @return
	 */
	public ViewSystemOutputBean queryServerListBySys(ViewSystemInputBean input) {
		logger.debug("--------------------queryServerListBySys begin---------------------");
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		
		//�ǿ�У��
		String sys_name = input.getSys_name();
		Assert.assertNotEmpty(sys_name, ViewSystemInputBean.SYS_NAMECN);
		
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
					}
				}
			}
		}
		output.setServer_list(server_list);
		
		logger.debug("--------------------queryServerListBySys end---------------------");
		return output;
	}
}
