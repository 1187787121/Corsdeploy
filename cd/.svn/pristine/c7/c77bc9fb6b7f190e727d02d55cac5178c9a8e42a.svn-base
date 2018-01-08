/**
 * Title: EnvBuildTaskDao.java
 * File Description: 构建任务扩展表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:构建任务扩展表
 * @author AutoGen
 */
abstract class EnvBuildTaskDao extends EntityDao<EnvBuildTaskInfo> {

	/** 
	 * Description: 根据任务编号更新构建步骤数
	 * @param build_step_id 构建步骤数
	 * @param work_id 任务编号
	 * @return 
	 */
	@SqlParam(updateSet = {"BUILD_STEP_ID"}, condition = "PK")
	abstract int updateBuildStepIdById(int build_step_id, String work_id);

	/** 
	 * Description: 根据任务编号更新构建参数信息
	 * @param template_name
	 * @param uuid
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"TEMPLATE_NAME", "TEMPLATE_PARAM_UUID"}, condition = "PK")
	abstract int updateBuildParamInfoByKey(String template_name, String template_param_uuid, String work_id);
	
	/** 
	 * Description: 更新构建任务基本信息
	 * @param instance_id
	 * @param task_status
	 * @param exe_user_id
	 * @param start_bk_tm
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"TASK_STATUS", "EXE_USER_ID", "START_BK_TM"}, condition = "PK")
	abstract int updateBuildTaskInfo(TASK_STATUS task_status, String exe_user_id, JaDateTime start_bk_tm, String work_id);
	
	/** 
	 * Description: 更新执行日志
	 * @param exelog_bk_path
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"EXELOG_BK_PATH"}, condition = "PK")
	abstract int updateExecuteLog(String exelog_bk_path, String work_id);
	
	/**
	 * Description: 根据主键修改任务状态
	 * @param task_status
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "TASK_STATUS" }, condition = "PK")
	abstract int updateTaskStatus(TASK_STATUS task_status, String work_id);
	
	/**
	 * Description: 根据主键修改执行结束时间
	 * @param end_bk_tm
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "END_BK_TM", "EXE_RESULT" }, condition = "PK")
	abstract int updateExecuteEndTime(JaDateTime end_bk_tm,
			EXE_RESULT exe_result, String work_id);

	/** 
	 * Description: 
	 * @param work_id
	 * @return 
	 */
	@SqlParam(condition = "PK")
	abstract int countExecuteTaskByKey(String work_id);

	/** 
	 * Description: 根据任务ID更新模板名
	 * @param template_name
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"TEMPLATE_NAME"}, condition = "PK")
	abstract int updateTemplateByWorkId(String template_name, String work_id);

	/** 
	 * Description: 更新数据源UUID
	 * @param uuid
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = "VER_SOC_UUID", condition = "PK")
	abstract int updateSocUuid(String ver_soc_uuid, String work_id);
}