/**
 * Title: DpDeptSqlPrivDao.java
 * File Description: 部门SQL操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.dp.dao;

import java.util.List;

import com.wk.cd.enu.PRIV_FLAG;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门SQL操作权限表
 * @author AutoGen
 */
abstract class DpDeptSqlPrivDao extends EntityDao<DpDeptSqlPrivInfo> {

	/**
	 * Description: 根据部门代码查询部门SQL权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(sql="select dept_id, soc_name, tbl_name, ins_priv_flag, del_priv_flag, upd_priv_flag, sel_priv_flag, backup_fld " +
			"from DP_DEPT_SQL_PRIV where DEPT_ID = :dept_id")
    abstract List<DpDeptSqlPrivInfo> queryDeptSqlPrivInfos(String dept_id);
	/**
	 * Description: 根据部门编码删除所有记录
	 * @param dept_id
	 * @return
	 */
	@SqlParam(condition="DEPT_ID = :dept_id")
	abstract int deleteInfo(String dept_id);

	
	/**
	 * Description: 根据部门代码修改部门SQL权限信息
	 * @param 
	 */
	@SqlParam(updateSet = {"INS_PRIV_FLAG","DEL_PRIV_FLAG","UPD_PRIV_FLAG","SEL_PRIV_FLAG"},condition = "DEPT_ID=:dept_id and SOC_NAME=:soc_name and TBL_NAME=:tbl_name")
	abstract void update(String dept_id,String soc_name,String tbl_name,PRIV_FLAG ins_priv_flag,PRIV_FLAG del_priv_flag,PRIV_FLAG upd_priv_flag,PRIV_FLAG sel_priv_flag);
	/**
	 * Description: 根据数据源名称删除SQL所有记录
	 * @param soc_name
	 * @return
	 */
	@SqlParam(condition = "SOC_NAME = :soc_name")
	abstract int deleteDeptSqlBySocName(String soc_name);


	/**
	 * Description: 根据部门代码查询部门SQL权限信息
	 * @param deptId
	 * @return
	 */
	@SqlParam(condition="DEPT_ID = :dept_id")
    abstract DBIterator<DpDeptSqlPrivInfo> queryDeptSqlPrivByDeptId(String dept_id);
}

