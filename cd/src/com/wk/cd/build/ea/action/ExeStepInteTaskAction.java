/**
 * Title: ExeStepInteTaskAction.java
 * File Description: ����ִ�м�������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExeStepInteTaskInputBean;
import com.wk.cd.build.ea.bean.ExeStepInteTaskOutputBean;
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
 * Class Description: ����ִ�м�������
 * @author Xul
 */
public class ExeStepInteTaskAction extends ActionBasic<ExeStepInteTaskInputBean, ExeStepInteTaskOutputBean>{
	@Inject private EnvTaskPublicService taskPubSrv;
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private InstanceExeDaoService instanceExeDaoService;
	@Inject private CmSeqDaoService cmsvc;
	@Inject private InteTaskInstanceService inteTaskInstanceService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ����ִ�м�������
	 * @param input
	 * @return 
	 */
	@Override
	protected ExeStepInteTaskOutputBean doAction(ExeStepInteTaskInputBean input) {
		logger.info("-----------------ExeStepInteTaskAction Begin------------------");
		ExeStepInteTaskOutputBean output = new ExeStepInteTaskOutputBean();
		
		/**
		 * -----------------------------ִ��ǰ׼��-----------------------------
		 */
		// �ǿ�У��
		String work_id = input.getWork_id();
		int step_id = input.getStep_id();
		boolean skip_flag = input.isSkip_flag();
		Assert.assertNotEmpty(work_id, ExeStepInteTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(step_id == 0 ? null : step_id, ExeStepInteTaskInputBean.STEP_IDCN);
		// ִ��ǰУ��
		taskPubSrv.checkTaskBeforeExe(work_id);
		// ����ִ�ж���
		taskSrv.updateExeMethodById(EXE_METHOD.STEP, work_id);
		
		//��ȡʵ����ִ��
		Instance inst = inteTaskInstanceService.readInstanceByWorkId(work_id);
		Process proc = ProcessManager.instance.buildProcess(inst);
		
		// ��XML��ȡʵ��
//		InstanceInfo inst = inteTaskSrv.readInstanceByXml(work_id);
//		Process proc = ProcessManager.instance.buildProcess(inst);
		// �޸�ִ�п�ʼʱ�䲢�޸�ִ��״̬Ϊִ����
		instanceExeDaoService.updateExecuteStartTime(JaDateTime.now(), inst.getInstance_id(), step_id);
		instanceExeDaoService.updateExecuteStatus(EXE_STATUS.RUNNING, inst.getInstance_id(), step_id);
		// ��ȡ�ܲ�����
		int all_steps = inst.getPhaseCount();
		
//		int all_steps = inst.getModuleCount();
		// ��������ִ����־
//		inteTaskSrv.addExeLog(work_id, step_id, proc, all_steps);
		inteTaskInstanceService.addExeLog(work_id, step_id, proc, all_steps);
		// ��ִ��ǰ��sql�ύ
		cmsvc.getSession().commitAndResume();
		
		/**
		 * -----------------------------ִ��������-----------------------------
		 */
		// ������ִ������
		Result result = inteTaskInstanceService.executeTaskByStep(work_id, step_id, proc, skip_flag);
//		Result result = inteTaskSrv.executeTaskByStep(work_id, step_id, proc, skip_flag);
		// ���ݲ������ͱ����Ҫ����Ϣ
		String msg = result.getMsg();
		taskPubSrv.saveInfoByType(work_id, step_id, msg, input, skip_flag);
		// ��ִ����Ϣ���ȴ���450�����ȡ���ಿ��
		logger.debug("����[{}], ��[{}]��ִ����־��[{}]", work_id, step_id, msg);
		if(msg.length() >= 450){
			msg = msg.substring(0, 450);
		}
		logger.info("ִ�н������޸���Ϣ");
		
		// �޸�ִ��״̬Ϊ��ִ�У�����ִ�н���ֱ�Ϊ�ɹ���ʧ�ܣ�������
		instanceExeDaoService.updateExecuteStatus(EXE_STATUS.EXECUTED, inst.getInstance_id(), step_id);
		if(CMD_STATUS.SUCCEED.equals(result.getStatus())){
			logger.debug("ִ��������[{}], ��[{}]���ɹ�", work_id, step_id);
			instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.SUCCESS, JaDateTime.now(), 0, inst.getInstance_id(), step_id);
		}else if(CMD_STATUS.ERROR.equals(result.getStatus())){
			logger.debug("ִ��������[{}], ��[{}]��ʧ��", work_id, step_id);
			instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.FAIL, JaDateTime.now(), 0, inst.getInstance_id(), step_id);
		}else if(CMD_STATUS.SKIP.equals(result.getStatus())){
			logger.debug("ִ��������[{}], ��[{}]������", work_id, step_id);
			instanceExeDaoService.updateExecuteStatus(EXE_STATUS.SKIP, inst.getInstance_id(), step_id);
			instanceExeDaoService.updateExecuteEnd(msg, null, JaDateTime.now(), 0, inst.getInstance_id(), step_id);
		}
		
		//������һ����ţ�������ִ�н���ж�����ִ��״̬
		if(CMD_STATUS.ERROR.equals(result.getStatus())){
			taskSrv.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
			taskSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.FAIL, work_id);
			output.setNext_step(-1);
		}else if(all_steps == step_id ){
			taskSrv.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
			taskSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.SUCCESS, work_id);
			output.setNext_step(step_id);
		}else{
			output.setNext_step(step_id + 1);
		}
		
		logger.info("-----------------ExeStepInteTaskAction End------------------");
		return output;
	}

	/** 
	 * Description: ����ִ�м�������
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ExeStepInteTaskInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("�����ţ�" + input.getWork_id());
		lst_val.add("�����ţ�" + input.getStep_id());
		return lgsvc.getLogTxt("����ִ�м�������", lst_val);
	}

}