/**
 * Title: UpdateComponentAction.java
 * File Description: 修改组件服务
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
import com.wk.cd.module1.bean.UpdateComponentGroupInputBean;
import com.wk.cd.module1.bean.UpdateComponentGroupOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.entity.ComponentGroup;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.module1.service.CompCommonService;
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
 * Class Description: 修改组件服务
 * @author yangl
 */
public class UpdateComponentGroupAction
		extends ActionBasic<UpdateComponentGroupInputBean, UpdateComponentGroupOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private MoComponentDaoService moComponentDaoService;
	@Inject
	private CompCommonService compCommonService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 修改组件操作
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateComponentGroupOutputBean doAction(UpdateComponentGroupInputBean input) {
		logger.info("------------------UpdateComponentGroupAction begin ---------------");
		UpdateComponentGroupOutputBean output = new UpdateComponentGroupOutputBean();
		// 获取参数
		ComponentGroup group = input.getGroup();
		// 非空校检
		Assert.assertNotEmpty(group, AddComponentGroupInputBean.GROUPCN);
		Assert.assertNotEmpty(group.getId(), ComponentGroup.GROUP_IDCN);
		Assert.assertNotEmpty(group.getCn_name(), ComponentGroup.GROUP_CN_NAMECN);
		Assert.assertNotEmpty(group.getBk_desc(), ComponentGroup.GROUP_BK_DESCCN);
//		Assert.assertNotEmpty(group.getComponent_purposes(), ComponentGroup.GROUP_PURPOSESCN);
		// 写入组件Xml文件
		XmlWriter.write(group);
		// 保存组件信息表
		MoComponentInfo group_info = moComponentDaoService.getInfoByKeyForUpdate(group.getId());
		group_info.setComponent_cn_name(group.getCn_name());
		group_info.setComponent_bk_desc(group.getBk_desc());
		if(!Assert.isEmpty(group.getComponent_purposes())) {
			group_info.setComponent_purposes(StringUtil.ary2str(group.getComponent_purposes()));
		}
		group_info.setPublish_state(PUBLISH_STATE.NO);
		group_info.setModify_bk_date(input.getDtbs_bk_date());
		group_info.setModify_bk_time(input.getDtbs_bk_time());
		group_info.setModify_user_id(input.getOrg_user_id());
		logger.debug("更新组件信息表记录：COMPID=[" + group.getId() + "]");
		moComponentDaoService.updateModuleInfo(group_info);
		logger.info("------------------UpdateComponentGroupAction end -----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateComponentGroupInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getGroup().getId());
		log_lst.add(input.getGroup().getCn_name());
		return lgsvc.getLogTxt("修改组件组", log_lst);
	}
}
