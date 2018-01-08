/**
 * Title: AddInteTaskAction.java
 * File Description: ���漯������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.AddInteTaskInputBean;
import com.wk.cd.build.ea.bean.AddInteTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.ea.service.InteTaskInstanceService;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.build.exc.TaskCannotExecuteException;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: ���漯������
 * @author Xul
 */
public class AddInteTaskAction extends ActionBasic<AddInteTaskInputBean, AddInteTaskOutputBean>{
	
	@Inject private GenNoService genSrv;
	@Inject private EnvProgPublicService progPubSrv;
	@Inject private ProjectPublicService pjPubSrv;
	@Inject private EnvironmentPublicService envPubSrv;
	@Inject private EnvTaskPublicService taskPubSrv;
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private EnvTagStorageDaoService envTagSrv;
	@Inject private PgInteStepDaoService pgInteStepSrv;
	@Inject private InteTaskInstanceService inteTaskInstanceService;
	@Inject private PublishTaskInstanceService publishTaskInstanceService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ���漯������
	 * @param input
	 * @return 
	 */
	@Override
	protected AddInteTaskOutputBean doAction(AddInteTaskInputBean input) {
		logger.info("-----------------AddInteTaskAction Begin------------------");
		AddInteTaskOutputBean output = new AddInteTaskOutputBean();
		
		//�ǿ�У��
		String work_id = input.getWork_id();
		String env_name = input.getEnv_name();
		String task_bk_desc = input.getTask_bk_desc();
		String project_name = input.getProject_name();
		String prog_id = input.getProg_id();
		String vercode_ver_num = input.getVercode_ver_num();
		Assert.assertNotEmpty(env_name, AddInteTaskInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(task_bk_desc, AddInteTaskInputBean.TASK_BK_DESCCN);
		Assert.assertNotEmpty(prog_id, AddInteTaskInputBean.PROG_IDCN);
//		Assert.assertNotEmpty(vercode_ver_num, AddInteTaskInputBean.VERCODE_VER_NUMCN);
		
		//�Ϸ���У��
		envPubSrv.checkEnvNameIsExist(env_name);
		if(!Assert.isEmpty(project_name)){
			pjPubSrv.checkProjectNameIsNotExist(project_name);
		}
		progPubSrv.checkProgIdIsNotExist(prog_id);
		
		String src_inst_id = "";
		//��Ϊ��һ�α���
		if(Assert.isEmpty(work_id)){
			//����ǰУ��
			checkTask(env_name, "");
			//����������
			work_id = genSrv.getWorkCode(input.getDtbs_bk_date());
		}else{
			//����ǰУ��
			taskPubSrv.checkEnvTaskIsExist(work_id);
			checkTask(env_name, work_id);
			EnvTaskInfo info = taskSrv.getInfoByKey(work_id);
			src_inst_id = info.getInstance_id();
		}
		
		//��ʽ������Դ��汾��
		vercode_ver_num = formatCodeVerNum(vercode_ver_num);
		//��ѯ���ɷ���������Ϣ
		List<PgInteStepInfo> pg_step_list = pgInteStepSrv.getInteListByProgId(prog_id);
//		String inst_id = inteTaskSrv.saveInstance2Xml(pg_step_list, env_name, vercode_ver_num);
		//��ȡʵ��ID
		if(Assert.isEmpty(src_inst_id)) {
			src_inst_id = UUID.randomUUID().toString().replaceAll("-", "");
		}
		//����ʵ����ŵ�����
		Instance instance = inteTaskInstanceService.generateInstance(pg_step_list, env_name, vercode_ver_num, src_inst_id);
		XmlWriter.write(instance);
		// ����ʵ��ִ����Ϣ��
		publishTaskInstanceService.insertInstanceExe(instance);
		
		//������������ִ�б�
		logger.info("ɾ����������ִ�б�");
		taskPubSrv.deleteEnvTaskExeInfoList(src_inst_id);
		logger.info("������������ִ�б�");
		taskPubSrv.addEnvTaskExeInfoList(src_inst_id, pg_step_list);
		
		//���漯������
		JaDate d = input.getDtbs_bk_date();
		JaTime t = input.getDtbs_bk_time();
		JaDateTime jt = JaDateTime.valueOf(d.getYear(), d.getMonth(), d.getDay(), t.getHour(), t.getMinute(), t.getSecond(), t.getMillisecond());
		logger.info("ɾ�����������");
		taskSrv.deleteByKey(work_id);
		logger.info("���滷�������");
		EnvTaskInfo info = new EnvTaskInfo();
		info.setWork_id(work_id);
		info.setTask_type(TASK_TYPE.INTEGRATION);
		info.setTask_bk_desc(task_bk_desc);
		info.setEnv_name(env_name);
		info.setProject_name(project_name);
		info.setProg_id(prog_id);
		info.setInstance_id(src_inst_id);
		info.setVercode_ver_num(vercode_ver_num);
		info.setVertarget_ver_num(vercode_ver_num);
		info.setTask_status(TASK_STATUS.RUNNING);
		info.setCrt_bk_date(d);
		info.setCrt_bk_time(t);
		info.setCrt_user_id(input.getOrg_user_id());
		info.setExe_user_id(input.getOrg_user_id());
		info.setStart_bk_tm(jt);
		info.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
		taskSrv.insertInfo(info);
		
		//ɾ��ԭʵ��
//		if(!Assert.isEmpty(src_inst_id)){
//			String file_path = CfgTool.getWebRootPath() + XmlPathUtil.getRelativePathByCompId(src_inst_id, MODULE_TYPE.INSTANCE);
//			File inst = new File(file_path);
//			if(inst.exists()){
//				inst.delete();
//			}
//		}
		
		output.setWork_id(work_id);
		output.setNext_step(1);
		logger.info("-----------------AddInteTaskAction End------------------");
		
		return output;
	}

	/** 
	 * Description: ���漯������
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddInteTaskInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("�������ƣ�" + input.getEnv_name());
		lst_val.add("����������" + input.getTask_bk_desc());
		return lgsvc.getLogTxt("���漯������", lst_val);
	}
	
	/**
	 * Description: ��ʽ������Դ��汾��
	 * @return
	 */
	private String formatCodeVerNum(String code_ver_num){
		if(code_ver_num.startsWith("/")){
			code_ver_num = code_ver_num.replaceFirst("/", "");
		}else if(code_ver_num.endsWith("/")){
			code_ver_num = code_ver_num.substring(0, code_ver_num.length() - 1);
		}
		return code_ver_num;
	}
	
	/**
	 * Description: ���黷�����Ƿ���������������ִ��
	 * @param env_anme
	 * @param work_id
	 */
	private void checkTask(String env_name, String work_id){
		// У�黷�����Ƿ������������
		List<EnvTaskInfo> task_info_list = taskSrv.getIdByEnvExceptId(env_name, work_id);
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

}