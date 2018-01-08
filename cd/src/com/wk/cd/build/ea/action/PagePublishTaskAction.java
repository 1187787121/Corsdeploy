/**
 * Title: PagePublishTaskAction.java
 * File Description: 分页查询发布任务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.PageInteTaskInputBean;
import com.wk.cd.build.ea.bean.PagePublishTaskInputBean;
import com.wk.cd.build.ea.bean.PagePublishTaskOutputBean;
import com.wk.cd.build.ea.bean.PubTaskBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 
 * @author chiss
 */
public class PagePublishTaskAction extends ActionBasic<PagePublishTaskInputBean, PagePublishTaskOutputBean>{
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject 
	private EnvironmentPublicService environmentPublicService;	
	@Inject
	private EnvTaskPublicService envTaskPublicService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 分页查询发布任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected PagePublishTaskOutputBean doAction(PagePublishTaskInputBean input) {
		logger.info("-----------PagePublishTaskAction begin----------");
		PagePublishTaskOutputBean output = new PagePublishTaskOutputBean();
		String env_name = input.getEnv_name();
		//非空校验
		Assert.assertNotEmpty(env_name, PageInteTaskInputBean.ENV_NAMECN);
		//合法性校验
		environmentPublicService.checkEnvNameIsExist(env_name);
		List<PubTaskBean> pub_list = new ArrayList<PubTaskBean>();
		List<EnvTaskInfo> task_list = envTaskDaoService.pageInfosByEnv(env_name, TASK_TYPE.PUBLISH, input.getStart_recd(), input.getLimit_recd());
		for (EnvTaskInfo envTaskInfo : task_list) {
			PubTaskBean bean = new PubTaskBean();
			bean.setWork_id(envTaskInfo.getWork_id());
			bean.setTarget_ver_num(envTaskInfo.getTarget_ver_num());
			//获取目标包名列表
			String list_uuid = envTaskInfo.getPub_list_uuid();
			if(!Assert.isEmpty(list_uuid)) {
				List<String> pack_list = uuFilelistDaoService.queryPacList(list_uuid);
				String[] package_list = pack_list.toArray(new String[pack_list.size()]);
				bean.setPackage_list(package_list);
			}
			String exelog_full_path = envTaskInfo.getExelog_bk_path();
			if(!Assert.isEmpty(exelog_full_path)) {
				bean.setExelog_name(FileTool.getFileName(exelog_full_path));
			}
			bean.setTask_status(envTaskInfo.getTask_status());
			bean.setExe_result(envTaskInfo.getExe_result());
			bean.setCrt_bk_date(envTaskInfo.getCrt_bk_date());
			bean.setCrt_bk_time(envTaskInfo.getCrt_bk_time());
			bean.setRol_inst_flag(envTaskPublicService.judgeRolInstExist(envTaskInfo.getWork_id()));
			pub_list.add(bean);
		}
		output.setPub_list(pub_list);
		output.setAll_recd(envTaskDaoService.countTaskByEnv(env_name, TASK_TYPE.PUBLISH));
		logger.info("-----------PagePublishTaskAction end----------");
		return output;
	}

	/** 
	 * Description: 分页查询发布任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PagePublishTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("环境名称: " + input.getEnv_name());
		return lgsvc.getLogTxt("查询发布列表", log_lst);
	}

}
