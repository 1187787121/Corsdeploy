/**
 * Title: ExePublishTaskAction.java
 * File Description: һ��ִ�з�������
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
import com.wk.cd.build.ea.bean.ExePublishTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
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
 * Class Description: һ��ִ�з�������
 * @author "Zhangj"
 */
public class ExePublishTaskAction
		extends ActionBasic<ExePublishTaskInputBean, ExePublishTaskOutputBean> {
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
	 * Description:һ��ִ�з�������
	 * @param arg0
	 * @return
	 */
	@Override
	protected ExePublishTaskOutputBean doAction(ExePublishTaskInputBean input) {
		logger.debug("-----------ExeStepPublishTaskAction Begin------------");
		ExePublishTaskOutputBean output = new ExePublishTaskOutputBean();
		// У��ǿպͺϷ���
		checkNull(input);
		String work_id = input.getWork_id();
		int exe_bk_no = input.getPhase_id() == 0 ? 1 : input.getPhase_id();
		// ��ȡִ��ʵ��
		Instance inst_info = publishTaskInstanceService.getInstanceByWorkId(work_id, exe_bk_no);
		int count = inst_info.getPhaseCount();
//		InstanceInfo inst_info = publistTaskService.getInstanceByWorkId(work_id, exe_bk_no);
//		int count = inst_info.getModuleCount();
		// ��ʼ�������������״̬
		String log_path = envTaskPublicService.generatePubExeLogPath(work_id);
		if (1 == exe_bk_no) {
			envTaskDaoService.updateTaskLogPath(log_path, work_id);
			envTaskDaoService.updateTaskStatus(TASK_STATUS.RUNNING, work_id);	
		}
		envTaskDaoService.updateExeMethodById(input.getExe_method(), work_id);
		// ����ִ�е�����
		Process proc = ProcessManager.instance.buildProcess(inst_info);
		proc.addInterceptor(new TextLogInterceptor(log_path, count, "��������"+ work_id));
//		Process proc = ProcessManager.instance.buildProcess(inst_info);
//		proc.addInterceptor(new TextLogInterceptor(log_path, count, work_id));
		for (int i = exe_bk_no; i < count + 1; i++) {
			boolean flag = publishTaskInstanceService.dealExecutePhase(proc, work_id,inst_info.getInstance_id(), i, count,false);
//			boolean flag = publistTaskService.dealExecutePhase(proc, work_id,inst_info.getInstanceId(), i, count,false);
			if (!flag) {
				envTaskDaoService.updateExecuteEndTime(JaDateTime.now(),
						EXE_RESULT.FAIL, work_id);
				cmsvc.getSession().commitAndResume();
				return output;
			}
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
	protected String getLogTxt(ExePublishTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("������: " + input.getWork_id());
		log_lst.add("ִ�б��" + input.getPhase_id());
		return lgsvc.getLogTxt("����ִ�з�������", log_lst);
	}
	/**
	 * Description: �ǿ�У��
	 * @param input
	 */
	private void checkNull(ExePublishTaskInputBean input){
		Assert.assertNotEmpty(input.getWork_id(),
				ExePublishTaskInputBean.WORK_IDCN);
		Assert.assertNotEmpty(input.getPhase_id(),
				ExePublishTaskInputBean.PHASE_IDCN);
		Assert.assertNotEmpty(input.getExe_method(), ExePublishTaskInputBean.EXE_METHODCN);
		envTaskPublicService.checkEnvTaskIsExist(input.getWork_id());
		envTaskPublicService.checkTaskBeforeExe(input.getWork_id());
	}

}