/**
 * Title: ExeStepBuildDeployAction.java
 * File Description: ����ִ�й���Ӧ�ò���
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.ExeStepBuildDeployInputBean;
import com.wk.cd.build.ea.bean.ExeStepBuildDeployOutputBean;
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
 * Class Description: ����ִ�й���Ӧ�ò���
 * @author Xul
 */
public class ExeStepBuildDeployAction extends ActionBasic<ExeStepBuildDeployInputBean, ExeStepBuildDeployOutputBean>{
	
	@Inject private EnvTaskPublicService taskPubSrv;
	@Inject private InstanceExeDaoService instanceExeDaoService;
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private EnvBuildTaskDaoService envBuildSrv;
	@Inject private InteTaskInstanceService inteTaskInstanceService;
	@Inject private CmSeqDaoService cmsvc;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ����ִ�й���Ӧ�ò���
	 * @param input
	 * @return 
	 */
	@Override
	protected ExeStepBuildDeployOutputBean doAction(ExeStepBuildDeployInputBean input) {
		logger.debug("-----------ExeStepBuildDeployAction Begin------------");
		ExeStepBuildDeployOutputBean output = new ExeStepBuildDeployOutputBean();
		
		/**
		 * -----------------------------ִ��ǰ׼��-----------------------------
		 */
		// �ǿ�У��
		String work_id = input.getWork_id();
		int tpl_phase_id = input.getPhase_id();
		boolean skip_flag = input.isSkip_flag();
		Assert.assertNotEmpty(work_id, ExeStepBuildDeployInputBean.WORK_IDCN);
		Assert.assertNotEmpty(tpl_phase_id == 0 ? null : tpl_phase_id, ExeStepBuildDeployInputBean.PHASE_IDCN);
		// ִ��ǰУ��
		taskPubSrv.checkTaskBeforeExe(work_id);
		// ����ִ�ж���
		taskSrv.updateExeMethodById(EXE_METHOD.STEP, work_id);
		// ��XML��ȡʵ��
		Instance inst = inteTaskInstanceService.readInstanceByWorkId(work_id);
		Process proc = ProcessManager.instance.buildProcess(inst);
		// ��ȡ�ܽ׶�����(ʵ��)
		int all_phases = inst.getPhaseCount();
		// ��ȡ�ܽ׶�����(ģ��)
		int all_phases2 = instanceExeDaoService.getMaxTplPhase(inst.getInstance_id());
		// ��������ִ����־
		inteTaskInstanceService.addBuildExeLog(work_id, tpl_phase_id, proc, all_phases, inst.getInstance_id());
//		inteTaskSrv.addBuildExeLog(work_id, tpl_phase_id, proc, all_phases, inst.getInstance_id());
		// ����ģ��׶κŲ�ѯʵ���׶κ��б�
		List<Integer> phase_list = instanceExeDaoService.queryInstPhaseByTplPhase(inst.getInstance_id(), tpl_phase_id);
		// ��ʼ��ִ�н����־
		boolean exe_flag = true;
		// ����ʵ���׶��б�
		if(!Assert.isEmpty(phase_list)){
			for(Integer phase_id : phase_list){
				// �޸�ִ�п�ʼʱ�䲢�޸�ִ��״̬Ϊִ����
				instanceExeDaoService.updateExecuteStartTime(JaDateTime.now(), inst.getInstance_id(), phase_id);
				instanceExeDaoService.updateExecuteStatus(EXE_STATUS.RUNNING, inst.getInstance_id(), phase_id);
				
				// ��ִ��ǰ��sql�ύ
				cmsvc.getSession().commitAndResume();
				
				/**
				 * -----------------------------ִ��������-----------------------------
				 */
				// ���׶�ִ������
				Result result = inteTaskInstanceService.executeTaskByStep(work_id, phase_id, proc, skip_flag);
//				Result result = inteTaskSrv.executeTaskByStep(work_id, phase_id, proc, skip_flag);
				String msg = result.getMsg();
				// ��ִ����Ϣ���ȴ���450�����ȡ���ಿ��
				logger.debug("����[{}], ģ���[{}]�׶�ִ����־��[{}]", work_id, tpl_phase_id, msg);
				logger.debug("����[{}], ʵ����[{}]�׶�ִ����־��[{}]", work_id, phase_id, msg);
				if(msg.length() >= 450){
					msg = msg.substring(0, 450);
				}
				logger.info("ִ�н������޸���Ϣ");
				
				// �޸�ִ��״̬Ϊ��ִ�У�����ִ�н���ֱ�Ϊ�ɹ���ʧ�ܣ�������
				instanceExeDaoService.updateExecuteStatus(EXE_STATUS.EXECUTED, inst.getInstance_id(), phase_id);
				if(CMD_STATUS.SUCCEED.equals(result.getStatus())){
					logger.debug("ִ��������[{}], ��[{}]�׶γɹ�", work_id, phase_id);
					instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.SUCCESS, JaDateTime.now(), 0, inst.getInstance_id(), phase_id);
				}else if(CMD_STATUS.ERROR.equals(result.getStatus())){
					logger.debug("ִ��������[{}], ��[{}]�׶�ʧ��", work_id, phase_id);
					instanceExeDaoService.updateExecuteEnd(msg, EXE_RESULT.FAIL, JaDateTime.now(), 0, inst.getInstance_id(), phase_id);
					exe_flag = false;
					break;
				}else if(CMD_STATUS.SKIP.equals(result.getStatus())){
					logger.debug("ִ��������[{}], ��[{}]�׶�����", work_id, phase_id);
					instanceExeDaoService.updateExecuteStatus(EXE_STATUS.SKIP, inst.getInstance_id(), phase_id);
					instanceExeDaoService.updateExecuteEnd(msg, null, JaDateTime.now(), 0, inst.getInstance_id(), phase_id);
				}
				// ��ִ�н���ύ
				cmsvc.getSession().commitAndResume();
			}
		}
		
		//������һ�׶κţ�������ִ�н���ж�����ִ��״̬
		if(!exe_flag){
			envBuildSrv.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
			envBuildSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.FAIL, work_id);
			output.setNext_step(-1);
		}else if(all_phases2 == tpl_phase_id ){
			envBuildSrv.updateTaskStatus(TASK_STATUS.EXECUTED, work_id);
			envBuildSrv.updateExecuteEndTime(JaDateTime.now(), EXE_RESULT.SUCCESS, work_id);
			output.setNext_step(tpl_phase_id);
		}else{
			output.setNext_step(tpl_phase_id + 1);
		}
		
		
		logger.debug("-----------ExeStepBuildDeployAction End------------");
		return output;
	}

	/** 
	 * Description: ����ִ�й���Ӧ�ò���
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ExeStepBuildDeployInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("������: " + input.getWork_id());
		return lgsvc.getLogTxt("����ִ�й���Ӧ�ò���", log_lst);
	}

}