/**
 * Title: WkDealStateDaoService.java
 * File Description: 任务处理状态表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.work.wk.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.QUERY_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.work.wk.bean.HistoryWorkBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:任务处理状态表
 * 
 * @author AutoGen
 */
public class WkDealStateDaoService {
	@Inject
	private WkDealStateDao dao;
	@Inject
	private WorkQuery wkqry;
	private static final String TD = WkDealStateInfo.TABLECN;

	/**
	 * 根据主键查询一条记录
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return 任务处理状态表信息
	 */
	public WkDealStateInfo getInfoByKey(WkDealStateInfo info) {
		String pend_work_seq = info.getPend_work_seq();
		WkDealStateInfo o = dao.get(pend_work_seq);
		if (Assert.isEmpty(o)) {
			throw new RecordNotFoundException().addScene("TABLE", TD).addScene(
					"FIELD", pend_work_seq);
		}
		return o;
	}

	/**
	 * 根据待处理流水号查询一条记录
	 * 
	 * @param pend_wk_seq
	 *            待处理流水号
	 * @return 任务处理状态表信息
	 */
	public WkDealStateInfo getInfoByKey(String pend_wk_seq) {
		return dao.get(pend_wk_seq);
	}

	/**
	 * 根据待处理流水号查询状态正常的记录，找不到记录报错
	 * 
	 * @param pend_work_seq
	 *            待处理流水号
	 * @return
	 */
	public WkDealStateInfo queryInfoByWorkSeq(String pend_work_seq) {
		WkDealStateInfo info = dao.queryInfoByWorkSeq(pend_work_seq);
		if (Assert.isEmpty(info)) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkDealStateInfo.TABLECN).addScene("FIELD", pend_work_seq);
		}
		return info;
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return 任务处理状态表信息
	 */
	public WkDealStateInfo getInfoByKeyForUpdate(String pend_work_seq) {
		WkDealStateInfo info = dao.getForUpdate(pend_work_seq);
		if (info == null) {
			throw new RecordNotFoundException().addScene("TABLE",
					WkDealStateInfo.TABLECN).addScene("FIELD", pend_work_seq);
		}
		return info;
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return 任务处理状态表信息
	 */
	public WkDealStateInfo getInfoByKeyForUpdate(WkDealStateInfo info) {
		return dao.getForUpdate(info);
	}
	
	/**
	 * 插入一条记录
	 * 
	 * @param WkDealStateInfo
	 *            info
	 * @return int
	 */
	public int insertInfo(WkDealStateInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * 
	 * @param List
	 *            <WkDealStateInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkDealStateInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 根据待复核用户查询待复核信息
	 * 
	 * @param user_id
	 *            待复核用户
	 * @param start_recd
	 *            起始记录条数
	 * @param limit_recd
	 *            查询条数
	 * @return
	 */
	public List<WkDealStateInfo> pageUncheckWork(String user_id,
			int start_recd, int limit_recd) {
		Assert.assertNotEmpty(user_id, "用户" + user_id);
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.RECHECK,
				start_recd, limit_recd);
	}

	/**
	 * 根据待处理用户查询待复核信息数量
	 * 
	 * @param user_id
	 *            待处理用户
	 * @return
	 */
	public int countUncheckWork(String user_id) {
		Assert.assertNotEmpty(user_id, "用户");
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.RECHECK);
	}

	/**
	 * 根据待授权用户查询待授权信息
	 * 
	 * @param user_id
	 *            待授权用户
	 * @param start_recd
	 *            起始记录条数
	 * @param limit_recd
	 *            查询条数
	 * @return
	 */
	public List<WkDealStateInfo> pageUnauthWork(String user_id, int start_recd,
			int limit_recd) {
		Assert.assertNotEmpty(user_id, "用户" + user_id);
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.APPROVAL,
				start_recd, limit_recd);
	}

	/**
	 * 根据待授权用户查询待授权信息数量
	 * 
	 * @param user_id
	 *            待授权用户
	 * @return
	 */
	public int countUnauthWork(String user_id) {
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.APPROVAL);
	}

	/**
	 * Description: 根据用户信息查询待执行任务
	 * 
	 * @param user_id
	 *            用户名称
	 * @param start_recd
	 *            起始条数
	 * @param limit_recd
	 *            查询条数
	 * @return 任务信息
	 */
	public List<WkDealStateInfo> pageUnexecuteWork(String user_id,
			int start_recd, int limit_recd) {
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.EXECUTORY,
				start_recd, limit_recd);
	}

	/**
	 * Description: 根据用户信息查询待执行任务数量
	 * 
	 * @param user_id
	 *            用户名称
	 * @param start_recd
	 *            起始条数
	 * @param limit_recd
	 *            查询条数
	 * @return 任务信息
	 */
	public int countUnexecuteWork(String user_id) {
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.EXECUTORY);
	}

	/**
	 * Description: 根据用户信息查询所有待处理任务(不包括自己提交)
	 * 
	 * @param user_id
	 *            用户名称
	 * @param start_recd
	 *            起始条数
	 * @param limit_recd
	 *            查询条数
	 * @return 任务信息
	 */
	public List<WkDealStateInfo> pageUnhandleWork(String user_id,
			int start_recd, int limit_recd) {
		return dao.pageUnhandleWork(user_id, null, start_recd, limit_recd);
	}
	
	/**
	 * Description: 根据用户信息查询所有待处理任务数量(不包括自己提交)
	 * 
	 * @param user_id
	 *            用户名称
	 * @return 任务信息
	 */
	public int countUnhandleWork(String user_id) {
		return dao.countUnhandleNum(user_id, null);
	}

	/**
	 * Description: 分页查询任务状态
	 * 
	 * @param crt_user_id
	 *            用户id
	 * @param query_type
	 *            查询类型
	 * @param start_recd
	 *            起始记录数
	 * @param limit_recd
	 *            总记录数
	 * @return 任务状态列表
	 */
	public List<WkDealStateInfo> pageInfoListByWorkStateAndUser(
			String crt_user_id, QUERY_TYPE query_type, int start_recd,
			int limit_recd) {
		// 查询类型为提交、复核、授权、执行、关闭
		if (query_type == QUERY_TYPE.SUBMITWORK) {
			return dao.pageInfoListByWorkStateAndUser(crt_user_id, start_recd,
					limit_recd);
		} else if (query_type == QUERY_TYPE.CHECKWORK) {
			return wkqry.pageInfoListCheckAndAuth(crt_user_id,
					DEAL_TYPE.RECHECK, start_recd, limit_recd);
		} else if (query_type == QUERY_TYPE.AUTHWORK) {
			return wkqry.pageInfoListCheckAndAuth(crt_user_id, DEAL_TYPE.AUTH,
					start_recd, limit_recd);
		} else if (query_type == QUERY_TYPE.EXCWORK) {
			return dao.pageInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.COMPLETE, start_recd, limit_recd);
		} else if (query_type == QUERY_TYPE.CLOSEWORK) {
			return dao.pageInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.CLOSE, start_recd, limit_recd);
		} else {
			throw new DataErrorException().addScene("INPUT", "任务类型");
		}
	}

	/**
	 * Description: 分页查询任务状态总记录
	 * 
	 * @param crt_user_id
	 *            用户id
	 * @param query_type
	 *            查询类型
	 * @return 总记录数
	 */
	public int countInfoListByWorkStateAndUser(String crt_user_id,
			QUERY_TYPE query_type) {
		// 查询类型为提交、复核、授权、执行、关闭
		if (query_type == QUERY_TYPE.SUBMITWORK) {
			return dao.countpageInfoListByWorkStateAndUser(crt_user_id);
		} else if (query_type == QUERY_TYPE.CHECKWORK) {
			return wkqry.countpageInfoListCheckAndAuth(crt_user_id,
					DEAL_TYPE.RECHECK);
		} else if (query_type == QUERY_TYPE.AUTHWORK) {
			return wkqry.countpageInfoListCheckAndAuth(crt_user_id,
					DEAL_TYPE.AUTH);
		} else if (query_type == QUERY_TYPE.EXCWORK) {
			return dao.countInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.COMPLETE);
		} else if (query_type == QUERY_TYPE.CLOSEWORK) {
			return dao.countInfoListExcByUser(crt_user_id, crt_user_id,
					WORKFLOW_STATE.CLOSE);
		} else {
			throw new DataErrorException().addScene("INPUT", "任务类型");
		}
	}
	
	/**
	 * 根据主键更新一条流水
	 * 
	 * @param pend_work_seq
	 *            主键：待处理流水
	 * @param pend_bk_seq
	 *            待处理序号
	 * @param pend_user_id
	 *            待处理用户
	 * @param workflow_state
	 *            任务状态
	 * @return 更新条数
	 */
	public int updateWorkStateByWorkSeq(String pend_work_seq,
			int pend_deal_seq, String pend_user_id,String pend_user_cn_name,
			WORKFLOW_STATE workflow_state) {
		return dao.updateWorkStateByWorkSeq(pend_deal_seq, pend_user_id,pend_user_cn_name,
				workflow_state, pend_work_seq);
	}

	/**
	 * 根据待处理服务名称和任务状态查询对应的待处理流水号
	 * 
	 * @param pend_srv_name
	 *            待处理服务名称
	 * @param workflow_state
	 *            任务状态
	 * @return 流水号列表
	 */
	public List<String> queryPendWorkSeqByWorkState(String pend_srv_name,
			List<WORKFLOW_STATE> workflow_state_list) {
		String str = "";
		if (Assert.isEmpty(workflow_state_list)) {
			throw new DataErrorException().addScene("INPUT", "输入信息");
		}
		for (WORKFLOW_STATE s : workflow_state_list) {
			str = str + s + ",";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return dao.queryPendWorkSeqByWorkState(pend_srv_name, str);
	}

	/**
	 * Description: 查询所有已拒绝的任务
	 * @param user_id 用户名
	 * @param start_recd 查询起始条数
	 * @param limit_recd 查询条数
	 * @return
	 */
	public List<WkDealStateInfo> pageRefuseWork(String user_id, int start_recd,
			int limit_recd) {
		return dao.pageUnhandleWork(user_id, WORKFLOW_STATE.CHECK_REFUSE,
				WORKFLOW_STATE.APP_REFUSE, start_recd, limit_recd);
	}

	/**
	 * Description: 查询所有已拒绝的任务数量
	 * @param user_id 用户名
	 * @return
	 */
	public int countRefuseWork(String user_id) {
		return dao.countUnhandleNum(user_id, WORKFLOW_STATE.CHECK_REFUSE,
				WORKFLOW_STATE.APP_REFUSE);
	}
	
	/**
	 * Description: 查询由我申请的任务列表
	 * @param crt_user_id
	 * @return
	 */
	public List<WkDealStateInfo> queryMineWorkList(String crt_user_id) {
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryMineWorkList(crt_user_id);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: 查询我待复核或授权的任务个数
	 * @param crt_user_id
	 * @return
	 */
	public int countMineExecutoryWork(String crt_user_id){
		return dao.countMineExecutoryWork(crt_user_id);
	}
	
	/**
	 * Description: 查询我待复核或授权的任务列表
	 * @param crt_user_id
	 * @return
	 */
	public List<WkDealStateInfo> queryMineExecutoryWork(String crt_user_id){
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryMineExecutoryWork(crt_user_id);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: 查询我处理过的复核或授权任务列表
	 * @param crt_user_id
	 * @param query_type
	 * @return
	 */
	public List<WkDealStateInfo> queryUncheckOrUnauthWorkList(String crt_user_id,DEAL_TYPE deal_type){
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryUncheckOrUnauthWorkList(crt_user_id,deal_type);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: 查询我待复核或授权的任务列表
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	public List<WkDealStateInfo> queryExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state){
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryExecutoryUncheckOrUnauth(crt_user_id,workflow_state);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/**
	 * Description: 查询我待复核或授权的任务个数
	 * @param crt_user_id
	 * @param workflow_state
	 * @return
	 */
	public int countExecutoryUncheckOrUnauth(String crt_user_id,WORKFLOW_STATE workflow_state){
		return dao.countExecutoryUncheckOrUnauth(crt_user_id,workflow_state);
	}
	
	/**
	 * Description: 分页查询我的历史任务列表
	 * @return
	 */
	public List<HistoryWorkBean> pageMineHistoryWork(String crt_user_id, int start_recd , int limit_recd){
		return dao.pageMineHistoryWork(crt_user_id,start_recd,limit_recd);
	}
	
	/**
	 * Description: 查询我的历史任务个数
	 * @param crt_user_id
	 * @return
	 */
	public int countMineHistoryWork(String crt_user_id){
		return dao.countMineHistoryWork(crt_user_id);
	}
	
	/**
	 * Description: 分页查询复核或授权的历史任务列表
	 * @param crt_user_id
	 * @param deal_type
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	public List<HistoryWorkBean> pageMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type , int start_recd , int limit_recd){
		return dao.pageMineUncheckOrUnauth(crt_user_id,deal_type,start_recd,limit_recd);
	}
	
	/**
	 * Description: 查询复核或授权的历史任务个数
	 * @param crt_user_id
	 * @param deal_type
	 * @return
	 */
	public int countMineUncheckOrUnauth(String crt_user_id, DEAL_TYPE deal_type){
		return dao.countMineUncheckOrUnauth(crt_user_id,deal_type);
	}

	/** 
	 * Description: 根据待处理服务查询待处理任务编号
	 * @param pend_srv_name 
	 */
	public List<WkDealStateInfo> queryPendWorkListByPendSrv(String pend_srv_name) {
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryPendWorkListByPendSrv(pend_srv_name);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
	
	/** 
	 * Description: 根据待处理服务查询待处理任务编号
	 * @param pend_srv_name 
	 */
	public List<WkDealStateInfo> queryWorkListByPendSrv(String pend_srv_name) {
		List<WkDealStateInfo> list = new ArrayList<WkDealStateInfo>();
        DBIterator<WkDealStateInfo> iterator = dao.queryWorkListByPendSrv(pend_srv_name);
        try {
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return list;
	}
}
