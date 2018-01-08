/**
 * Title: UsRoleSqlPrivDao.java
 * File Description: 部门角色SQL操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:部门角色SQL操作权限表
 * @author AutoGen
 */
abstract class UsRoleSqlPrivDao
		extends EntityDao<UsRoleSqlPrivInfo> {
	/**
	 * Description: 根据部门角色编码查询SQL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code ", orderBy = "dprl_code ,soc_name,tbl_name")
	abstract List<UsRoleSqlPrivInfo> querySqlByDprl(String dprl_code);

	/**
	 * Description: 根据部门角色编码数组查询SQL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "SELECT dprl_code, soc_name, tbl_name, ins_priv_flag, del_priv_flag, upd_priv_flag, sel_priv_flag, backup_fld " +
			"FROM us_role_sql_priv WHERE DPRL_CODE IN ${dprl_code_arr::1=0} ", dynamic = true)
	abstract List<UsRoleSqlPrivInfo> querySqlByDprlArr(String dprl_code);

	/**
	 * Description: 检查对应的记录是否已经存在，如果存在返回1，不存在返回0
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code and SOC_NAME=:soc_name and TBL_NAME=:tbl_name ")
	abstract int countSqlByDprlCode(String dprl_code, String soc_name,
			String tbl_name);

	/**
	 * Description: 更改部门角色SQL操作权限
	 * @param ins_priv_flag
	 * @param del_priv_flag
	 * @param upd_priv_flag
	 * @param sel_priv_flag
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 */
	@SqlParam(updateSet = { "INS_PRIV_FLAG", "DEL_PRIV_FLAG", "UPD_PRIV_FLAG",
			"SEL_PRIV_FLAG" }, condition = "DPRL_CODE=:dprl_code and SOC_NAME=:soc_name and TBL_NAME=:tbl_name ")
	abstract void updateSqlPriv(int ins_priv_flag, int del_priv_flag,
			int upd_priv_flag, int sel_priv_flag, String dprl_code,
			String soc_name, String tbl_name);

	/**
	 * Description: 根据部门角色编码删除部门角色的所有SQL权限
	 * @param dprl_code
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code")
	abstract void deleteAllSqlByDprl(String dprl_code);

	/** 
	 * Description: 通过部门角色查询sql权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="DPRL_CODE=:dprl_code")
	abstract DBIterator<UsRoleSqlPrivInfo> queryIteratorSqlByDprl(String dprl_code);
	/** 
	 * Description: 通过部门角色数组查询sql权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select distinct a.* from us_role_sql_priv a where (dprl_code in ${dprl_code})",dynamic = true)
	abstract DBIterator<UsRoleSqlPrivInfo> queryIteratorSqlByDprlArray(String dprl_code);
	
	/**
	 * Description: 根据数据源名称删除部门角色的所有数据源SQL权限信息
	 * @param soc_name
	 */
	@SqlParam(condition = "SOC_NAME=:soc_name")
	abstract int deleteRoleSQLBySocName(String soc_name);

	/** 
	 * Description: 根据部门角色代码查询部门角色SQL权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="DPRL_CODE = :dprl_code")
	abstract DBIterator<UsRoleSqlPrivInfo> querySqlPrivByDprl(String dprl_code);
}