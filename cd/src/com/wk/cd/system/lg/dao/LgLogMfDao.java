/**
 * Title: LgLogMfDao.java
 * File Description: 任务日志流水表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.db.DBIterator;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;

/**
 * Class description:任务日志流水表
 * @author AutoGen
 */
abstract class LgLogMfDao
		extends EntityDao<LgLogMfInfo> {
	@SqlParam(updateSet = { "LOG_TXT" }, condition = "WORK_SEQ = :work_seq")
	abstract int updateLogTxtByKey(LgLogMfInfo info);

	@SqlParam(updateSet = { "SR_YN_FLAG" }, condition = "WORK_SEQ= :work_seq")
	abstract int updateLogStateByKey(LgLogMfInfo info);

	@SqlParam(sql = "select work_seq, org_channel_code, org_term_no, org_work_code, org_srv_name, org_srv_bk_desc, org_rs_code, org_ary_socname, " +
			"pend_work_seq, pend_work_code, pend_srv_name, pend_rs_code, pend_ary_socname, pendwk_bk_expl, pbl_code, sr_yn_flag, crt_user_id, " +
			"crt_user_cn_name, crt_dept_id, crt_dept_cn_name, crt_bk_date, crt_bk_time, log_txt, log_level, backup_fld " +
			"from LG_LOG_MF where (CRT_BK_DATE >= '${start_date}') and (CRT_BK_DATE <= '${end_date}') and (CRT_DEPT_ID in ${dept_str::1=0}) " +
			"order by CRT_BK_DATE desc, CRT_BK_TIME desc", dynamic = true)
	abstract List<LgLogMfInfo> pageLogMfByDate(JaDate start_date,
			JaDate end_date, String dept_str, int start_recd, int limit_recd);

	@SqlParam(sql = "select * from LG_LOG_MF where CRT_BK_DATE >= :start_date and CRT_BK_DATE <= :end_date order by CRT_BK_DATE desc, CRT_BK_TIME desc")
	abstract DBIterator<LgLogMfInfo> getAllLogInfoForFile(JaDate start_date,JaDate end_date);
	
	@SqlParam(sql = "select * from LG_LOG_MF where CRT_BK_DATE >= :start_date and CRT_BK_DATE <= :end_date and crt_user_id=:user_id order by CRT_BK_DATE desc, CRT_BK_TIME desc")
	abstract DBIterator<LgLogMfInfo> getSelfLogInfoForFile(JaDate start_date,JaDate end_date,String user_id);

	@SqlParam(sql = "select work_seq, org_channel_code, org_term_no, org_work_code, org_srv_name, org_srv_bk_desc, org_rs_code, " +
			"org_ary_socname, pend_work_seq, pend_work_code, pend_srv_name, pend_rs_code, pend_ary_socname, pendwk_bk_expl, pbl_code, " +
			"sr_yn_flag, crt_user_id, crt_user_cn_name, crt_dept_id, crt_dept_cn_name, crt_bk_date, crt_bk_time, log_txt, log_level, " +
			"backup_fld from LG_LOG_MF where WORK_SEQ in ${work_seq_str::1=0} order by CRT_BK_DATE desc, CRT_BK_TIME desc", dynamic = true)
	abstract List<LgLogMfInfo> pageLogByWorkSeqList(String work_seq_str,
			int start_recd, int limit_recd);

}