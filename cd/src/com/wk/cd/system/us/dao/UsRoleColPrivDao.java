/**
 * Title: UsRoleColPrivDao.java
 * File Description: 部门角色SQL字段操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.us.info.*;

/**
 * Class description:部门角色SQL字段操作权限表
 * @author AutoGen
 */
abstract class UsRoleColPrivDao
		extends EntityDao<UsRoleColPrivInfo> {
	/**
	 * Description: 根据部门角色编码查询COL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code", orderBy = "dprl_code ,soc_name,tbl_name,col_name")
	abstract List<UsRoleColPrivInfo> queryColByDprl(String dprl_code);

	/**
	 * Description: 根据部门角色编码数组查询COL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "SELECT dprl_code, soc_name, tbl_name, col_name, ins_priv_flag, del_priv_flag, upd_priv_flag, sel_priv_flag, backup_fld " +
			"FROM us_role_col_priv WHERE DPRL_CODE IN ${dprl_code_arr::1=0} ", dynamic = true)
	abstract List<UsRoleColPrivInfo> queryColByDprlArr(String dprl_code);

	/**
	 * Description: 检查对应的记录是否已经存在，如果存在返回1，不存在返回0
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code and SOC_NAME=:soc_name and TBL_NAME=:tbl_name and COL_NAME=:col_name")
	abstract int countColByDprlCode(String dprl_code, String soc_name,
			String tbl_name, String col_name);

	/**
	 * Description: 部门角色COL权限操作权限更改
	 * @param ins_priv_flag
	 * @param del_priv_flag
	 * @param upd_priv_flag
	 * @param sel_priv_flag
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	@SqlParam(updateSet = { "INS_PRIV_FLAG", "DEL_PRIV_FLAG", "UPD_PRIV_FLAG",
			"SEL_PRIV_FLAG" }, condition = "DPRL_CODE=:dprl_code and SOC_NAME=:soc_name and TBL_NAME=:tbl_name and COL_NAME=:col_name")
	abstract void updateColPriv(int ins_priv_flag, int del_priv_flag,
			int upd_priv_flag, int sel_priv_flag, String dprl_code,
			String soc_name, String tbl_name, String col_name);

	/**
	 * Description: 根据部门角色编码删除部门角色的所有COL权限
	 * @param dprl_code
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code")
	abstract void deleteAllColByDprl(String dprl_code);

	/** 
	 * Description: 通过部门角色查询col权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="DPRL_CODE=:dprl_code")
	abstract DBIterator<UsRoleColPrivInfo> queryIteratorColByDprl(String dprl_code);

	/** 
	 * Description: 通过部门角色数组查询col权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select distinct a.* from us_role_col_priv a where (a.dprl_code in ${dprl_code})",dynamic = true)
	abstract DBIterator<UsRoleColPrivInfo> queryIteratorColByDprlArray(String dprl_code);
	/**
	 * Description: 根据数据源名称删除部门角色的所有数据源SQL列权限信息
	 * @param soc_name
	 */
	@SqlParam(condition = "SOC_NAME=:soc_name")
	abstract int deleteRoleCOLBySocName(String soc_name);

	/** 
	 * Description: 根据部门代码查询部门SQL权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="DPRL_CODE = :dprl_code")
	abstract DBIterator<UsRoleColPrivInfo> queryColPrivByDprl(String dprl_code);
}