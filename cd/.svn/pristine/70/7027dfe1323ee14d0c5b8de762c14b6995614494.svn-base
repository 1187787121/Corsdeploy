/**
 * Title: EnvTaskDao.java
 * File Description: 环境任务表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:环境任务表
 * @author AutoGen
 */
abstract class EnvTaskDao
		extends EntityDao<EnvTaskInfo> {

	/**
	 * Description: 统计环境任务表的个数
	 * @param prog_id
	 * @return
	 */
	@SqlParam(condition = "PROG_ID =:prog_id")
	abstract int countEnvTaskByProgId(String prog_id);

	/**
	 * Description: 根据环境名查询正在执行的任务编号
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND WORK_ID !=:work_id AND (TASK_STATUS = 1 OR TASK_STATUS = 2 OR TASK_STATUS = 4)")
	abstract List<EnvTaskInfo> getIdByEnvExceptId(String env_name, String work_id);
	
	/**
	 * Description: 根据环境名查询正在执行的任务编号
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND (TASK_STATUS = 2 OR TASK_STATUS = 4)")
	abstract List<EnvTaskInfo> getIdByEnv(String env_name );

	/**
	 * Description: 根据环境及任务类型查询多条记录
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND TASK_TYPE =:task_type", orderBy="start_bk_tm desc,crt_bk_date desc,crt_bk_time desc")
	abstract List<EnvTaskInfo> pageInfosByEnv(String env_name,
			TASK_TYPE task_type, int start_recd, int limit_recd);

	/**
	 * Description: 根据环境及任务类型查询记录总数
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND TASK_TYPE =:task_type")
	abstract int countTaskByEnv(String env_name, TASK_TYPE task_type);

	/**
	 * Description: 根据主键修改任务状态
	 * @param task_status
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "TASK_STATUS" }, condition = "PK")
	abstract int updateTaskStatus(TASK_STATUS task_status, String work_id);
	
	/**
	 * Description: 根据主键更新日志路径
	 * @param exelog_bk_path
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "EXELOG_BK_PATH" }, condition = "PK")
	abstract int updateTaskLogPath(String exelog_bk_path, String work_id);

	/**
	 * Description: 根据主键修改执行开始时间
	 * @param start_bk_tm
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "START_BK_TM" }, condition = "PK")
	abstract int updateExecuteStartTime(JaDateTime start_bk_tm, String work_id);

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
	 * Description: 根据任务编号查看数量
	 * @param work_id
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id")
	abstract int countByWorkId(String work_id);

	/** 
	 * Description: 更新执行日志
	 * @param exelog_bk_path
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"EXELOG_BK_PATH"}, condition = "PK")
	abstract int updateExecuteLog(String exelog_bk_path, String work_id);

	/** 
	 * Description: 根据环境名称查询任务表
	 * @param env_name
	 * @return 
	 */
	@SqlParam(sql="SELECT * FROM ENV_TASK WHERE ENV_NAME ='${env_name}' ORDER BY CRT_BK_DATE DESC, CRT_BK_TIME DESC",dynamic=true)
	abstract List<EnvTaskInfo> queryTaskInfoByEnvName(String env_name);

	/** 
	 * Description: 根据项目名查询任务表
	 * @param project_name
	 * @return 
	 */
	@SqlParam(condition = "PROJECT_NAME =:project_name")
	abstract List<EnvTaskInfo> queryTaskInfoByProName(String project_name);

	/** 
	 * Description: 更新集成目标包清单
	 * @param target_list_uuid
	 * @param work_id 
	 */
	@SqlParam(updateSet = {"TAGPAC_LIST_UUID"}, condition = "PK")
	abstract int updateTagpacListUuid(String tagpac_list_uuid, String work_id);

	/** 
	 * Description: 更新发布目标版本号
	 * @param target_ver_num
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"TARGET_VER_NUM"}, condition = "PK")
	abstract int updateTargetVerNum(String target_ver_num, String work_id);

	/** 
	 * Description: 
	 * @param env_name
	 * @param task_type
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND TASK_TYPE =:task_type AND (TASK_STATUS = 2 OR TASK_STATUS = 4)")
	abstract int countTaskByEnvAndStatus(String env_name, TASK_TYPE task_type);

	/** 
	 * Description: 根据任务编号更新执行动作
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"EXE_METHOD"}, condition = "PK")
	abstract int updateExeMethodById(EXE_METHOD exe_method, String work_id);

	/** 
	 * Description: 更新环境构建任务,修改任务名称、环境编号
	 * @param work_id
	 * @param task_bk_desc
	 * @param project_name
	 * @return 
	 */
	@SqlParam(updateSet = {"TASK_BK_DESC", "PROJECT_NAME"}, condition = "PK")
	abstract int updateBuildTaskInfoByWorkId(String task_bk_desc, String project_name, String work_id);

	/** 
	 * Description: 更新环境构建任务，修改状态、结束时间、执行人
	 * @param executed
	 * @param now
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"TASK_STATUS", "END_BK_TM", "EXE_USER_ID"}, condition = "PK")
	abstract int updateBuildStatusByWorkId(TASK_STATUS executed,
			JaDateTime now, String exe_user_id, String work_id);

	/** 
	 * Description: 更新实例ID
	 * @param inst_id
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"INSTANCE_ID"}, condition = "PK")
	abstract int updateInstanceId(String instance_id, String work_id);

	/** 
	 * Description: 更新源码版本机版本号
	 * @param code_ver_num
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"CODE_VER_NUM"}, condition = "PK")
	abstract int updateCodeVerNum(String code_ver_num, String work_id);

	/** 
	 * Description: 根据环境名称分页查询任务表
	 * @param env_name
	 * @param limit_recd 
	 * @param start_recd 
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract List<EnvTaskInfo> pageTaskInfoByEnvName(String env_name, int start_recd, int limit_recd);
	
	/** 
	 * Description: 根据环境名称查询任务总量
	 * @param env_name
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countTaskInfoByEnvName(String env_name);
}