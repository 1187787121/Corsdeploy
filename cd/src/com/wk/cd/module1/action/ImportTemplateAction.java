package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.module1.bean.ImportTemplateInputBean;
import com.wk.cd.module1.bean.ImportTemplateOutputBean;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class ImportTemplateAction extends
		ActionBasic<ImportTemplateInputBean, ImportTemplateOutputBean> {

	@Inject
	private GenNoService genNoSrv;

	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	protected ImportTemplateOutputBean doAction(ImportTemplateInputBean input) {
		logger.info("---------------ImportTemplateAction Begin------------------");
		ImportTemplateOutputBean output = new ImportTemplateOutputBean();
		String file_name = input.getFile_name();
		//非空判断
		Assert.assertNotEmpty(file_name, "文件名");
		//模板全路径
		String file_path = CfgTool.getWebRootPath()+XmlPathUtil.getRelativeUploadDirByModuleType(MODULE_TYPE.TEMPLATE)+"/"+file_name;
		logger.debug("--------Import ProcessTemplate File Fullpath [{}]----",file_path);
		Template template = XmlReader.read(file_path, Template.class);
		output.setTemplate(template);
		logger.info("---------------ImportTemplateAction Begin------------------");
		return output;
	}

	protected String getLogTxt(ImportTemplateInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getFile_name());
		return this.lgsvc.getLogTxt("流程模板导入", log_lst);
	}
}