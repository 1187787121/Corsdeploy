/**
 * Title: ViSysEnvServerInfo.java
 * File Description: VIEW
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */

package com.wk.cd.build.view.info;

import java.io.Serializable;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.SERVER_OS;
import com.wk.cd.enu.SYS_TYPE;
/**
 * Class description:ϵͳ������������ͼ
 * @author AutoGen
 */
public class ViSysEnvServerView implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String VIEWCN = "ϵͳ������������ͼ";

	/**
	 *Ӧ��ϵͳ����
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ����";

	/**
	 *Ӧ��ϵͳ���
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "Ӧ��ϵͳ���";

	/**
	 *Ӧ��ϵͳ����
	 */
	private String sys_bk_desc;

	public static final String SYS_BK_DESCCN = "Ӧ��ϵͳ����";
	
	/**
	 *Ӧ��ϵͳ����
	 */
	private SYS_TYPE sys_type;

	public static final String SYS_TYPECN = "Ӧ��ϵͳ����";

	/**
	 *��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 *�������
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "�������";

	/**
	 *��������
	 */
	private String env_bk_desc;

	public static final String ENV_BK_DESCCN = "��������";

	/**
	 *��������
	 */
	private ENV_TYPE env_type;

	public static final String ENV_TYPECN = "��������";

	/**
	 *���ݷ�Χ
	 */
	private DT_RANGE dt_range;

	public static final String DT_RANGECN = "���ݷ�Χ";

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
	 *����������
	 */
	private String server_type;

	public static final String SERVER_TYPECN = "����������";

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
	 *���ݿ�汾��
	 */
	private String db_bbk_ver;

	public static final String DB_BBK_VERCN = "���ݿ�汾��";

	/**
	 *�м��
	 */
	private String server_mid_ware;

	public static final String SERVER_MID_WARECN = "�м��";

	/**
	 *@return sys_name Ӧ��ϵͳ����
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name Ӧ��ϵͳ����
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return sys_cn_name Ӧ��ϵͳ���
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 *@param sys_cn_name Ӧ��ϵͳ���
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 *@return sys_bk_desc Ӧ��ϵͳ����
	 */
	public String getSys_bk_desc() {
		return this.sys_bk_desc;
	}

	/**
	 *@param sys_bk_desc Ӧ��ϵͳ����
	 */
	public void setSys_bk_desc(String sys_bk_desc) {
		this.sys_bk_desc = sys_bk_desc;
	}
	
	/**
	 * @return sys_type Ӧ��ϵͳ����
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type Ӧ��ϵͳ����
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
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
	 *@return env_cn_name �������
	 */
	public String getEnv_cn_name() {
		return this.env_cn_name;
	}

	/**
	 *@param env_cn_name �������
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 *@return env_bk_desc ��������
	 */
	public String getEnv_bk_desc() {
		return this.env_bk_desc;
	}

	/**
	 *@param env_bk_desc ��������
	 */
	public void setEnv_bk_desc(String env_bk_desc) {
		this.env_bk_desc = env_bk_desc;
	}

	/**
	 *@return env_type ��������
	 */
	public ENV_TYPE getEnv_type() {
		return this.env_type;
	}

	/**
	 *@param env_type ��������
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 *@return dt_range ���ݷ�Χ
	 */
	public DT_RANGE getDt_range() {
		return this.dt_range;
	}

	/**
	 *@param dt_range ���ݷ�Χ
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

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
	 *@return server_bk_desc ����������
	 */
	public String getServer_desc() {
		return this.server_desc;
	}

	/**
	 *@param server_bk_desc ����������
	 */
	public void setServer_desc(String server_desc) {
		this.server_desc = server_desc;
	}

	/**
	 *@return server_type ����������
	 */
	public String getServer_type() {
		return this.server_type;
	}

	/**
	 *@param server_type ����������
	 */
	public void setServer_type(String server_type) {
		this.server_type = server_type;
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
	 *@return db_bbk_ver ���ݿ�汾��
	 */
	public String getDb_bbk_ver() {
		return this.db_bbk_ver;
	}

	/**
	 *@param db_bbk_ver ���ݿ�汾��
	 */
	public void setDb_bbk_ver(String db_bbk_ver) {
		this.db_bbk_ver = db_bbk_ver;
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

}
