/**
 * Title: PageUnhandleWorkAction.java
 * File Description: 查询需待复核或者待授权的任务
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-26
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

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
 * Class Description: 查询需待复核或者待授权的任务
 * 
 * @author tlw
 */
public class PageUnhandleWorkAction extends
		ActionBasic<PageUnhandleWorkInputBean, PageUnhandleWorkOutputBean> {
	@Inject
	private WorkPublicService daosrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 查询需待复核或者待授权的任务
	 * 
	 * @param input
	 *            输入信息
	 * @return
	 */
	@Override
	protected PageUnhandleWorkOutputBean doAction(
			PageUnhandleWorkInputBean input) {
		PageUnhandleWorkOutputBean output = new PageUnhandleWorkOutputBean();
		logger.info("*******PageUnhandleWorkAction Begin*********");
		String user_id = input.getOrg_user_id();
		List<WkDealStateInfo> infos = daosrv.pageUnhandleWork(user_id, input
				.getStart_recd(), input.getLimit_recd());
		output.setAll_recd(daosrv.countInfoNumByUser(user_id));
		output.setUnhandle_work_list(infos);
		return output;
	}

	/**
	 * Description: 查询需待复核或者待授权的任务
	 * 
	 * @param input
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(PageUnhandleWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsrv.getLogTxt("查询所有待处理任务", lst_val);
	}

}
