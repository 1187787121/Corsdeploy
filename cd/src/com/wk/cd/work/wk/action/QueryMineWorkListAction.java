/**
 * Title: PageWorkListAction.java
 * File Description: 我申请的任务处理列表查询
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月8日
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.PEND_TYPE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.work.wk.bean.MineWorkBean;
import com.wk.cd.work.wk.bean.QueryMineWorkListInputBean;
import com.wk.cd.work.wk.bean.QueryMineWorkListOutputBean;
import com.wk.cd.work.wk.bean.WkDealDetailBean;
import com.wk.cd.work.wk.bean.WorkStateDetailBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkWorkProcessDaoService;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.info.WkWorkProcessInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 我申请的任务处理列表查询
 * @author Xul
 */
public class QueryMineWorkListAction extends ActionBasic<QueryMineWorkListInputBean, QueryMineWorkListOutputBean>{
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
	 * Description: 我申请的任务处理列表查询
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryMineWorkListOutputBean doAction(QueryMineWorkListInputBean input) {
		logger.info("*************PageWorkListAction Begin**************");
		QueryMineWorkListOutputBean output = new QueryMineWorkListOutputBean();
		
		String crt_user_id = input.getOrg_user_id();
		logger.debug("用户：[{}]", crt_user_id);
		// 非空检查
		Assert.assertNotEmpty(crt_user_id,
				QueryMineWorkListInputBean.ORG_USER_IDCN);
		
		List<WkDealStateInfo> mine_work_list = wkPubSrv.queryMineWorkList(crt_user_id);
		List<WkDealStateInfo> mine_executory_list= wkPubSrv.queryMineExecutoryWork(crt_user_id);
		List<MineWorkBean> mine_list = new ArrayList<MineWorkBean>();
		List<MineWorkBean> executory_list = new ArrayList<MineWorkBean>();
		//我的已处理任务列表
		if(!Assert.isEmpty(mine_work_list)){
			for(int i = 0 ; i < mine_work_list.size() ; i++){
				MineWorkBean bean = new MineWorkBean();
				//查询任务审批流程表
				List<WkWorkProcessInfo> process_info_list = wkproDaos.getInfoListByKey(mine_work_list.get(i).getPend_work_seq());
				if(Assert.isEmpty(process_info_list)){
					throw new RecordNotFoundException().addScene("TABLE", "任务审批流程表").addScene("FIELD", "");
				}
				//查询任务处理明细
				List<WkDealDetailBean> detail_bean_list = wkDealDetailDaoService.queryDealDetailByWorkSeq(mine_work_list.get(i).getPend_work_seq());
				bean.setCrt_bk_date(mine_work_list.get(i).getCrt_bk_date());
				bean.setCrt_bk_time(mine_work_list.get(i).getCrt_bk_time());
				bean.setPend_work_seq(mine_work_list.get(i).getPend_work_seq());
				bean.setPend_cn_name(mine_work_list.get(i).getPend_user_cn_name());
				bean.setPendwk_bk_expl(mine_work_list.get(i).getPendwk_bk_expl());
				bean.setWorkflow_state(mine_work_list.get(i).getWorkflow_state());
				//生成任务状态列表
				List<WorkStateDetailBean> work_detail_list = new ArrayList<WorkStateDetailBean>();
				//是否有拒绝处理
				boolean isRefuse = false;
				for(int j = 0 ; j < process_info_list.size(); j++){
					WorkStateDetailBean state_detail_bean = new WorkStateDetailBean();
					//定义任务处理明细列表长度
					int detail_list_size = 0;
					if(!Assert.isEmpty(detail_bean_list)){
						detail_list_size = detail_bean_list.size();
					}
					if(j!=0 && j <= detail_list_size){
						state_detail_bean.setDeal_bk_date(detail_bean_list.get(j-1).getDeal_bk_date());
						state_detail_bean.setDeal_bk_time(detail_bean_list.get(j-1).getDeal_bk_time());
						state_detail_bean.setDeal_bk_desc(detail_bean_list.get(j-1).getDeal_bk_desc());
						if(DEAL_RES.REFUSE.equals(detail_bean_list.get(j-1).getDeal_res())){
							isRefuse = true;
							state_detail_bean.setWork_state(2);
						}else{
							state_detail_bean.setWork_state(1);
						}
					}else if(j == 0){
						state_detail_bean.setDeal_bk_date(mine_work_list.get(i).getCrt_bk_date());
						state_detail_bean.setDeal_bk_time(mine_work_list.get(i).getCrt_bk_time());
						state_detail_bean.setDeal_bk_desc(mine_work_list.get(i).getApply_bk_expl());
						state_detail_bean.setWork_state(1);
					}else if(j == detail_list_size+1){
						if(!isRefuse){
							state_detail_bean.setWork_state(3);
						}else{
							state_detail_bean.setWork_state(4);
						}
					}else{
						state_detail_bean.setWork_state(4);
					}
					state_detail_bean.setPend_type(process_info_list.get(j).getPend_type());
					state_detail_bean.setDeal_user_cn_name(process_info_list.get(j).getPend_user_cn_name());
					work_detail_list.add(state_detail_bean);
				}
				
				bean.setWork_detail_list(work_detail_list);
				mine_list.add(bean);
			}
		}
		
		//我的待处理任务列表
		if(!Assert.isEmpty(mine_executory_list)){
			for(int i = 0 ; i < mine_executory_list.size() ; i++){
				MineWorkBean bean = new MineWorkBean();
				//查询任务审批流程表
				List<WkWorkProcessInfo> process_info_list = wkproDaos.getInfoListByKey(mine_executory_list.get(i).getPend_work_seq());
				if(Assert.isEmpty(process_info_list)){
					throw new RecordNotFoundException().addScene("TABLE", "任务审批流程表").addScene("FIELD", "");
				}
				//查询任务处理明细
				List<WkDealDetailBean> detail_bean_list = wkDealDetailDaoService.queryDealDetailByWorkSeq(mine_executory_list.get(i).getPend_work_seq());
				bean.setCrt_bk_date(mine_executory_list.get(i).getCrt_bk_date());
				bean.setCrt_bk_time(mine_executory_list.get(i).getCrt_bk_time());
				bean.setPend_work_seq(mine_executory_list.get(i).getPend_work_seq());
				bean.setPend_cn_name(mine_executory_list.get(i).getPend_user_cn_name());
				bean.setPendwk_bk_expl(mine_executory_list.get(i).getPendwk_bk_expl());
				bean.setWorkflow_state(mine_executory_list.get(i).getWorkflow_state());
				//生成任务状态列表
				List<WorkStateDetailBean> work_detail_list = new ArrayList<WorkStateDetailBean>();
				//是否有拒绝处理
				boolean isRefuse = false;
				for(int j = 0 ; j < process_info_list.size(); j++){
					WorkStateDetailBean state_detail_bean = new WorkStateDetailBean();
					int detail_list_size = 0;
					if(!Assert.isEmpty(detail_bean_list)){
						detail_list_size = detail_bean_list.size();
					}
					if(j!=0 && j <= detail_list_size){
						state_detail_bean.setDeal_bk_date(detail_bean_list.get(j-1).getDeal_bk_date());
						state_detail_bean.setDeal_bk_time(detail_bean_list.get(j-1).getDeal_bk_time());
						state_detail_bean.setDeal_bk_desc(detail_bean_list.get(j-1).getDeal_bk_desc());
						if(DEAL_RES.REFUSE.equals(detail_bean_list.get(j-1).getDeal_res())){
							isRefuse = true;
							state_detail_bean.setWork_state(2);
						}else{
							state_detail_bean.setWork_state(1);
						}
					}else if(j == 0){
						state_detail_bean.setDeal_bk_date(mine_executory_list.get(i).getCrt_bk_date());
						state_detail_bean.setDeal_bk_time(mine_executory_list.get(i).getCrt_bk_time());
						state_detail_bean.setDeal_bk_desc(mine_executory_list.get(i).getApply_bk_expl());
						state_detail_bean.setWork_state(1);
					}else if(j == detail_list_size+1){
						if(!isRefuse){
							state_detail_bean.setWork_state(3);
						}else{
							state_detail_bean.setWork_state(4);
						}
					}else{
						state_detail_bean.setWork_state(4);
					}
					state_detail_bean.setPend_type(process_info_list.get(j).getPend_type());
					//若已有拒绝操作，则最后一步流程为关闭
					if(j == process_info_list.size()-1 && isRefuse){
						state_detail_bean.setPend_type(PEND_TYPE.CLOSE);
					}
					state_detail_bean.setDeal_user_cn_name(process_info_list.get(j).getPend_user_cn_name());
					work_detail_list.add(state_detail_bean);
				}
				
				bean.setWork_detail_list(work_detail_list);
				executory_list.add(bean);
			}
		}
		
		output.setMine_work_list(mine_list);
		output.setMine_executory_list(executory_list);
		output.setExecutory_count(executory_list.size());
		logger.info("*************PageWorkListAction End**************");
		return output;
	}

	/** 
	 * Description: 我申请的任务处理列表查询
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryMineWorkListInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsrv.getLogTxt("我申请的任务处理列表查询", lst_val);
	}

}
