/**
 * Title: MgMsgDaoService.java
 * File Description: ��Ϣ��Ϣ��
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
 * Class description:��Ϣ��Ϣ��
 * @author AutoGen
 */
public class MgMsgDaoService {
	@Inject private MgMsgDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param MgMsgInfo info
	 * @return MgMsgInfo
	 */
	public MgMsgInfo getInfoByKey(MgMsgInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param MgMsgInfo info
	 * @return MgMsgInfo
	 */
	public MgMsgInfo getInfoByKeyForUpdate(MgMsgInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param MgMsgInfo info
	 * @return int
	 */
	public int insertInfo(MgMsgInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<MgMsgInfo>
	 * @return int
	 */
	public int insertListInfo(List<MgMsgInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * 
	 * Description: ������Ϣ���ͺͽ���״̬��ѯ��Ϣ�б�
	 * @param msg_TYPE
	 * @param rc_FLAG
	 * @return
	 */
	public List<MgMsgRcBean> pageMsgRcBeansByRcAndType(String user_id,RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title, int start_recd, int limit_recd) {
		return dao.pageMsgRcBeansByRcAndType(user_id,rc_FLAG,msg_type,msg_title,start_recd,limit_recd);
	}
	
	/** 
	 * Description: ������Ϣ���ͺͽ���״̬��ѯ��Ϣ����
	 * @param user_id
	 * @param rc_FLAG
	 * @return 
	 */
	public int countMessageByRcAndType(String user_id, RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title) {
		return dao.countMessageByRcAndType(user_id,rc_FLAG,msg_type,msg_title);
	}


	/** 
	 * Description: ��ҳ��ѯ������Ϣ�б�
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
	 * Description: ��ѯ�ѷ�����Ϣ����
	 * @param user_id
	 * @return 
	 */
	public int countSendMessage(String user_id) {
		return dao.countSendMessage(user_id);
	}

	/** 
	 * Description:  ��ҳ��ѯ��ע��Ϣ�б�
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
	 * Description: ��ѯ��ע��Ϣ����
	 * @param user_id
	 * @param yes
	 * @return 
	 */
	public int countAttentMessage(String user_id, YN_FLAG yes,MSG_TYPE msg_type) {
		return dao.countAttentMessage(user_id,yes,msg_type);
	}

	/** 
	 * Description: ɾ��������Ϣ
	 * @param work_seq
	 * @param user_id 
	 */
	public void deleteSendMess(String work_seq) {
		dao.updateDelFlag(YN_FLAG.YES,work_seq);
	}

}