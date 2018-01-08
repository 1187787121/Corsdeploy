/**
 * Title: AddServerInputBean.java
 * File Description: 添加服务器Action输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.enu.SERVER_OS;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 添加服务器Action输入接口
 * @author yangl
 */
public class AddServerInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : -239693455573535659L
	 */
	private static final long serialVersionUID = -239693455573535659L;

	/**
	 * 服务器名称
	 */
	private String ce_server_name;
	public static final String CE_SERVER_NAMECN = "服务器名称";

	/**
	 * 服务器简称
	 */
	private String server_cn_name;
	public static final String SERVER_CN_NAMECN = "服务器简称";

	/**
	 * 服务器描述
	 */
	private String server_bk_desc;
	public static final String SERVER_BK_DESCCN = "服务器描述";

	/**
	 * 服务器地址
	 */
	private String server_ip;
	public static final String SERVER_IP_CN = "服务器地址";

	/**
	 * 操作系统
	 */
	private SERVER_OS server_os;
	public static final String SERVER_OS_CN = "操作系统";

	/**
	 * 操作系统版本号
	 */
	private String os_sbk_ver;
	public static final String OS_SBK_VERCN = "操作系统版本号";

	/**
	 * 数据库列表
	 */
	private DBBean[] server_db_list;
	public static final String SERVER_DB_LISTCN = "数据库列表";

	/**
	 * 中间件
	 */
	private int[] mid_ware_list;
	public static final String MID_WARE_LISTCN = "中间件";

	/**
	 * 数据源列表
	 */
	private String[] soc_name_list;
	public static final String SOC_NAME_LISTCN = "数据源列表";

	/**
	 * FTP配置数据源
	 */
	private String ftp_config_soc;
	public static final String FTP_CONFIG_SOCCN = "FTP配置数据源";
	
	/**
	 * SHELL配置数据源
	 */
	private String shell_config_soc;
	public static final String SHELL_CONFIG_SOCCN = "SHELL配置数据源";
	
	/**
	 * @return 服务器名称
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param 服务器名称
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return 服务器简称
	 */
	public String getServer_cn_name() {
		return server_cn_name;
	}

	/**
	 * @param 服务器简称
	 */
	public void setServer_cn_name(String server_cn_name) {
		this.server_cn_name = server_cn_name;
	}

	/**
	 * @return 服务器描述
	 */
	public String getServer_bk_desc() {
		return server_bk_desc;
	}

	/**
	 * @param 服务器描述
	 */
	public void setServer_bk_desc(String server_bk_desc) {
		this.server_bk_desc = server_bk_desc;
	}

	/**
	 * @return 服务器地址
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param 服务器地址
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return 操作系统
	 */
	public SERVER_OS getServer_os() {
		return server_os;
	}

	/**
	 * @param 操作系统
	 */
	public void setServer_os(SERVER_OS server_os) {
		this.server_os = server_os;
	}

	/**
	 * @return 操作系统版本号
	 */
	public String getOs_sbk_ver() {
		return os_sbk_ver;
	}

	/**
	 * @param 操作系统版本号
	 */
	public void setOs_sbk_ver(String os_sbk_ver) {
		this.os_sbk_ver = os_sbk_ver;
	}

	/**
	 * @return 数据库列表
	 */
	public DBBean[] getServer_db_list() {
		return server_db_list;
	}

	/**
	 * @param 数据库列表
	 */
	public void setServer_db_list(DBBean[] server_db_list) {
		this.server_db_list = server_db_list;
	}

	/**
	 * @return 中间件列表
	 */
	public int[] getMid_ware_list() {
		return mid_ware_list;
	}

	/**
	 * @param 中间件列表
	 */
	public void setMid_ware_list(int[] mid_ware_list) {
		this.mid_ware_list = mid_ware_list;
	}

	/**
	 * @return 数据源列表
	 */
	public String[] getSoc_name_list() {
		return soc_name_list;
	}

	/**
	 * @param 数据源列表
	 */
	public void setSoc_name_list(String[] soc_name_list) {
		this.soc_name_list = soc_name_list;
	}

	/**
	 * @return shell_config_soc SHELL配置数据源
	 */
	public String getShell_config_soc() {
		return shell_config_soc;
	}

	/**
	 * @param shell_config_soc SHELL配置数据源
	 */
	public void setShell_config_soc(String shell_config_soc) {
		this.shell_config_soc = shell_config_soc;
	}

	/**
	 * @return ftp_config_soc FTP配置数据源
	 */
	public String getFtp_config_soc() {
		return ftp_config_soc;
	}

	/**
	 * @param ftp_config_soc FTP配置数据源
	 */
	public void setFtp_config_soc(String ftp_config_soc) {
		this.ftp_config_soc = ftp_config_soc;
	}

}
