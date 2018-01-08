/**
 * Title: WkWorkProcessDaoService.java
 * File Description: 任务审批流程表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-31
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.work.wk.info.*;
import com.wk.lang.Inject;

/**
 * Class description:任务审批流程表
 * @author AutoGen
 */
public class WkWorkProcessDaoService {
	@Inject private WkWorkProcessDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param WkWorkProcessInfo info
	 * @return WkWorkProcessInfo
	 */
	public WkWorkProcessInfo getInfoByKey(WkWorkProcessInfo info) {
		return dao.get(info);
	}
	
	/**
	 * Description: 根据流水号查询多条记录
	 * @param pend_work_seq
	 * @return
	 */
	public List<WkWorkProcessInfo> getInfoListByKey(String pend_work_seq){
		return dao.getInfoListByKey(pend_work_seq);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param WkWorkProcessInfo info
	 * @return WkWorkProcessInfo
	 */
	public WkWorkProcessInfo getInfoByKeyForUpdate(WkWorkProcessInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param WkWorkProcessInfo info
	 * @return int
	 */
	public int insertInfo(WkWorkProcessInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<WkWorkProcessInfo>
	 * @return int
	 */
	public int insertListInfo(List<WkWorkProcessInfo> infos) {
		return dao.insert(infos);
	}

}