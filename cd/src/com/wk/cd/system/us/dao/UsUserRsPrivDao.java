/**
 * Title: UsUserRsPrivDao.java
 * File Description: �û���ԴȨ�ޱ�
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
 * Class description:�û���ԴȨ�ޱ�
 * @author AutoGen
 */
abstract class UsUserRsPrivDao
		extends EntityDao<UsUserRsPrivInfo> {

	/**
	 * Description: ɾ���û���������ԴȨ��
	 * @param user_id
	 */
	@SqlParam(condition = "USER_ID=:user_id")
	abstract void deleteAllRsByUserId(String user_id);
	
	/**
	 * Description: ɾ���û�������������ԴȨ��
	 * @param user_id
	 */
	@SqlParam(condition = "USER_ID=:user_id and priv_type=1")
	abstract void deleteAllTualRsByUserId(String user_id);

	/** 
	 * Description: �����û������ѯ�û���ԴȨ�޵���Դ�����б�
	 * @param user_id
	 * @return 
	 */
	@SqlParam(querySet={ "RS_CODE" },condition="user_id=:user_id and af_flag = 1")
	abstract DBIterator<String> iteratorUserRsPriv(String user_id);

	/** 
	 * Description:  ɾ���Ѿ����ڵ��û���Դ��ʱȨ��
	 * @param user_id
	 * @param dt_datetime
	 * @return 
	 */
	@SqlParam(condition="user_id=:user_id and priv_type<>1 and tempend_bk_datetime<:dt_datetime")
	abstract int deleteRsTempPriv(String user_id, JaDateTime dt_datetime);
	/** 
	 * Description: ��ѯ���и��û�����ʱ��ԴȨ�� 
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select rs.RS_CN_NAME,priv.* from us_user_rs_priv priv,rs_res rs where priv.RS_CODE=rs.RS_CODE and PRIV_TYPE=3")
	abstract List<TempRsBean> getTempUsUserRsPriv(String user_id);
}