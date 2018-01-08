/**
 * Title: BuildScriptDao.java
 * File Description: 构建脚本信息表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.BuildScriptInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:构建脚本信息表
 * @author AutoGen
 */
abstract class BuildScriptDao
		extends EntityDao<BuildScriptInfo> {

	/**
	 * Description: 根据主键查询一条记录
	 * @param work_id
	 * @param script_bk_seq
	 * @param script_type
	 * @return
	 */
	@SqlParam(condition = "PK")
	abstract BuildScriptInfo getScriptByKey(String work_id,
			SCRIPT_TYPE script_type, long scirpt_bk_seq);

	/**
	 * Description: 修改构建的时间和状态
	 * @param end_bk_tm
	 * @param exe_status
	 * @param exe_result
	 * @param work_id
	 * @param script_type
	 * @param scirpt_bk_seq
	 */
	@SqlParam(updateSet = { "END_BK_TM", "EXE_STATUS", "EXE_RESULT" }, condition = "PK")
	abstract int updateScriptStatusInfoByKey(JaDateTime end_bk_tm,
			EXE_STATUS exe_status, EXE_RESULT exe_result, String work_id,
			SCRIPT_TYPE script_type, long scirpt_bk_seq);

	/**
	 * Description: 修改耗时
	 * @param time_used
	 * @param work_id
	 * @param script_type
	 * @param scirpt_bk_seq
	 * @return
	 */
	@SqlParam(updateSet = { "TIME_USED" }, condition = "PK")
	abstract int updateScriptTimeByKey(int time_used, String work_id,
			SCRIPT_TYPE script_type, long scirpt_bk_seq);

	/**
	 * Description: 通过任务编号和脚本类型
	 * @param work_id
	 * @param script_type
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id AND SCRIPT_TYPE =:script_type")
	abstract List<BuildScriptInfo> getScriptByIdAndType(String work_id,
			SCRIPT_TYPE script_type);

	/**
	 * Description: 通过任务编号获得脚本信息
	 * @param work_id
	 * @param script_type
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id")
	abstract List<BuildScriptInfo> getScriptByWorkId(String work_id);
	/** 
	 * Description: 
	 * @param work_id
	 * @param script_type
	 * @return 
	 */
	@SqlParam(condition = "WORK_ID =:work_id AND SCRIPT_TYPE =:script_type")
	abstract int countBuildScript(String work_id, SCRIPT_TYPE script_type);

}