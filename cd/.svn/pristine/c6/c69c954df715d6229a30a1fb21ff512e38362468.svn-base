/**
 * Title: WkDealStateDao.java
 * File Description: 任务处理状态表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.work.wk.bean.HistoryWorkBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:任务处理状态表
 * @author AutoGen
 */
abstract class WkDealStateDao
		extends EntityDao<WkDealStateInfo> {

	/**
	 * Description: 查询用户所有待处理的任务信息
	 * @param pend_user_id 用户名
	 * @param workflow_state 任务状态
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return 待授权任务信息列表
	 */
	@SqlParam(sql = "select pend_work_seq,submit_work_seq,pend_work_code,pend_srv_name,pend_rs_code,pend_ary_socname,"
			+ "pendwk_bk_expl,pend_deal_seq,pend_user_id,pbl_code,proxy_user_id,crt_user_id,crt_user_cn_name,crt_dept_id,"
			+ "crt_dept_cn_name,crt_bk_date,crt_bk_time,workflow_state,backup_fld,rcd_state from WK_DEAL_STATE "
			+ "where (PEND_USER_ID = '${pend_user_id}') and (workflow_state = ${workflow_state}) and rcd_state = 1 "
			+ "order by crt_bk_date, crt_bk_time desc", dynamic = true)
	abstract List<WkDealStateInfo> pageUnhandleWork(String pend_user_id,
			WORKFLOW_STATE workflow_state, int start_recd, int limit_recd);

	/**
	 * Description: 根据传入的待处理任务状态和待处理用户查询待处理任务数量
	 * @param pend_user_id 用户名
	 * @param workflow_state 任务状态
	 * @return
	 */
	@SqlParam(sql = "select count(*) from WK_DEAL_STATE where (PEND_USER_ID = '${pend_user_id}') and "
			+ "(workflow_state = ${workflow_state}) and rcd_state = 1", dynamic = true)
	abstract int countUnhandleNum(String pend_user_id,
			WORKFLOW_STATE workflow_state);

	/**
	 * Description: 根据主键更新一条待处理任务的审批信息
	 * @param pend_deal_seq 待审批序号
	 * @param pend_user_id 待处理用户
	 * @param workflow_state 任务状态
	 * @param pend_work_seq 待处理任务流水号
	 * @return 更新条数
	 */
	@SqlParam(updateSet = { "PEND_DEAL_SEQ", "PEND_USER_ID","PEND_USER_CN_NAME","WORKFLOW_STATE" }, condition = "PK")
	abstract int updateWorkStateByWorkSeq(int pend_deal_seq,
			String pend_user_id,String pend_user_cn_name,WORKFLOW_STATE workflow_state,
			String pend_work_seq);

	/**
	 * Description: 根据待处理流水号查询状态正常的记录
	 * @param pend_work_seq 待处理流水号
	 * @return 待处理任务信息
	 */
	@SqlParam(condition = "PEND_WORK_SEQ = :pend_work_seq and RCD_STATE = 1")
	abstract WkDealStateInfo queryInfoByWorkSeq(String pend_work_seq);

	/**
	 * Description: 根据待处理服务名称和任务状态查询对应的待处理流水号
	 * @param pend_srv_name 待处理服务名称
	 * @param workflow_state 任务状态
	 * @return 流水号列表
	 */
	@SqlParam(sql = "select PEND_WORK_SEQ from WK_DEAL_STATE where PEND_SRV_NAME = '${pend_srv_name}' and WORKFLOW_STATE in ${workflow_state_str::1=0} and RCD_STATE = 1", dynamic = true)
	abstract List<String> queryPendWorkSeqByWorkState(String pend_srv_name,
			String workflow_state_str);

	/**
	 * Description: 查询任务状态(提交)信息
	 * @param crt_user_id 用户id
	 * @param start_recd 起始记录数
	 * @param limit_recd 总记录数
	 * @return 任务列表
	 */
	@SqlParam(condition = "CRT_USER_ID = :crt_user_id and RCD_STATE = 1", orderBy = "CRT_BK_DATE desc, CRT_BK_TIME desc")
	abstract List<WkDealStateInfo> pageInfoListByWorkStateAndUser(
			String crt_user_id, int start_recd, int limit_recd);

	/**
	 * Description: 查询任务状态(提交)数量
	 * @param crt_user_id 用户id
	 * @return 任务列表
	 */
	@SqlParam(condition = "CRT_USER_ID = :crt_user_id and RCD_STATE = 1")
	abstract int countpageInfoListByWorkStateAndUser(String crt_user_id);

	/**
	 * Description: 分页查询任务状态(执行，关闭)信息
	 * @param crt_user_id1 用户id
	 * @param crt_user_id2 用户id
	 * @param start_recd 起始记录数
	 * @param limit_recd 总记录数
	 * @return
	 */
	@SqlParam(condition = "((CRT_USER_ID = :crt_user_id1) or (PROXY_USER_ID = :crt_user_id2)) and WORKFLOW_STATE = :workflow_state and RCD_STATE = 1", orderBy = "CRT_BK_DATE, CRT_BK_TIME desc")
	abstract List<WkDealStateInfo> pageInfoListExcByUser(String crt_user_id1,
			String crt_user_id2, WORKFLOW_STATE workflow_state, int start_recd,
			int limit_recd);

	/**
	 * Description: 分页查询任务状态(执行，关闭)条数
	 * @param crt_user_id1 用户id
	 * @param crt_user_id2 用户id
	 * @param start_recd 起始记录数
	 * @param limit_recd 总记录数
	 * @return
	 */
	@SqlParam(condition = "((CRT_USER_ID = :crt_user_id1) or (PROXY_USER_ID = :crt_user_id2)) and WORKFLOW_STATE = :workflow_state and RCD_STATE = 1")
	abstract int countInfoListExcByUser(String crt_user_id1,
			String crt_user_id2, WORKFLOW_STATE workflow_state);

	/**
	 * Description: 查询用户所有拒绝未关闭的任务信息
	 * @param pend_user_id 用户名
	 * @param workflow_state1 任务状态 复核拒绝
	 * @param workflow_state2 任务状态 授权拒绝
	 * @param start_recd 起始条数
	 * @param limit_recd 查询条数
	 * @return 待授权任务信息列表
	 */
	@SqlParam(sql = "select pend_work_seq,submit_work_seq,pend_work_code,pend_srv_name,pend_rs_code,pend_ary_socname,"
			+ "pendwk_bk_expl,pend_deal_seq,pend_user_id,pbl_code,proxy_user_id,crt_user_id,crt_user_cn_name,crt_dept_id,"
			+ "crt_dept_cn_name,crt_bk_date,crt_bk_time,workflow_state,backup_fld,rcd_state from WK_DEAL_STATE "
			+ "where (PEND_USER_ID = '${pend_user_id}') and ((workflow_state = ${workflow_state1}) or (workflow_state = ${workflow_state2})) "
			+ "and rcd_state = 1 order by crt_bk_date, crt_bk_time desc", dynamic = true)
	abstract List<WkDealStateInfo> pageUnhandleWork(String pend_user_id,
			WORKFLOW_STATE workflow_state1, WORKFLOW_STATE workflow_state2,
			int start_recd, int limit_recd);

	/**
	 * Description: 查询用户所有拒绝未关闭的任务数量
	 * @param pend_user_id 用户名
	 * @param workflow_state1 任务状态 复核拒绝
	 * @param workflow_state2 任务状态 授权拒绝
	 * @param workflow_state2 任务状态
	 * @return
	 */
	@SqlParam(sql = "select count(*) from WK_DEAL_STATE where (PEND_USER_ID = '${pend_user_id}') and "
			+ "((workflow_state = ${workflow_state1}) or (workflow_state = ${workflow_state2})) and rcd_state = 1", dynamic = true)
	abstract int countUnhandleNum(String pend_user_id,
			WORKFLOW_STATE workflow_state1, WORKFLOW_STATE workflow_state2);
	
	/**
	 * Description: 查询我申请已处理的任务列表
	 * @param crt_user_id
	 * @return
	 */
	@SqlParam(condition="CRT_USER_ID = :crt_user_id and (WORKFLOW_STATE = 1 or WORKFLOW_STATE = 3) and RCD_STATE = 1",orderBy="CRT_BK_DATE DESC,CRT_BK_TIME DESC")
	abstract DBIterator<WkDealStateInfo> queryMineWorkList(String crt_user_id);
	
	/**
	 * Description: 查询我待复核或授权的任务列表
	 * @param crt_user_id
	 * @return
	 */
	@SqlParam(condition="CRT_USER_ID = :crt_user_id and (WORKFLOW_STATE = 2 or WORKFLOW_STATE = 4 or WORKFLOW_STATE = 5) and RCD_STATE = 1" ,orderBy="CRT_BK_DATE DESC,CRT_BK_TIME DESC")
	abstract DBIterator<WkDealStateInfo> queryMineExecutoryWork(String crt_user_id);
	
	/**
	 * Description: 查询我待复核或授权的任务个数
	 * @param crt_user_id
	 * @return
	 */
	@SqlParam(condition="CRT_USER_ID = :crt_user_id and (WORKFLOW_STATE = 2 or WORKFLOW_STATE = 4 or WORKFLOW_STATE = 5) and RCD_STATE = 1")
	abstract int countMineExecutoryWork(String crt_user_id);
	
	/**
	 * Description: 查询我处理过的复核或授权任务列表
	 * @param crt_user_id
	 * @param query_type
	 * @return
	 */
	@SqlParam(sql="SELECT st.* from wk_deal_state st left JOIN wk_deal_detail de ON st.PEND_WORK_SEQ = de.PEND_WORK_SEQ WHERE st.WORKFLOW_STATE < 6 and de.DEAL_USER_ID =:crt_user_id and de.DEAL_TYPE =:deal_type and RCD_STATE = 1 ORDER BY st.CRT_BK_DATE desc,st.CRT_BK_TIME desc")
	abstract DBIterator<WkDealStateInfo> queryUncheckOrUnauthWorkList(String crt_user_id,DEAL_TYPE deal_type);
	
	/**
	 * Description: 查询我待复核或授权的任务列表
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	@SqlParam(condition="PEND_USER_ID = :crt_user_id and WORKFLOW_STATE =:workflow_state and RCD_STATE = 1 ",orderBy="CRT_BK_DATE DESC,CRT_BK_TIME DESC")
	abstract DBIterator<WkDealStateInfo> queryExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state);
	
	/**
	 * Description: 查询我待复核或授权的任务个数
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	@SqlParam(condition="PEND_USER_ID = :crt_user_id and WORKFLOW_STATE =:workflow_state and RCD_STATE = 1")
	abstract int countExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state);
	
	/**
	 * Description: 分页查询我的历史任务列表
	 * @return
	 */
	@SqlParam(sql="select DISTINCT(st.PEND_WORK_SEQ),st.PENDWK_BK_EXPL,st.WORKFLOW_STATE,st.CRT_USER_CN_NAME,de2.DEAL_BK_DATE,de2.DEAL_BK_TIME,st.PEND_SRV_NAME,st.CRT_BK_DATE,st.CRT_BK_TIME,st.APPLY_BK_EXPL from wk_deal_state st LEFT JOIN wk_deal_detail de ON st.PEND_WORK_SEQ = de.PEND_WORK_SEQ LEFT JOIN wk_deal_detail de2 on de.PEND_WORK_SEQ = de2.PEND_WORK_SEQ where st.CRT_USER_ID =:crt_user_id and de2.DEAL_TYPE > 2 and st.WORKFLOW_STATE >= 6 and RCD_STATE = 1 order by st.CRT_BK_DATE DESC,st.CRT_BK_TIME DESC")
	abstract List<HistoryWorkBean> pageMineHistoryWork(String crt_user_id, int start_recd , int limit_recd);
	
	/**
	 * Description: 查询我的历史任务个数
	 * @param crt_user_id
	 * @return
	 */
	@SqlParam(sql="select COUNT(DISTINCT st.PEND_WORK_SEQ) from wk_deal_state st LEFT JOIN wk_deal_detail de ON st.PEND_WORK_SEQ = de.PEND_WORK_SEQ where st.CRT_USER_ID =:crt_user_id and st.WORKFLOW_STATE >= 6 and RCD_STATE = 1")
	abstract int countMineHistoryWork(String crt_user_id);
	
	/**
	 * Description: 分页查询复核或授权的历史任务列表
	 * @param crt_user_id
	 * @param deal_type
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	@SqlParam(sql="select st.PEND_WORK_SEQ,st.PENDWK_BK_EXPL,st.WORKFLOW_STATE,st.CRT_USER_CN_NAME,de2.DEAL_BK_DATE,de2.DEAL_BK_TIME,st.PEND_SRV_NAME,st.CRT_BK_DATE,st.CRT_BK_TIME,st.APPLY_BK_EXPL from wk_deal_state st LEFT JOIN wk_deal_detail de ON st.PEND_WORK_SEQ = de.PEND_WORK_SEQ LEFT JOIN wk_deal_detail de2 ON de2.PEND_WORK_SEQ = de.PEND_WORK_SEQ where de.DEAL_USER_ID =:crt_user_id and de.DEAL_TYPE =:deal_type and de2.DEAL_TYPE>2 and st.WORKFLOW_STATE >= 6 and RCD_STATE = 1 order by st.CRT_BK_DATE DESC,st.CRT_BK_TIME DESC")
	abstract List<HistoryWorkBean> pageMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type , int start_recd , int limit_recd);
	
	/**
	 * Description: 查询复核或授权的历史任务个数
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	@SqlParam(sql="select COUNT(DISTINCT st.PEND_WORK_SEQ) from wk_deal_state st LEFT JOIN wk_deal_detail de ON st.PEND_WORK_SEQ = de.PEND_WORK_SEQ where de.DEAL_USER_ID =:crt_user_id and de.DEAL_TYPE =:deal_type and st.WORKFLOW_STATE >= 6 and RCD_STATE = 1")
	abstract int countMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type);

	/** 
	 * Description: 根据待处理服务查询待处理任务编号
	 * @param pend_srv_name
	 * @return 
	 */
	@SqlParam(condition = "PEND_SRV_NAME=:pend_srv_name and WORKFLOW_STATE < 6", orderBy = "CRT_BK_DATE desc,CRT_BK_TIME desc")
	abstract DBIterator<WkDealStateInfo> queryPendWorkListByPendSrv(String pend_srv_name);

	@SqlParam(condition = "PEND_SRV_NAME=:pend_srv_name and WORKFLOW_STATE <= 6", orderBy = "CRT_BK_DATE desc,CRT_BK_TIME desc")
	abstract DBIterator<WkDealStateInfo> queryWorkListByPendSrv(String pend_srv_name);
}