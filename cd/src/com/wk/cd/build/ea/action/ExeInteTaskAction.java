/**

 * Title: ExeInteTaskAction.java
 * File Description: 一键执行集成任务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExeInteTaskInputBean;
import com.wk.cd.build.ea.bean.ExeInteTaskOutputBean;
import com.wk.cd.build.ea.bean.ExeStepInteTaskInputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.InteTaskInstanceService;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 一键执行集成任务
 * @author Xul
 */
public class ExeInteTaskAction extends ActionBasic<ExeInteTaskInputBean, ExeInteTaskOutputBean>{
	
	@Inject private EnvTaskPublicService taskPubSrv;
	@Inject private InstanceExeDaoService instanceExeDaoService;
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private CmSeqDaoService cmsvc;
	@Inject private InteTaskInstanceService inteTaskInstanceService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 一键执行集成任务
	 * @param input
	 * @return 
	 */
	@Override
	protected ExeInteTaskOutputBean doAction(ExeInteTaskInputBean input) {
		logger.info("-----------------ExeInteTaskAction Begin------------------");
		ExeInteTaskOutputBean output = new ExeInteTaskOutputBean();
		
		/**
		 * -----------------------------执行前准备-----------------------------
		 */
		// 非空校验
		String work_id = input.getWork_id();
		Assert.assertNotEmpty(work_id, ExeStepInteTaskInputBean.WORK_IDCN);
		// 执行前校验
		logger.debug("执行前校验, 任务ID:[{}]", work_id);
		taskPubSrv.checkTaskBeforeExe(work_id);
		// 插入执行动作
		taskSrv.updateExeMethodById(EXE_METHOD.TOEND, work_id);
		
		//读取实例并执行
		Instance inst = inteTaskInstanceService.readInstanceByWorkId(work_id);
		Process proc = ProcessManager.instance.buildProcess(inst);
		// 从XML读取实例
//		InstanceInfo inst = inteTaskSrv.readInstanceByXml(work_id);
//		Process proc = ProcessManager.instance.buildProcess(inst);
		// 获取总步骤数
		int all_steps = inst.getPhaseCount();
//		int all_steps = inst.getModuleCount();
		// 添加任务执行日志
		inteTaskInstanceService.addExeLog(work_id, 1, proc, all_steps);
//		inteTaskSrv.addExeLog(work_id, 1, proc, all_steps);
		
		/**
		 * -----------------------------执行主程序-----------------------------
		 */
		// 初始化执行结果标志
		boolean exe_flag = true;
		// 遍历执行所有步骤
		for(int step_id = 1 ; step_id <= all_steps ; step_id++){
			logger.debug("一键执行集成任务--现在执行的是任务:[{}], 第[{}]步", work_id, step_id);
			// 修改执行开始时间
			instanceExeDaoService.updateExecuteStartTime(JaDateTime.now(), inst.getInstance_id(), step_id);
			// 修改执行状态为执行中
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.RUNNING, inst.getInstance_id(), step_id);
			// 将执行前的sql提交
			cmsvc.getSession().commitAndResume();
			
			// 按步骤执行任务
			Result result = inteTaskInstanceService.executeTaskByStep(work_id, step_id, proc, false);
//			Result result = inteTaskSrv.executeTaskByStep(work_id, step_id, proc, false);
			// 根据步骤类型保存必要的信息
			String msg = result.getMsg();
			taskPubSrv.saveInfoByType(work_id, step_id, msg, input, false);
			// 若执行信息长度大于450，则截取多余部分
			logger.debug("任务：[{}], 第[{}]步执行日志：[{}]", work_id, step_id, msg);
			if(msg.length() >= 450){
				msg = msg.substring(0, 450);
			}
			
			// 修改执行状态为已执行（根据执行结果分别为成功，失败）
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.EXECUTED, inst.getInstance_id(), step_id);
			if(CMD_STATUS.SUCCEED.equals(result.getStatus())){
				logger.debug("执行任务编号[{}], 第[{}]步成功", work_id, step_id);
				instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.SUCCESS, JaDateTime.now(), 0, inst.getInstance_id(), step_id);
				cmsvc.getSession().commitAndResume();
			}else if(CMD_STATUS.ERROR.equals(result.getStatus())){
				logger.debug("执行任务编号[{}], 第[{}]步失败", work_id, step_id);
				instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.FAIL, JaDateTime.now(), 0, inst.getInstance_id(), step_id);
				// 若执行结果为失败，则终止执行
				exe_flag = false;
				break;
			}
		}
		// 根据执行状态更新任务明细
		taskSrv.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
		if(exe_flag){
			taskSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.SUCCESS, work_id);
		}else{
			taskSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.FAIL, work_id);
		}
		cmsvc.getSession().commitAndResume();
		
		logger.info("-----------------ExeInteTaskAction End------------------");
		return output;
	}

	/** 
	 * Description: 一键执行集成任务
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ExeInteTaskInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("任务编号：" + input.getWork_id());
		return lgsvc.getLogTxt("一键执行集成任务", lst_val);
	}

}
