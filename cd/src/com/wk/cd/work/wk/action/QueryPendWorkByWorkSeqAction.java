/**
 * Title: QueryPendWorkByWorkSeqAction.java
 * File Description:��ѯ�����״̬����ϸ������ӿ�ֵ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-23
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.QueryPendWorkByWorkSeqInputBean;
import com.wk.cd.work.wk.bean.QueryPendWorkByWorkSeqOutputBean;
import com.wk.cd.work.wk.bean.QueryPendWorkOutputBean;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ��ѯ�����״̬����ϸ������ӿ�ֵ
 * @author tlw
 */
public class QueryPendWorkByWorkSeqAction
		extends
		ActionBasic<QueryPendWorkByWorkSeqInputBean, QueryPendWorkByWorkSeqOutputBean> {

	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * ��ѯ�����״̬����ϸ������ӿ�ֵ
	 * @param input ����ӿ�
	 * @return
	 */
	@Override
	protected QueryPendWorkByWorkSeqOutputBean doAction(
			QueryPendWorkByWorkSeqInputBean input) {
		QueryPendWorkByWorkSeqOutputBean output = new QueryPendWorkByWorkSeqOutputBean();
		String wrk_seq = input.getWrk_seq();
		logger.info("��ѯ����״̬����ϸ������ӿ���ϸ��Ϣ��pend_work_seq = [{}]", wrk_seq);
		Assert.assertNotEmpty(wrk_seq,
				QueryPendWorkByWorkSeqInputBean.WRK_SEQCN);
		QueryPendWorkOutputBean<Object> bean = wkPubSrv.queryPendWork(wrk_seq);
		output.setDw_state(bean.getDw_state());
		output.setDw_dtl(bean.getDw_dtl());
		output.setObj(bean.getObj());
		output.setNew_json_list(bean.getNew_json_list());
		return output;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QueryPendWorkByWorkSeqInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWrk_seq());
		return lgsrv.getLogTxt("��ѯ����״̬����ϸ������ӿ�", lst_val);
	}

}
