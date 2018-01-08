/**
 * Title: QueryPendWorkDetailByWorkSeqAction.java
 * File Description:ͨ��������ˮ�Ų�ѯ����������״̬��Ϣ 
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
import com.wk.cd.work.wk.bean.QueryPendWorkStateByWorkSeqInputBean;
import com.wk.cd.work.wk.bean.QueryPendWorkStateByWorkSeqOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:ͨ��������ˮ�Ų�ѯ����������״̬��Ϣ
 * @author tlw
 */
public class QueryPendWorkStateByWorkSeqAction
		extends
		ActionBasic<QueryPendWorkStateByWorkSeqInputBean, QueryPendWorkStateByWorkSeqOutputBean> {
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * ͨ��������ˮ�Ų�ѯ������������ϸ��Ϣ
	 * @param input ������Ϣ
	 * @return
	 */
	@Override
	protected QueryPendWorkStateByWorkSeqOutputBean doAction(
			QueryPendWorkStateByWorkSeqInputBean input) {
		QueryPendWorkStateByWorkSeqOutputBean output = new QueryPendWorkStateByWorkSeqOutputBean();
		String pend_work_seq = input.getWrk_seq();
		logger.info("��ѯ��ˮ��pend_work_seq = [{}]", pend_work_seq);
		// ��ˮ�ŷǿռ��
		Assert.assertNotEmpty(pend_work_seq,
				QueryPendWorkStateByWorkSeqInputBean.WRK_SEQCN);
		// ��ѯ����״̬��Ϣ
		WkDealStateInfo info = wkPubSrv.queryWorkStateByWorkSeq(pend_work_seq);
		output.setPend_work_seq(info.getPend_work_seq());
		output.setSubmit_work_seq(info.getSubmit_work_seq());
		output.setPend_work_code(input.getPend_work_code());
		output.setPend_srv_name(info.getPend_srv_name());
		output.setPend_rs_code(info.getPend_rs_code());
		output.setPend_ary_socname(info.getPend_ary_socname());
		output.setPendwk_bk_expl(info.getPendwk_bk_expl());
		output.setPend_deal_seq(info.getPend_deal_seq());
		output.setPend_dprl_code(info.getPend_user_id());
		output.setPbl_code(info.getPbl_code());
		output.setCrt_user_id(info.getCrt_user_id());
		output.setCrt_dept_id(info.getCrt_dept_id());
		output.setCrt_bk_date(info.getCrt_bk_date());
		output.setCrt_bk_time(info.getCrt_bk_time());
		output.setWorkflow_state(info.getWorkflow_state());
		return output;
	}

	/**
	 * ��ȡ��־��Ϣ
	 * @param input ������Ϣ
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(QueryPendWorkStateByWorkSeqInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWrk_seq());
		return lgsrv.getLogTxt("������ˮ�Ų�ѯ������ϸ", lst_val);
	}
}
