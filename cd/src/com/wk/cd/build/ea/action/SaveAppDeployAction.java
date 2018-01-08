/**
 * Title: SaveAppDeployAction.java
 * File Description: ����Ӧ�ò�����Ϣ
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.SaveAppDeployInputBean;
import com.wk.cd.build.ea.bean.SaveAppDeployOutputBean;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.service.InstanceGenerateService;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: ����Ӧ�ò�����Ϣ
 * @author Xul
 */
public class SaveAppDeployAction extends ActionBasic<SaveAppDeployInputBean, SaveAppDeployOutputBean>{
	
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private EnvBuildTaskDaoService envBuildTaskDaoService;
	@Inject private EnvProgPublicService envProgPublicService;
	@Inject private EnvTaskPublicService taskPubSrv;
	@Inject private PublishTaskInstanceService publishTaskInstanceService;
	@Inject private InstanceGenerateService instanceGenerateService;
	@Inject private MoTemplateDaoService moTemplateDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ����Ӧ�ò�����Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected SaveAppDeployOutputBean doAction(SaveAppDeployInputBean input) {
		logger.debug("-----------SaveAppDeployAction Begin------------");
		SaveAppDeployOutputBean output = new SaveAppDeployOutputBean();
		
		//�ǿ�У��
		String work_id = input.getWork_id();
		List<PhaseParam> param_list = input.getParam_list();
		Assert.assertNotEmpty(work_id, SaveAppDeployInputBean.WORK_IDCN);
		
		//У������ID�Ƿ����
		envTaskDaoService.checkEnvIdIsNotExist(work_id);
		//����ǰУ��
		taskPubSrv.checkTaskBeforeExe(work_id);
		
		//��ȡģ����
		EnvBuildTaskInfo env_build = envBuildTaskDaoService.getInfoByKey(work_id);
		if(!Assert.isEmpty(env_build)) {
			//ͨ��ģ������ȡģ��id
			String template_name = env_build.getTemplate_name();
			MoTemplateInfo templateInfo = moTemplateDaoService.getInfoByKey(template_name);
			if(!Assert.isEmpty(templateInfo)) {
				String template_id = templateInfo.getRef_module_id();
				Template template = new Template(template_id);
				template = XmlReader.read(template);
				//����ʵ���ļ�
				if(!Assert.isEmpty(template)) {
					//�׶���Ϣ
					List<Phase> phase_list = template.getPhase_list();
					//������Ϣ
//					List<PhaseParam> params = template.getParam_list();
					//ϵͳ����
					List<PhaseParam> system_params = new ArrayList<PhaseParam>();
					PhaseParam task_no1 = new PhaseParam();
					task_no1.setParam_name("task_no");
					task_no1.setParam_value(work_id);
					system_params.add(task_no1);
					// ��ȡִ��ʵ��id
					String instance_id = UUID.randomUUID().toString().replaceAll("-", "");
					Instance instance = instanceGenerateService.phaseListGenerate(phase_list, param_list, system_params, instance_id , null);
					com.wk.cd.module1.xml1.XmlWriter.write(instance);
					//����׶�ִ����Ϣ
					logger.info("����׶�ִ����Ϣ:������[{}]", work_id);
					publishTaskInstanceService.insertInstanceExe(instance);
					
					//��ʱ�ȴ��(uu_param)
					logger.info("��������б���Ϣ:������[{}]", work_id);
					String template_param_uuid = UUID.randomUUID().toString().replace("-", "");
					envProgPublicService.deleteUuParam(work_id);
					envProgPublicService.insertUuParamByParams(param_list, template_param_uuid);
					logger.info("���湹��������չ��:������[{}]", work_id);
					envBuildTaskDaoService.updateBuildParamInfoByKey(template_name, template_param_uuid, work_id);
					
					//���¹���������չ��
					envBuildTaskDaoService.updateBuildTaskInfo(TASK_STATUS.RUNNING, input.getOrg_user_id(), JaDateTime.now(), work_id);
					//����ʵ��ID
					envTaskDaoService.updateInstanceId(instance_id, work_id);
				}
			}
		}
		//��ȡ�׶��б�
//		List<PhasePublishBean> phases = publistTaskService.generatePhases(work_id, template_name);
		
		//���ϵͳ����
//		List<UuParamInfo> sys_param_list = new ArrayList<UuParamInfo>();
//		UuParamInfo info = new UuParamInfo();
//		info.setParam_name("task_no");
//		info.setParam_value(work_id);
//		sys_param_list.add(info);
		
		//���ɷ���ʵ��������ʵ����xml�ļ�
		
		// ��ȡִ��ʵ��
//		EnvTaskInfo env_task_info = envTaskDaoService.getInfoByKey(work_id);
//		String inst_id = env_task_info.getInstance_id();
//		logger.debug("��ȡʵ��Inst_id:[{}]", inst_id);
//		Instance instance = new Instance(inst_id);
//		instance = XmlReader.read(instance);
		//����׶�ִ����Ϣ
//		logger.info("����׶�ִ����Ϣ:������[{}]", work_id);
//		publishTaskInstanceService.insertInstanceExe(instance);
//		InstanceInfo inst = publistTaskService.generateInstance(param_list, phases, sys_param_list, template_name);
//		String inst_id = inst.getInstanceId();
//		XmlWriter.writerInstance(inst);
//		
//		
//		//����׶�ִ����Ϣ
//		logger.info("����׶�ִ����Ϣ:������[{}]", work_id);
//		publistTaskService.insertInstanceExe(inst);
		
		//��������б���Ϣ
//		logger.info("��������б���Ϣ:������[{}]", work_id);
//		String template_param_uuid = UUID.randomUUID().toString().replace("-", "");
//		envProgPublicService.deleteUuParam(work_id);
//		envProgPublicService.insertUuParam(param_list, template_param_uuid);
//		logger.info("���湹��������չ��:������[{}]", work_id);
//		envBuildTaskDaoService.updateBuildParamInfoByKey(template_name, template_param_uuid, work_id);
//		//���¹���������չ��
//		envBuildTaskDaoService.updateBuildTaskInfo(TASK_STATUS.RUNNING, input.getOrg_user_id(), JaDateTime.now(), work_id);
//		//����ʵ��ID
//		envTaskDaoService.updateInstanceId(inst_id, work_id);
//		
		logger.debug("-----------SaveAppDeployAction End------------");
		return output;
	}

	/** 
	 * Description: ����Ӧ�ò�����Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(SaveAppDeployInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("������: " + input.getWork_id());
		return lgsvc.getLogTxt("����Ӧ�ò�����Ϣ", log_lst);
	}

}
