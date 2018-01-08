/**
 * Title: CloseInteTaskAction.java
 * File Description: 关闭发布任务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ClosePublishTaskInputBean;
import com.wk.cd.build.ea.bean.ClosePublishTaskOutputBean;
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
 * Class Description: 关闭单个发布任务
 * @author ZHANGJ
 */
public class ClosePublishTaskAction extends ActionBasic<ClosePublishTaskInputBean, ClosePublishTaskOutputBean>{
	
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 关闭单个发布任务
	 * @param input
	 * @return 
	 */
	@Override
	protected ClosePublishTaskOutputBean doAction(ClosePublishTaskInputBean input) {
		logger.info("-----------------CloseInteTaskAction Begin------------------");
		ClosePublishTaskOutputBean output = new ClosePublishTaskOutputBean();
		
		//非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ClosePublishTaskInputBean.WORK_IDCN);
		
		//获取任务信息
		EnvTaskInfo info = taskSrv.getInfoByKey(work_id);
		if(TASK_STATUS.EXECUTED.equals(info.getTask_status())){
			throw new TaskCannotCloseException();
		}
		
		//修改任务状态为关闭
		taskSrv.updateTaskStatus(TASK_STATUS.CLOSE, work_id);
		
		logger.info("-----------------CloseInteTaskAction End------------------");
		return output;
	}

	/** 
	 * Description: 关闭单个发布任务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ClosePublishTaskInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("任务编号：" + input.getWork_id());
		return lgsvc.getLogTxt("关闭单个发布任务", lst_val);
	}

}
