/**
 * Title: UpdateWkDealStateByKeyAction.java
 * File Description: 更新任务状态 ,写任务明细
 * @copyright: 2015
 * @company: CORSWORK
 * @author: qianc
 * @version: 1.0
 * @date: 2015-3-30
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.UpdateWkDealStateInputBean;
import com.wk.cd.work.wk.bean.UpdateWkDealStateOutputBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkDealStateDaoService;
import com.wk.cd.work.wk.info.WkDealDetailInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 更新任务状态 ,写任务明细
 * @author qianc
 */
public class UpdateWkDealStateByKeyAction 
	extends ActionBasic<UpdateWkDealStateInputBean, UpdateWkDealStateOutputBean>{
	@Inject
	private WkDealStateDaoService  wksdsrv;
	@Inject
	private WkDealDetailDaoService wkDetaiDaos;
	@Inject
	private WkDealDetailDaoService wkddsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();
	/**
	 * 
	 * @param input 输入接口
	 * @return
	 */
	protected UpdateWkDealStateOutputBean doAction(UpdateWkDealStateInputBean input) {
		UpdateWkDealStateOutputBean output = new UpdateWkDealStateOutputBean();
		logger.info("****************UpdateWkDealStateByKeyAction Begin***************");
		String pend_wk_seq = input.getPend_wk_seq();
		WORKFLOW_STATE workflow_state = input.getWorkflow_state();
		//非空检查
		Assert.assertNotEmpty(pend_wk_seq, UpdateWkDealStateInputBean.PEND_WK_SEQCN);
		Assert.assertNotEmpty(workflow_state, UpdateWkDealStateInputBean.WORKFLOW_STATECN);
		//更新任务状态
		wksdsrv.updateWorkStateByWorkSeq(pend_wk_seq,0,"","",workflow_state);
		//取改流水在任务处理明细表的现有条数 
		int count = wkDetaiDaos.countWorkDetailByWorkSeq(pend_wk_seq);
		WkDealDetailInfo  detailinfo = new WkDealDetailInfo();
		detailinfo.setPend_work_seq(input.getPend_wk_seq());
		detailinfo.setDeal_seq(count + 1);
		detailinfo.setDeal_type(DEAL_TYPE.CLOSE);
		detailinfo.setDeal_res(DEAL_RES.CLOSE);
		detailinfo.setDeal_user_id(input.getOrg_user_id());
		detailinfo.setDeal_bk_desc(input.getDeal_bk_desc());
		detailinfo.setDeal_bk_date(input.getDtbs_bk_date());
		detailinfo.setDeal_bk_time(input.getDtbs_bk_time());
		//插入一条记录
		wkddsrv.insertInfo(detailinfo);
		return output;
	}
	/**
	 * 
	 * @param input 输入接口
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(UpdateWkDealStateInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("待处理任务流水号 ：" + input.getPend_work_seq());
		lst_val.add("任务状态：" + input.getWorkflow_state().getCname());
		return lgsrv.getLogTxt("更新任务状态,,写任务明细", lst_val);
	}


}
