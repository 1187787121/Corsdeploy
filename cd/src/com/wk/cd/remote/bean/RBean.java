/**
 * Title: RBean.java
 * File Description: 远程调用服务公共接口
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/29/2014
 */

package com.wk.cd.remote.bean;

import com.wk.cd.enu.PROTOCOL_TYPE;

/**
 * Class Description:远程调用服务公共接口
 * @author lixl
 */
public class RBean {
	// 流水号
	private String work_seq;
	// 提交用户
	private String user_id;
	// 数据源名称
	private String soc_name;
	// IP地址
	private String soc_ip;
	// 端口号
	private int soc_port;
	// 协议类型
	private PROTOCOL_TYPE protocol_type;
	// 服务器登陆用户名
	private String remote_uname;
	// 服务器登陆密码
	private String remote_passwd;
	// 超时时间，秒为单位
	private int timeout;
	// was参数
	private String was_params;

	/**
	 * @return was_params  was参数
	 */
	public String getWas_params() {
		return this.was_params;
	}

	/**
	 * @param was_params  was参数
	 */
	public void setWas_params(String was_params) {
		this.was_params = was_params;
	}

	/**
	 * @return String 流水号
	 */
	public String getWork_seq() {
		return this.work_seq;
	}

	/**
	 * @param work_seq 流水号
	 */
	public void setWork_seq(String work_seq) {
		this.work_seq = work_seq;
	}

	/**
	 * @return String 提交用户
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id 提交用户
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return soc_name数据源名称
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 * @param socName数据源名称
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
	 * @param socIp IP地址
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
	 * @param socPort 端口号
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
	 * @param protocolType 协议类型
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * @return remote_uname 服务器登陆用户名
	 */
	public String getRemote_uname() {
		return this.remote_uname;
	}

	/**
	 * @param remoteUname 服务器登陆用户名
	 */
	public void setRemote_uname(String remote_uname) {
		this.remote_uname = remote_uname;
	}

	/**
	 * @return remote_passwd 服务器登陆密码
	 */
	public String getRemote_passwd() {
		return this.remote_passwd;
	}

	/**
	 * @param remotePasswd 服务器登陆密码
	 */
	public void setRemote_passwd(String remote_passwd) {
		this.remote_passwd = remote_passwd;
	}

	/**
	 * @return long 超时时间（s)
	 */
	public int getTimeout() {
		return this.timeout;
	}

	/**
	 * @param timeout 超时时间(s)
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
