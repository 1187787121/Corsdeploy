/**
 * Title: LgLogQuery.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年5月22日
 */
package com.wk.cd.system.lg.dao;

import java.util.List;

import com.wk.cd.system.lg.bean.LogBean;
import com.wk.db.Query;
import com.wk.db.SqlParam;
import com.wk.util.JaDate;

/**
 * @author HT
 */
@Query
public abstract class LgLogQuery {
	
	/**
	 * 用户查询自己收藏的日志信息列表
	 * @param log_level
	 * @param start_date
	 * @param end_date
	 * @param user_id
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	@SqlParam(sql = "select A.*, B.log_label from LG_LOG_MF A, LG_LOG_LABEL B where A.WORK_SEQ = B.WORK_SEQ and A.LOG_LEVEL = :log_level and A.CRT_BK_DATE >= :start_date and A.CRT_BK_DATE <= :end_date and B.USER_ID = :user_id and B.LOG_LABEL = 1 order by WORK_SEQ desc")
	abstract List<LogBean> pageLogByDateAndLabel(int log_level,JaDate start_date,JaDate end_date, String user_id, int start_recd,int limit_recd);
	
	/** 
	 * Description: 用户查询自己收藏的日志信息列表总数
	 * @param log_level
	 * @param start_date
	 * @param end_date
	 * @param user_id
	 * @return 
	 */
	@SqlParam(sql = "select count(*) from LG_LOG_MF A, LG_LOG_LABEL B where A.WORK_SEQ = B.WORK_SEQ and A.LOG_LEVEL = :log_level and A.CRT_BK_DATE >= :start_date and A.CRT_BK_DATE <= :end_date and B.USER_ID = :user_id and B.LOG_LABEL = 1")
	abstract int countLogByDateAndLabel(int log_level, JaDate start_date,JaDate end_date, String user_id);
	
	/**
	 * 用户查询自己的日志及收藏信息列表
	 * @param user_id
	 * @param log_level
	 * @param start_date
	 * @param end_date
	 * @param user_id1
	 * @param start_recd
	 * @param limit_recd
	 * @return
	 */
	@SqlParam(sql = "select A.*,(select LOG_LABEL from lg_log_label B where A.WORK_SEQ=B.WORK_SEQ and B.USER_ID=:user_id) LOG_LABEL from lg_log_mf A where A.LOG_LEVEL = :log_level and A.CRT_BK_DATE >= :start_date and A.CRT_BK_DATE <= :end_date and A.CRT_USER_ID = :user_id1 order by WORK_SEQ desc")
	abstract List<LogBean> pageLogByDate(String user_id,int log_level,JaDate start_date, JaDate end_date,String user_id1,int start_recd, int limit_recd);

	/** 
	 * Description: 用户查询自己的日志及收藏信息列表总数
	 * @param user_id
	 * @param log_level
	 * @param start_date
	 * @param end_date
	 * @param user_id2
	 * @return 
	 */
	@SqlParam(sql = "select count(*) from lg_log_mf A where A.LOG_LEVEL = :log_level and A.CRT_BK_DATE >= :start_date and A.CRT_BK_DATE <= :end_date and A.CRT_USER_ID = :user_id")
	abstract int countLogByDate(int log_level, JaDate start_date,JaDate end_date, String user_id);

	/** 
	 * Description: 管理员查询所有的日志及收藏信息列表
	 * @param start_date
	 * @param end_date
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql = "select A.*, B.log_label from LG_LOG_MF A, LG_LOG_LABEL B where A.WORK_SEQ = B.WORK_SEQ and A.LOG_LEVEL = :log_level and A.CRT_BK_DATE >= :start_date and A.CRT_BK_DATE <= :end_date and B.LOG_LABEL = 1 order by WORK_SEQ desc")
	abstract List<LogBean> pageAllLogByDateAndLabel(int log_level,JaDate start_date,JaDate end_date, int start_recd, int limit_recd);

	/** 
	 * Description: 管理员查询所有的日志及收藏信息列表总数
	 * @param log_level
	 * @param start_date
	 * @param end_date
	 * @return 
	 */
	@SqlParam(sql = "select count(*) from LG_LOG_MF A, LG_LOG_LABEL B where A.WORK_SEQ = B.WORK_SEQ and A.LOG_LEVEL = :log_level and A.CRT_BK_DATE >= :start_date and A.CRT_BK_DATE <= :end_date and B.LOG_LABEL = 1")
	abstract int countAllLogByDateAndLabel(int log_level, JaDate start_date,JaDate end_date);
	
	/** 
	 * Description: 管理员查询自己收藏信息列表
	 * @param start_date
	 * @param end_date
	 * @param start_recd
	 * @param limit_recd
	 * @return 
	 */
	@SqlParam(sql = "select A.*,(select LOG_LABEL from lg_log_label B where A.WORK_SEQ=B.WORK_SEQ and B.USER_ID=:user_id) LOG_LABEL from lg_log_mf A where A.LOG_LEVEL =:log_level and A.CRT_BK_DATE >=:start_date and A.CRT_BK_DATE <=:end_date order by WORK_SEQ desc")
	abstract List<LogBean> pageAllLogByDate(String user_id,int log_level,JaDate start_date, JaDate end_date,int start_recd, int limit_recd);
	
	/** 
	 * Description: 管理员查询自己收藏信息列表总数
	 * @param log_level
	 * @param start_date
	 * @param end_date
	 * @return 
	 */
	@SqlParam(sql = "select count(*) from lg_log_mf A where A.LOG_LEVEL =:log_level and A.CRT_BK_DATE >=:start_date and A.CRT_BK_DATE <=:end_date")
	abstract int countAllLogByDate(int log_level,JaDate start_date, JaDate end_date);
}
