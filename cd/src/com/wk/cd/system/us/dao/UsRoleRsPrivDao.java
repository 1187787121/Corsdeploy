/**
 * Title: UsRoleRsPrivDao.java
 * File Description: 部门角色资源权限查询、添加和删除
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.info.*;

/**
 * Class description:部门角色资源权限查询、添加和删除
 * @author AutoGen
 */
abstract class UsRoleRsPrivDao
		extends EntityDao<UsRoleRsPrivInfo> {
	/**
	 * Description: 根据部门角色编码查询资源列表
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(querySet = { "rs_code" }, condition = "DPRL_CODE=:dprl_code ", orderBy = "dprl_code asc")
	abstract List<String> queryRsByDprl(String dprl_code);

	/**
	 * Description: 查询部门角色数组所拥有的资源权限
	 * @param dprl_code_arr
	 * @return
	 */
	@SqlParam(sql = "SELECT b.* FROM us_role_rs_priv a,rs_res b WHERE a.RS_CODE=b.RS_CODE AND a.DPRL_CODE IN ${dprl_code_arr::1=0} AND RCD_STATE=1", dynamic = true)
	abstract DBIterator<RsResInfo> iteratorRsByDprlArr(String dprl_code_arr);

	/**
	 * Description: 检查对应的记录是否已经存在，如果存在返回1，不存在返回0
	 * @param dprl_code
	 * @param rs_code
	 * @return
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code and RS_CODE=:rs_code ")
	abstract int countRsByDprlCode(String dprl_code, String rs_code);

	// /**
	// * Description: 更改部门角色的资源编码状态，正常-->删除
	// * @param dprl_code
	// * @param rs_code
	// */
	// @SqlParam(updateSet = { "RCD_STATE" }, condition =
	// "DPRL_CODE=:dprl_code and RS_CODE=:rs_code")
	// abstract void updateRsByDprlCode(int rcd_state, String dprl_code,
	// String rs_code);

	/**
	 * Description: 根据部门角色编码删除部门角色的所有资源权限
	 * @param dprl_code
	 */
	@SqlParam(condition = "DPRL_CODE=:dprl_code")
	abstract void deleteAllRsByDprl(String dprl_code);

	/** 
	 * Description: 根据部门角色编码查询资源信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(sql="select b.* from us_role_rs_priv a,rs_res b where dprl_code=:dprl_code and a.rs_code=b.rs_code and rcd_state=1")
	abstract DBIterator<RsResInfo> iteratorRsByDprl(String dprl_code);
	
	/**
	 * 
	 * Description: 根据部门角色编码数组查询资源权限
	 * @param dprl_code
	 * @return
	 */
	@SqlParam(sql="select distinct b.* from us_role_rs_priv a,rs_res b where (dprl_code in ${dprl_code}) and a.rs_code=b.rs_code and rcd_state=1",dynamic = true)
	abstract DBIterator<RsResInfo> iteratorRsByDprlArray(String dprl_code);
	
	/** 
	 * Description: 根据部门角色查询资源权限信息
	 * @param dprl_code
	 * @return 
	 */
	@SqlParam(querySet={"RS_CODE"},condition="dprl_code=:dprl_code")
	abstract DBIterator<String> iteratorRsPrivByDprl(String dprl_code);
}