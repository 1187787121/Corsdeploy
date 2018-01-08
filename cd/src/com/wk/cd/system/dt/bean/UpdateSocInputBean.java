/**
 * Title: UpdateSocInputBean.java
 * File Description: 修改数据源输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;

/**
 * Class Description: 修改数据源输入接口
 * @author link
 */
public class UpdateSocInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -5701264087275747262L;
	private String soc_name;
	public static final String SOC_NAMECN = "数据源名称";

	private String soc_ip;
	public static final String SOC_IPCN = "IP地址";

	private int soc_port;
	public static final String SOC_PORTCN = "端口号";

	private PROTOCOL_TYPE protocol_type;
	public static final String PROTOCOL_TYPECN = "协议类型";

	private String remote_uname;
	public static final String REMOTE_UNAMECN = "目标主机用户名";

	private String remote_passwd;
	public static final String REMOTE_PASSWDCN = "目标主机密码";

	private String key_remote_passwd;
	public static final String KEY_REMOTE_PASSWDCN = "密钥";

	private long bk_timeout;
	public static final String BK_TIMEOUTCN = "超时时间";

	private String jdbc_drv;
	public static final String JDBC_DRVCN = "Jdbc_driver";

	private String jdbc_url;
	public static final String JDBC_URLCN = "Jdbc_url";

	private String jdbc_schema;
	public static final String JDBC_SCHEMACN = "Jdbc_schema";

	private CVT_TYPE cvt_type;
	public static final String CVT_TYPECN = "转码方式";

	private String ftp_local_script;
	public static final String FTP_LOCAL_SCRIPTCN = "上下传脚本";

	private String cvt_local_script;
	public static final String CVT_LOCAL_SCRIPTCN = "转码脚本";

	private String soc_domain;
	public static final String SOC_DOMAINCN = "数据源域名";

	private String soc_bk_desc;
	public static final String SOC_BK_DESCCN = "数据源描述";
	
	private String user_root_path;
	public static final String USER_ROOT_PATHCN = "用户根路径";
	/**
	 *环境参数
	 */
	private String environment_variables;

	public static final String ENVIRONMENT_VARIABLESCN = "环境参数";
	
	/**
	 *编码格式
	 */
	private String encoding_type;

	public static final String ENCODING_TYPECN = "编码格式";

	/**
	 * @return soc_name 数据源名称
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 * @param soc_name 数据源名称
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return soc_ip IP地址
	 */
	public String getSoc_ip() {
		return this.soc_ip;
	}

	/**
	 * @param soc_ip IP地址
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
	}

	/**
	 * @return soc_port 端口号
	 */
	public int getSoc_port() {
		return this.soc_port;
	}

	/**
	 * @param soc_port 端口号
	 */
	public void setSoc_port(int soc_port) {
		this.soc_port = soc_port;
	}

	/**
	 * @return protocol_type 协议类型
	 */
	public PROTOCOL_TYPE getProtocol_type() {
		return this.protocol_type;
	}

	/**
	 * @param protocol_type 协议类型
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * @return remote_uname 目标主机用户名
	 */
	public String getRemote_uname() {
		return this.remote_uname;
	}

	/**
	 * @param remote_uname 目标主机用户名
	 */
	public void setRemote_uname(String remote_uname) {
		this.remote_uname = remote_uname;
	}

	/**
	 * @return remote_passwd 目标主机密码
	 */
	public String getRemote_passwd() {
		return this.remote_passwd;
	}

	/**
	 * @param remote_passwd 目标主机密码
	 */
	public void setRemote_passwd(String remote_passwd) {
		this.remote_passwd = remote_passwd;
	}

	/**
	 * @return key_remote_passwd 密钥
	 */
	public String getKey_remote_passwd() {
		return this.key_remote_passwd;
	}

	/**
	 * @param key_remote_passwd 密钥
	 */
	public void setKey_remote_passwd(String key_remote_passwd) {
		this.key_remote_passwd = key_remote_passwd;
	}

	/**
	 * @return bk_timeout 超时时间
	 */
	public long getBk_timeout() {
		return this.bk_timeout;
	}

	/**
	 * @param bk_timeout 超时时间
	 */
	public void setBk_timeout(long bk_timeout) {
		this.bk_timeout = bk_timeout;
	}

	/**
	 * @return jdbc_drv Jdbc_driver
	 */
	public String getJdbc_drv() {
		return this.jdbc_drv;
	}

	/**
	 * @param jdbc_drv Jdbc_driver
	 */
	public void setJdbc_drv(String jdbc_drv) {
		this.jdbc_drv = jdbc_drv;
	}

	/**
	 * @return jdbc_url Jdbc_url
	 */
	public String getJdbc_url() {
		return this.jdbc_url;
	}

	/**
	 * @param jdbc_url Jdbc_url
	 */
	public void setJdbc_url(String jdbc_url) {
		this.jdbc_url = jdbc_url;
	}

	/**
	 * @return jdbc_schema Jdbc_schema
	 */
	public String getJdbc_schema() {
		return this.jdbc_schema;
	}

	/**
	 * @param jdbc_schema Jdbc_schema
	 */
	public void setJdbc_schema(String jdbc_schema) {
		this.jdbc_schema = jdbc_schema;
	}

	/**
	 * @return cvt_type 转码方式
	 */
	public CVT_TYPE getCvt_type() {
		return this.cvt_type;
	}

	/**
	 * @param cvt_type 转码方式
	 */
	public void setCvt_type(CVT_TYPE cvt_type) {
		this.cvt_type = cvt_type;
	}

	/**
	 * @return ftp_local_script 上下传脚本
	 */
	public String getFtp_local_script() {
		return this.ftp_local_script;
	}

	/**
	 * @param ftp_local_script 上下传脚本
	 */
	public void setFtp_local_script(String ftp_local_script) {
		this.ftp_local_script = ftp_local_script;
	}

	/**
	 * @return cvt_local_script 转码脚本
	 */
	public String getCvt_local_script() {
		return this.cvt_local_script;
	}

	/**
	 * @param cvt_local_script 转码脚本
	 */
	public void setCvt_local_script(String cvt_local_script) {
		this.cvt_local_script = cvt_local_script;
	}

	/**
	 * @return soc_domain 数据源域名
	 */
	public String getSoc_domain() {
		return this.soc_domain;
	}

	/**
	 * @param soc_domain 数据源域名
	 */
	public void setSoc_domain(String soc_domain) {
		this.soc_domain = soc_domain;
	}

	/**
	 * @return soc_bk_desc 数据源描述
	 */
	public String getSoc_bk_desc() {
		return this.soc_bk_desc;
	}

	/**
	 * @param soc_bk_desc 数据源描述
	 */
	public void setSoc_bk_desc(String soc_bk_desc) {
		this.soc_bk_desc = soc_bk_desc;
	}
	
	/**
	 * @return user_root_path 用户根路径
	 */
	public String getUser_root_path() {
		return user_root_path;
	}

	/**
	 * @param user_root_path 用户根路径
	 */
	public void setUser_root_path(String user_root_path) {
		this.user_root_path = user_root_path;
	}
	/**
	 *@return environment_variables 环境参数
	 */
	public String getEnvironment_variables() {
		return this.environment_variables;
	}

	/**
	 *@param environment_variables 环境参数
	 */
	public void setEnvironment_variables(String environment_variables) {
		this.environment_variables = environment_variables;
	}
	/**
	 *@return encoding_type 编码格式
	 */
	public String getEncoding_type() {
		return this.encoding_type;
	}

	/**
	 *@param encoding_type 编码格式
	 */
	public void setEncoding_type(String encoding_type) {
		this.encoding_type = encoding_type;
	}
}
