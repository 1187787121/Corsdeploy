/**
 * Title: ExeBuildDeployAction.java
 * File Description: һ��ִ�й���Ӧ�ò���
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExeBuildDeployInputBean;
import com.wk.cd.build.ea.bean.ExeBuildDeployOutputBean;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
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
 * Class Description: һ��ִ�й���Ӧ�ò���
 * @author Xul
 */
public class ExeBuildDeployAction extends ActionBasic<ExeBuildDeployInputBean, ExeBuildDeployOutputBean>{
	
	@Inject private EnvTaskPublicService taskPubSrv;
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private EnvBuildTaskDaoService envBuildSrv;
	@Inject private InstanceExeDaoService instanceExeDaoService;
	@Inject private CmSeqDaoService cmsvc;
	@Inject private InteTaskInstanceService inteTaskInstanceService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: һ��ִ�й���Ӧ�ò���
	 * @param input
	 * @return 
	 */
	@Override
	protected ExeBuildDeployOutputBean doAction(ExeBuildDeployInputBean input) {
		logger.debug("-----------ExeBuildDeployAction Begin------------");
		ExeBuildDeployOutputBean ouptut = new ExeBuildDeployOutputBean();
		
		/**
		 * -----------------------------ִ��ǰ׼��-----------------------------
		 */
		// �ǿ�У��
		String work_id = input.getWork_id();
		int tpl_phase_id = input.getPhase_id();
		Assert.assertNotEmpty(work_id, ExeBuildDeployInputBean.WORK_IDCN);
		Assert.assertNotEmpty(tpl_phase_id == 0 ? null : tpl_phase_id, ExeBuildDeployInputBean.PHASE_IDCN);
		
		// ִ��ǰУ��
		logger.debug("ִ��ǰУ��, ����ID:[{}]", work_id);
		taskPubSrv.checkTaskBeforeExe(work_id);
		// ����ִ�ж���
		taskSrv.updateExeMethodById(EXE_METHOD.TOEND, work_id);
		// ��XML��ȡʵ��
		Instance inst = inteTaskInstanceService.readInstanceByWorkId(work_id);
		Process proc = ProcessManager.instance.buildProcess(inst);
		// ��ȡ�ܽ׶���
		int all_phases = inst.getPhaseCount();
		//��������ִ����־
		inteTaskInstanceService.addBuildExeLog(work_id, tpl_phase_id, proc, all_phases, inst.getInstance_id());
		
		// ����ģ��׶κŲ�ѯʵ���׶κ��б�
		List<Integer> phase_list = instanceExeDaoService.queryInstPhaseByTplPhase(inst.getInstance_id(), tpl_phase_id);
		// ��ʼ��ִ�н����־
		boolean exe_flag = true;
		if(!Assert.isEmpty(phase_list)){
			//��ȡ��ǰʵ���׶κ�
			int phase_id = phase_list.get(0);
			
			/**
			 * -----------------------------ִ��������-----------------------------
			 */
			// ����ִ�����н׶�
			for(; phase_id <= all_phases ; phase_id++){
				logger.debug("һ��ִ�й���Ӧ�ò���--����ִ�е�������:[{}], ��[{}]�׶�", inst.getInstance_id(), phase_id);
				// �޸�ִ�п�ʼʱ��
				instanceExeDaoService.updateExecuteStartTime(JaDateTime.now(), inst.getInstance_id(), phase_id);
				// �޸�ִ��״̬Ϊִ����
				instanceExeDaoService.updateExecuteStatus(EXE_STATUS.RUNNING, inst.getInstance_id(), phase_id);
				
				// ��ִ��ǰ��sql�ύ
				cmsvc.getSession().commitAndResume();
				
				// ���׶�ִ������
				Result result = inteTaskInstanceService.executeTaskByStep(work_id, phase_id, proc, false);
//				Result result = inteTaskSrv.executeTaskByStep(work_id, phase_id, proc, false);
				String msg = result.getMsg();
				// ��ִ����Ϣ���ȴ���450�����ȡ���ಿ��
				logger.debug("����[{}], ��[{}]�׶�ִ����־��[{}]", work_id, phase_id, msg);
				if(msg.length() >= 450){
					msg = msg.substring(0, 450);
				}
				
				// �޸�ִ��״̬Ϊ��ִ�У�����ִ�н���ֱ�Ϊ�ɹ���ʧ�ܣ�
				instanceExeDaoService.updateExecuteStatus(EXE_STATUS.EXECUTED, inst.getInstance_id(), phase_id);
				if(CMD_STATUS.SUCCEED.equals(result.getStatus())){
					logger.debug("ִ��������[{}], ��[{}]�׶γɹ�", work_id, phase_id);
					instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.SUCCESS, JaDateTime.now(), 0, inst.getInstance_id(), phase_id);
					cmsvc.getSession().commitAndResume();
				}else if(CMD_STATUS.ERROR.equals(result.getStatus())){
					logger.debug("ִ��������[{}], ��[{}]�׶�ʧ��", work_id, phase_id);
					instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.FAIL, JaDateTime.now(), 0, inst.getInstance_id(), phase_id);
					// ��ִ�н��Ϊʧ�ܣ�����ִֹ��
					exe_flag = false;
					break;
				}
				// ��ִ�н���ύ
				cmsvc.getSession().commitAndResume();
			}
		}
		
		// ����ִ��״̬����������ϸ
		envBuildSrv.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
		if(exe_flag){
			envBuildSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.SUCCESS, work_id);
		}else{
			envBuildSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.FAIL, work_id);
		}
		cmsvc.getSession().commitAndResume();
		
		logger.debug("-----------ExeBuildDeployAction End------------");
		return ouptut;
	}

	/** 
	 * Description: һ��ִ�й���Ӧ�ò���
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ExeBuildDeployInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("������: " + input.getWork_id());
		return lgsvc.getLogTxt("һ��ִ�й���Ӧ�ò���", log_lst);
	}

}