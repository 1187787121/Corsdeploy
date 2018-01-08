/**
 * Title: UsUserColPrivDao.java
 * File Description: 用户SQL字段操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:用户SQL字段操作权限表
 * @author AutoGen
 */
abstract class UsUserColPrivDao
		extends EntityDao<UsUserColPrivInfo> {

	/**
	 * Description: 检查用户的COL操作权限是否已经存在
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	@SqlParam(condition = "USER_ID=:user_id and SOC_NAME=:soc_name and TBL_NAME=:tbl_name and COL_NAME=:col_name")
	abstract int countByUserColPriv(String user_id, String soc_name,
			String tbl_name, String col_name);

	/**
	 * Description: 检测用户COL权限表中是否存在该权限
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 * @return
	 */
	@SqlParam(sql = "SELECT COUNT(*) FROM us_user_col_priv "
			+ "WHERE AF_FLAG=1 AND USER_ID=:user_id AND SOC_NAME=:soc_name AND TBL_NAME=:tbl_name  AND COL_NAME=:col_name ")
	abstract int countByUserCol(String user_id, String soc_name,
			String tbl_name, String col_name);

	/**
	 * Description: 查询用户的临时COL操作权限
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "PRIV_TYPE=2  and USER_ID=:user_id")
	abstract DBIterator<UsUserColPrivInfo> queryUserTempColPriv(String user_id);

	/**
	 * Description:根据用户名查询COL操作权限列表
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract List<UsUserColPrivInfo> queryColPrivByUserId(String user_id);

	/**
	 * Description: 删除用户的所有COL权限
	 * @param user_id
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract void deleteAllColByUserId(String user_id);

	/**
	 * Description: 删除用户数据源col信息
	 * @param soc_name
	 */
	@SqlParam(condition = "SOC_NAME=:soc_name")
	abstract int deleteUserCOLBySocName(String soc_name);

	/**
	 * Description: 删除已经过期的用户col临时权限
	 * @param user_id
	 * @param dt_datetime
	 * @param dt_datetime2
	 * @return
	 */
	@SqlParam(condition = "user_id=:user_id and priv_type=2 and tempend_bk_datetime<:dt_datetime")
	abstract int deleteColTempPriv(String user_id, JaDateTime dt_datetime);

	/**
	 * Description: 删除用户临时权限
	 * @param user_id
	 */
	@SqlParam(condition = "USER_ID=:user_id and PRIV_TYPE=2")
	abstract void deleteAllTempPriv(String user_id);

	/**
	 * Description: 删除用户临时权限
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @param col_name
	 */
	@SqlParam(condition = "USER_ID=:user_id and soc_name=:soc_name and tbl_name=:tbl_name and col_name=:col_name and PRIV_TYPE=2")
	abstract void deleteTempPriv(String user_id, String soc_name,
			String tbl_name, String col_name);

	/**
	 * Description:
	 * @param user_id
	 * @param soc_name
	 * @param table_name
	 * @param col_name
	 * @param priv_type
	 * @return
	 */
//	@SqlParam(condition = "PK")
	@SqlParam(condition = "USER_ID=:user_id and SOC_NAME=:soc_name and TBL_NAME=:tbl_name and COL_NAME=:col_name and PRIV_TYPE=:priv_type")
	abstract UsUserColPrivInfo getInfoByKey(String user_id, String soc_name,
			String tbl_name, String col_name, PRIV_TYPE priv_type);
}