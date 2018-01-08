package com.wk.cd.module1.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.service.ModuleCommonService;
import com.wk.cd.module1.service.TemplateService;
import com.wk.cd.module1.bean.DeleteTemplateInputBean;
import com.wk.cd.module1.bean.DeleteTemplateOutputBean;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class DeleteTemplateAction extends
		ActionBasic<DeleteTemplateInputBean, DeleteTemplateOutputBean> {

	@Inject
	private ActionLogPublicService lgsvc;

	@Inject
	private MoTemplateDaoService moTemplateDaoService;

	@Inject
	private TemplateService templateService;

	@Inject
	private ModuleCommonService moduleCommonService;
	private static final Log logger = LogFactory.getLog();

	protected DeleteTemplateOutputBean doAction(DeleteTemplateInputBean input) {
		logger.info("**********************DeleteTemplateAction begin********************");
		DeleteTemplateOutputBean output = new DeleteTemplateOutputBean();
		String template_name = input.getTemplate_name();

		Assert.assertNotEmpty(template_name, "模板名");

		templateService.checkTemplateNameIsExist(template_name);

		String template_id = moTemplateDaoService.getRefModuleIdByName(template_name);
		//删除模板文件
		Template template = new Template();
		template.setTemplate_id(template_id);
		String full_path  = XmlPathUtil.getXmlPath(template);
		File file = new File(full_path);
		if(file.exists()) {
			file.delete();
		}
		//删除表信息
		MoTemplateInfo info = new MoTemplateInfo();
		info.setTemplate_name(template_name);
		logger.info("删除投产模版表记录，template_name：[" + template_name + "]");
		moTemplateDaoService.deleteTemplate(info);
		logger.info("**********************DeleteTemplateAction end********************");
		return output;
	}

	protected String getLogTxt(DeleteTemplateInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("template_name" + input.getTemplate_name());
		return this.lgsvc.getLogTxt("删除流程模板", log_lst);
	}
}