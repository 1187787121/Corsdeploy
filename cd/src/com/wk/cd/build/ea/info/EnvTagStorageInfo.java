/**
 * Title: EnvTagStorageInfo.java
 * File Description: Ŀ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-28
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;
/**
 * Class description:Ŀ������
 * @author AutoGen
 */
@Table("ENV_TAG_STORAGE")
@PrimaryKey({"storage_id"})
public class EnvTagStorageInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "Ŀ������";

	/**
	 *�����
	 */
	private String storage_id;

	public static final String STORAGE_IDCN = "�����";

	/**
	 *�������
	 */
	private String storage_bk_desc;

	public static final String STORAGE_BK_DESCCN = "�������";

	/**
	 *��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 *��Ŀ���
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "��Ŀ���";

	/**
	 *ʵ��ID
	 */
	private String instance_id;

	public static final String INSTANCE_IDCN = "ʵ��ID";

	/**
	 *ִ������ԴUUID
	 */
	private String exe_soc_uuid;

	public static final String EXE_SOC_UUIDCN = "ִ������ԴUUID";

	/**
	 *���ɰ汾��
	 */
	private String inte_ver_num;

	public static final String INTE_VER_NUMCN = "���ɰ汾��";

	/**
	 *����嵥UUID
	 */
	private String storage_list_uuid;

	public static final String STORAGE_LIST_UUIDCN = "����嵥UUID";

	/**
	 *Ŀ��汾����ԴUUID
	 */
	private String tar_versoc_uuid;

	public static final String TAR_VERSOC_UUIDCN = "Ŀ��汾����ԴUUID";

	/**
	 *Ŀ��汾��
	 */
	private String tar_ver_num;

	public static final String TAR_VER_NUMCN = "Ŀ��汾��";

	/**
	 *���״̬
	 */
	private STORAGE_STATUS storage_status;

	public static final String STORAGE_STATUSCN = "���״̬";

	/**
	 *������
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "������";

	/**
	 *�����
	 */
	private STORAGE_RESULT storage_result;

	public static final String STORAGE_RESULTCN = "�����";

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
	 *�����
	 */
	private String storage_user_id;

	public static final String STORAGE_USER_IDCN = "�����";

	/**
	 *��⿪ʼʱ��
	 */
	private JaDateTime start_bk_tm;

	public static final String START_BK_TMCN = "��⿪ʼʱ��";

	/**
	 *������ʱ��
	 */
	private JaDateTime end_bk_tm;

	public static final String END_BK_TMCN = "������ʱ��";

	/**
	 *�����־ȫ·��
	 */
	private String log_bk_path;

	public static final String LOG_BK_PATHCN = "�����־ȫ·��";

	/**
	 *��ʱ
	 */
	private int time_used;

	public static final String TIME_USEDCN = "��ʱ";

	/**
	 *@return storage_id �����
	 */
	public String getStorage_id() {
		return this.storage_id;
	}

	/**
	 *@param storage_id �����
	 */
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}

	/**
	 *@return storage_bk_desc �������
	 */
	public String getStorage_bk_desc() {
		return this.storage_bk_desc;
	}

	/**
	 *@param storage_bk_desc �������
	 */
	public void setStorage_bk_desc(String storage_bk_desc) {
		this.storage_bk_desc = storage_bk_desc;
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
	 *@return project_name ��Ŀ���
	 */
	public String getProject_name() {
		return this.project_name;
	}

	/**
	 *@param project_name ��Ŀ���
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
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
	 *@return exe_soc_uuid ִ������ԴUUID
	 */
	public String getExe_soc_uuid() {
		return this.exe_soc_uuid;
	}

	/**
	 *@param exe_soc_uuid ִ������ԴUUID
	 */
	public void setExe_soc_uuid(String exe_soc_uuid) {
		this.exe_soc_uuid = exe_soc_uuid;
	}

	/**
	 *@return inte_ver_num ���ɰ汾��
	 */
	public String getInte_ver_num() {
		return this.inte_ver_num;
	}

	/**
	 *@param inte_ver_num ���ɰ汾��
	 */
	public void setInte_ver_num(String inte_ver_num) {
		this.inte_ver_num = inte_ver_num;
	}

	/**
	 *@return storage_list_uuid ����嵥UUID
	 */
	public String getStorage_list_uuid() {
		return this.storage_list_uuid;
	}

	/**
	 *@param storage_list_uuid ����嵥UUID
	 */
	public void setStorage_list_uuid(String storage_list_uuid) {
		this.storage_list_uuid = storage_list_uuid;
	}

	/**
	 *@return tar_versoc_uuid Ŀ��汾����ԴUUID
	 */
	public String getTar_versoc_uuid() {
		return this.tar_versoc_uuid;
	}

	/**
	 *@param tar_versoc_uuid Ŀ��汾����ԴUUID
	 */
	public void setTar_versoc_uuid(String tar_versoc_uuid) {
		this.tar_versoc_uuid = tar_versoc_uuid;
	}

	/**
	 *@return tar_ver_num Ŀ��汾��
	 */
	public String getTar_ver_num() {
		return this.tar_ver_num;
	}

	/**
	 *@param tar_ver_num Ŀ��汾��
	 */
	public void setTar_ver_num(String tar_ver_num) {
		this.tar_ver_num = tar_ver_num;
	}

	/**
	 *@return storage_status ���״̬
	 */
	public STORAGE_STATUS getStorage_status() {
		return this.storage_status;
	}

	/**
	 *@param storage_status ���״̬
	 */
	public void setStorage_status(STORAGE_STATUS storage_status) {
		this.storage_status = storage_status;
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
	 *@return storage_result �����
	 */
	public STORAGE_RESULT getStorage_result() {
		return this.storage_result;
	}

	/**
	 *@param storage_result �����
	 */
	public void setStorage_result(STORAGE_RESULT storage_result) {
		this.storage_result = storage_result;
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
	 *@return storage_user_id �����
	 */
	public String getStorage_user_id() {
		return this.storage_user_id;
	}

	/**
	 *@param storage_user_id �����
	 */
	public void setStorage_user_id(String storage_user_id) {
		this.storage_user_id = storage_user_id;
	}

	/**
	 *@return start_bk_tm ��⿪ʼʱ��
	 */
	public JaDateTime getStart_bk_tm() {
		return this.start_bk_tm;
	}

	/**
	 *@param start_bk_tm ��⿪ʼʱ��
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 *@return end_bk_tm ������ʱ��
	 */
	public JaDateTime getEnd_bk_tm() {
		return this.end_bk_tm;
	}

	/**
	 *@param end_bk_tm ������ʱ��
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 *@return log_bk_path �����־ȫ·��
	 */
	public String getLog_bk_path() {
		return this.log_bk_path;
	}

	/**
	 *@param log_bk_path �����־ȫ·��
	 */
	public void setLog_bk_path(String log_bk_path) {
		this.log_bk_path = log_bk_path;
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
