/**
 * Title: DpDeptSocPrivDao.java
 * File Description: 部门数据源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.List;

import com.wk.cd.system.dp.info.DpDeptSocPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门数据源权限表
 * @author AutoGen
 */
abstract class DpDeptSocPrivDao
		extends EntityDao<DpDeptSocPrivInfo> {

	/**
	 * Description: 根据部门代码查询部门数据源权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(sql = "select SOC_NAME from DP_DEPT_SOC_PRIV where DEPT_ID = :dept_id")
	abstract List<String> queryDeptSocPrivInfos(String dept_id);

	/**
	 * Description: 根据部门编码删除所有记录
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition = "DEPT_ID = :dept_id")
	abstract int deleteInfo(String dept_id);

	/**
	 * Description: 根据数据源名称删除所有记录
	 * @param soc_name
	 * @return
	 */
	@SqlParam(condition = "SOC_NAME = :soc_name")
	abstract int deleteInfoBySocName(String soc_name);
	/**
	 * 根据多个部门编码查询部门数据源编码
	 * @param dept_ids_str
	 * @return
	 */
	@SqlParam(sql = "select SOC_NAME from DP_DEPT_SOC_PRIV where DEPT_ID in ${dept_ids_str::1 = 0}", dynamic = true)
	abstract List<String> queryDeptRsByDeptIds(String dept_ids_str);

	/**
	 * Description: 根据部门代码查询部门资源权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(querySet={"SOC_NAME"}, condition = "DEPT_ID =:dept_id")
	abstract DBIterator<String> iteratorDeptSocPrivInfos(String dept_id);
}