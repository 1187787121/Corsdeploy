/**
 * Title: UsUserSocPrivDao.java
 * File Description: 用户数据源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:用户数据源权限表
 * @author AutoGen
 */
abstract class UsUserSocPrivDao
		extends EntityDao<UsUserSocPrivInfo> {
	/**
	 * Description: 检查用户的数据源权限是否已经存在
	 * @param user_id
	 * @param soc_name
	 * @return
	 */
	@SqlParam(condition = "USER_ID=:user_id and SOC_NAME=:soc_name")
	abstract int countByUserSocPriv(String user_id, String soc_name);

	/**
	 * Description: 根据用户名查询用户被禁止的数据源权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(querySet = { "SOC_NAME" }, condition = "USER_ID=:user_id and AF_FLAG=2 ")
	abstract List<String> queryUserAfSocPriv(String user_id);

	/**
	 * Description: 查询用户的临时数据源权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "PRIV_TYPE=2 and USER_ID=:user_id")
	abstract DBIterator<UsUserSocPrivInfo> queryUserTempSocPriv(String user_id);
	
	/**
	 * Description: 删除用户的所有数据源权限
	 * @param user_id
	 */
	@SqlParam( condition = "USER_ID=:user_id")
	abstract void deleteAllSocByUserId(String user_id);

	/**
	 * Description: 删除用户数据源信息
	 * @param soc_name
	 */
	@SqlParam( condition = "SOC_NAME=:soc_name")
	abstract int deleteUserSocBySocName(String soc_name);
	/**
	 * 根据多个用户编码查询数据源编码
	 * @param user_ids_str
	 * @return
	 */
	@SqlParam(sql = "select SOC_NAME from US_USER_SOC_PRIV where USER_ID in ${user_ids_str::1 = 0} and AF_FLAG = 1", dynamic = true)
	abstract List<String> querySocByIds(String user_ids_str);

	/** 
	 * Description: 根据用户编码查询用户资源权限的数据源名列表
	 * @param user_id
	 * @return 
	 */
	@SqlParam(querySet={ "SOC_NAME" },condition="user_id=:user_id and af_flag = 1 and priv_type=1")
	abstract DBIterator<String> iteratorUserSocPriv(String user_id);

	/** 
	 * Description: 删除已经过期的用户数据源临时权限
	 * @param user_id
	 * @param dt_datetime
	 * @param dt_datetime2
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_type=2 and tempend_bk_datetime<:dt_datetime")
	abstract int deleteSocTempPriv(String user_id, JaDateTime dt_datetime);

	/** 
	 * Description: 检查用户的临时权限是否已经存在
	 * @param user_id
	 * @param soc_name
	 * @return 
	 */
	@SqlParam(condition = "USER_ID=:user_id and SOC_NAME=:soc_name and PRIV_TYPE=2")
	abstract int countTempPriv(String user_id, String soc_name);
	
	/** 
	 * Description: 删除用户临时权限
	 * @param user_id
	 * @param soc_name 
	 */
	@SqlParam(condition = "USER_ID=:user_id and SOC_NAME=:soc_name and PRIV_TYPE=2")
	abstract void deleteTempPriv(String user_id, String soc_name);
}