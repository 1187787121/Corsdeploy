/**
 * Title: MgMsgDao.java
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
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:��Ϣ��Ϣ��
 * @author AutoGen
 */
abstract class MgMsgDao extends EntityDao<MgMsgInfo> {
	/** 
	 * Description: ������Ϣ���ͺͽ���״̬��ѯ��Ϣ�б�
	 * @param user_id
	 * @param rc_FLAG
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select * from mg_msg mg,mg_msg_user mu where mg.WORK_SEQ=mu.WORK_SEQ and mu.RC_USER_ID='${user_id}' and (RC_FLAG=${rc_FLAG}) and (MSG_TYPE=${msg_type}) and (msg_title like '%${msg_title}%') and mu.RCD_STATE=1 and mg.RCD_STATE=1 order by mg.crt_bk_date desc,mg.crt_bk_time desc",dynamic=true)
	abstract List<MgMsgRcBean> pageMsgRcBeansByRcAndType(String user_id,RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title, int start_recd, int limit_recd);

	/** 
	 * Description: ������Ϣ���ͺͽ���״̬��ѯ��Ϣ����
	 * @param user_id
	 * @param rc_FLAG
	 * @return 
	 */
	@SqlParam(sql="select count(*) from mg_msg mg,mg_msg_user mu where mg.WORK_SEQ=mu.WORK_SEQ and mu.RC_USER_ID='${user_id}' and (RC_FLAG=${rc_FLAG}) and (MSG_TYPE=${msg_type}) and (msg_title like '%${msg_title}%') and mu.RCD_STATE=1 and mg.RCD_STATE=1",dynamic=true)
	abstract int countMessageByRcAndType(String user_id, RC_FLAG rc_FLAG,MSG_TYPE msg_type,String msg_title);
	
	/** 
	 * Description:  ��ҳ��ѯ������Ϣ�б�
	 * @param user_id
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select * from mg_msg where CRT_USER_ID=:user_id and DEL_YN_FLAG=2 and RCD_STATE=1 order by crt_bk_date desc,crt_bk_time desc")
	abstract List<MgMsgInfo> pageMgMsgInfosByUser(String user_id, int start_recd,int limit_recd);

	/** 
	 * Description: ��ѯ�ѷ�����Ϣ����
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql="select count(*) from mg_msg where CRT_USER_ID=:user_id and DEL_YN_FLAG=2 and RCD_STATE=1")
	abstract int countSendMessage(String user_id);

	/** 
	 * Description: ��ҳ��ѯ��ע��Ϣ�б�
	 * @param user_id
	 * @param yes
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql="select * from mg_msg mg,mg_msg_user mu where mg.WORK_SEQ=mu.WORK_SEQ and mu.RC_USER_ID='${user_id}' and ATTENT_YN_FLAG=${yes} and (MSG_TYPE=${msg_type}) and mu.RCD_STATE=1 and mg.RCD_STATE=1 order by mg.crt_bk_date desc,mg.crt_bk_time desc",dynamic=true)
	abstract List<MgMsgRcBean> pageMsgRcBeansByAttent(String user_id,
			YN_FLAG yes,MSG_TYPE msg_type, int start_recd, int limit_recd);

	/** 
	 * Description:  ��ѯ��ע��Ϣ����
	 * @param user_id
	 * @param yes
	 * @return 
	 */
	@SqlParam(sql="select count(*) from mg_msg mg,mg_msg_user mu where mg.WORK_SEQ=mu.WORK_SEQ and mu.RC_USER_ID='${user_id}' and ATTENT_YN_FLAG=${yes} and (MSG_TYPE=${msg_type}) and mu.RCD_STATE=1 and mg.RCD_STATE=1",dynamic=true)
	abstract int countAttentMessage(String user_id, YN_FLAG yes,MSG_TYPE msg_type);

	/** 
	 * Description: ɾ��������Ϣ
	 * @param yes
	 * @param work_seq
	 * @param user_id 
	 */
	@SqlParam(updateSet = { "del_yn_flag" }, condition = "PK")
	abstract void updateDelFlag(YN_FLAG yes, String work_seq);
}