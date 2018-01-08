/**
 * Title: WkDealDetailDao.java
 * File Description: 任务处理明细
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.work.wk.bean.WkDealDetailBean;
import com.wk.cd.work.wk.info.*;

/**
 * Class description:任务处理明细
 * @author AutoGen
 */
abstract class WkDealDetailDao
		extends EntityDao<WkDealDetailInfo> {

	/**
	 * 根据流水号查询一组任务处理明细记录
	 * @param work_seq 流水号
	 * @return 任务处理明细记录
	 */
	@SqlParam(sql = "select A.*, B.USER_CN_NAME from WK_DEAL_DETAIL A, US_USER B where A.PEND_WORK_SEQ = :pend_work_seq and A.DEAL_USER_ID = B.USER_ID order by A.DEAL_SEQ")
	abstract List<WkDealDetailBean> queryDealDetailByWorkSeq(String pend_work_seq);
	
	/**
	 * 根据流水号查询明细条数
	 * @param pend_work_seq 流水号
	 * @return 记录条数
	 */
	@SqlParam(condition = "PEND_WORK_SEQ = :pend_work_seq")
	abstract int countWorkDetailByWorkSeq(String pend_work_seq);

	/**
	 * 根据流水号查询一组状态为正常的任务处理明细记录
	 * @param work_seq 流水号
	 * @return 任务处理明细记录
	 */
	@SqlParam(condition = "PEND_WORK_SEQ = :pend_work_seq")
	abstract List<WkDealDetailInfo> queryWorkDetailListByWorkSeq(String pend_work_seq);
	
	/**
	 * Description: 通过流水号和处理方式查询所有已经做过复核或者授权的人员
	 * @param pend_work_seq 流水号
	 * @param deal_type 处理方式
	 * @return 人员信息
	 */
	@SqlParam(querySet = {"deal_user_id"}, condition = "(PEND_WORK_SEQ = :pend_work_seq) and (deal_type = :deal_type)")
	abstract List<String> queryDealUserByWorkSeq(String pend_work_seq, DEAL_TYPE deal_type);
}