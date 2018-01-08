/**
 * Title: ReadEnvConfigFileInputBean.java
 * File Description: 读取配置文件服务输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 读取配置文件服务输入接口
 * @author Xul
 */
public class ReadEnvConfigFileInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -425851689853052811L;
	
	/**
	 * 服务器名
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "服务器名";
	
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
	 * @return ce_server_name 服务器名
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name 服务器名
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
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
}
