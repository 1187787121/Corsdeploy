/**
 * Title: MgMsgDaoService.java
 * File Description: 消息信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-3-31
 */
package com.wk.cd.system.mg.dao;

import java.util.List;

import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.mg.bean.MgMsgRcBean;
import com.wk.cd.system.mg.info.MgMsgInfo;
import com.wk.lang.Inject;

/**
 * Class description:消息信息表
 * @author AutoGen
 */
public class MgMsgDaoService {
	@Inject private MgMsgDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param MgMsgInfo info
	 * @return MgMsgInfo
	 */
	public MgMsgInfo getInfoByKey(MgMsgInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param MgMsgInfo info
	 * @return MgMsgInfo
	 */
	public MgMsgInfo getInfoByKeyForUpdate(MgMsgInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param MgMsgInfo info
	 * @return int
	 */
	public int insertInfo(MgMsgInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<MgMsgInfo>
	 * @return int
	 */
	public int insertListInfo(List<MgMsgInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 
	 * Description: 根据消息类型和接收状态查询消息列表
	 * @param msg_TYPE
	 * @param rc_FLAG
	 * @return
	 */
	public List<MgMsgRcBean> pageMsgRcBeansByRcAndType(String user_id,RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title, int start_recd, int limit_recd) {
		return dao.pageMsgRcBeansByRcAndType(user_id,rc_FLAG,msg_type,msg_title,start_recd,limit_recd);
	}
	
	/** 
	 * Description: 根据消息类型和接收状态查询消息总数
	 * @param user_id
	 * @param rc_FLAG
	 * @return 
	 */
	public int countMessageByRcAndType(String user_id, RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title) {
		return dao.countMessageByRcAndType(user_id,rc_FLAG,msg_type,msg_title);
	}


	/** 
	 * Description: 分页查询发送消息列表
	 * @param user_id
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<MgMsgInfo> pageMgMsgInfosByUser(String user_id, int start_recd,
			int limit_recd) {
		return dao.pageMgMsgInfosByUser(user_id,start_recd,limit_recd);
	}

	/** 
	 * Description: 查询已发送消息总数
	 * @param user_id
	 * @return 
	 */
	public int countSendMessage(String user_id) {
		return dao.countSendMessage(user_id);
	}

	/** 
	 * Description:  分页查询关注消息列表
	 * @param user_id
	 * @param yes
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<MgMsgRcBean> pageMsgRcBeansByAttent(String user_id,
			YN_FLAG yes,MSG_TYPE msg_type, int start_recd, int limit_recd) {
		return dao.pageMsgRcBeansByAttent(user_id,yes,msg_type,start_recd,limit_recd);
	}

	/** 
	 * Description: 查询关注消息总数
	 * @param user_id
	 * @param yes
	 * @return 
	 */
	public int countAttentMessage(String user_id, YN_FLAG yes,MSG_TYPE msg_type) {
		return dao.countAttentMessage(user_id,yes,msg_type);
	}

	/** 
	 * Description: 删除发送消息
	 * @param work_seq
	 * @param user_id 
	 */
	public void deleteSendMess(String work_seq) {
		dao.updateDelFlag(YN_FLAG.YES,work_seq);
	}

}