/**
 * Title: AddPublishTaskAction.java
 * File Description: �������񷢲���Ϣ ͬʱ���ɷ����ͻ���ʵ��
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.AddPublishTaskInputBean;
import com.wk.cd.build.ea.bean.AddPublishTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description:�������񷢲���Ϣ ͬʱ���ɷ����ͻ���ʵ��o
 * @author "Zhangj"
 */
public class AddPublishTaskAction
		extends ActionBasic<AddPublishTaskInputBean, AddPublishTaskOutputBean> {
	@Inject
	GenNoService genNoService;
	@Inject
	EnvTaskDaoService envTaskDaoService;
	@Inject
	EnvProgPublicService envProgPublicService;
	@Inject
	InstanceExeDaoService instanceExeDaoService;
	@Inject
	EnvTaskPublicService envTaskPublicService;
	@Inject
	PublishTaskInstanceService publishTaskInstanceService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	private static final String SUFFIX_ROLL = "rollback";

	/**
	 * Description:�������񷢲���Ϣ ͬʱ���ɷ����ͻ���ʵ��
	 * @param arg0
	 * @return
	 */
	@Override
	protected AddPublishTaskOutputBean doAction(AddPublishTaskInputBean input) {
		logger.debug("---------------AddPublishTaskAction_Begin------------------");
		AddPublishTaskOutputBean output = new AddPublishTaskOutputBean();
		// �ǿ�У��
		Assert.assertNotEmpty(input.getEnv_name(),AddPublishTaskInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(input.getTask_bk_desc(),AddPublishTaskInputBean.TASK_BK_DESCCN);
		Assert.assertNotEmpty(input.getProg_id(),AddPublishTaskInputBean.PROG_ID);
//		Assert.assertNotEmpty(input.getTarget_ver_num(),AddPublishTaskInputBean.TARGET_VER_NUMCN);
		logger.debug("������Ϊ[{}],�����汾��[{}],ʹ�÷������[{}]��ִ�з�������ʵ��",input.getEnv_name(), input.getTarget_ver_num(),input.getProg_id());
		
		String prog_id = input.getProg_id();
		String ver_num = input.getTarget_ver_num();
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		
		// �ж��Ƿ��л���ʵ�� ���
		String work_id = input.getWork_id();
		if(Assert.isEmpty(work_id)){
			work_id = genNoService.getWorkCode(input.getDtbs_bk_date());
		}else{
			// ������������work_id��ʾ�������½� ���֮ǰ�����ʵ��ɾ��
			envTaskPublicService.checkTaskBeforeExe(work_id);
			EnvTaskInfo env_task_info = envTaskDaoService.getInfoByKey(work_id);
			String instance_id = env_task_info.getInstance_id();
			String rol_instance_id = env_task_info.getRol_work_id();
			instanceExeDaoService.deleteByInstanceId(instance_id);
			instanceExeDaoService.deleteByInstanceId(rol_instance_id);
			envTaskDaoService.deleteByKey(work_id);
		}
		//���ɷ���ʵ��
		Instance pub_inst = publishTaskInstanceService.generateInstanceById(work_id,prog_id,ver_num);
		//����ʵ��ִ����Ϣ��
		publishTaskInstanceService.insertInstanceExe(pub_inst);
		//��ʼ��������������ʵ��
		EnvTaskInfo env_task_info = envTaskInfoMerge(work_id, input, false, pub_inst.getInstance_id());
		envTaskDaoService.insertInfo(env_task_info);
		
		Program program = new Program(prog_id);
		program = XmlReader.read(program);
		// �л���ģ��ʱ
		if (!Assert.isEmpty(program.getRol_template_name())) {
			String rol_work_id = genNoService.getWorkCode(input.getDtbs_bk_date());
			env_task_info.setRol_work_id(rol_work_id);
			Instance rol_inst = publishTaskInstanceService.generateInstanceById(rol_work_id,prog_id+SUFFIX_ROLL,ver_num);
			publishTaskInstanceService.insertInstanceExe(rol_inst);
			// ��ʼ�����˻�������ʵ��
			EnvTaskInfo rol_task_info = envTaskInfoMerge(rol_work_id, input, true, rol_inst.getInstance_id());
			envTaskDaoService.insertInfo(rol_task_info);
		}
		output.setWork_id(work_id);
		output.setExe_bk_no(1);
		
		// ���ɷ���ʵ��
//		InstanceInfo pub_inst = publistTaskService.generateInstanceById(prog_id,
//				pub_template_name, pg_rele_info.getPubl_param_uuid(), work_id,
//				ver_num);
//		// ��ʼ��������������ʵ��
//		EnvTaskInfo env_task_info = envTaskInfoMerge(work_id, input, false,
//				pub_inst.getInstanceId());
//		publistTaskService.insertInstanceExe(pub_inst);
//		if (!Assert.isEmpty(rol_template_name)) {
//			String rol_work_id = genNoService.getWorkCode(input
//					.getDtbs_bk_date());
//			env_task_info.setRol_work_id(rol_work_id);
//			// ���ɻ���ʵ��
//			InstanceInfo rol_inst = publistTaskService.generateInstanceById(
//					prog_id, rol_template_name,
//					pg_rele_info.getRoll_param_uuid(), rol_work_id, ver_num);
//			publistTaskService.insertInstanceExe(rol_inst);
//			// ��ʼ�����˻�������ʵ��
//			EnvTaskInfo rol_task_info = envTaskInfoMerge(rol_work_id, input,
//					true, rol_inst.getInstanceId());
//			envTaskDaoService.insertInfo(rol_task_info);
//		}
//		envTaskDaoService.deleteByKey(work_id);
//		envTaskDaoService.insertInfo(env_task_info);
//		output.setWork_id(work_id);
//		output.setExe_bk_no(1);
		logger.debug("---------------AddPublishTaskAction_End------------------");
		return output;
	}

	/**
	 * Description:
	 * @param arg0
	 * @return
	 */
	@Override
	protected String getLogTxt(AddPublishTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("�������: " + input.getProg_id());
		return lgsvc.getLogTxt("�������񷢲���Ϣ", log_lst);
	}

	/**
	 * Description: ��ʼ�������������ݣ������š�����������Ŀ��š�Ŀ��汾�š�������š��������ڡ�����ʱ�䡢�����û���ִ�п�ʼ����ʱ�䣩
	 * @param work_id
	 * @param rol_flag ���˱�־ true��ʾ���˵�
	 * @param input
	 */
	private EnvTaskInfo envTaskInfoMerge(String work_id,
			AddPublishTaskInputBean input, boolean rol_flag, String instance_id) {
		EnvTaskInfo info = new EnvTaskInfo();
		info.setWork_id(work_id);
		info.setEnv_name(input.getEnv_name());
		info.setProject_name(input.getProject_name());
		info.setTarget_ver_num(input.getTarget_ver_num());
		info.setProg_id(input.getProg_id());
		info.setCrt_bk_date(input.getDtbs_bk_date());
		info.setTask_bk_desc(input.getTask_bk_desc());
		info.setCrt_bk_time(input.getDtbs_bk_time());
		info.setCrt_user_id(input.getOrg_user_id());
		info.setStart_bk_tm(JaDateTime.now());
		info.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
		info.setInstance_id(instance_id);
		if (rol_flag) {
			info.setTask_type(TASK_TYPE.PUBLISHROLL);
		} else {
			info.setTask_status(TASK_STATUS.RUNNING);
			info.setTask_type(TASK_TYPE.PUBLISH);
		}
		return info;

	}

}