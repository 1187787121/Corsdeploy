/**
 * Title: MgMsgUserDaoService.java
 * File Description: 消息用户接收表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-3-31
 */
package com.wk.cd.system.mg.dao;

import java.util.List;

import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.mg.info.*;
import com.wk.lang.Inject;

/**
 * Class description:消息用户接收表
 * @author AutoGen
 */
public class MgMsgUserDaoService {
	@Inject private MgMsgUserDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param MgMsgUserInfo info
	 * @return MgMsgUserInfo
	 */
	public MgMsgUserInfo getInfoByKey(MgMsgUserInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param MgMsgUserInfo info
	 * @return MgMsgUserInfo
	 */
	public MgMsgUserInfo getInfoByKeyForUpdate(MgMsgUserInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param MgMsgUserInfo info
	 * @return int
	 */
	public int insertInfo(MgMsgUserInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<MgMsgUserInfo>
	 * @return int
	 */
	public int insertListInfo(List<MgMsgUserInfo> infos) {
		return dao.insert(infos);
	}
	
	/** 
	 * Description: 修改接收状态
	 * @param work_seq
	 * @param user_id 
	 */
	public void updateRcFlag(String work_seq, String user_id) {
		dao.updateRcFlag(RC_FLAG.YES,work_seq,user_id);
	}

	/** 
	 * Description: 修改关注标志
	 * @param attent_flag
	 * @param work_seq
	 * @param user_id 
	 */
	public void updateAttentFlag(YN_FLAG attent_flag, String work_seq,
			String user_id) {
		dao.updateAttentFlag(attent_flag,work_seq,user_id);
	}

	/** 
	 * Description: 删除接收消息
	 * @param work_seq
	 * @param user_id 
	 */
	public void deleteRcMess(String work_seq, String user_id) {
		dao.updateRcdState(RCD_STATE.ABNORMAL,work_seq,user_id);
	}
}