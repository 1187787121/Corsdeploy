/**
 * Title: WkDealDetailDaoService.java
 * File Description: 任务处理明细
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.work.wk.bean.WkDealDetailBean;
import com.wk.cd.work.wk.info.*;
import com.wk.lang.Inject;

/**
 * Class description:任务处理明细
 * @author AutoGen
 */
public class WkDealDetailDaoService {
	@Inject
	private WkDealDetailDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param WkDealDetailInfo info
	 * @return WkDealDetailInfo
	 */
	public WkDealDetailInfo getInfoByKey(WkDealDetailInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkDealDetailInfo info
	 * @return WkDealDetailInfo
	 */
	public WkDealDetailInfo getInfoByKeyForUpdate(WkDealDetailInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 根据到处理任务流水号查询待处理任务明细，找不到记录报错
	 * @param pend_work_seq 待处理任务流水号
	 * @return
	 */
	public List<WkDealDetailInfo> queryWorkDetailListByWorkSeq(
			String pend_work_seq) {
		List<WkDealDetailInfo> dt_lst = dao
				.queryWorkDetailListByWorkSeq(pend_work_seq);
		if (Assert.isEmpty(dt_lst)) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkDealDetailInfo.TABLECN).addScene("FIELD", pend_work_seq);
		}
		return dt_lst;
	}

	/**
	 * 插入一条记录
	 * @param WkDealDetailInfo info
	 * @return int
	 */
	public int insertInfo(WkDealDetailInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkDealDetailInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkDealDetailInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据待处理流水号查询待处理任务明细
	 * @param work_seq 待处理流水号
	 * @return List<WkDealDetailInfo>
	 */
	public List<WkDealDetailBean> queryDealDetailByWorkSeq(String work_seq) {
		return dao.queryDealDetailByWorkSeq(work_seq);
	}

	/**
	 * 查询一条待处理流水的明细条数
	 * @param pend_work_seq 待处理流水号
	 * @return 记录条数
	 */
	public int countWorkDetailByWorkSeq(String pend_work_seq) {
		return dao.countWorkDetailByWorkSeq(pend_work_seq);
	}
	
	/**
	 * Description: 通过流水号和处理方式查询所有已经做过复核或者授权的人员
	 * @param pend_work_seq 流水号
	 * @param deal_type 处理方式
	 * @return 人员信息
	 */
	public List<String> queryDealUserByWorkSeq(String pend_work_seq, DEAL_TYPE deal_type){
		return dao.queryDealUserByWorkSeq(pend_work_seq, deal_type);
	}
}