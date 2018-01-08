/**
 * Title: WriteEnvConfigFileInputBean.java
 * File Description: 修改配置文件服务输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 修改配置文件服务输入接口
 * @author Xul
 */
public class WriteEnvConfigFileInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -6879983315945823772L;
	
	/**
	 * 环境名称
	 */
	private String env_name;

	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 服务器名称
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "服务器名称";
	
	/**
	 * 批次号
	 */
	private String batch_no;

	public static final String BATCH_NOCN = "批次号";
	
	/**
	 * 文件相对路径
	 */
	private String relative_path;
	
	public static final String RELATIVE_PATHCN = "文件相对路径";
	
	/**
	 * 字符集
	 */
	private String encoding;
	
	public static final String ENCODINGCN = "字符集";
	
	/**
	 * 文件字符串
	 */
	private String config_string;
	
	public static final String CONFIG_STRINGCN = "文件字符串";
	
	/**
	 * 操作系统
	 */
	private String system;
	
	public static final String SYSTEMCN = "操作系统";
	
	/**
	 * @return env_name 环境名称
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}
	
	/**
	 * @return ce_server_name 服务器名称
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name 服务器名称
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}
	
	/**
	 * @return batch_no 批次号
	 */
	public String getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no 批次号
	 */
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	/**
	 * @return relative_path 文件相对路径
	 */
	public String getRelative_path() {
		return relative_path;
	}

	/**
	 * @param relative_path 文件相对路径
	 */
	public void setRelative_path(String relative_path) {
		this.relative_path = relative_path;
	}

	/**
	 * @return encoding 字符集
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding 字符集
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * @return config_string 文件字符串
	 */
	public String getConfig_string() {
		return config_string;
	}

	/**
	 * @param config_string 文件字符串
	 */
	public void setConfig_string(String config_string) {
		this.config_string = config_string;
	}
	
	/**
	 * @return system 操作系统
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system 操作系统
	 */
	public void setSystem(String system) {
		this.system = system;
	}
}
