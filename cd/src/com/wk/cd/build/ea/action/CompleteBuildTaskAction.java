/**
 * Title: CompleteBuildTaskAction.java
 * File Description: 完成环境构建任务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016年12月13日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.CompleteBuildTaskInputBean;
import com.wk.cd.build.ea.bean.CompleteBuildTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 完成环境构建任务
 * @author wangj
 */
public class CompleteBuildTaskAction extends ActionBasic<CompleteBuildTaskInputBean, CompleteBuildTaskOutputBean>{
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 完成环境构建任务
	 * @param input
	 * @return 
	 */
	@Override
	protected CompleteBuildTaskOutputBean doAction(
			CompleteBuildTaskInputBean input) {
		logger.info("-----------CompleteBuildTaskAction Start----------");
		
		CompleteBuildTaskOutputBean output = new CompleteBuildTaskOutputBean();
		String work_id = input.getWork_id();
		String exe_user_id = input.getOrg_user_id();
		
		//校验非空
		Assert.assertNotEmpty(work_id, CompleteBuildTaskOutputBean.WORK_SEQCN);
		
		//校验合法性
		envTaskDaoService.checkEnvIdIsNotExist(work_id);
		
		//更新环境任务表任务状态
		envTaskDaoService.updateBuildStatusByWorkId(TASK_STATUS.EXECUTED, JaDateTime.now(), exe_user_id, work_id);
		
		logger.info("-----------CompleteBuildTaskAction End----------");
		return output;
	}

	/** 
	 * Description: 完成环境构建任务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(CompleteBuildTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("环境构建任务编号: " + input.getWork_id());
		return lgsvc.getLogTxt("完成环境构建任务", log_lst);
	}

}
