/**
 * Title: BuildScriptExeDao.java
 * File Description: 构建脚本执行表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.BuildScriptExeInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:构建脚本执行表
 * @author AutoGen
 */
abstract class BuildScriptExeDao
		extends EntityDao<BuildScriptExeInfo> {

	/**
	 * Description:
	 * @param start_bk_tm
	 * @param exe_status
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "START_BK_TM", "EXE_STATUS" }, condition = "PK")
	abstract int updateExeStarBkTmByKey(JaDateTime start_bk_tm,
			EXE_STATUS exe_status, String instance_id, int exe_bk_no);

	/**
	 * Description:
	 * @param end_bk_tm
	 * @param exe_status
	 * @param exe_result
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "END_BK_TM", "EXE_STATUS", "EXE_RESULT" }, condition = "PK")
	abstract int updateExeEndBkTmByKey(JaDateTime end_bk_tm,
			EXE_STATUS exe_status, EXE_RESULT exe_result, String instance_id,
			int exe_bk_no);

	/**
	 * Description: 更新执行信息
	 * @param exec_text
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "EXEC_TEXT" }, condition = "PK")
	abstract int updateExeMsgByKey(String exec_text, String instance_id,
			int exe_bk_no);

	/**
	 * Description: 通过Key获得一条记录
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(condition = "PK")
	abstract BuildScriptExeInfo getExeScriptByKey(String instance_id,
			int exe_bk_no);

	/**
	 * Description:
	 * @param time_used
	 * @param instance_id
	 * @param exe_bk_no
	 * @return
	 */
	@SqlParam(updateSet = { "TIME_USED" }, condition = "PK")
	abstract int updateCostTmBykey(int time_used, String instance_id,
			int exe_bk_no);

	/** 
	 * Description: 获得构建执行信息
	 * @param inst_id
	 * @return 
	 */
	@SqlParam(condition = "INSTANCE_ID =:instance_id")
	abstract List<BuildScriptExeInfo> getExeScriptMsgByInstId(String instance_id);

}