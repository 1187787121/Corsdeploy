/**
 * Title: PageUnauthWorkAction.java
 * File Description:查询待授权任务 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-7
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.PageUnhandleWorkInputBean;
import com.wk.cd.work.wk.bean.PageUnhandleWorkOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:查询待授权任务
 * 
 * @author tlw
 */
public class PageUnauthWorkAction extends
		ActionBasic<PageUnhandleWorkInputBean, PageUnhandleWorkOutputBean> {
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 查询待授权任务
	 * 
	 * @param input
	 *            输入信息
	 * @return 待授权任务信息
	 */
	@Override
	protected PageUnhandleWorkOutputBean doAction(
			PageUnhandleWorkInputBean input) {
		PageUnhandleWorkOutputBean output = new PageUnhandleWorkOutputBean();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		logger.info("*******************查询待授权任务开始********************");
		// 非空检查
		Assert.assertNotEmpty(start_recd,
				PageQueryActionRootInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd,
				PageQueryActionRootInputBean.LIMIT_RECDCN);
		List<WkDealStateInfo> unauth_work_list = wkPubSrv.pageUnauthWork(input
				.getOrg_user_id(), start_recd, limit_recd);
		output.setUnhandle_work_list(unauth_work_list);
		output.setAll_recd(wkPubSrv.countUnauthWork(input.getOrg_user_id()));
		return output;
	}

	/**
	 * 生成日志信息
	 * 
	 * @param input
	 *            输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(PageUnhandleWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsrv.getLogTxt("查询待授权任务", lst_val);
	}

}
