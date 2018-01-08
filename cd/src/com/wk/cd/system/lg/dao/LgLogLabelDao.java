/**
 * Title: LgLogLabelDao.java
 * File Description: 日志标记级别表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-23
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.db.*;
import com.wk.cd.system.lg.info.*;

/**
 * Class description:日志标记级别表
 * @author AutoGen
 */
abstract class LgLogLabelDao
		extends EntityDao<LgLogLabelInfo> {

	/**
	 * 查询标记流水号
	 * @param user_id 用户名
	 * @param log_label 日期标记级别
	 * @return 日志流水号
	 */
	@SqlParam(condition = "USER_ID = :crt_user_id and log_label = :log_label and log_label > 0")
	abstract DBIterator<String> getLogWorkSeqByLabel(String crt_user_id,
			int log_label);

	/**
	 * 查询标记流水号条数
	 * @param user_id 用户名
	 * @param log_label 日期标记级别
	 * @return 日志流水号
	 */
	@SqlParam(condition = "USER_ID = :crt_user_id and log_label = :log_label")
	abstract int countLogWorkSeqByLabel(String crt_user_id, int log_label);

	/**
	 * 根据流水号查询日志标记信息
	 * @param work_seq_str 流水号
	 * @return
	 */
	@SqlParam(sql = "select work_seq, user_id, log_label, label_bk_date, label_bk_time, backup_fld from LG_LOG_LABEL " +
			"where WORK_SEQ in ${work_seq_str::1=0} and (USER_ID = '${user_id}')", dynamic = true)
	abstract List<LgLogLabelInfo> queryLogLabelInfo(String work_seq_str,
			String user_id);
}