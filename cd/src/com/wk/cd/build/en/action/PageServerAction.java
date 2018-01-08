/**
 * Title: PageServerAction.java
 * File Description: 服务器分页查询搜索服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.PageServerBean;
import com.wk.cd.build.en.bean.PageServerInputBean;
import com.wk.cd.build.en.bean.PageServerOutputBean;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 服务器分页查询搜索服务
 * @author yangl
 */
public class PageServerAction
		extends ActionBasic<PageServerInputBean, PageServerOutputBean> {
	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 服务器分页查询搜索
	 * @param input
	 * @return
	 */
	@Override
	protected PageServerOutputBean doAction(PageServerInputBean input) {
		logger.info("--------------PageServerOutputBean Begin-------------");
		PageServerOutputBean output = new PageServerOutputBean();
		int limit_recd = input.getLimit_recd();
		int start_recd = input.getStart_recd();
		String server_name = input.getCe_server_name();
		String server_ip = input.getServer_ip();
		String order_col_name = input.getOrder_col_name();
		ORDER_TYPE order_type = input.getOrder_type();
		// 非空校验
		Assert.assertNotEmpty(start_recd, PageServerInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageServerInputBean.LIMIT_RECDCN);
		Assert.assertNotEmpty(order_col_name,PageServerInputBean.ORDER_COL_NAMECN);
		Assert.assertNotEmpty(order_type, PageServerInputBean.ORDER_TYPECN);
		// 分页查询应用环境信息
		List<PageServerBean> server_list = ceServerDaoService
				.pageServerByNameAndIP(server_name, server_ip, order_col_name,
						order_type, start_recd, limit_recd);
		output.setServer_list(server_list);
		int all_recd = ceServerDaoService.countServerByNameAndIP(server_name,
				server_ip);
		output.setAll_recd(all_recd);
		logger.info("-------------PageServerOutputBean End--------------");
		return output;
	}

	/**
	 * Description:服务器分页查询搜索
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(PageServerInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("server_name=" + input.getServer_name());
		lst_val.add("server_ip=" + input.getServer_ip());
		return lgsvc.getLogTxt("服务器分页查询搜索", lst_val);
	}

}
