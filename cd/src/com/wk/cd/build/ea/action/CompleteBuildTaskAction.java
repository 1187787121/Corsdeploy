/**
 * Title: CompleteBuildTaskAction.java
 * File Description: ��ɻ�����������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016��12��13��
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.CompleteBuildTaskInputBean;
import com.wk.cd.build.ea.bean.CompleteBuildTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: ��ɻ�����������
 * @author wangj
 */
public class CompleteBuildTaskAction extends ActionBasic<CompleteBuildTaskInputBean, CompleteBuildTaskOutputBean>{
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: ��ɻ�����������
	 * @param input
	 * @return 
	 */
	@Override
	protected CompleteBuildTaskOutputBean doAction(
			CompleteBuildTaskInputBean input) {
		logger.info("-----------CompleteBuildTaskAction Start----------");
		
		CompleteBuildTaskOutputBean output = new CompleteBuildTaskOutputBean();
		String work_id = input.getWork_id();
		String exe_user_id = input.getOrg_user_id();
		
		//У��ǿ�
		Assert.assertNotEmpty(work_id, CompleteBuildTaskOutputBean.WORK_SEQCN);
		
		//У��Ϸ���
		envTaskDaoService.checkEnvIdIsNotExist(work_id);
		
		//���»������������״̬
		envTaskDaoService.updateBuildStatusByWorkId(TASK_STATUS.EXECUTED, JaDateTime.now(), exe_user_id, work_id);
		
		logger.info("-----------CompleteBuildTaskAction End----------");
		return output;
	}

	/** 
	 * Description: ��ɻ�����������
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(CompleteBuildTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("��������������: " + input.getWork_id());
		return lgsvc.getLogTxt("��ɻ�����������", log_lst);
	}

}
