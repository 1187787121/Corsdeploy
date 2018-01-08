/**
 * Title: EditBuildTaskAction.java
 * File Description: �༭������������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EditBuildTaskInputBean;
import com.wk.cd.build.ea.bean.EditBuildTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.build.exc.CanNotModifyTplException;
import com.wk.cd.build.exc.TaskCannotExecuteException;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: �༭������������
 * @author wangj
 */
public class EditBuildTaskAction extends ActionBasic<EditBuildTaskInputBean, EditBuildTaskOutputBean>{
	@Inject private GenNoService genSrv;
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	@Inject private ProjectPublicService projectPublicService;
	@Inject private PublishTaskInstanceService publistTaskService;
	@Inject private EnvironmentPublicService enviromentPublicService;
	@Inject private EnvTagStorageDaoService envTagSrv;
	@Inject private EnvBuildTaskDaoService envBuildTaskDaoService;
	@Inject private EnvTaskPublicService envTaskPublicService;
	private static final Log logger = LogFactory.getLog();
	//��ʼ������Ϊ��һ��
	private static final int BUILD_STEP_ID = 1;
	
	/** 
	 * Description: �༭������������
	 * @param input
	 * @return 
	 */
	@Override
	protected EditBuildTaskOutputBean doAction(EditBuildTaskInputBean input) {
		logger.info("-----------AddBuildTaskAction begin----------");
		
		EditBuildTaskOutputBean output = new EditBuildTaskOutputBean();
		String work_id = input.getWork_id();
		String env_name = input.getEnv_name();
		String task_bk_desc = input.getTask_bk_desc();
		String project_name = input.getProject_name();
		String template_name = input.getTemplate_name();
		
		//�ǿ�У��
		Assert.assertNotEmpty(task_bk_desc, EditBuildTaskInputBean.TASK_BK_DESCCN);
		Assert.assertNotEmpty(template_name, EditBuildTaskInputBean.TEMPLATE_NAMECN);
		
		//�Ϸ���У��
		if(!Assert.isEmpty(env_name))
		enviromentPublicService.checkEnvNameIsExist(env_name);
		if(!Assert.isEmpty(project_name))
		projectPublicService.checkProjectNameIsNotExist(project_name);
		//����ǰУ��
		checkTask(env_name, "");
		
		//����������������
		if(Assert.isEmpty(work_id)){
			//����������
			work_id = genSrv.getWorkCode(input.getDtbs_bk_date());
			
			//��ȡǰ�˸�·��
			String web_root_path = CfgTool.getProjectPropterty("web.root.path");
			if (Assert.isEmpty(web_root_path)) {
				throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
			}
			//���ɹ�����־ȫ·��
			String exelog_bk_path = envTaskPublicService.generateTaskRootPath(work_id) + work_id + "_log.txt";
			String log_relative_path = exelog_bk_path.replace(web_root_path, "");
			
			//����������
			EnvTaskInfo envTaskInfo = new EnvTaskInfo();
			envTaskInfo.setWork_id(work_id);
			envTaskInfo.setTask_type(TASK_TYPE.BUILD);
			envTaskInfo.setEnv_name(env_name);
			envTaskInfo.setExelog_bk_path(exelog_bk_path);
			envTaskInfo.setTask_bk_desc(task_bk_desc);
			envTaskInfo.setProject_name(project_name);
			envTaskInfo.setTask_status(TASK_STATUS.RUNNING);
			envTaskInfo.setCrt_user_id(input.getOrg_user_id());
			envTaskInfo.setCrt_bk_time(input.getDtbs_bk_time());
			envTaskInfo.setCrt_bk_date(input.getDtbs_bk_date());
			envTaskInfo.setExe_user_id(input.getOrg_user_id());
			envTaskInfo.setStart_bk_tm(JaDateTime.now());
			envTaskInfo.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
			
			//����������չ��
			EnvBuildTaskInfo envBuildTaskInfo = new EnvBuildTaskInfo();
			envBuildTaskInfo.setWork_id(work_id);
			envBuildTaskInfo.setTemplate_name(template_name);
			envBuildTaskInfo.setBuild_step_id(BUILD_STEP_ID);
			envBuildTaskInfo.setStart_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
			envBuildTaskInfo.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
			
			//����һ����¼
			logger.info("����������������: work_id = " + work_id + " env_name = " + env_name);
			envTaskDaoService.insertInfo(envTaskInfo);
			envBuildTaskDaoService.insertInfo(envBuildTaskInfo);
			output.setExelog_bk_path(log_relative_path);
			output.setBuild_step_id(1);
		//�޸Ļ�����������
		}else{
			//�Ϸ���У��
			envTaskPublicService.checkEnvTaskIsExist(work_id);
			logger.info("�޸Ļ���������: work_id=" + work_id + " task_bk_desc=" + task_bk_desc + " project_name=" + project_name + " template_name=" + template_name);
			//��Ӧ�ò�����ִ�У��򲻿��޸�ģ���������׳��쳣
			EnvBuildTaskInfo env_build_info = envBuildTaskDaoService.getInfoByKey(work_id);
			if(!template_name.equals(env_build_info.getTemplate_name()) && !Assert.isEmpty(env_build_info.getExelog_bk_path())){
				throw new CanNotModifyTplException();
			}
			//���޸�ģ����֮ǰ��һ�£�ɾ������ģ�������Ϣ
			if(!template_name.equals(env_build_info.getTemplate_name())){
				publistTaskService.deleteAllBuildTpInfo(work_id);
			}
			//�޸Ļ���������
			envTaskDaoService.updateBuildTaskInfoByWorkId(task_bk_desc, project_name, work_id);
			envBuildTaskDaoService.updateTemplateByWorkId(template_name, work_id);
			//��ȡ��־·��
			output.setExelog_bk_path(envTaskDaoService.getInfoByKey(work_id).getExelog_bk_path());
			output.setBuild_step_id(env_build_info.getBuild_step_id());
		}
		
		output.setWork_id(work_id);
		logger.info("-----------AddBuildTaskAction End----------");
		return output;
	}
	
	/**
	 * Description: ���黷�����Ƿ���������������ִ��
	 * @param env_anme
	 * @param work_id
	 */
	private void checkTask(String env_name, String work_id){
		// У�黷�����Ƿ������������
		List<EnvTaskInfo> task_info_list = envTaskDaoService.getIdByEnvExceptId(env_name, work_id);
		if (!Assert.isEmpty(task_info_list)) {
			for (EnvTaskInfo task_info : task_info_list) {// ����к��Լ���ID��һ���ľͱ���
				if (work_id.equalsIgnoreCase(task_info.getRol_work_id()) && TASK_STATUS.ROLLBACKING.equals(task_info.getTask_status())) {// ��������е� ������������һ�� �����������ڷ������ˣ�
					continue;
				}
				logger.debug("Ӱ��ִ�е�������[{}]", task_info.getWork_id());
				throw new TaskCannotExecuteException().addScene("REASON", "ִ�л����ϴ�����������ִ�е�����");
			}
		}
		List<EnvTagStorageInfo> tag_list = envTagSrv.getIdByEnv(env_name);
		if (!Assert.isEmpty(tag_list)) {
			throw new TaskCannotExecuteException().addScene("REASON", "ִ�л����ϴ�������ִ�е��������");
		}
	}
	
	/** 
	 * Description: �༭������������
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(EditBuildTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("����������������: " + input.getEnv_name());
		return lgsvc.getLogTxt("�༭������������", log_lst);
	}

}