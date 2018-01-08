/**
 * Title: AddServerInputBean.java
 * File Description: ��ӷ�����Action����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��1��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.enu.SERVER_OS;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ӷ�����Action����ӿ�
 * @author yangl
 */
public class AddServerInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : -239693455573535659L
	 */
	private static final long serialVersionUID = -239693455573535659L;

	/**
	 * ����������
	 */
	private String ce_server_name;
	public static final String CE_SERVER_NAMECN = "����������";

	/**
	 * ���������
	 */
	private String server_cn_name;
	public static final String SERVER_CN_NAMECN = "���������";

	/**
	 * ����������
	 */
	private String server_bk_desc;
	public static final String SERVER_BK_DESCCN = "����������";

	/**
	 * ��������ַ
	 */
	private String server_ip;
	public static final String SERVER_IP_CN = "��������ַ";

	/**
	 * ����ϵͳ
	 */
	private SERVER_OS server_os;
	public static final String SERVER_OS_CN = "����ϵͳ";

	/**
	 * ����ϵͳ�汾��
	 */
	private String os_sbk_ver;
	public static final String OS_SBK_VERCN = "����ϵͳ�汾��";

	/**
	 * ���ݿ��б�
	 */
	private DBBean[] server_db_list;
	public static final String SERVER_DB_LISTCN = "���ݿ��б�";

	/**
	 * �м��
	 */
	private int[] mid_ware_list;
	public static final String MID_WARE_LISTCN = "�м��";

	/**
	 * ����Դ�б�
	 */
	private String[] soc_name_list;
	public static final String SOC_NAME_LISTCN = "����Դ�б�";

	/**
	 * FTP��������Դ
	 */
	private String ftp_config_soc;
	public static final String FTP_CONFIG_SOCCN = "FTP��������Դ";
	
	/**
	 * SHELL��������Դ
	 */
	private String shell_config_soc;
	public static final String SHELL_CONFIG_SOCCN = "SHELL��������Դ";
	
	/**
	 * @return ����������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ����������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return ���������
	 */
	public String getServer_cn_name() {
		return server_cn_name;
	}

	/**
	 * @param ���������
	 */
	public void setServer_cn_name(String server_cn_name) {
		this.server_cn_name = server_cn_name;
	}

	/**
	 * @return ����������
	 */
	public String getServer_bk_desc() {
		return server_bk_desc;
	}

	/**
	 * @param ����������
	 */
	public void setServer_bk_desc(String server_bk_desc) {
		this.server_bk_desc = server_bk_desc;
	}

	/**
	 * @return ��������ַ
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param ��������ַ
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return ����ϵͳ
	 */
	public SERVER_OS getServer_os() {
		return server_os;
	}

	/**
	 * @param ����ϵͳ
	 */
	public void setServer_os(SERVER_OS server_os) {
		this.server_os = server_os;
	}

	/**
	 * @return ����ϵͳ�汾��
	 */
	public String getOs_sbk_ver() {
		return os_sbk_ver;
	}

	/**
	 * @param ����ϵͳ�汾��
	 */
	public void setOs_sbk_ver(String os_sbk_ver) {
		this.os_sbk_ver = os_sbk_ver;
	}

	/**
	 * @return ���ݿ��б�
	 */
	public DBBean[] getServer_db_list() {
		return server_db_list;
	}

	/**
	 * @param ���ݿ��б�
	 */
	public void setServer_db_list(DBBean[] server_db_list) {
		this.server_db_list = server_db_list;
	}

	/**
	 * @return �м���б�
	 */
	public int[] getMid_ware_list() {
		return mid_ware_list;
	}

	/**
	 * @param �м���б�
	 */
	public void setMid_ware_list(int[] mid_ware_list) {
		this.mid_ware_list = mid_ware_list;
	}

	/**
	 * @return ����Դ�б�
	 */
	public String[] getSoc_name_list() {
		return soc_name_list;
	}

	/**
	 * @param ����Դ�б�
	 */
	public void setSoc_name_list(String[] soc_name_list) {
		this.soc_name_list = soc_name_list;
	}

	/**
	 * @return shell_config_soc SHELL��������Դ
	 */
	public String getShell_config_soc() {
		return shell_config_soc;
	}

	/**
	 * @param shell_config_soc SHELL��������Դ
	 */
	public void setShell_config_soc(String shell_config_soc) {
		this.shell_config_soc = shell_config_soc;
	}

	/**
	 * @return ftp_config_soc FTP��������Դ
	 */
	public String getFtp_config_soc() {
		return ftp_config_soc;
	}

	/**
	 * @param ftp_config_soc FTP��������Դ
	 */
	public void setFtp_config_soc(String ftp_config_soc) {
		this.ftp_config_soc = ftp_config_soc;
	}

}
