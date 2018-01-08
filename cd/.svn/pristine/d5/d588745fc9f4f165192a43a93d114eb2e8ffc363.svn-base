/**
 * Title: ExeStepPublishTaskAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExeStepPublishTaskInputBean;
import com.wk.cd.build.ea.bean.ExeStepPublishTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.service.TextLogInterceptor;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 单步执行发布任务
 * @author "Zhangj"
 */
public class ExeStepPublishTaskAction
		extends ActionBasic<ExeStepPublishTaskInputBean, ExeStepPublishTaskOutputBean> {
	@Inject
	EnvTaskDaoService envTaskDaoService;
	@Inject
	EnvTaskPublicService envTaskPublicService;
	@Inject
	PublishTaskInstanceService publishTaskInstanceService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description:
	 * @param arg0
	 * @return
	 */
	@Override
	protected ExeStepPublishTaskOutputBean doAction(ExeStepPublishTaskInputBean input) {
		logger.debug("-----------ExeStepPublishTaskAction Begin------------");
		ExeStepPublishTaskOutputBean output = new ExeStepPublishTaskOutputBean();
		// 非空校验，合法性校验
		checkNull(input);
		String work_id = input.getWork_id();
		int exe_bk_no = input.getPhase_id() == 0 ? 1 : input.getPhase_id();
		logger.debug("当前执行[{}]第[{}]阶段，阶段从1开始计算", work_id, exe_bk_no);
		// 获取实例
		Instance inst_info = publishTaskInstanceService.getInstanceByWorkId(work_id, exe_bk_no);
//		InstanceInfo inst_info = publistTaskService.getInstanceByWorkId(work_id, exe_bk_no);
		
		String log_path = envTaskPublicService.generatePubExeLogPath(work_id);
		if (1 == exe_bk_no) {// 第一次执行 初始化开始时间 和状态
			envTaskDaoService.updateTaskStatus(TASK_STATUS.RUNNING, work_id);
			envTaskDaoService.updateTaskLogPath(log_path, work_id);
			envTaskDaoService.updateExeMethodById(EXE_METHOD.STEP, work_id);
		}
		Process proc = ProcessManager.instance.buildProcess(inst_info);
		int count  = inst_info.getPhaseCount();
		proc.addInterceptor(new TextLogInterceptor(log_path, count, "发布任务："+ work_id));
//		Process proc = ProcessManager.instance.buildProcess(inst_info);
//		int count = inst_info.getModuleCount();
//		proc.addInterceptor(new TextLogInterceptor(log_path, count, ""));
		// 更新表数据 任务执行信息表
		boolean flag = publishTaskInstanceService.dealExecutePhase(proc, work_id, inst_info.getInstance_id(), exe_bk_no, count, input.isSkip_flag());
//		boolean flag = publistTaskService.dealExecutePhase(proc, work_id, inst_info.getInstance_id(), exe_bk_no, count, input.isSkip_flag());
		if (!flag) {
			output.setNext_phase(-1);
		} else if (exe_bk_no == count) {
			output.setNext_phase(exe_bk_no);
		} else {
			output.setNext_phase(exe_bk_no + 1);
		}
		logger.debug("-----------ExeStepPublishTaskAction End------------");
		return output;
	}

	/**
	 * Description:
	 * @param arg0
	 * @return
	 */
	@Override
	protected String getLogTxt(ExeStepPublishTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("任务编号: " + input.getWork_id());
		log_lst.add("执行编号" + input.getPhase_id());
		return lgsvc.getLogTxt("单步执行发布任务", log_lst);
	}

	/**
	 * Description: 非空校验
	 * @param input
	 */
	private void checkNull(ExeStepPublishTaskInputBean input) {
		Assert.assertNotEmpty(input.getWork_id(), ExeStepPublishTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(input.getPhase_id(), ExeStepPublishTaskInputBean.PHASE_IDCN);
		envTaskPublicService.checkEnvTaskIsExist(input.getWork_id());
		envTaskPublicService.checkTaskBeforeExe(input.getWork_id());
	}

}
