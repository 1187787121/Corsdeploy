/**
 * Title: CeServerInfo.java
 * File Description: ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-3
 */

package com.wk.cd.build.en.info;

import java.io.Serializable;

import com.wk.cd.enu.SERVER_OS;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;
/**
 * Class description:��������
 * @author AutoGen
 */
@Table("CE_SERVER")
@PrimaryKey({"server_name"})
public class CeServerInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "��������";

	/**
	 *����������
	 */
	private String server_name;

	public static final String SERVER_NAMECN = "����������";

	/**
	 *���������
	 */
	private String server_cn_name;

	public static final String SERVER_CN_NAMECN = "���������";

	/**
	 *����������
	 */
	private String server_desc;

	public static final String SERVER_DESCCN = "����������";

	/**
	 *��������ַ
	 */
	private String server_ip;

	public static final String SERVER_IPCN = "��������ַ";

	/**
	 *����ϵͳ
	 */
	private SERVER_OS server_os;

	public static final String SERVER_OSCN = "����ϵͳ";

	/**
	 *����ϵͳ�汾
	 */
	private String os_sbk_ver;

	public static final String OS_SBK_VERCN = "����ϵͳ�汾";

	/**
	 *���ݿ�����
	 */
	private String server_db;

	public static final String SERVER_DBCN = "���ݿ�����";

	/**
	 *�м��
	 */
	private String server_mid_ware;

	public static final String SERVER_MID_WARECN = "�м��";

	/**
	 *��������
	 */
	private JaDate create_bk_date;

	public static final String CREATE_BK_DATECN = "��������";

	/**
	 *����ʱ��
	 */
	private JaTime create_bk_time;

	public static final String CREATE_BK_TIMECN = "����ʱ��";

	/**
	 *�����û�
	 */
	private String create_user_id;

	public static final String CREATE_USER_IDCN = "�����û�";

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
	 *�޸��û�
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "�޸��û�";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return server_name ����������
	 */
	public String getServer_name() {
		return this.server_name;
	}

	/**
	 *@param server_name ����������
	 */
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	/**
	 *@return server_cn_name ���������
	 */
	public String getServer_cn_name() {
		return this.server_cn_name;
	}

	/**
	 *@param server_cn_name ���������
	 */
	public void setServer_cn_name(String server_cn_name) {
		this.server_cn_name = server_cn_name;
	}

	/**
	 *@return server_desc ����������
	 */
	public String getServer_desc() {
		return this.server_desc;
	}

	/**
	 *@param server_desc ����������
	 */
	public void setServer_desc(String server_desc) {
		this.server_desc = server_desc;
	}

	/**
	 *@return server_ip ��������ַ
	 */
	public String getServer_ip() {
		return this.server_ip;
	}

	/**
	 *@param server_ip ��������ַ
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 *@return server_os ����ϵͳ
	 */
	public SERVER_OS getServer_os() {
		return this.server_os;
	}

	/**
	 *@param server_os ����ϵͳ
	 */
	public void setServer_os(SERVER_OS server_os) {
		this.server_os = server_os;
	}

	/**
	 *@return os_sbk_ver ����ϵͳ�汾
	 */
	public String getOs_sbk_ver() {
		return this.os_sbk_ver;
	}

	/**
	 *@param os_sbk_ver ����ϵͳ�汾
	 */
	public void setOs_sbk_ver(String os_sbk_ver) {
		this.os_sbk_ver = os_sbk_ver;
	}

	/**
	 *@return server_db ���ݿ�����
	 */
	public String getServer_db() {
		return this.server_db;
	}

	/**
	 *@param server_db ���ݿ�����
	 */
	public void setServer_db(String server_db) {
		this.server_db = server_db;
	}

	/**
	 *@return server_mid_ware �м��
	 */
	public String getServer_mid_ware() {
		return this.server_mid_ware;
	}

	/**
	 *@param server_mid_ware �м��
	 */
	public void setServer_mid_ware(String server_mid_ware) {
		this.server_mid_ware = server_mid_ware;
	}

	/**
	 *@return create_bk_date ��������
	 */
	public JaDate getCreate_bk_date() {
		return this.create_bk_date;
	}

	/**
	 *@param create_bk_date ��������
	 */
	public void setCreate_bk_date(JaDate create_bk_date) {
		this.create_bk_date = create_bk_date;
	}

	/**
	 *@return create_bk_time ����ʱ��
	 */
	public JaTime getCreate_bk_time() {
		return this.create_bk_time;
	}

	/**
	 *@param create_bk_time ����ʱ��
	 */
	public void setCreate_bk_time(JaTime create_bk_time) {
		this.create_bk_time = create_bk_time;
	}

	/**
	 *@return create_user_id �����û�
	 */
	public String getCreate_user_id() {
		return this.create_user_id;
	}

	/**
	 *@param create_user_id �����û�
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
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
	 *@return modify_user_id �޸��û�
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 *@param modify_user_id �޸��û�
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
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
