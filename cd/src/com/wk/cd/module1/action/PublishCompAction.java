/**
 * Title: PublishCompAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年10月21日
 */
package com.wk.cd.module1.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.bean.PublishCompInputBean;
import com.wk.cd.module1.bean.PublishCompOutputBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.module1.info.MoComponentInfo;
import com.wk.cd.module1.service.CompCommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 发布组件或组件组
 * @author yangl
 */
public class PublishCompAction
		extends ActionBasic<PublishCompInputBean, PublishCompOutputBean> {

	@Inject
	private MoComponentDaoService moComponentDaoService;
	@Inject
	private CompCommonService compCommonService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 发布组件或组件组操作
	 * @param input
	 * @return
	 */
	@Override
	protected PublishCompOutputBean doAction(PublishCompInputBean input) {
		logger.info("----------------- PublishCompAction Begin ---------------");
		PublishCompOutputBean output = new PublishCompOutputBean();
		String comp_id = input.getId();
		MODULE_TYPE comp_type = input.getType();
		// 非空校检
		Assert.assertNotEmpty(comp_id, PublishCompInputBean.IDCN);
		Assert.assertNotEmpty(comp_type, PublishCompInputBean.TYPECN);
		// 合法性校检
		compCommonService.checkCompIdIsExist(comp_id, comp_type);
		MoComponentInfo cp_info = new MoComponentInfo();
		cp_info.setComponent_id(comp_id);
		// 修改发布状态
		moComponentDaoService.updateCompPubStateById(comp_id, PUBLISH_STATE.YES);
		logger.info("----------------- PublishCompAction End -----------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PublishCompInputBean input) {
		List<String> publish_list = new ArrayList<String>();
		publish_list.add(input.getId());
		return lgsvc.getLogTxt("发布组件/组件组", publish_list);
	}

}
