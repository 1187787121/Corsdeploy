/**
 * Title: LgLogDownInfo.java
 * File Description: ����������Ϣ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.lg.info;

import java.io.Serializable;
import com.wk.util.*;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:����������Ϣ��
 * @author AutoGen
 */
@Table("LG_LOG_DOWN")
@PrimaryKey({"log_root_path","log_file_name"})
public class LgLogDownInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "����������Ϣ��";

	/**
	 *��־�ļ�·��
	 */
	private String log_root_path;

	public static final String LOG_ROOT_PATHCN = "��־�ļ�·��";

	/**
	 *��־�ļ�����
	 */
	private String log_file_name;

	public static final String LOG_FILE_NAMECN = "��־�ļ�����";

	/**
	 *��־��ʼ����
	 */
	private JaDate start_bk_date;

	public static final String START_BK_DATECN = "��־��ʼ����";

	/**
	 *��־��ֹ����
	 */
	private JaDate end_bk_date;

	public static final String END_BK_DATECN = "��־��ֹ����";

	/**
	 *�û���
	 */
	private String user_id;

	public static final String USER_IDCN = "�û���";

	/**
	 *������־����
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "������־����";

	/**
	 *������־ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "������־ʱ��";

	/**
	 *����
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "����";

	/**
	 *@return log_root_path ��־�ļ�·��
	 */
	public String getLog_root_path() {
		return this.log_root_path;
	}

	/**
	 *@param log_root_path ��־�ļ�·��
	 */
	public void setLog_root_path(String log_root_path) {
		this.log_root_path = log_root_path;
	}

	/**
	 *@return log_file_name ��־�ļ�����
	 */
	public String getLog_file_name() {
		return this.log_file_name;
	}

	/**
	 *@param log_file_name ��־�ļ�����
	 */
	public void setLog_file_name(String log_file_name) {
		this.log_file_name = log_file_name;
	}

	/**
	 *@return start_bk_date ��־��ʼ����
	 */
	public JaDate getStart_bk_date() {
		return this.start_bk_date;
	}

	/**
	 *@param start_bk_date ��־��ʼ����
	 */
	public void setStart_bk_date(JaDate start_bk_date) {
		this.start_bk_date = start_bk_date;
	}

	/**
	 *@return end_bk_date ��־��ֹ����
	 */
	public JaDate getEnd_bk_date() {
		return this.end_bk_date;
	}

	/**
	 *@param end_bk_date ��־��ֹ����
	 */
	public void setEnd_bk_date(JaDate end_bk_date) {
		this.end_bk_date = end_bk_date;
	}

	/**
	 *@return user_id �û���
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return crt_bk_date ������־����
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date ������־����
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time ������־ʱ��
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time ������־ʱ��
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return backup_fld ����
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld ����
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
