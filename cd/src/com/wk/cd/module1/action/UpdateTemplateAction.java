package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.enu.TEMPLATE_FORMATE;
import com.wk.cd.module1.bean.UpdateTemplateInputBean;
import com.wk.cd.module1.bean.UpdateTemplateOutputBean;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.service.ModuleCommonService;
import com.wk.cd.module1.service.TemplateService;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * �޸�����ģ��
 * Class Description: 
 * @author 12049
 */
public class UpdateTemplateAction extends ActionBasic<UpdateTemplateInputBean, UpdateTemplateOutputBean> {

	@Inject
	private ActionLogPublicService lgsvc;

	@Inject
	private TemplateService tpTemplateService;

	@Inject
	private MoTemplateDaoService tpTemplateDaoService;

	@Inject
	private ModuleCommonService componentPublicService;
	private static final Log logger = LogFactory.getLog();

	protected UpdateTemplateOutputBean doAction(UpdateTemplateInputBean input) {
		logger.info("-----UpdateTemplateAction Begin-----");
		UpdateTemplateOutputBean output = new UpdateTemplateOutputBean();
		Template template = input.getTemplate();

		Assert.assertNotEmpty(template, "ģ����Ϣ");
		Assert.assertNotEmpty(template.getTemplate_cn_name(), "ģ����");
		Assert.assertNotEmpty(template.getTemplate_bk_desc(), "ģ������");
		Assert.assertNotEmpty(template.getTemplate_type(), "ģ������");
		Assert.assertNotEmpty(template.getOperating_system(), "����ϵͳ");

		tpTemplateService.checkTemplateNameIsExist(template.getTemplate_cn_name());

		logger.info("��ʼ����ģ�������ļ���["+ XmlPathUtil.getXmlPath(template) + "]");
		XmlWriter.write(template);

		MoTemplateInfo tpTemplateInfo = new MoTemplateInfo();
		tpTemplateInfo.setTemplate_name(template.getTemplate_cn_name());
		tpTemplateInfo = tpTemplateDaoService.getInfoByKeyForUpdate(tpTemplateInfo);
		tpTemplateInfo.setTemplate_bk_desc(template.getTemplate_bk_desc());
		tpTemplateInfo.setRef_module_id(template.getTemplate_id());
		tpTemplateInfo.setTemplate_type(template.getTemplate_type());
		tpTemplateInfo.setOperating_system(componentPublicService.generateIntString(template.getOperating_system()));
		tpTemplateInfo.setModify_bk_date(input.getDtbs_bk_date());
		tpTemplateInfo.setModify_bk_time(input.getDtbs_bk_time());
		tpTemplateInfo.setModify_user_id(input.getOrg_user_id());
		tpTemplateInfo.setTemplate_formate(TEMPLATE_FORMATE.XML);
		tpTemplateInfo.setPublish_state(PUBLISH_STATE.YES);
		logger.info("��ʼ����Ͷ��ģ���,TEMPLATE_NAME��[" + template.getTemplate_cn_name() + "]");
		tpTemplateDaoService.updateTmplateInfo(tpTemplateInfo);

//		componentPublicService.saveCompQuoteMsg(template.getModules(),template.getId(), MODULE_TYPE.TEMPLATE);
		logger.info("-----UpdateTemplateAction End-----");
		return output;
	}

	protected String getLogTxt(UpdateTemplateInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		return this.lgsvc.getLogTxt("�޸�����ģ��", log_lst);
	}
}