/**
 * Title: EditBuildTaskPhaseAction.java
 * File Description: 编辑构建任务阶段信息
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月20日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EditBuildTaskPhaseInputBean;
import com.wk.cd.build.ea.bean.EditBuildTaskPhaseOutputBean;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.service.PublishTaskInstanceService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 编辑构建任务阶段信息
 * @author Xul
 */
public class EditBuildTaskPhaseAction extends ActionBasic<EditBuildTaskPhaseInputBean, EditBuildTaskPhaseOutputBean>{
	
	@Inject private EnvBuildTaskDaoService envBuildTaskDaoService;
	@Inject private PublishTaskInstanceService publishTaskInstanceService;
	@Inject private MoTemplateDaoService moTemplateDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 编辑构建任务阶段信息
	 * @param input
	 * @return 
	 */
	@Override
	protected EditBuildTaskPhaseOutputBean doAction(EditBuildTaskPhaseInputBean input) {
		logger.debug("-----------EditBuildTaskPhaseAction Start------------");
		EditBuildTaskPhaseOutputBean output = new EditBuildTaskPhaseOutputBean();
		
		//非空校验
		String work_id = input.getWork_id();
//		Template template = input.getTemplate();
		Phase phase = input.getPhase();
//		PhasePublishBean phase = input.getPhase();
		Assert.assertNotEmpty(work_id, EditBuildTaskPhaseInputBean.WORK_IDCN);
		
		//获取模板
		EnvBuildTaskInfo task_info = envBuildTaskDaoService.getInfoByKey(work_id);
		String template_name = task_info.getTemplate_name();
		
		//保存构建阶段表的基本信息
		publishTaskInstanceService.saveBuildStep(work_id, template_name, phase);
		
		//重新写入模板文件(加入数据源)
		MoTemplateInfo template_info = moTemplateDaoService.getInfoByKey(template_name);
		if(!Assert.isEmpty(template_info)) {
			String template_id = template_info.getRef_module_id();
			Template template = new Template(template_id);
			template = XmlReader.read(template);
			if(!Assert.isEmpty(template)) {
				List<Phase> phase_list = template.getPhase_list();
				//修改对应阶段(加入数据源)
				phase_list.set(phase.getPhase_no()-1, phase);
				//保存修改模板文件
				XmlWriter.write(template);
			}
		}
		//保存阶段信息
//		publistTaskService.saveBuildStep(phase, work_id, template_name);
		
		logger.debug("-----------EditBuildTaskPhaseAction End------------");
		return output;
	}

	/** 
	 * Description: 编辑构建任务阶段信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(EditBuildTaskPhaseInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("任务编号: " + input.getWork_id());
		return lgsvc.getLogTxt("编辑构建任务阶段信息", log_lst);
	}

}
