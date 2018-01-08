/**
 * Title: BuildConfigfileInfo.java
 * File Description: �������������ļ������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.enu.OPT_STATUS;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
/**
 * Class description:�������������ļ������
 * @author AutoGen
 */
@Table("BUILD_CONFIGFILE")
@PrimaryKey({"file_work_seq"})
public class BuildConfigfileInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�������������ļ������";

	/**
	 *�ļ������ˮ��
	 */
	private String file_work_seq;

	public static final String FILE_WORK_SEQCN = "�ļ������ˮ��";

	/**
	 *������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";

	/**
	 *��������
	 */
	private CFG_TYPE cfg_type;

	public static final String CFG_TYPECN = "��������";

	/**
	 *��������
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "��������";

	/**
	 *������IP
	 */
	private String server_ip;

	public static final String SERVER_IPCN = "������IP";

	/**
	 *��������
	 */
	private FOPT_TYPE fopt_type;

	public static final String FOPT_TYPECN = "��������";

	/**
	 *�ļ�·��
	 */
	private String file_bk_path;

	public static final String FILE_BK_PATHCN = "�ļ�·��";

	/**
	 *�ļ���
	 */
	private String file_bk_fname;

	public static final String FILE_BK_FNAMECN = "�ļ���";

	/**
	 *�ļ�CSUM
	 */
	private String file_bk_csum;

	public static final String FILE_BK_CSUMCN = "�ļ�CSUM";

	/**
	 *�Ƿ�Ŀ¼��־
	 */
	private YN_FLAG dir_yn_flag;

	public static final String DIR_YN_FLAGCN = "�Ƿ�Ŀ¼��־";

	/**
	 *����״̬
	 */
	private OPT_STATUS opt_status;

	public static final String OPT_STATUSCN = "����״̬";

	/**
	 *�޸���
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "�޸���";

	/**
	 *�޸�����
	 */
	private JaDate modify_bk_date;

	public static final String MODIFY_BK_DATECN = "�޸�����";

	/**
	 *�޸�ʱ��
	 */
	private JaTime modify_bk_time;

	public static final String MODIFY_BK_TIMECN = "�޸�ʱ��";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return file_work_seq �ļ������ˮ��
	 */
	public String getFile_work_seq() {
		return this.file_work_seq;
	}

	/**
	 *@param file_work_seq �ļ������ˮ��
	 */
	public void setFile_work_seq(String file_work_seq) {
		this.file_work_seq = file_work_seq;
	}

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
	 *@return cfg_type ��������
	 */
	public CFG_TYPE getCfg_type() {
		return this.cfg_type;
	}

	/**
	 *@param cfg_type ��������
	 */
	public void setCfg_type(CFG_TYPE cfg_type) {
		this.cfg_type = cfg_type;
	}

	/**
	 *@return server_name ��������
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name ��������
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 *@return server_ip ������IP
	 */
	public String getServer_ip() {
		return this.server_ip;
	}

	/**
	 *@param server_ip ������IP
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 *@return fopt_type ��������
	 */
	public FOPT_TYPE getFopt_type() {
		return this.fopt_type;
	}

	/**
	 *@param fopt_type ��������
	 */
	public void setFopt_type(FOPT_TYPE fopt_type) {
		this.fopt_type = fopt_type;
	}

	/**
	 *@return file_bk_path �ļ�·��
	 */
	public String getFile_bk_path() {
		return this.file_bk_path;
	}

	/**
	 *@param file_bk_path �ļ�·��
	 */
	public void setFile_bk_path(String file_bk_path) {
		this.file_bk_path = file_bk_path;
	}

	/**
	 *@return file_bk_fname �ļ���
	 */
	public String getFile_bk_fname() {
		return this.file_bk_fname;
	}

	/**
	 *@param file_bk_fname �ļ���
	 */
	public void setFile_bk_fname(String file_bk_fname) {
		this.file_bk_fname = file_bk_fname;
	}

	/**
	 *@return file_bk_csum �ļ�CSUM
	 */
	public String getFile_bk_csum() {
		return this.file_bk_csum;
	}

	/**
	 *@param file_bk_csum �ļ�CSUM
	 */
	public void setFile_bk_csum(String file_bk_csum) {
		this.file_bk_csum = file_bk_csum;
	}

	/**
	 *@return dir_yn_flag �Ƿ�Ŀ¼��־
	 */
	public YN_FLAG getDir_yn_flag() {
		return this.dir_yn_flag;
	}

	/**
	 *@param dir_yn_flag �Ƿ�Ŀ¼��־
	 */
	public void setDir_yn_flag(YN_FLAG dir_yn_flag) {
		this.dir_yn_flag = dir_yn_flag;
	}

	/**
	 *@return opt_status ����״̬
	 */
	public OPT_STATUS getOpt_status() {
		return this.opt_status;
	}

	/**
	 *@param opt_status ����״̬
	 */
	public void setOpt_status(OPT_STATUS opt_status) {
		this.opt_status = opt_status;
	}

	/**
	 *@return modify_user_id �޸���
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id �޸���
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	/**
	 *@return modify_bk_date �޸�����
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 *@param modify_bk_date �޸�����
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 *@return modify_bk_time �޸�ʱ��
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 *@param modify_bk_time �޸�ʱ��
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 *@return backup_fld �����ֶ�
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld �����ֶ�
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
