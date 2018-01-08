/**
 * Title: WkWorkProcessDao.java
 * File Description: 任务审批流程表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-31
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.work.wk.info.WkWorkProcessInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:任务审批流程表
 * @author AutoGen
 */
abstract class WkWorkProcessDao extends EntityDao<WkWorkProcessInfo> {
	
	/**
	 * Description: 根据流水号查询多条记录
	 * @param pend_work_seq
	 * @return
	 */
	@SqlParam(condition="PEND_WORK_SEQ=:pend_work_seq")
	abstract List<WkWorkProcessInfo> getInfoListByKey(String pend_work_seq);
}