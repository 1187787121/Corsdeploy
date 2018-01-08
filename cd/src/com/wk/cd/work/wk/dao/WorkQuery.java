/**
 * Title: WorkQuery.java
 * File Description: ����ģ���ѯ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tianlw
 * @version: 1.0
 * @date: 2015-4-24
 */
package com.wk.cd.work.wk.dao;

import java.util.List;

import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.db.Query;
import com.wk.db.SqlParam;

/**
 * Class Description: ����ģ���ѯ��
 * @author tianlw
 */
@Query
public abstract class WorkQuery {
	
	/** 
	 * Description:  ��ҳ��ѯ����״̬(���ˡ���Ȩ)��Ϣ
	 * @param crt_user_id �û�id
	 * @param detail_type ����״̬
	 * @param start_recd ��ʼ��¼��
	 * @param limit_recd �ܼ�¼��
	 * @return  �����б�
	 */
	@SqlParam(sql = "select pend_work_seq, submit_work_seq, pend_work_code, pend_srv_name, pend_rs_code, pend_ary_socname, pendwk_bk_expl, " +
			"pend_deal_seq, pend_user_id, pbl_code, proxy_user_id, crt_user_id, crt_user_cn_name, crt_dept_id, crt_dept_cn_name, crt_bk_date, " +
			"crt_bk_time, workflow_state, backup_fld, rcd_state from WK_DEAL_STATE WHERE RCD_STATE = 1 and PEND_WORK_SEQ in (select B.PEND_WORK_SEQ " +
			"from WK_DEAL_DETAIL B WHERE B.DEAL_USER_ID = :crt_user_id and B.DEAL_TYPE = :deal_type) order by PEND_WORK_SEQ desc")
	abstract List<WkDealStateInfo> pageInfoListCheckAndAuth(String crt_user_id,
			DEAL_TYPE deal_type, int start_recd, int limit_recd);
	
	/** 
	 * Description: ��ҳ��ѯ����״̬(���ˡ���Ȩ)����
	 * @param crt_user_id �û�id
	 * @param detail_type ����״̬
	 * @return  �����б�
	 */
	@SqlParam(sql = "select count(*) from WK_DEAL_STATE WHERE RCD_STATE = 1 and PEND_WORK_SEQ in (select B.PEND_WORK_SEQ from WK_DEAL_DETAIL B WHERE B.DEAL_USER_ID = :crt_user_id and B.DEAL_TYPE = :deal_type)")
	abstract int countpageInfoListCheckAndAuth(String crt_user_id, DEAL_TYPE deal_type);
}
