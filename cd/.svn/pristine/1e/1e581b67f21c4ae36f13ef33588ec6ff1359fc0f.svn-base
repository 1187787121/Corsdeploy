/**
 * Title: PageCeSystemListAction.java
 * File Description: 应用系统列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.PageSystemListBean;
import com.wk.cd.build.en.bean.PageSystemListInputBean;
import com.wk.cd.build.en.bean.PageSystemListOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.dao.CeSystemTemplateDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 应用系统列表
 * @author chiss
 */
public class PageSystemListAction extends
		ActionBasic<PageSystemListInputBean, PageSystemListOutputBean> {
	@Inject
	private CeSystemDaoService ceSystemDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
//	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 应用系统列表
	 * @param input
	 * @return
	 */
	@Override
	protected PageSystemListOutputBean doAction(PageSystemListInputBean input) {
//		logger.info("-----------PageSystemListAction begin----------");
		PageSystemListOutputBean output = new PageSystemListOutputBean();
		String sys_name = input.getSys_name();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		String order_col_name = input.getOrder_col_name();
		ORDER_TYPE order_type = input.getOrder_type();
		// 非空校验
		if (!Assert.isEmpty(sys_name)) {
			sys_name = sys_name.trim();
		}
		Assert.assertNotEmpty(start_recd, PageSystemListInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageSystemListInputBean.LIMIT_RECDCN);
		Assert.assertNotEmpty(order_col_name,
				PageSystemListInputBean.ORDER_COL_NAMECN);
		Assert.assertNotEmpty(order_type, PageSystemListInputBean.ORDER_TYPECN);
		// 分页查询所有应用系统并排序
		List<PageSystemListBean> sys_list = ceSystemDaoService.pageAllSystem(
				sys_name, sys_name, order_col_name, order_type, start_recd, limit_recd);
		if (!Assert.isEmpty(sys_list)) {
			for (PageSystemListBean sysInfo : sys_list) {
				int template_num = ceSystemTemplateDaoService
						.countSystemTemplateBySysName(sysInfo.getSys_name());
				sysInfo.setTemplate_num(template_num);
			}
		}
		int recd = ceSystemDaoService.countSystemBySysName(sys_name);
		output.setAll_recd(recd);
		output.setPage_sys_List(sys_list);
//		logger.info("-----------PageSystemListAction begin----------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageSystemListInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("sys_name: "+input.getSys_name());
		return lgsvc.getLogTxt("应用系统名称", log_lst);
	}

}
