/**
 * Title: PageMineHistoryUnauthAction.java
 * File Description: 分页查询我的历史授权任务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月10日
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.PEND_TYPE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.HistoryWorkBean;
import com.wk.cd.work.wk.bean.PageMineHistoryUnauthInputBean;
import com.wk.cd.work.wk.bean.PageMineHistoryUnauthOutputBean;
import com.wk.cd.work.wk.bean.WkDealDetailBean;
import com.wk.cd.work.wk.bean.WorkStateDetailBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkWorkProcessDaoService;
import com.wk.cd.work.wk.info.WkWorkProcessInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询我的历史授权任务列表
 * @author Xul
 */
public class PageMineHistoryUnauthAction extends ActionBasic<PageMineHistoryUnauthInputBean, PageMineHistoryUnauthOutputBean>{
	
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ApPublicService apPubSrv;
	@Inject
	private WkWorkProcessDaoService wkproDaos;
	@Inject 
	private WkDealDetailDaoService wkDealDetailDaoService;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 分页查询我的历史授权任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected PageMineHistoryUnauthOutputBean doAction(
			PageMineHistoryUnauthInputBean input) {
		logger.info("**********PageMineHistoryUnauthAction Begin**********");
		PageMineHistoryUnauthOutputBean output = new PageMineHistoryUnauthOutputBean();
		String crt_user_id = input.getOrg_user_id();
		logger.debug("用户：[{}]", crt_user_id);
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		//非空校验	
		Assert.assertNotEmpty(crt_user_id,
				PageMineHistoryUnauthInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(start_recd,
				PageMineHistoryUnauthInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd,
				PageMineHistoryUnauthInputBean.LIMIT_RECDCN);
		
		List<HistoryWorkBean> unauth_history_list = wkPubSrv.pageMineUncheckOrUnauth(crt_user_id, DEAL_TYPE.AUTH, start_recd, limit_recd);
		if(!Assert.isEmpty(unauth_history_list)){
			for (int i = 0; i < unauth_history_list.size(); i++) {
				//查询任务处理明细
				List<WkDealDetailBean> detail_bean_list = wkDealDetailDaoService.queryDealDetailByWorkSeq(unauth_history_list.get(i).getPend_work_seq());
				if(Assert.isEmpty(detail_bean_list)){
					throw new RecordNotFoundException().addScene("TABLE", "任务处理明细表").addScene("FIELD", "");
				}
				//查询任务审批流程表
				List<WkWorkProcessInfo> process_info_list = wkproDaos.getInfoListByKey(unauth_history_list.get(i).getPend_work_seq());
				if(Assert.isEmpty(process_info_list)){
					throw new RecordNotFoundException().addScene("TABLE", "任务审批流程表").addScene("FIELD", "");
				}
				
				//生成任务状态列表
				List<WorkStateDetailBean> work_detail_list = new ArrayList<WorkStateDetailBean>();
				//是否有拒绝处理
				boolean isRefuse = false;
				for(int j = 0 ; j < process_info_list.size(); j++){
					WorkStateDetailBean bean = new WorkStateDetailBean();
					if(j == 0){
						bean.setDeal_bk_date(unauth_history_list.get(i).getCrt_bk_date());
						bean.setDeal_bk_time(unauth_history_list.get(i).getCrt_bk_time());
						bean.setDeal_user_cn_name(unauth_history_list.get(i).getCrt_user_cn_name());
						bean.setDeal_bk_desc(unauth_history_list.get(i).getApply_bk_expl());
						bean.setPend_type(PEND_TYPE.APPLY);
						bean.setWork_state(1);
					}else if(j > 0 && !isRefuse){
						bean.setDeal_bk_date(detail_bean_list.get(j-1).getDeal_bk_date());
						bean.setDeal_bk_time(detail_bean_list.get(j-1).getDeal_bk_time());
						bean.setDeal_user_cn_name(detail_bean_list.get(j-1).getUser_cn_name());
						bean.setDeal_bk_desc(detail_bean_list.get(j-1).getDeal_bk_desc());
						//设置处理方式
						if(DEAL_TYPE.RECHECK.equals(detail_bean_list.get(j-1).getDeal_type())){
							bean.setPend_type(PEND_TYPE.RECHECK);
						}else if(DEAL_TYPE.AUTH.equals(detail_bean_list.get(j-1).getDeal_type())){
							bean.setPend_type(PEND_TYPE.AUTH);
						}else if(DEAL_TYPE.EXE.equals(detail_bean_list.get(j-1).getDeal_type())){
							bean.setPend_type(PEND_TYPE.EXE);
						}else{
							bean.setPend_type(PEND_TYPE.CLOSE);
						}
						//设置任务执行状态
						if(DEAL_RES.REFUSE.equals(detail_bean_list.get(j-1).getDeal_res())){
							isRefuse = true;
							bean.setWork_state(2);
						}else{
							bean.setWork_state(1);
						}
					}else if(j<process_info_list.size()-1 && isRefuse){
						bean.setDeal_user_cn_name(process_info_list.get(j).getPend_user_cn_name());
						bean.setPend_type(process_info_list.get(j).getPend_type());
						bean.setWork_state(4);
					}else{
						bean.setDeal_bk_desc(detail_bean_list.get(detail_bean_list.size()-1).getDeal_bk_desc());
						bean.setDeal_bk_date(detail_bean_list.get(detail_bean_list.size()-1).getDeal_bk_date());
						bean.setDeal_bk_time(detail_bean_list.get(detail_bean_list.size()-1).getDeal_bk_time());
						bean.setDeal_user_cn_name(detail_bean_list.get(detail_bean_list.size()-1).getUser_cn_name());
						//设置处理方式
						if(DEAL_TYPE.EXE.equals(detail_bean_list.get(detail_bean_list.size()-1).getDeal_type())){
							bean.setPend_type(PEND_TYPE.EXE);
						}else{
							bean.setPend_type(PEND_TYPE.CLOSE);
						}
						//设置任务执行状态
						bean.setWork_state(1);
					}
					work_detail_list.add(bean);
				}
				unauth_history_list.get(i).setWork_detail_list(work_detail_list);
			}
		}
		int all_recd = wkPubSrv.countMineUncheckOrUnauth(crt_user_id, DEAL_TYPE.AUTH);
		
		output.setUnauth_history_list(unauth_history_list);
		output.setAll_recd(all_recd);
		
		logger.info("**********PageMineHistoryUnauthAction End**********");
		return output;
	}

	/** 
	 * Description: 分页查询我的历史授权任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageMineHistoryUnauthInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsrv.getLogTxt("分页查询我的历史授权任务列表", lst_val);
	}

}
