/**
 * Title: EnvTaskInfo.java
 * File Description: ���������
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-6
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;
import com.wk.util.*;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.EXE_RESULT;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:���������
 * @author AutoGen
 */
@Table("ENV_TASK")
@PrimaryKey({"work_id"})
public class EnvTaskInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���������";

	/**
	 *������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";

	/**
	 *��������
	 */
	private TASK_TYPE task_type;

	public static final String TASK_TYPECN = "��������";

	/**
	 *��������
	 */
	private String task_bk_desc;

	public static final String TASK_BK_DESCCN = "��������";

	/**
	 *����������
	 */
	private String rol_work_id;

	public static final String ROL_WORK_IDCN = "����������";

	/**
	 *��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 *������Ŀ
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "������Ŀ";

	/**
	 *�������
	 */
	private String prog_id;

	public static final String PROG_IDCN = "�������";

	/**
	 *ʵ��ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "ʵ��ID";

	/**
	 *ִ�ж���
	 */
	private EXE_METHOD exe_method;

	public static final String EXE_METHODCN = "ִ�ж���";

	/**
	 *����Ŀ����嵥UUID
	 */
	private String tagpac_list_uuid;

	public static final String TAGPAC_LIST_UUIDCN = "����Ŀ����嵥UUID";

	/**
	 *�����嵥UUID
	 */
	private String pub_list_uuid;

	public static final String PUB_LIST_UUIDCN = "�����嵥UUID";

	/**
	 *Դ��汾���汾��
	 */
	private String code_ver_num;

	public static final String CODE_VER_NUMCN = "Դ��汾���汾��";

	/**
	 *Ŀ��汾���汾��
	 */
	private String target_ver_num;

	public static final String TARGET_VER_NUMCN = "Ŀ��汾���汾��";

	/**
	 *�汾����Դ��汾��
	 */
	private String vercode_ver_num;

	public static final String VERCODE_VER_NUMCN = "�汾����Դ��汾��";

	/**
	 *�汾����Ŀ��汾��
	 */
	private String vertarget_ver_num;

	public static final String VERTARGET_VER_NUMCN = "�汾����Ŀ��汾��";

	/**
	 *����״̬
	 */
	private TASK_STATUS task_status;

	public static final String TASK_STATUSCN = "����״̬";

	/**
	 *ִ�н��
	 */
	private EXE_RESULT exe_result;

	public static final String EXE_RESULTCN = "ִ�н��";

	/**
	 *ִ����־
	 */
	private String exelog_bk_path;

	public static final String EXELOG_BK_PATHCN = "ִ����־";

	/**
	 *������
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "������";

	/**
	 *��������
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "��������";

	/**
	 *����ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "����ʱ��";

	/**
	 *ִ����
	 */
	private String exe_user_id;

	public static final String EXE_USER_IDCN = "ִ����";

	/**
	 *ִ�п�ʼʱ��
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "ִ�п�ʼʱ��";

	/**
	 *ִ�н���ʱ��
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "ִ�н���ʱ��";

	/**
	 *��ʱ
	 */
	private int time_used;

	public static final String TIME_USEDCN = "��ʱ";

	/**
	 *@return work_id ������
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/**
	 *@param work_id ������
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 *@return task_type ��������
	 */
	public TASK_TYPE getTask_type() {
		return this.task_type;
	}

	/**
	 *@param task_type ��������
	 */
	public void setTask_type(TASK_TYPE task_type) {
		this.task_type = task_type;
	}

	/**
	 *@return task_bk_desc ��������
	 */
	public String getTask_bk_desc() {
		return this.task_bk_desc;
	}

	/**
	 *@param task_bk_desc ��������
	 */
	public void setTask_bk_desc(String task_bk_desc) {
		this.task_bk_desc = task_bk_desc;
	}

	/**
	 *@return rol_work_id ����������
	 */
	public String getRol_work_id() {
		return this.rol_work_id;
	}

	/**
	 *@param rol_work_id ����������
	 */
	public void setRol_work_id(String rol_work_id) {
		this.rol_work_id = rol_work_id;
	}

	/**
	 *@return env_name ��������
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return project_name ������Ŀ
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name ������Ŀ
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 *@return prog_id �������
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 *@param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 *@return instance_id ʵ��ID
	 */
	public String getInstance_id() {
		return this.instance_id;
	}

	/**
	 *@param instance_id ʵ��ID
	 */
	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	/**
	 *@return exe_method ִ�ж���
	 */
	public EXE_METHOD getExe_method() {
		return this.exe_method;
	}

	/**
	 *@param exe_method ִ�ж���
	 */
	public void setExe_method(EXE_METHOD exe_method) {
		this.exe_method = exe_method;
	}

	/**
	 *@return tagpac_list_uuid ����Ŀ����嵥UUID
	 */
	public String getTagpac_list_uuid() {
		return this.tagpac_list_uuid;
	}

	/**
	 *@param tagpac_list_uuid ����Ŀ����嵥UUID
	 */
	public void setTagpac_list_uuid(String tagpac_list_uuid) {
		this.tagpac_list_uuid = tagpac_list_uuid;
	}

	/**
	 *@return pub_list_uuid �����嵥UUID
	 */
	public String getPub_list_uuid() {
		return this.pub_list_uuid;
	}

	/**
	 *@param pub_list_uuid �����嵥UUID
	 */
	public void setPub_list_uuid(String pub_list_uuid) {
		this.pub_list_uuid = pub_list_uuid;
	}

	/**
	 *@return code_ver_num Դ��汾���汾��
	 */
	public String getCode_ver_num() {
		return this.code_ver_num;
	}

	/**
	 *@param code_ver_num Դ��汾���汾��
	 */
	public void setCode_ver_num(String code_ver_num) {
		this.code_ver_num = code_ver_num;
	}

	/**
	 *@return target_ver_num Ŀ��汾���汾��
	 */
	public String getTarget_ver_num() {
		return this.target_ver_num;
	}

	/**
	 *@param target_ver_num Ŀ��汾���汾��
	 */
	public void setTarget_ver_num(String target_ver_num) {
		this.target_ver_num = target_ver_num;
	}

	/**
	 *@return vercode_ver_num �汾����Դ��汾��
	 */
	public String getVercode_ver_num() {
		return this.vercode_ver_num;
	}

	/**
	 *@param vercode_ver_num �汾����Դ��汾��
	 */
	public void setVercode_ver_num(String vercode_ver_num) {
		this.vercode_ver_num = vercode_ver_num;
	}

	/**
	 *@return vertarget_ver_num �汾����Ŀ��汾��
	 */
	public String getVertarget_ver_num() {
		return this.vertarget_ver_num;
	}

	/**
	 *@param vertarget_ver_num �汾����Ŀ��汾��
	 */
	public void setVertarget_ver_num(String vertarget_ver_num) {
		this.vertarget_ver_num = vertarget_ver_num;
	}

	/**
	 *@return task_status ����״̬
	 */
	public TASK_STATUS getTask_status() {
		return this.task_status;
	}

	/**
	 *@param task_status ����״̬
	 */
	public void setTask_status(TASK_STATUS task_status) {
		this.task_status = task_status;
	}

	/**
	 *@return exe_result ִ�н��
	 */
	public EXE_RESULT getExe_result() {
		return this.exe_result;
	}

	/**
	 *@param exe_result ִ�н��
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/**
	 *@return exelog_bk_path ִ����־
	 */
	public String getExelog_bk_path() {
		return this.exelog_bk_path;
	}

	/**
	 *@param exelog_bk_path ִ����־
	 */
	public void setExelog_bk_path(String exelog_bk_path) {
		this.exelog_bk_path = exelog_bk_path;
	}

	/**
	 *@return crt_user_id ������
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id ������
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return crt_bk_date ��������
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date ��������
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time ����ʱ��
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time ����ʱ��
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return exe_user_id ִ����
	 */
	public String getExe_user_id() {
		return this.exe_user_id;
	}

	/**
	 *@param exe_user_id ִ����
	 */
	public void setExe_user_id(String exe_user_id) {
		this.exe_user_id = exe_user_id;
	}

	/**
	 *@return start_bk_tm ִ�п�ʼʱ��
	 */
	public JaDateTime getStart_bk_tm() {
		return this.start_bk_tm;
	}

	/**
	 *@param start_bk_tm ִ�п�ʼʱ��
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 *@return end_bk_tm ִ�н���ʱ��
	 */
	public JaDateTime getEnd_bk_tm() {
		return this.end_bk_tm;
	}

	/**
	 *@param end_bk_tm ִ�н���ʱ��
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 *@return time_used ��ʱ
	 */
	public int getTime_used() {
		return this.time_used;
	}

	/**
	 *@param time_used ��ʱ
	 */
	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}

}
