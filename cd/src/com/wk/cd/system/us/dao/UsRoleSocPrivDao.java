/**
 * Title: UsRoleSocPrivDao.java
 * File Description: 部门角色数据源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.us.info.*;

/**
 * Class description:部门角色数据源权限表
 * @author AutoGen
 */
abstract class UsRoleSocPrivDao
		extends EntityDao<UsRoleSocPrivInfo> {

	/**
	 * Description: 根据部门角色编码查询数据源列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(querySet = { "soc_name" }, condition = "DPRL_CODE=:dprl_code ", orderBy = "dprl_code asc")
	abstract List<String> querySocByDprl(String dprl_code);

	/**
	 * Description: 根据部门角色编码查询数据源列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql = "SELECT b.* FROM us_role_soc_priv a,dt_source b WHERE a.SOC_NAME=b.SOC_NAME AND a.DPRL_CODE IN ${dprl_code_arr::1=0} ", dynamic = true)
	abstract List<DtSourceInfo> querySocByDprlArr(String dprl_code_arr);

	/**
	 * Description: 检查对应的记录是否已经存在，如果存在返回1，不存在返回0
	 * @param dprl_code
	 * @param soc_code
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code and SOC_NAME=:soc_name ")
	abstract int countSocByDprlCode(String dprl_code, String soc_name);

	// /**
	// * Description: 更改部门角色的数据源编码状态，正常-->删除
	// * @param dprl_code
	// * @param soc_name
	// */
	// @SqlParam(updateSet = { "RCD_STATE" }, condition =
	// "DPRL_CODE=:dprl_code and SOC_NAME=:soc_name")
	// abstract void updateSocByDprlCode(int rcd_state, String dprl_code,
	// String soc_name);
	
	/**
	 * Description: 根据部门角色编码删除部门角色的所有数据源权限
	 * @param dprl_code
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code")
	abstract void deleteAllSocByDprl(String dprl_code);
	
	
	/**
	 * Description: 根据数据源名称删除部门角色的所有数据源权限信息
	 * @param soc_name
	 */
	@SqlParam(condition = "SOC_NAME=:soc_name")
	abstract int deleteRoleSocBySocName(String soc_name);

	/** 
	 * Description: 通过部门角色查询数据源信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select b.* from us_role_soc_priv a,dt_source b where dprl_code=:dprl_code and a.soc_name=b.soc_name and rcd_state=1")
	abstract DBIterator<DtSourceInfo> iteratorSocByDprl(String dprl_code);
	/** 
	 * Description: 通过部门角色数组查询数据源信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select distinct b.* from us_role_soc_priv a,dt_source b where (dprl_code in ${dprl_code}) and a.soc_name=b.soc_name and rcd_state=1",dynamic = true)
	abstract DBIterator<DtSourceInfo> iteratorSocByDprlArray(String dprl_code);

	/** 
	 * Description: 根据部门角色查询资源权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(condition="dprl_code=:dprl_code")
	abstract DBIterator<String> iteratorSocPrivByDprl(String dprl_code);
}