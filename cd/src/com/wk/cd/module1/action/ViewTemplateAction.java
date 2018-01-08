package com.wk.cd.module1.action;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.bean.ViewTemplateInputBean;
import com.wk.cd.module1.bean.ViewTemplateOutputBean;
import com.wk.cd.module1.dao.MoTemplateDaoService;
import com.wk.cd.module1.service.TemplateService;
import com.wk.cd.service.IViewActionBasic;
import com.wk.lang.Inject;

public class ViewTemplateAction extends
		IViewActionBasic<ViewTemplateInputBean, ViewTemplateOutputBean> {

	@Inject
	private MoTemplateDaoService tpTemplateDaoService;

	@Inject
	private TemplateService tpTemplateService;

	public ViewTemplateOutputBean checkTemplateName(ViewTemplateInputBean input) {
		ViewTemplateOutputBean output = new ViewTemplateOutputBean();

		if (Assert.isEmpty(input.getData())) {
			output.setResult(false);
			return output;
		}
		output.setResult(tpTemplateDaoService.getInfoByKey(input.getData()) != null);
		return output;
	}

	public ViewTemplateOutputBean getTemplateNamesByType(ViewTemplateInputBean input) {
		ViewTemplateOutputBean output = new ViewTemplateOutputBean();
		Assert.assertNotEmpty(input.getTemplate_type(), "ģ������");
		List<String> template_names = tpTemplateDaoService.queryTemplateNameByType(input.getTemplate_type());
		if (!Assert.isEmpty(template_names)) {
			output.setTemplate_names(template_names.toArray(new String[template_names.size()]));
		}
		return output;
	}

	public ViewTemplateOutputBean getPubAndRolTemplateList(ViewTemplateInputBean input) {
		ViewTemplateOutputBean output = new ViewTemplateOutputBean();
		List<String> pub_template_names = tpTemplateDaoService.queryTemplateNameByType(TEMPLATE_TYPE.PRODUCT);
		if (!Assert.isEmpty(pub_template_names)) {
			output.setPub_template_names(pub_template_names.toArray(new String[pub_template_names.size()]));
		}
		
		List<String> rol_template_names = tpTemplateDaoService.queryTemplateNameByType(TEMPLATE_TYPE.ROLLBACK);
		if (!Assert.isEmpty(rol_template_names)) {
			output.setRol_template_names(rol_template_names.toArray(new String[rol_template_names.size()]));
		}
		return output;
	}
}