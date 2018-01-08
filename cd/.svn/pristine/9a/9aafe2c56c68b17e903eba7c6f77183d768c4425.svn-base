/**
 * Title: DpDeptColPrivDao.java
 * File Description: 部门SQL字段操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.List;

import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门SQL字段操作权限表
 * @author AutoGen
 */
abstract class DpDeptColPrivDao extends EntityDao<DpDeptColPrivInfo> {

	
	/**
	 * 
	 * Description: 根据部门代码查询部门SQL权限信息
	 * @param dept_id
	 * @return
	 */
	@SqlParam(sql="select dept_id, soc_name, tbl_name, col_name, ins_priv_flag, del_priv_flag, upd_priv_flag, sel_priv_flag, backup_fld from DP_DEPT_COL_PRIV where DEPT_ID = :dept_id")
    abstract List<DpDeptColPrivInfo> queryDeptColPrivInfos(String dept_id);
	/**
	 * Description: 根据部门编码删除所有记录
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition="DEPT_ID = :dept_id")
	abstract int deleteInfo(String dept_id);

	/**
	 * Description: 根据数据源名称删除SQL列所有记录
	 * @param soc_name
	 * @return
	 */
	@SqlParam(condition = "SOC_NAME = :soc_name")
	abstract int deleteDeptColBySocName(String soc_name);
	
	/**
	 * 
	 * Description: 根据部门代码查询部门SQL权限信息
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition="DEPT_ID = :dept_id")
    abstract DBIterator<DpDeptColPrivInfo> queryDeptColPrivByDeptId(String dept_id);
	
}