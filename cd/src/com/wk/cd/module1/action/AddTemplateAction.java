package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.enu.TEMPLATE_FORMATE;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.service.ModuleCommonService;
import com.wk.cd.module1.service.TemplateService;
import com.wk.cd.module1.bean.AddTemplateInputBean;
import com.wk.cd.module1.bean.AddTemplateOutputBean;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class AddTemplateAction extends ActionBasic<AddTemplateInputBean, AddTemplateOutputBean> {

	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private TemplateService templateService;
	@Inject
	private MoTemplateDaoService moTemplateDaoService;
	@Inject
	private ModuleCommonService modulePublicService;
	@Inject
	private GenNoService genNoSrv;
	private static final Log logger = LogFactory.getLog();

	protected AddTemplateOutputBean doAction(AddTemplateInputBean input) {
		logger.info("-----AddTemplateAction Begin-----");
		AddTemplateOutputBean output = new AddTemplateOutputBean();
		Template template = input.getTemplate();
		Assert.assertNotEmpty(template, "模版信息");
		Assert.assertNotEmpty(template.getTemplate_cn_name(), "模板名");
		Assert.assertNotEmpty(template.getTemplate_bk_desc(), "模板描述");
		Assert.assertNotEmpty(template.getTemplate_type(), "模板类型");
		Assert.assertNotEmpty(template.getOperating_system(), "操作系统");
		Assert.assertNotEmpty(template.getPhase_list(), "阶段列表信息");
		//合法性校检
		templateService.checkTemplateNameIsRight(template.getTemplate_cn_name());
		templateService.checkTemplateNameIsNotExist(template.getTemplate_cn_name());
		template.setTemplate_id(genNoSrv.getCompCode(input.getDtbs_bk_date()));
		//写入XML模板文件
		logger.info("开始生成模版配置文件：["+ XmlPathUtil.getXmlPath(template) + "]");
		XmlWriter.write(template);
		MoTemplateInfo tpTemplateInfo = new MoTemplateInfo();
		tpTemplateInfo.setTemplate_name(template.getTemplate_cn_name());
		tpTemplateInfo.setTemplate_bk_desc(template.getTemplate_bk_desc());
		tpTemplateInfo.setTemplate_type(template.getTemplate_type());
		tpTemplateInfo.setOperating_system(modulePublicService.generateIntString(template.getOperating_system()));
		tpTemplateInfo.setRef_module_id(template.getTemplate_id());
		tpTemplateInfo.setScript_file_path(XmlPathUtil.getXmlPath(template));
		tpTemplateInfo.setCrt_bk_date(input.getDtbs_bk_date());
		tpTemplateInfo.setCrt_bk_time(input.getDtbs_bk_time());
		tpTemplateInfo.setCrt_user_id(input.getOrg_user_id());
		tpTemplateInfo.setTemplate_formate(TEMPLATE_FORMATE.XML);
		tpTemplateInfo.setPublish_state(PUBLISH_STATE.YES);
		logger.info("开始插入投产模板表,TEMPLATE_NAME：[" + template.getTemplate_cn_name() + "]");
		moTemplateDaoService.insertInfo(tpTemplateInfo);
		logger.info("-----AddTemplateAction End-----");
		return output;
	}

	protected String getLogTxt(AddTemplateInputBean input) {
		List<String> log_lst = new ArrayList<String>();

		return this.lgsvc.getLogTxt("新增流程模板", log_lst);
	}
}