/**
 * Title: QueryDownFileNameAction.java
 * File Description: 下载日志文件信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.bean.QueryDownFileNameInputBean;
import com.wk.cd.system.lg.bean.QueryDownFileNameOutputBean;
import com.wk.cd.system.lg.dao.LgLogDownDaoService;
import com.wk.cd.system.lg.info.LgLogDownInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description: 下载日志文件信息
 * @author tlw
 */
public class QueryDownFileNameAction
		extends
		ActionBasic<QueryDownFileNameInputBean, QueryDownFileNameOutputBean> {

	@Inject
	private LgLogDownDaoService lgdownsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 下载日志文件信息
	 * @param input 输入信息
	 * @return 下载日志文件信息
	 */
	@Override
	protected QueryDownFileNameOutputBean doAction(
			QueryDownFileNameInputBean input) {
		QueryDownFileNameOutputBean output = new QueryDownFileNameOutputBean();
		logger.info("*********QueryDownFileNameAction Begin************");
		String user_id = input.getOrg_user_id();
		JaDate crt_bk_date = input.getCrt_bk_date();
		Assert.assertNotEmpty(crt_bk_date,
				QueryDownFileNameInputBean.CRT_BK_DATECN);
		List<LgLogDownInfo> down_infos = new ArrayList<LgLogDownInfo>();
		DBIterator<LgLogDownInfo> down_iterator = lgdownsrv.getLogFileInfo(
				user_id, crt_bk_date);
		try {
			while (down_iterator.hasNext()) {
				down_infos.add(down_iterator.next());
			}
		} finally {
			down_iterator.close();
		}
		output.setLog_down_list(down_infos);
		return output;
	}

	/**
	 * 生成日志
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(QueryDownFileNameInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("查询日期" + input.getCrt_bk_date());
		return lgsrv.getLogTxt("查询下载日志文件信息", lst_val);
	}

}
