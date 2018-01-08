/**
 * Title: PageBuildTaskAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.BuildEnvTaskBean;
import com.wk.cd.build.ea.bean.PageBuildTaskInputBean;
import com.wk.cd.build.ea.bean.PageBuildTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 根据环境查询构建任务列表（带分页）
 * @author wangj
 */
public class PageBuildTaskAction extends ActionBasic<PageBuildTaskInputBean, PageBuildTaskOutputBean>{
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private EnvironmentPublicService environmentPublicService;
	@Inject private CeProjectDaoService ceProjectDaoService;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 根据环境查询构建任务列表（带分页）
	 * @param input
	 * @return 
	 */
	@Override
	protected PageBuildTaskOutputBean doAction(PageBuildTaskInputBean input) {
		logger.info("-----------------PageBuildTaskAction Begin------------------");
		
		PageBuildTaskOutputBean output = new PageBuildTaskOutputBean();
		String env_name = input.getEnv_name();
		
		//非空校验
		Assert.assertNotEmpty(env_name, PageBuildTaskInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(input.getStart_recd(), PageBuildTaskInputBean.START_RECDCN);
		Assert.assertNotEmpty(input.getLimit_recd(), PageBuildTaskInputBean.LIMIT_RECDCN);
		
		//合法性校验
		environmentPublicService.checkEnvNameIsExist(env_name);
		
		List<EnvTaskInfo> task_list = envTaskDaoService.pageInfosByEnv(env_name, TASK_TYPE.BUILD, input.getStart_recd(), input.getLimit_recd());
		List<BuildEnvTaskBean> build_task_list = new ArrayList<BuildEnvTaskBean>();
		//遍历构建任务列表
		for(EnvTaskInfo taskInfo : task_list){
			BuildEnvTaskBean bean = new BuildEnvTaskBean();
			bean.setEnv_name(env_name);
			bean.setWork_id(taskInfo.getWork_id());
			bean.setTask_status(taskInfo.getTask_status());
			bean.setExe_result(taskInfo.getExe_result());
			bean.setTask_bk_desc(taskInfo.getTask_bk_desc());
			bean.setCrt_bk_date(taskInfo.getCrt_bk_date());
			bean.setCrt_bk_time(taskInfo.getCrt_bk_time());
			String project_name = taskInfo.getProject_name();
			if(!Assert.isEmpty(project_name)){
				bean.setProject_short_name(ceProjectDaoService.getInfoByProjectName(project_name).getProject_short_name());
			}
			build_task_list.add(bean);
		}
		output.setBuild_task_list(build_task_list);
		output.setAll_recd(envTaskDaoService.countTaskByEnv(env_name, TASK_TYPE.BUILD));
		
		logger.info("-----------------PageBuildTaskAction End------------------");
		
		return output;
	}

	/** 
	 * Description: 根据环境查询构建任务列表（带分页）
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageBuildTaskInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("环境名称：" + input.getEnv_name());
		return lgsvc.getLogTxt("分页查询构建任务列表", lst_val);
	}
}
