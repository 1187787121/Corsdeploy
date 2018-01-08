/**
 * Title: EnvTaskDao.java
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
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;
import com.wk.util.JaDateTime;

/**
 * Class description:���������
 * @author AutoGen
 */
abstract class EnvTaskDao
		extends EntityDao<EnvTaskInfo> {

	/**
	 * Description: ͳ�ƻ��������ĸ���
	 * @param prog_id
	 * @return
	 */
	@SqlParam(condition = "PROG_ID =:prog_id")
	abstract int countEnvTaskByProgId(String prog_id);

	/**
	 * Description: ���ݻ�������ѯ����ִ�е�������
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND WORK_ID !=:work_id AND (TASK_STATUS = 1 OR TASK_STATUS = 2 OR TASK_STATUS = 4)")
	abstract List<EnvTaskInfo> getIdByEnvExceptId(String env_name, String work_id);
	
	/**
	 * Description: ���ݻ�������ѯ����ִ�е�������
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND (TASK_STATUS = 2 OR TASK_STATUS = 4)")
	abstract List<EnvTaskInfo> getIdByEnv(String env_name );

	/**
	 * Description: ���ݻ������������Ͳ�ѯ������¼
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND TASK_TYPE =:task_type", orderBy="start_bk_tm desc,crt_bk_date desc,crt_bk_time desc")
	abstract List<EnvTaskInfo> pageInfosByEnv(String env_name,
			TASK_TYPE task_type, int start_recd, int limit_recd);

	/**
	 * Description: ���ݻ������������Ͳ�ѯ��¼����
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name AND TASK_TYPE =:task_type")
	abstract int countTaskByEnv(String env_name, TASK_TYPE task_type);

	/**
	 * Description: ���������޸�����״̬
	 * @param task_status
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "TASK_STATUS" }, condition = "PK")
	abstract int updateTaskStatus(TASK_STATUS task_status, String work_id);
	
	/**
	 * Description: ��������������־·��
	 * @param exelog_bk_path
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "EXELOG_BK_PATH" }, condition = "PK")
	abstract int updateTaskLogPath(String exelog_bk_path, String work_id);

	/**
	 * Description: ���������޸�ִ�п�ʼʱ��
	 * @param start_bk_tm
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "START_BK_TM" }, condition = "PK")
	abstract int updateExecuteStartTime(JaDateTime start_bk_tm, String work_id);

	/**
	 * Description: ���������޸�ִ�н���ʱ��
	 * @param end_bk_tm
	 * @param work_id
	 * @return
	 */
	@SqlParam(updateSet = { "END_BK_TM", "EXE_RESULT" }, condition = "PK")
	abstract int updateExecuteEndTime(JaDateTime end_bk_tm,
			EXE_RESULT exe_result, String work_id);

	/**
	 * Description: ���������Ų鿴����
	 * @param work_id
	 * @return
	 */
	@SqlParam(condition = "WORK_ID =:work_id")
	abstract int countByWorkId(String work_id);

	/** 
	 * Description: ����ִ����־
	 * @param exelog_bk_path
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"EXELOG_BK_PATH"}, condition = "PK")
	abstract int updateExecuteLog(String exelog_bk_path, String work_id);

	/** 
	 * Description: ���ݻ������Ʋ�ѯ�����
	 * @param env_name
	 * @return 
	 */
	@SqlParam(sql="SELECT * FROM ENV_TASK WHERE ENV_NAME ='${env_name}' ORDER BY CRT_BK_DATE DESC, CRT_BK_TIME DESC",dynamic=true)
	abstract List<EnvTaskInfo> queryTaskInfoByEnvName(String env_name);

	/** 
	 * Description: ������Ŀ����ѯ�����
	 * @param project_name
	 * @return 
	 */
	@SqlParam(condition = "PROJECT_NAME =:project_name")
	abstract List<EnvTaskInfo> queryTaskInfoByProName(String project_name);

	/** 
	 * Description: ���¼���Ŀ����嵥
	 * @param target_list_uuid
	 * @param work_id 
	 */
	@SqlParam(updateSet = {"TAGPAC_LIST_UUID"}, condition = "PK")
	abstract int updateTagpacListUuid(String tagpac_list_uuid, String work_id);

	/** 
	 * Description: ���·���Ŀ��汾��
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
	 * Description: ���������Ÿ���ִ�ж���
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"EXE_METHOD"}, condition = "PK")
	abstract int updateExeMethodById(EXE_METHOD exe_method, String work_id);

	/** 
	 * Description: ���»�����������,�޸��������ơ��������
	 * @param work_id
	 * @param task_bk_desc
	 * @param project_name
	 * @return 
	 */
	@SqlParam(updateSet = {"TASK_BK_DESC", "PROJECT_NAME"}, condition = "PK")
	abstract int updateBuildTaskInfoByWorkId(String task_bk_desc, String project_name, String work_id);

	/** 
	 * Description: ���»������������޸�״̬������ʱ�䡢ִ����
	 * @param executed
	 * @param now
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"TASK_STATUS", "END_BK_TM", "EXE_USER_ID"}, condition = "PK")
	abstract int updateBuildStatusByWorkId(TASK_STATUS executed,
			JaDateTime now, String exe_user_id, String work_id);

	/** 
	 * Description: ����ʵ��ID
	 * @param inst_id
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"INSTANCE_ID"}, condition = "PK")
	abstract int updateInstanceId(String instance_id, String work_id);

	/** 
	 * Description: ����Դ��汾���汾��
	 * @param code_ver_num
	 * @param work_id
	 * @return 
	 */
	@SqlParam(updateSet = {"CODE_VER_NUM"}, condition = "PK")
	abstract int updateCodeVerNum(String code_ver_num, String work_id);

	/** 
	 * Description: ���ݻ������Ʒ�ҳ��ѯ�����
	 * @param env_name
	 * @param limit_recd 
	 * @param start_recd 
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract List<EnvTaskInfo> pageTaskInfoByEnvName(String env_name, int start_recd, int limit_recd);
	
	/** 
	 * Description: ���ݻ������Ʋ�ѯ��������
	 * @param env_name
	 * @return 
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countTaskInfoByEnvName(String env_name);
}