/**
 * Title: MgMsgUserDao.java
 * File Description: 消息用户接收表
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
 * Class description:消息用户接收表
 * @author AutoGen
 */
abstract class MgMsgUserDao extends EntityDao<MgMsgUserInfo> {
	/** 
	 * Description: 修改接收状态
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "rc_flag" }, condition = "PK")
	abstract void updateRcFlag(RC_FLAG rc_flag,String work_seq, String user_id);

	/** 
	 * Description: 修改关注标志
	 * @param attent_flag
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "attent_yn_flag" }, condition = "PK")
	abstract void updateAttentFlag(YN_FLAG attent_flag, String work_seq,String user_id);

	/** 
	 * Description: 删除接收消息
	 * @param abnormal
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "rcd_state" }, condition = "PK")
	abstract void updateRcdState(RCD_STATE abnormal, String work_seq,String user_id);
}