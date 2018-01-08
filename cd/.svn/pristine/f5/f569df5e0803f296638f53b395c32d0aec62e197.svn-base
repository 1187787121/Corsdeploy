/**
 * Title: EnvTaskDaoService.java
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
import com.wk.cd.build.exc.EnvTaskIsNotExistException;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.lang.Inject;
import com.wk.util.JaDateTime;

/**
 * Class description:环境任务表
 * @author AutoGen
 */
public class EnvTaskDaoService {
	@Inject
	private EnvTaskDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param EnvTaskInfo info
	 * @return EnvTaskInfo
	 */
	public EnvTaskInfo getInfoByKey(String work_id) {
		return dao.get(work_id);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param EnvTaskInfo info
	 * @return EnvTaskInfo
	 */
	public EnvTaskInfo getInfoByKeyForUpdate(EnvTaskInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param EnvTaskInfo info
	 * @return int
	 */
	public int insertInfo(EnvTaskInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<EnvTaskInfo>
	 * @return int
	 */
	public int insertListInfo(List<EnvTaskInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 统计环境任务表的个数
	 * @param prog_id
	 * @return
	 */
	public int countEnvTaskByProgId(String prog_id) {
		return dao.countEnvTaskByProgId(prog_id);
	}

	/**
	 * Description: 根据环境名查询正在执行和回退中的任务编号，并且排除自己的work_id
	 * @param env_name
	 */
	public List<EnvTaskInfo> getIdByEnvExceptId(String env_name,String work_id) {
		System.out.println(env_name+":"+work_id);
		return dao.getIdByEnvExceptId(env_name,work_id);
	}
	
	/**
	 * Description: 根据环境名查询正在执行的任务编号
	 * @param env_name
	 * @return
	 */
	public List<EnvTaskInfo> getIdByEnv(String env_name){
		return dao.getIdByEnv(env_name);
	}
	
	/**
	 * Description: 根据环境及任务类型查询多条记录
	 * @param env_name
	 * @param task_type
	 */
	public List<EnvTaskInfo> pageInfosByEnv(String env_name,
			TASK_TYPE task_type, int start_recd, int limit_recd) {
		return dao.pageInfosByEnv(env_name, task_type, start_recd, limit_recd);
	}

	/**
	 * Description: 根据环境及任务类型查询记录总数
	 * @param env_name
	 * @param task_type
	 * @return
	 */
	public int countTaskByEnv(String env_name, TASK_TYPE task_type) {
		return dao.countTaskByEnv(env_name, task_type);
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
	 * Description: 根据主键更新日志路径
	 * @param exelog_bk_path
	 * @param work_id
	 * @return
	 */
	public int updateTaskLogPath(String exelog_bk_path,String work_id){
		return dao.updateTaskLogPath(exelog_bk_path, work_id);
	}

	/**
	 * Description: 根据主键修改执行开始时间
	 * @param start_bk_tm
	 * @param work_id
	 * @return
	 */
	public int updateExecuteStartTime(JaDateTime start_bk_tm, String work_id) {
		return dao.updateExecuteStartTime(start_bk_tm, work_id);
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
	 * Description: 根据任务编号查看数量
	 * @param work_id
	 * @return
	 */
	public int countByWorkId(String work_id){
		return dao.countByWorkId(work_id);
	}
	
	/**
	 * Description: 更新执行日志
	 * @return
	 */
	public int updateExecuteLog(String exelog_bk_path, String work_id){
		return dao.updateExecuteLog(exelog_bk_path, work_id);
	}

	/** 
	 * Description: 根据环境名称查询任务表
	 * @param env_name
	 * @return 
	 */
	public List<EnvTaskInfo> queryTaskInfoByEnvName(String env_name) {
		return dao.queryTaskInfoByEnvName(env_name);
	}

	/** 
	 * Description: 根据项目名称查询任务表
	 * @param project_name
	 * @return 
	 */
	public List<EnvTaskInfo> queryTaskInfoByProName(String project_name) {
		return dao.queryTaskInfoByProName(project_name);
	}

	/** 
	 * Description: 更新集成目标包清单
	 * @param uuid
	 * @param work_id 
	 */
	public int updateTagpacListUuid(String target_list_uuid, String work_id) {
		return dao.updateTagpacListUuid(target_list_uuid, work_id);
	}

	/** 
	 * Description: 更新发布目标版本号
	 * @param target_ver_num
	 * @param work_id 
	 */
	public int updateTargetVerNum(String target_ver_num, String work_id) {
		return dao.updateTargetVerNum(target_ver_num, work_id);
	}
	
	public int deleteByKey(String work_id){
		return dao.delete(work_id);
	}

	/** 
	 * Description: 
	 * @param env_name
	 * @param build
	 * @return 
	 */
	public int countTaskByEnvAndStatus(String env_name, TASK_TYPE task_type) {
		return dao.countTaskByEnvAndStatus(env_name,task_type);
	}
	
	/**
	 * Description: 根据任务编号更新执行动作
	 * @return
	 */
	public int updateExeMethodById(EXE_METHOD exe_method, String work_id){
		return dao.updateExeMethodById(exe_method, work_id);
	}

	/** 
	 * Description: 校检任务编号是否存在，不存在就抛出异常
	 * @param work_id 
	 */
	public void checkEnvIdIsNotExist(String work_id) {
		if(dao.countByWorkId(work_id) <= 0) {
			throw new EnvTaskIsNotExistException().addScene("TASK", work_id);
		}
	}

	/**
	 * Description: 更新环境构建任务,修改任务名称、环境编号
	 * @param task_bk_desc
	 * @param project_name
	 * @param work_id
	 * @return
	 */
	public int updateBuildTaskInfoByWorkId(String task_bk_desc, String project_name, String work_id) {
		return dao.updateBuildTaskInfoByWorkId(task_bk_desc, project_name, work_id);
	}

	/** 
	 * Description: 更新环境构建任务，修改状态、结束时间、执行人
	 * @param executed
	 * @param now
	 * @param work_id 
	 */
	public int updateBuildStatusByWorkId(TASK_STATUS executed, JaDateTime now, String exe_user_id,
			String work_id) {
		return dao.updateBuildStatusByWorkId(executed, now, exe_user_id, work_id);
	}

	/** 
	 * Description: 更新实例ID
	 * @param inst_id
	 * @param work_id 
	 */
	public int updateInstanceId(String inst_id, String work_id) {
		return dao.updateInstanceId(inst_id, work_id);
	}

	/** 
	 * Description: 更新源码版本机版本号
	 * @param code_ver_num
	 * @param work_id 
	 */
	public int updateCodeVerNum(String code_ver_num, String work_id) {
		return dao.updateCodeVerNum(code_ver_num, work_id);
	}
	
	/** 
	 * Description: 根据环境名称分页查询任务表
	 * @param env_name
	 * @param limit_recd 
	 * @param start_recd 
	 * @return 
	 */
	public List<EnvTaskInfo> pageTaskInfoByEnvName(String env_name, int start_recd, int limit_recd) {
		return dao.pageTaskInfoByEnvName(env_name, start_recd, limit_recd);
	}
	
	/** 
	 * Description: 根据环境名称查询任务总量
	 * @param env_name
	 * @return 
	 */
	public int countTaskInfoByEnvName(String env_name) {
		return dao.countTaskInfoByEnvName(env_name);
	}

}