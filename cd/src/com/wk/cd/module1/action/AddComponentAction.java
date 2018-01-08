/**
 * Title: AddComponentAction.java
 * File Description: 新增组件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年12月8日
 */
package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.module1.bean.AddComponentInputBean;
import com.wk.cd.module1.bean.AddComponentOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.dao.MoTagsDaoService;
import com.wk.cd.module1.entity.Component;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.enu.COMPONENT_SOURCE;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.module1.service.CompCommonService;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.FileUtil;

/**
 * Class Description: 新增组件服务
 * @author xuph
 */
public class AddComponentAction extends ActionBasic<AddComponentInputBean, AddComponentOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private MoComponentDaoService moComponentDaoService;
	@Inject
	private MoTagsDaoService moTagsDaoService;
	@Inject
	private CompCommonService compCommonService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 新增组件操作
	 * @param input
	 * @return
	 */
	@Override
	protected AddComponentOutputBean doAction(AddComponentInputBean input) {
		logger.info("------------------AddComponentAction begin ---------------");
		AddComponentOutputBean output = new AddComponentOutputBean();
		// 获取参数
		Component component = input.getComponent();
		// 非空校检
		Assert.assertNotEmpty(component, AddComponentInputBean.COMPONENTCN);
		Assert.assertNotEmpty(component.getCn_name(), Component.COMPONENT_CN_NAMECN);
		Assert.assertNotEmpty(component.getBk_desc(), Component.COMPONENT_BK_DESCCN);
		Assert.assertNotEmpty(component.getImpl_type(), Component.IMPL_TYPECN);
		// 脚本来源
		if (component.getComponent_source() == COMPONENT_SOURCE.INPUT) {
			Assert.assertNotEmpty(component.getScript_list(), Component.SCRIPT_LISTCN);
			for (Script script : component.getScript_list()) {
				Assert.assertNotEmpty(script.getScript_type(), Script.SCRIPT_TYPECN);
				Assert.assertNotEmpty(script.getCmds(), Script.CMDSCN);
			}
		} else if (component.getComponent_source() == COMPONENT_SOURCE.FILE) {
			Assert.assertNotEmpty(component.getFile_path(), Component.FILE_PATHCN);
		}
		//引用组件判断
		if (Assert.notEmpty(component.getCheck_comp_id())) {
			compCommonService.checkCompIdIsExist(component.getCheck_comp_id(), MODULE_TYPE.COMPONENT);
		}
		//生成组件ID
		component.setId(genNoSrv.getCompCode(input.getDtbs_bk_date()));
		if (component.getComponent_source() == COMPONENT_SOURCE.FILE) {
			String web_root = CfgTool.getWebRootPath();
			String upload_path = component.getFile_path();
			String file_name = FileTool.getFileName(upload_path);
			String save_dir = XmlPathUtil.getComponentFileRelativeDir(component.getId());
			FileTool.mkdir(web_root + save_dir);
			FileUtil.fileMove(web_root + upload_path, web_root + save_dir + file_name);
			component.setFile_path(save_dir + file_name);
		}
		// 写入组件XML文件
		XmlWriter.write(component);
		// 保存组件信息表
		MoComponentInfo comp_info = new MoComponentInfo();
		comp_info.setComponent_id(component.getId());
		comp_info.setModule_type(MODULE_TYPE.COMPONENT);
		comp_info.setComponent_cn_name(component.getCn_name());
		comp_info.setImpl_type(component.getImpl_type());
		comp_info.setComponent_bk_desc(component.getBk_desc());
		comp_info.setComponent_source(component.getComponent_source());
		if(!Assert.isEmpty(component.getComponent_purposes())) {
			comp_info.setComponent_purposes(StringUtil.ary2str(component.getComponent_purposes()));
		}
		comp_info.setPublish_state(PUBLISH_STATE.NO);
		comp_info.setCrt_bk_date(input.getDtbs_bk_date());
		comp_info.setCrt_bk_time(input.getDtbs_bk_time());
		comp_info.setCrt_user_id(input.getOrg_user_id());
		comp_info.setModify_bk_date(input.getDtbs_bk_date());
		comp_info.setModify_bk_time(input.getDtbs_bk_time());
		comp_info.setModify_user_id(input.getOrg_user_id());
		logger.debug("新增组件信息表记录：COMPID=[" + component.getId() + "]");
		moComponentDaoService.insertInfo(comp_info);
//		moTagsDaoService.insertTags(component.getId(), component.getTag_list());
		logger.info("------------------AddComponentAction end -----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddComponentInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getComponent().getId());
		log_lst.add(input.getComponent().getCn_name());
		return lgsvc.getLogTxt("新增组件", log_lst);
	}

//	private void coModule(Component component) {
//		int[] purpose = component.getComponent_purposes();
//		String p = Arrays.toString(purpose);
//		if (p.contains("2")) {
//			CoProgramModuleBean bean = new CoProgramModuleBean();
//			bean.setImpl_type(component.getImpl_type().getValue());
//			bean.setModule_bk_desc(component.getBk_desc());
//			bean.setModule_cn_name(component.getCn_name());
//			bean.setModule_id(component.getId());
//			bean.setModule_purpose(2);
//			bean.setScript(component.getScript_list().get(0).getCmds());
//			bean.setScript_file(component.getFile_path());
//			CoProgramModuleBean.writeToXML(bean, bean.getModule_id());
//		}
//
//	}
}
