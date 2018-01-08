/**
 * Title: MgMsgService.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��3��31��
 */
package com.wk.cd.system.mg.service;

import java.util.List;

import com.wk.cd.enu.MSG_TYPE;
import com.wk.cd.enu.RC_FLAG;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.mg.bean.MgMsgRcBean;
import com.wk.cd.system.mg.dao.MgMsgDaoService;
import com.wk.cd.system.mg.dao.MgMsgUserDaoService;
import com.wk.cd.system.mg.info.MgMsgInfo;
import com.wk.cd.system.mg.info.MgMsgUserInfo;
import com.wk.lang.Inject;

/**
 * Class Description:��Ϣ������
 * @author HT
 */
public class MgMsgService {
	@Inject
	private MgMsgDaoService mgMsgDaoService;
	@Inject
	private MgMsgUserDaoService mgMsgUserDaoService;
	
	/**
	 * 
	 * Description: ������Ϣ��Ϣ
	 * @param info
	 * @return
	 */
	public int insertMgMsgInfo(MgMsgInfo info) {
		return mgMsgDaoService.insertInfo(info);
	}
	
	/**
	 * 
	 * Description: ������Ϣ������Ϣ�б�
	 * @param infos
	 * @return
	 */
	public int insertMgMsgInfoList(List<MgMsgUserInfo> infos) {
		return mgMsgUserDaoService.insertListInfo(infos);
	}

	/**
	 * 
	 * Description: ��ȡ��Ϣ��ϸ��Ϣ
	 * @param workseq
	 * @return
	 */
	public MgMsgInfo getMgMsgInfoById(String workseq) {
		MgMsgInfo info = new MgMsgInfo();
		info.setWork_seq(workseq);
		return mgMsgDaoService.getInfoByKey(info);
	}
	

	/**
	 * 
	 * Description: ������Ϣ���ͺͽ���״̬��ѯ��Ϣ�б�
	 * @param rc_FLAG
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	public List<MgMsgRcBean> pageMsgRcBeansByRcAndType(String user_id,RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title,int start_recd,int limit_recd) {
		return mgMsgDaoService.pageMsgRcBeansByRcAndType(user_id,rc_FLAG,msg_type,msg_title,start_recd,limit_recd);
	}
	
	/** 
	 * Description: ������Ϣ���ͺͽ���״̬��ѯ��Ϣ����
	 * @param user_id
	 * @param yes
	 * @return 
	 */
	public int countMessageByRcAndType(String user_id, RC_FLAG rc_FLAG, MSG_TYPE msg_type,String msg_title) {
		return mgMsgDaoService.countMessageByRcAndType(user_id,rc_FLAG,msg_type,msg_title);
	}
	
	/**
	 * 
	 * Description:  ��ҳ��ѯ������Ϣ�б�
	 * @param user_id
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	public List<MgMsgInfo> pageMgMsgInfosByUser(String user_id,int start_recd,int limit_recd) {
		return mgMsgDaoService.pageMgMsgInfosByUser(user_id,start_recd,limit_recd);
	}

	/** 
	 * Description: ��ѯ�ѷ�����Ϣ����
	 * @param user_id
	 * @return 
	 */
	public int countSendMessage(String user_id) {
		return mgMsgDaoService.countSendMessage(user_id);
	}

	/** 
	 * Description: ��ҳ��ѯ��ע��Ϣ�б�
	 * @param user_id
	 * @param yes
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	public List<MgMsgRcBean> pageMsgRcBeansByAttent(String user_id,
			YN_FLAG yes,MSG_TYPE msg_type, int start_recd, int limit_recd) {
		return mgMsgDaoService.pageMsgRcBeansByAttent(user_id,yes,msg_type,start_recd,limit_recd);
	}

	/** 
	 * Description: ��ѯ��ע��Ϣ����
	 * @param user_id
	 * @param yes
	 * @return 
	 */
	public int countAttentMessage(String user_id, YN_FLAG yes,MSG_TYPE msg_type) {
		return mgMsgDaoService.countAttentMessage(user_id,yes,msg_type);
	}

	/** 
	 * Description: �޸Ľ���״̬
	 * @param work_seq
	 * @param user_id 
	 */
	public void updateRcFlag(String work_seq, String user_id) {
		mgMsgUserDaoService.updateRcFlag(work_seq,user_id);
	}

	/** 
	 * Description:  �޸Ĺ�ע��־
	 * @param attent_flag
	 * @param work_seq
	 * @param user_id 
	 */
	public void updateAttentFlag(YN_FLAG attent_flag, String work_seq,
			String user_id) {
		mgMsgUserDaoService.updateAttentFlag(attent_flag,work_seq,user_id);
	}

	/** 
	 * Description: ɾ��������Ϣ
	 * @param work_seq
	 * @param user_id 
	 */
	public void deleteRcMess(String work_seq, String user_id) {
		mgMsgUserDaoService.deleteRcMess(work_seq,user_id);
	}

	/** 
	 * Description: ɾ��������Ϣ
	 * @param work_seq
	 * @param user_id 
	 */
	public void deleteSendMess(String work_seq) {
		mgMsgDaoService.deleteSendMess(work_seq);
	}

}
