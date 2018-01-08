/**
 * Title: MgMsgUserDao.java
 * File Description: ��Ϣ�û����ձ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-3-31
 */
package com.wk.cd.system.mg.dao;

import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.mg.info.MgMsgUserInfo;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:��Ϣ�û����ձ�
 * @author AutoGen
 */
abstract class MgMsgUserDao extends EntityDao<MgMsgUserInfo> {
	/** 
	 * Description: �޸Ľ���״̬
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "rc_flag" }, condition = "PK")
	abstract void updateRcFlag(RC_FLAG rc_flag,String work_seq, String user_id);

	/** 
	 * Description: �޸Ĺ�ע��־
	 * @param attent_flag
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "attent_yn_flag" }, condition = "PK")
	abstract void updateAttentFlag(YN_FLAG attent_flag, String work_seq,String user_id);

	/** 
	 * Description: ɾ��������Ϣ
	 * @param abnormal
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "rcd_state" }, condition = "PK")
	abstract void updateRcdState(RCD_STATE abnormal, String work_seq,String user_id);
}