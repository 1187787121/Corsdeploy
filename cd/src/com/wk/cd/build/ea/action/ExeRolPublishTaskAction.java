/**
 * Title: ExePublishTaskAction.java
 * File Description:  һ��ִ�з�����������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExePublishTaskInputBean;
import com.wk.cd.build.ea.bean.ExeRolPublishTaskInputBean;
import com.wk.cd.build.ea.bean.ExeRolPublishTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.EXE_RESULT;
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
import com.wk.util.JaDateTime;

/**
 * Class Description: һ��ִ�з�����������
 * @author "Zhangj"
 */
public class ExeRolPublishTaskAction
		extends
		ActionBasic<ExeRolPublishTaskInputBean, ExeRolPublishTaskOutputBean> {
	@Inject
	EnvTaskDaoService envTaskDaoService;
	@Inject
	EnvTaskPublicService envTaskPublicService;
	@Inject
	private CmSeqDaoService cmsvc;
	@Inject
	PublishTaskInstanceService publishTaskInstanceService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description:һ��ִ�з�����������
	 * @param arg0
	 * @return
	 */
	@Override
	protected ExeRolPublishTaskOutputBean doAction(
			ExeRolPublishTaskInputBean input) {
		logger.debug("-----------ExeStepPublishTaskAction Begin------------");
		ExeRolPublishTaskOutputBean output = new ExeRolPublishTaskOutputBean();
		checkNull(input);
		String work_id = input.getWork_id();
		int exe_bk_no = input.getPhase_id();
		// ��ȡ����
		envTaskPublicService.checkEnvTaskIsExist(work_id);
		EnvTaskInfo env_task_info = envTaskDaoService.getInfoByKey(work_id);
		// ��ʼ�������������״̬
		String rol_work_id = env_task_info.getRol_work_id();
		envTaskPublicService.checkEnvTaskIsExist(rol_work_id);
		envTaskPublicService.checkRolTaskBeforeExe(rol_work_id);
		String log_path = envTaskPublicService.generatePubExeLogPath(rol_work_id);
		if (1 == exe_bk_no) {// ��һ��ִ�� ��ʼ����ʼʱ�� ��״̬
			envTaskDaoService
					.updateTaskStatus(TASK_STATUS.ROLLBACKING, work_id);
			envTaskDaoService.updateExecuteStartTime(JaDateTime.now(), rol_work_id);
			envTaskDaoService.updateExecuteEndTime(JaDateTime.valueOf("1971-01-01 00:00:00"), null, rol_work_id);
			envTaskDaoService.updateTaskLogPath(log_path, rol_work_id);
		}
		// ��ȡʵ��
		Instance inst_info = publishTaskInstanceService.getInstanceByWorkId(work_id, exe_bk_no);
		Process proc = ProcessManager.instance.buildProcess(inst_info);
		int count  = inst_info.getPhaseCount();
		proc.addInterceptor(new TextLogInterceptor(log_path, count, "������������"+ work_id));
		
		
//		InstanceInfo inst_info = publistTaskService.getInstanceByWorkId(rol_work_id, exe_bk_no);
//		int count = inst_info.getModuleCount();
//		Process proc = ProcessManager.instance.buildProcess(inst_info);
//		proc.addInterceptor(new TextLogInterceptor(log_path, count, ""));
		for (int i = exe_bk_no; i < count + 1; i++) {
			boolean flag = publishTaskInstanceService.dealExecutePhase(proc, rol_work_id,inst_info.getInstance_id(), i, count,false);
//			boolean flag = publistTaskService.dealExecutePhase(proc, rol_work_id,inst_info.getInstanceId(), i, count,false);
			if (!flag) {
				envTaskDaoService.updateTaskStatus(TASK_STATUS.ROLLBACKFAIL,
						work_id);
				envTaskDaoService.updateExecuteEndTime(JaDateTime.now(),
						EXE_RESULT.FAIL, rol_work_id);
				cmsvc.getSession().commitAndResume();
				return output;
			}
		}
		envTaskDaoService.updateTaskStatus(TASK_STATUS.ROLLBACKOK, work_id);
		logger.debug("-----------ExeStepPublishTaskAction End------------");
		return output;
	}

	/**
	 * Description:
	 * @param arg0
	 * @return
	 */
	@Override
	protected String getLogTxt(ExeRolPublishTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("������: " + input.getWork_id());
		log_lst.add("ִ�б��" + input.getPhase_id());
		return lgsvc.getLogTxt("����ִ�з�������", log_lst);
	}
	
	/**
	 * Description: �ǿ�У��
	 * @param input
	 */
	private void checkNull(ExeRolPublishTaskInputBean input){
		Assert.assertNotEmpty(input.getWork_id(),
				ExePublishTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(input.getPhase_id(),
				ExePublishTaskInputBean.PHASE_IDCN);
	}

}