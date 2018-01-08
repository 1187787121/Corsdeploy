/**
 * Title: EnvBuildTaskDaoService.java
 * File Description: 构建任务扩展表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.build.ea.dao;

import java.util.List;

import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:构建任务扩展表
 * @author AutoGen
 */
public class EnvBuildTaskDaoService {
	@Inject private EnvBuildTaskDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param EnvBuildTaskInfo info
	 * @return EnvBuildTaskInfo
	 */
	public EnvBuildTaskInfo getInfoByKey(String work_id) {
		return dao.get(work_id);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param EnvBuildTaskInfo info
	 * @return EnvBuildTaskInfo
	 */
	public EnvBuildTaskInfo getInfoByKeyForUpdate(EnvBuildTaskInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param EnvBuildTaskInfo info
	 * @return int
	 */
	public int insertInfo(EnvBuildTaskInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<EnvBuildTaskInfo>
	 * @return int
	 */
	public int insertListInfo(List<EnvBuildTaskInfo> infos) {
		return dao.insert(infos);
	}
	
	/**
	 * Description: 根据任务编号更新执行动作
	 * @return
	 */
	public int updateBuildStepIdById(int build_step_id, String work_id){
		return dao.updateBuildStepIdById(build_step_id, work_id);
	}

	/** 
	 * Description: 根据任务编号更新构建参数信息
	 * @param template_name
	 * @param uuid
	 * @param work_id 
	 */
	public int updateBuildParamInfoByKey(String template_name, String template_param_uuid, String work_id) {
		return dao.updateBuildParamInfoByKey(template_name, template_param_uuid, work_id);
	}
	
	/** 
	 * Description: 更新构建任务基本信息
	 * @param instance_id
	 * @param task_status
	 * @param exe_user_id
	 * @param start_bk_tm
	 * @param work_id 
	 */
	public int updateBuildTaskInfo(TASK_STATUS task_status, String exe_user_id, JaDateTime start_bk_tm, String work_id) {
		return dao.updateBuildTaskInfo(task_status, exe_user_id, start_bk_tm, work_id);
	}

	/**
	 * Description: 更新执行日志
	 * @return
	 */
	public int updateExecuteLog(String exelog_bk_path, String work_id){
		return dao.updateExecuteLog(exelog_bk_path, work_id);
	}
	
	/**
	 * Description: 根据主键修改任务状态
	 * @param task_status
	 * @param work_id
	 * @return
	 */
	public int updateTaskStatus(TASK_STATUS task_status, String work_id) {
		return dao.updateTaskStatus(task_status, work_id);
	}
	
	/**
	 * Description: 根据主键修改执行结束时间
	 * @param end_bk_tm
	 * @param work_id
	 * @return
	 */
	public int updateExecuteEndTime(JaDateTime end_bk_tm,
			EXE_RESULT exe_result, String work_id) {
		return dao.updateExecuteEndTime(end_bk_tm, exe_result, work_id);
	}
	
	/**
	 * Description: 
	 * @param work_id
	 * @return
	 */
	public int countExecuteTask(String work_id){
		return dao.countExecuteTaskByKey(work_id);
	}

	/** 
	 * Description: 根据任务ID更新模板名
	 * @param template_name
	 * @param work_id 
	 */
	public int updateTemplateByWorkId(String template_name, String work_id) {
		return dao.updateTemplateByWorkId(template_name, work_id);
	}

	/** 
	 * Description: 更新数据源UUID
	 * @param uuid
	 * @param work_id 
	 */
	public int updateSocUuid(String ver_soc_uuid, String work_id) {
		return dao.updateSocUuid(ver_soc_uuid, work_id);
	}

}