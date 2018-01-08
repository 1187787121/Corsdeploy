/**
 * Title: AddComponentGroupAction.java
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

import com.wk.cd.module1.bean.AddComponentGroupInputBean;
import com.wk.cd.module1.bean.AddComponentGroupOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.entity.ComponentGroup;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 新增组件服务
 * @author xuph
 */
public class AddComponentGroupAction
		extends ActionBasic<AddComponentGroupInputBean, AddComponentGroupOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private MoComponentDaoService moComponentDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 新增组件操作
	 * @param input
	 * @return
	 */
	@Override
	protected AddComponentGroupOutputBean doAction(AddComponentGroupInputBean input) {
		logger.info("------------------AddComponentGroupAction begin ---------------");
		AddComponentGroupOutputBean output = new AddComponentGroupOutputBean();
		
		// 获取参数
		ComponentGroup group = input.getGroup();
		
		// 非空校检
		Assert.assertNotEmpty(group, AddComponentGroupInputBean.GROUPCN);
		Assert.assertNotEmpty(group.getCn_name(), ComponentGroup.GROUP_CN_NAMECN);
		Assert.assertNotEmpty(group.getBk_desc(), ComponentGroup.GROUP_BK_DESCCN);
	//	Assert.assertNotEmpty(group.getComponent_purposes(), ComponentGroup.GROUP_PURPOSESCN);
		group.setId(genNoSrv.getCompCode(input.getDtbs_bk_date()));
		
		// 写入组件组XML文件
		XmlWriter.write(group);
		
		// 保存组件信息表
		MoComponentInfo comp_info = new MoComponentInfo();
		comp_info.setComponent_id(group.getId());
		comp_info.setModule_type(MODULE_TYPE.GROUP);
		comp_info.setComponent_cn_name(group.getCn_name());
		comp_info.setComponent_bk_desc(group.getBk_desc());
		if(!Assert.isEmpty(group.getComponent_purposes())) {
			comp_info.setComponent_purposes(StringUtil.ary2str(group.getComponent_purposes()));
		}
		comp_info.setPublish_state(PUBLISH_STATE.NO);
		comp_info.setCrt_bk_date(input.getDtbs_bk_date());
		comp_info.setCrt_bk_time(input.getDtbs_bk_time());
		comp_info.setCrt_user_id(input.getOrg_user_id());
		logger.debug("新增组件信息表记录：GROUPID=[" + group.getId() + "]");
		moComponentDaoService.insertInfo(comp_info);
		logger.info("------------------AddComponentGroupAction end -----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddComponentGroupInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getGroup().getId());
		log_lst.add(input.getGroup().getCn_name());
		return lgsvc.getLogTxt("新增组件组", log_lst);
	}
}
