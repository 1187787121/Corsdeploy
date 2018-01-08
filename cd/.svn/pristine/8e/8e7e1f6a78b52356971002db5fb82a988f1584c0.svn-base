/**
 * Title: CloseBuildTaskAction.java
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

import com.wk.cd.build.ea.bean.CloseBuildTaskInputBean;
import com.wk.cd.build.ea.bean.CloseBuildTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.exc.TaskCannotCloseException;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 关闭单个构建任务
 * @author wangj
 */
public class CloseBuildTaskAction extends ActionBasic<CloseBuildTaskInputBean, CloseBuildTaskOutputBean>{
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 关闭单个构建任务
	 * @param input
	 * @return 
	 */
	@Override
	protected CloseBuildTaskOutputBean doAction(CloseBuildTaskInputBean input) {
		logger.info("-----------CloseBuildTaskAction Start----------");
		
		CloseBuildTaskOutputBean output = new CloseBuildTaskOutputBean();
		String work_id = input.getWork_id();
		
		//非空校验
		Assert.assertNotEmpty(work_id, CloseBuildTaskInputBean.WORK_IDCN);
		
		//合法性校验
		envTaskDaoService.checkEnvIdIsNotExist(work_id);
		
		//获取任务信息
		EnvTaskInfo info = envTaskDaoService.getInfoByKey(work_id);
		TASK_STATUS task_status = info.getTask_status();
		if(!(TASK_STATUS.PENDING.equals(task_status) || TASK_STATUS.RUNNING.equals(task_status))) {
			throw new TaskCannotCloseException();
		}
		
		logger.info("修改任务状态为关闭: work_id = " + work_id);
		
		//修改任务状态为关闭
		envTaskDaoService.updateTaskStatus(TASK_STATUS.CLOSE, work_id);
		
		logger.info("-----------CloseBuildTaskAction End----------");
		
		return output;
	}

	/** 
	 * Description: 关闭单个构建任务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(CloseBuildTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("环境构建任务编号: " + input.getWork_id());
		return lgsvc.getLogTxt("关闭单个构建任务", log_lst);
	}
}
