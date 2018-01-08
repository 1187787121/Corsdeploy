/**
 * Title: QueryWorkDetailAction.java
 * File Description: 任务详情查看
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月14日
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.PEND_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.work.wk.bean.QueryPendWorkOutputBean;
import com.wk.cd.work.wk.bean.QueryWorkDetailInputBean;
import com.wk.cd.work.wk.bean.QueryWorkDetailOutputBean;
import com.wk.cd.work.wk.bean.WkDealDetailBean;
import com.wk.cd.work.wk.bean.WorkStateDetailBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkDetailRegisterDaoService;
import com.wk.cd.work.wk.dao.WkWorkProcessDaoService;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.info.WkDetailRegisterInfo;
import com.wk.cd.work.wk.info.WkWorkProcessInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 任务详情查看
 * @author Xul
 */
public class QueryWorkDetailAction extends ActionBasic<QueryWorkDetailInputBean, QueryWorkDetailOutputBean>{
	
	@Inject
	private WkDealDetailDaoService wkDeDaoSrv;
	@Inject
	private ApPublicService apPubSrv;
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private WkWorkProcessDaoService wkproDaos;
	@Inject
	private RsPublicService rsPubSrv;
	@Inject
	private WkDetailRegisterDaoService wkDetailRegisterDaoService;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 任务详情查看
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryWorkDetailOutputBean doAction(QueryWorkDetailInputBean input) {
		logger.info("***********QueryWorkDetailAction Begin***********");
		QueryWorkDetailOutputBean output = new QueryWorkDetailOutputBean();
		String wrk_seq = input.getWrk_seq();
		logger.debug("任务详情查看，pend_work_seq = [{}]", wrk_seq);
		
		//非空校验
		Assert.assertNotEmpty(wrk_seq, QueryWorkDetailInputBean.WRK_SEQCN);
		//根据流水号查看任务处理状态表
		WkDealStateInfo wk_state_info = wkPubSrv.queryWorkStateByWorkSeq(wrk_seq);
		//查看任务处理明细列表
		List<WkDealDetailBean> work_detail_list = wkDeDaoSrv.queryDealDetailByWorkSeq(wrk_seq);
		//查询任务审批流程表
		List<WkWorkProcessInfo> process_info_list = wkproDaos.getInfoListByKey(wrk_seq);
		if(Assert.isEmpty(process_info_list)){
			throw new RecordNotFoundException().addScene("TABLE", "任务审批流程表").addScene("FIELD", "");
		}
		//查询接口信息
		QueryPendWorkOutputBean<Object> bean = wkPubSrv.queryPendWork(wrk_seq);
		logger.debug("检查前的json:"+bean.getNew_json_list());
		logger.debug("检查后的json:"+checkToStandardJson(bean.getNew_json_list()));
		output.setNew_json_list(checkToStandardJson(bean.getNew_json_list()));
		
		//生成任务状态列表
		List<WorkStateDetailBean> detail_list = new ArrayList<WorkStateDetailBean>();
		//是否有拒绝处理
		boolean isRefuse = false;
		//是否是历史任务
		boolean ishistory = false;
		for(int i = 0; i < process_info_list.size() ; i++){
			WorkStateDetailBean state_detail_bean = new WorkStateDetailBean();
			//定义任务处理明细列表长度
			int detail_list_size = 0;
			if(!Assert.isEmpty(work_detail_list)){
				detail_list_size = work_detail_list.size();
				ishistory = DEAL_TYPE.CLOSE.equals(work_detail_list.get(detail_list_size-1).getDeal_type()) || DEAL_TYPE.EXE.equals(work_detail_list.get(detail_list_size-1).getDeal_type());
			}
			//申请流程
			if(i == 0){
				state_detail_bean.setDeal_bk_date(wk_state_info.getCrt_bk_date());
				state_detail_bean.setDeal_bk_time(wk_state_info.getCrt_bk_time());
				state_detail_bean.setWork_state(1);
			//没有拒绝操作且已处理过的流程
			}else if(i!=0 && i <= detail_list_size && !isRefuse){
				state_detail_bean.setDeal_bk_date(work_detail_list.get(i-1).getDeal_bk_date());
				state_detail_bean.setDeal_bk_time(work_detail_list.get(i-1).getDeal_bk_time());
				state_detail_bean.setDeal_bk_desc(work_detail_list.get(i-1).getDeal_bk_desc());
				if(DEAL_RES.REFUSE.equals(work_detail_list.get(i-1).getDeal_res())){
					isRefuse = true;
					state_detail_bean.setWork_state(2);
				}else if(DEAL_RES.AGREE.equals(work_detail_list.get(i-1).getDeal_res()) || DEAL_RES.NOTHING.equals(work_detail_list.get(i-1).getDeal_res())){
					state_detail_bean.setWork_state(1);
				}else{
					state_detail_bean.setWork_state(4);
				}
			//非历史任务，且为当前任务流程
			}else if(i == detail_list_size+1 && !ishistory){
				if(!isRefuse){
					state_detail_bean.setWork_state(3);
				}else{
					state_detail_bean.setWork_state(4);
				}
			//历史任务中关闭流程
			}else if(isRefuse && i == process_info_list.size()-1 && ishistory){
				state_detail_bean.setDeal_user_cn_name(work_detail_list.get(detail_list_size-1).getUser_cn_name());
				state_detail_bean.setPend_type(PEND_TYPE.CLOSE);
				state_detail_bean.setWork_state(1);
				state_detail_bean.setDeal_bk_date(work_detail_list.get(detail_list_size-1).getDeal_bk_date());
				state_detail_bean.setDeal_bk_desc(work_detail_list.get(detail_list_size-1).getDeal_bk_desc());
				state_detail_bean.setDeal_bk_time(work_detail_list.get(detail_list_size-1).getDeal_bk_time());
				detail_list.add(state_detail_bean);
				break;
			//非历史任务中未处理任务流程或历史任务中拒绝操作后未执行的任务流程
			}else{
				state_detail_bean.setWork_state(4);
			}
			state_detail_bean.setPend_type(process_info_list.get(i).getPend_type());
			//若已有拒绝操作或任务状态为关闭，则最后一步流程为关闭
			if((i == process_info_list.size()-1 && isRefuse && !ishistory) || (i == process_info_list.size()-1 && WORKFLOW_STATE.CLOSE.equals(wk_state_info.getWorkflow_state()))){
				state_detail_bean.setPend_type(PEND_TYPE.CLOSE);
			}
			state_detail_bean.setDeal_user_cn_name(process_info_list.get(i).getPend_user_cn_name());
			detail_list.add(state_detail_bean);
		}
		
		output.setWork_detail_list(detail_list);
		output.setApply_bk_expl(wk_state_info.getApply_bk_expl());
		output.setWorkflow_state(wk_state_info.getWorkflow_state());
		output.setPend_srv_name(wk_state_info.getPend_srv_name());
		
		//查询待处理任务明细登记表
		WkDetailRegisterInfo register_info = new WkDetailRegisterInfo();
		register_info.setPend_work_seq(wrk_seq);
		WkDetailRegisterInfo wk_register_info = wkDetailRegisterDaoService.getInfoByKey(register_info);
		output.setAprov_type(wk_register_info.getAprov_type());
		//若为定制页面，则返回定制资源地址
		if(APROV_TYPE.CUSTOM_PAGE.equals(wk_register_info.getAprov_type())){
			if(Assert.isEmpty(wk_register_info.getCustom_rs_code())){
				throw new RecordNotFoundException().addScene("TABLE", "待处理任务明细登记表").addScene("FIELD", "定制页面资源编码");
			}
			String rs_url = rsPubSrv.getResInfo(wk_register_info.getCustom_rs_code()).getRs_url();
			output.setRs_url(rs_url);
		}
		output.setPendwk_bk_expl(wk_state_info.getPendwk_bk_expl());
		
		logger.info("***********QueryWorkDetailAction End***********");
		return output;
	}
	
	/**
	 * Description: 转化成标准json(将“'”转化为“\'”)
	 * @return
	 */
	private String checkToStandardJson(String str){
		int first_fval = str.indexOf("'fval'");
		int start = first_fval;
		while(true){
			start = str.indexOf("'fval':'",start+1)+8;
			//若查不到'fval'字段，则跳出循环
			if(start == 7){
				break;
			}
			int end = str.indexOf("', 'ftype",start);
			String sub = str.substring(start,end);
			if(sub.contains("'")){
				String sub_replace = sub.replace("'", "\\'");
				str = str.replace(sub, sub_replace);
			}
			start+=1;
		}
		return str;
	}
	
	/** 
	 * Description: 任务详情查看
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryWorkDetailInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWrk_seq());
		return lgsrv.getLogTxt("任务详情查看", lst_val);
	}

}
