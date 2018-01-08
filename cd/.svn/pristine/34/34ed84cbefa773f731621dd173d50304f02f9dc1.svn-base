/**
 * Title: PageLogByDateAndLabelAction.java
 * File Description: 按照日期和标记分页查询日志信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.lg.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.bean.LogBean;
import com.wk.cd.system.lg.bean.PageLogByDateAndLabelInputBean;
import com.wk.cd.system.lg.bean.PageLogByDateAndLabelOutputBean;
import com.wk.cd.system.lg.dao.LgLogMfDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description: 按照日期和标记分页查询日志信息
 * @author tlw
 */
public class PageLogByDateAndLabelAction
		extends
		ActionBasic<PageLogByDateAndLabelInputBean, PageLogByDateAndLabelOutputBean> {
	@Inject
	private LgLogMfDaoService lgmfsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private UsGetUserInfoService usinfosrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 按照日期和标记分页查询日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected PageLogByDateAndLabelOutputBean doAction(
			PageLogByDateAndLabelInputBean input) {
		PageLogByDateAndLabelOutputBean output = new PageLogByDateAndLabelOutputBean();
		logger.info("**************PageLogByDateAction Begin***************");
		JaDate start_date = input.getStart_bk_date();
		JaDate end_date = input.getEnd_bk_date();
		String user_id = input.getOrg_user_id();
		int log_label = input.getLog_label();
		int log_level=input.getLog_level();
		Assert.assertNotEmpty(start_date,PageLogByDateAndLabelInputBean.START_BK_DATECN);
		Assert.assertNotEmpty(end_date,PageLogByDateAndLabelInputBean.END_BK_DATECN);
		Assert.assertNotEmpty(log_level, PageLogByDateAndLabelInputBean.LOG_LEVELCN);
		logger.info("获取日志列表信息");
		List<Integer> role_types=usinfosrv.queryRoleTypeByUserId(user_id);
		List<LogBean> log_info_list = lgmfsrv.pageLog(start_date, end_date, log_label, user_id,role_types,log_level, input.getStart_recd(), input
						.getLimit_recd());
		// 分页查询
		
		int count=lgmfsrv.countLog(start_date, end_date, log_label, user_id,role_types,log_level);
		output.setLog_list(log_info_list);
		output.setAll_recd(count);
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(PageLogByDateAndLabelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("起始日期" + input.getStart_bk_date());
		lst_val.add("截止日期" + input.getEnd_bk_date());
		return lgsrv.getLogTxt("分页查询日志信息", lst_val);
	}

}
