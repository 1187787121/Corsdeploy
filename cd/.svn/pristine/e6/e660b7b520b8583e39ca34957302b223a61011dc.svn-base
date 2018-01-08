/**
 * Title: PageLogByLabelAction.java
 * File Description: 按照标记级别分页查询日志信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.bean.PageLogByLabelInputBean;
import com.wk.cd.system.lg.bean.PageLogByLabelOutputBean;
import com.wk.cd.system.lg.dao.LgLogLabelDaoService;
import com.wk.cd.system.lg.dao.LgLogMfDaoService;
import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 按照标记级别分页查询日志信息
 * @author tlw
 */
public class PageLogByLabelAction
		extends ActionBasic<PageLogByLabelInputBean, PageLogByLabelOutputBean> {
	@Inject
	private LgLogLabelDaoService lglabelsrv;
	@Inject
	private LgLogMfDaoService lgmfsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 按照标记级别分页查询日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected PageLogByLabelOutputBean doAction(PageLogByLabelInputBean input) {
		PageLogByLabelOutputBean output = new PageLogByLabelOutputBean();
		String user_id = input.getOrg_user_id();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		int log_label = input.getLog_label();
		logger.info("按照日志标记级别[" + log_label + "]查询日志信息");
		// 获取标记对应的日志流水号
		DBIterator<String> work_seq_iterator = lglabelsrv.getLogWorkSeqByLabel(
				user_id, log_label);
		// 获取日志信息
		List<LgLogMfInfo> log_list = lgmfsrv.getLogByWorkSeqList(
				work_seq_iterator, start_recd, limit_recd);
		output.setLog_list(log_list);
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(PageLogByLabelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("查询日志标记级别" + input.getLog_label());
		return lgsrv.getLogTxt("按照标记级别查询日志信息", lst_val);
	}

}
