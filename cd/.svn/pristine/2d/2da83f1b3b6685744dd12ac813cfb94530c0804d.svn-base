/**
 * Title: UsUserRsPrivDao.java
 * File Description: 用户资源权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.bean.TempRsBean;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:用户资源权限表
 * @author AutoGen
 */
abstract class UsUserRsPrivDao
		extends EntityDao<UsUserRsPrivInfo> {

	/**
	 * Description: 删除用户的所有资源权限
	 * @param user_id
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract void deleteAllRsByUserId(String user_id);
	
	/**
	 * Description: 删除用户的所有永久资源权限
	 * @param user_id
	 */
	@SqlParam(condition = "USER_ID=:user_id and priv_type=1")
	abstract void deleteAllTualRsByUserId(String user_id);

	/** 
	 * Description: 根据用户编码查询用户资源权限的资源编码列表
	 * @param user_id
	 * @return 
	 */
	@SqlParam(querySet={ "RS_CODE" },condition="user_id=:user_id and af_flag = 1")
	abstract DBIterator<String> iteratorUserRsPriv(String user_id);

	/** 
	 * Description:  删除已经过期的用户资源临时权限
	 * @param user_id
	 * @param dt_datetime
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_type<>1 and tempend_bk_datetime<:dt_datetime")
	abstract int deleteRsTempPriv(String user_id, JaDateTime dt_datetime);
	/** 
	 * Description: 查询所有该用户的临时资源权限 
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select rs.RS_CN_NAME,priv.* from us_user_rs_priv priv,rs_res rs where priv.RS_CODE=rs.RS_CODE and PRIV_TYPE=3")
	abstract List<TempRsBean> getTempUsUserRsPriv(String user_id);
}