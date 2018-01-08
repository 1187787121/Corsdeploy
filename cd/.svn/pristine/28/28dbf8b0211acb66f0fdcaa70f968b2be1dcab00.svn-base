/**
 * Title: DpDeptRsPrivDao.java
 * File Description: 部门资源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import com.wk.cd.system.dp.info.DpDeptRsPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门资源权限表
 * @author AutoGen
 */
abstract class DpDeptRsPrivDao
		extends EntityDao<DpDeptRsPrivInfo> {
	/**
	 * Description: 根据部门代码删除资源权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(condition="DEPT_ID =:dept_id")
	abstract int deleteByDeptId(String dept_id);
	/**
	 * Description: 根据部门代码查询部门资源权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(sql = "select RS_CODE from DP_DEPT_RS_PRIV where DEPT_ID =:dept_id")
	abstract DBIterator<String> iteratorDeptRsPrivInfos(String dept_id);

	/**
	 * 根据多个部门编码查询部门资源编码
	 * @param dept_ids_str
	 * @return
	 */
	@SqlParam(sql = "select RS_CODE from DP_DEPT_RS_PRIV where DEPT_ID in ${dept_ids_str::1 = 0}", dynamic = true)
	abstract DBIterator<String> iteratorDeptRsByDeptIds(String dept_ids_str);
}