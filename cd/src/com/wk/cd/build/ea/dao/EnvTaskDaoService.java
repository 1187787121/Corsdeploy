/**
 * Title: EnvTaskDaoService.java
 * File Description: ���������
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
 * Class description:���������
 * @author AutoGen
 */
public class EnvTaskDaoService {
	@Inject
	private EnvTaskDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param EnvTaskInfo info
	 * @return EnvTaskInfo
	 */
	public EnvTaskInfo getInfoByKey(String work_id) {
		return dao.get(work_id);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param EnvTaskInfo info
	 * @return EnvTaskInfo
	 */
	public EnvTaskInfo getInfoByKeyForUpdate(EnvTaskInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param EnvTaskInfo info
	 * @return int
	 */
	public int insertInfo(EnvTaskInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<EnvTaskInfo>
	 * @return int
	 */
	public int insertListInfo(List<EnvTaskInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ͳ�ƻ��������ĸ���
	 * @param prog_id
	 * @return
	 */
	public int countEnvTaskByProgId(String prog_id) {
		return dao.countEnvTaskByProgId(prog_id);
	}

	/**
	 * Description: ���ݻ�������ѯ����ִ�кͻ����е������ţ������ų��Լ���work_id
	 * @param env_name
	 */
	public List<EnvTaskInfo> getIdByEnvExceptId(String env_name,String work_id) {
		System.out.println(env_name+":"+work_id);
		return dao.getIdByEnvExceptId(env_name,work_id);
	}
	
	/**
	 * Description: ���ݻ�������ѯ����ִ�е�������
	 * @param env_name
	 * @return
	 */
	public List<EnvTaskInfo> getIdByEnv(String env_name){
		return dao.getIdByEnv(env_name);
	}
	
	/**
	 * Description: ���ݻ������������Ͳ�ѯ������¼
	 * @param env_name
	 * @param task_type
	 */
	public List<EnvTaskInfo> pageInfosByEnv(String env_name,
			TASK_TYPE task_type, int start_recd, int limit_recd) {
		return dao.pageInfosByEnv(env_name, task_type, start_recd, limit_recd);
	}

	/**
	 * Description: ���ݻ������������Ͳ�ѯ��¼����
	 * @param env_name
	 * @param task_type
	 * @return
	 */
	public int countTaskByEnv(String env_name, TASK_TYPE task_type) {
		return dao.countTaskByEnv(env_name, task_type);
	}

	/**
	 * Description: ���������޸�����״̬
	 * @param task_status
	 * @param work_id
	 * @return
	 */
	public int updateTaskStatus(TASK_STATUS task_status, String work_id) {
		return dao.updateTaskStatus(task_status, work_id);
	}
	
	/**
	 * Description: ��������������־·��
	 * @param exelog_bk_path
	 * @param work_id
	 * @return
	 */
	public int updateTaskLogPath(String exelog_bk_path,String work_id){
		return dao.updateTaskLogPath(exelog_bk_path, work_id);
	}

	/**
	 * Description: ���������޸�ִ�п�ʼʱ��
	 * @param start_bk_tm
	 * @param work_id
	 * @return
	 */
	public int updateExecuteStartTime(JaDateTime start_bk_tm, String work_id) {
		return dao.updateExecuteStartTime(start_bk_tm, work_id);
	}

	/**
	 * Description: ���������޸�ִ�н���ʱ��
	 * @param end_bk_tm
	 * @param work_id
	 * @return
	 */
	public int updateExecuteEndTime(JaDateTime end_bk_tm,
			EXE_RESULT exe_result, String work_id) {
		return dao.updateExecuteEndTime(end_bk_tm, exe_result, work_id);
	}
	
	/**
	 * Description: ���������Ų鿴����
	 * @param work_id
	 * @return
	 */
	public int countByWorkId(String work_id){
		return dao.countByWorkId(work_id);
	}
	
	/**
	 * Description: ����ִ����־
	 * @return
	 */
	public int updateExecuteLog(String exelog_bk_path, String work_id){
		return dao.updateExecuteLog(exelog_bk_path, work_id);
	}

	/** 
	 * Description: ���ݻ������Ʋ�ѯ�����
	 * @param env_name
	 * @return 
	 */
	public List<EnvTaskInfo> queryTaskInfoByEnvName(String env_name) {
		return dao.queryTaskInfoByEnvName(env_name);
	}

	/** 
	 * Description: ������Ŀ���Ʋ�ѯ�����
	 * @param project_name
	 * @return 
	 */
	public List<EnvTaskInfo> queryTaskInfoByProName(String project_name) {
		return dao.queryTaskInfoByProName(project_name);
	}

	/** 
	 * Description: ���¼���Ŀ����嵥
	 * @param uuid
	 * @param work_id 
	 */
	public int updateTagpacListUuid(String target_list_uuid, String work_id) {
		return dao.updateTagpacListUuid(target_list_uuid, work_id);
	}

	/** 
	 * Description: ���·���Ŀ��汾��
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
	 * Description: ���������Ÿ���ִ�ж���
	 * @return
	 */
	public int updateExeMethodById(EXE_METHOD exe_method, String work_id){
		return dao.updateExeMethodById(exe_method, work_id);
	}

	/** 
	 * Description: У���������Ƿ���ڣ������ھ��׳��쳣
	 * @param work_id 
	 */
	public void checkEnvIdIsNotExist(String work_id) {
		if(dao.countByWorkId(work_id) <= 0) {
			throw new EnvTaskIsNotExistException().addScene("TASK", work_id);
		}
	}

	/**
	 * Description: ���»�����������,�޸��������ơ��������
	 * @param task_bk_desc
	 * @param project_name
	 * @param work_id
	 * @return
	 */
	public int updateBuildTaskInfoByWorkId(String task_bk_desc, String project_name, String work_id) {
		return dao.updateBuildTaskInfoByWorkId(task_bk_desc, project_name, work_id);
	}

	/** 
	 * Description: ���»������������޸�״̬������ʱ�䡢ִ����
	 * @param executed
	 * @param now
	 * @param work_id 
	 */
	public int updateBuildStatusByWorkId(TASK_STATUS executed, JaDateTime now, String exe_user_id,
			String work_id) {
		return dao.updateBuildStatusByWorkId(executed, now, exe_user_id, work_id);
	}

	/** 
	 * Description: ����ʵ��ID
	 * @param inst_id
	 * @param work_id 
	 */
	public int updateInstanceId(String inst_id, String work_id) {
		return dao.updateInstanceId(inst_id, work_id);
	}

	/** 
	 * Description: ����Դ��汾���汾��
	 * @param code_ver_num
	 * @param work_id 
	 */
	public int updateCodeVerNum(String code_ver_num, String work_id) {
		return dao.updateCodeVerNum(code_ver_num, work_id);
	}
	
	/** 
	 * Description: ���ݻ������Ʒ�ҳ��ѯ�����
	 * @param env_name
	 * @param limit_recd 
	 * @param start_recd 
	 * @return 
	 */
	public List<EnvTaskInfo> pageTaskInfoByEnvName(String env_name, int start_recd, int limit_recd) {
		return dao.pageTaskInfoByEnvName(env_name, start_recd, limit_recd);
	}
	
	/** 
	 * Description: ���ݻ������Ʋ�ѯ��������
	 * @param env_name
	 * @return 
	 */
	public int countTaskInfoByEnvName(String env_name) {
		return dao.countTaskInfoByEnvName(env_name);
	}

}