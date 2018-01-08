/**
 * Title: QueryPendWorkDetailByWorkSeqAction.java
 * File Description:通过任务流水号查询待处理任务明细信息 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.QueryPendWorkDetailByWorkSeqInputBean;
import com.wk.cd.work.wk.bean.QueryPendWorkDetailByWorkSeqOutputBean;
import com.wk.cd.work.wk.info.WkDealDetailInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:通过任务流水号查询待处理任务明细信息
 * @author tlw
 */
public class QueryPendWorkDetailByWorkSeqAction
		extends
		ActionBasic<QueryPendWorkDetailByWorkSeqInputBean, QueryPendWorkDetailByWorkSeqOutputBean> {
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 通过任务流水号查询待处理任务明细信息
	 * @param input 输入信息
	 * @return
	 */
	@Override
	protected QueryPendWorkDetailByWorkSeqOutputBean doAction(
			QueryPendWorkDetailByWorkSeqInputBean input) {
		QueryPendWorkDetailByWorkSeqOutputBean output = new QueryPendWorkDetailByWorkSeqOutputBean();
		String pend_work_seq = input.getWrk_seq();
		logger.info("查询流水号pend_work_seq = [{}]", pend_work_seq);
		// 流水号非空检查
		Assert.assertNotEmpty(pend_work_seq,
				QueryPendWorkDetailByWorkSeqInputBean.WRK_SEQCN);
		List<WkDealDetailInfo> detail_list = wkPubSrv
				.queryWorkDetailListByWorkSeq(pend_work_seq);
		output.setDetail_list(detail_list);
		return output;
	}

	/**
	 * 获取日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(QueryPendWorkDetailByWorkSeqInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWrk_seq());
		return lgsrv.getLogTxt("根据流水号查询任务明细", lst_val);
	}
}
