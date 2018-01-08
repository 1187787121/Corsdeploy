/**
 * Title: QueryTermByTermNoAction.java
 * File Description: 按照终端号查询终端配置信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.tm.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.tm.bean.QueryTermByTermNoInputBean;
import com.wk.cd.system.tm.bean.QueryTermByTermNoOutputBean;
import com.wk.cd.system.tm.dao.TmTermDaoService;
import com.wk.cd.system.tm.info.TmTermInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 按照终端号查询终端配置信息
 * @author tlw
 */
public class QueryTermByTermNoAction
		extends
		ActionBasic<QueryTermByTermNoInputBean, QueryTermByTermNoOutputBean> {

	@Inject
	private TmTermDaoService tmsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 按照终端号查询终端配置信息
	 * @param input 输入信息
	 * @return 终端配置信息
	 */
	@Override
	protected QueryTermByTermNoOutputBean doAction(
			QueryTermByTermNoInputBean input) {
		QueryTermByTermNoOutputBean output = new QueryTermByTermNoOutputBean();
		TmTermInfo info = new TmTermInfo();
		logger.info("***********QueryTermByTermNoAction Begin*************");
		String term_no = input.getTerm_no();
		Assert.assertNotEmpty(term_no, QueryTermByTermNoInputBean.TERM_NOCN);
		info.setTerm_no(term_no);
		output.setTm_term_info(tmsrv.getInfoByTermNo(info));
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(QueryTermByTermNoInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("终端号  " + input.getTerm_no());
		return lgsrv.getLogTxt("通过终端号查询终端信息", lst_val);
	}

}
