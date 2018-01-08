/**
 * Title: UsUserSqlPrivDao.java
 * File Description: �û�SQL����Ȩ�ޱ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.List;

import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:�û�SQL����Ȩ�ޱ�
 * @author AutoGen
 */
abstract class UsUserSqlPrivDao
		extends EntityDao<UsUserSqlPrivInfo> {

	/**
	 * Description: ����û���SQL����Ȩ���Ƿ��Ѿ�����
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name
	 * @return
	 */
	@SqlParam(condition = "USER_ID=:user_id and SOC_NAME=:soc_name and TBL_NAME=:tbl_name")
	abstract int countByUserSqlPriv(String user_id, String soc_name,
			String tbl_name);

	/**
	 * Description: ����û�SQLȨ�ޱ����Ƿ���ڸ�Ȩ��
	 * @param dprl_code
	 * @param soc_name
	 * @param tbl_name
	 * @param user_id
	 * @return
	 */
	@SqlParam(sql = "SELECT COUNT(*) FROM us_user_sql_priv "
			+ "WHERE AF_FLAG=1 AND USER_ID=:user_id AND SOC_NAME=:soc_name AND TBL_NAME=:tbl_name ")
	abstract int countByUserSql(String user_id, String soc_name, String tbl_name);

	/**
	 * Description: ��ѯ�û�����ʱSQL����Ȩ��
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "PRIV_TYPE=2 and USER_ID=:user_id")
	abstract DBIterator<UsUserSqlPrivInfo> queryUserTempSqlPriv(String user_id);

	/**
	 * Description: �����û�����ѯSQL����Ȩ���б�
	 * @param user_id
	 * @return
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract List<UsUserSqlPrivInfo> querySqlPrivByUserId(String user_id);
	
	/**
	 * Description: ɾ���û�������SQLȨ��
	 * @param user_id
	 */
	@SqlParam( condition = "USER_ID=:user_id")
	abstract void deleteAllSqlByUserId(String user_id);

	/**
	 * Description: ɾ���û�����Դsql��Ϣ
	 * @param soc_name
	 */
	@SqlParam( condition = "SOC_NAME=:soc_name")
	abstract int deleteUserSQLBySocName(String soc_name);

	/** 
	 * Description: ɾ���Ѿ����ڵ��û�sql��ʱȨ��
	 * @param user_id
	 * @param dt_datetime
	 * @param dt_datetime2
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_type=2 and tempend_bk_datetime<:dt_datetime")
	abstract int deleteSqlTempPriv(String user_id, JaDateTime dt_datetime);

	/** 
	 * Description:  ɾ���û���ʱȨ��
	 * @param user_id 
	 */
	@SqlParam(condition = "USER_ID=:user_id and PRIV_TYPE=2")
	abstract void deleteAllTempPriv(String user_id);

	/** 
	 * Description: ɾ���û���ʱȨ��
	 * @param user_id
	 * @param soc_name
	 * @param tbl_name 
	 */
	@SqlParam(condition = "USER_ID=:user_id and soc_name=:soc_name and tbl_name=:tbl_name and PRIV_TYPE=2")
	abstract void deleteTempPriv(String user_id, String soc_name, String tbl_name);
}