/**
 * Title: RBean.java
 * File Description: Զ�̵��÷��񹫹��ӿ�
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/29/2014
 */

package com.wk.cd.remote.bean;

import com.wk.cd.enu.PROTOCOL_TYPE;

/**
 * Class Description:Զ�̵��÷��񹫹��ӿ�
 * @author lixl
 */
public class RBean {
	// ��ˮ��
	private String work_seq;
	// �ύ�û�
	private String user_id;
	// ����Դ����
	private String soc_name;
	// IP��ַ
	private String soc_ip;
	// �˿ں�
	private int soc_port;
	// Э������
	private PROTOCOL_TYPE protocol_type;
	// ��������½�û���
	private String remote_uname;
	// ��������½����
	private String remote_passwd;
	// ��ʱʱ�䣬��Ϊ��λ
	private int timeout;
	// was����
	private String was_params;

	/**
	 * @return was_params  was����
	 */
	public String getWas_params() {
		return this.was_params;
	}

	/**
	 * @param was_params  was����
	 */
	public void setWas_params(String was_params) {
		this.was_params = was_params;
	}

	/**
	 * @return String ��ˮ��
	 */
	public String getWork_seq() {
		return this.work_seq;
	}

	/**
	 * @param work_seq ��ˮ��
	 */
	public void setWork_seq(String work_seq) {
		this.work_seq = work_seq;
	}

	/**
	 * @return String �ύ�û�
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id �ύ�û�
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return soc_name����Դ����
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 * @param socName����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return soc_ip IP��ַ
	 */
	public String getSoc_ip() {
		return this.soc_ip;
	}

	/**
	 * @param socIp IP��ַ
	 */
	public void setSoc_ip(String soc_ip) {
		this.soc_ip = soc_ip;
	}

	/**
	 * @return soc_port �˿ں�
	 */
	public int getSoc_port() {
		return this.soc_port;
	}

	/**
	 * @param socPort �˿ں�
	 */
	public void setSoc_port(int soc_port) {
		this.soc_port = soc_port;
	}

	/**
	 * @return protocol_type Э������
	 */
	public PROTOCOL_TYPE getProtocol_type() {
		return this.protocol_type;
	}

	/**
	 * @param protocolType Э������
	 */
	public void setProtocol_type(PROTOCOL_TYPE protocol_type) {
		this.protocol_type = protocol_type;
	}

	/**
	 * @return remote_uname ��������½�û���
	 */
	public String getRemote_uname() {
		return this.remote_uname;
	}

	/**
	 * @param remoteUname ��������½�û���
	 */
	public void setRemote_uname(String remote_uname) {
		this.remote_uname = remote_uname;
	}

	/**
	 * @return remote_passwd ��������½����
	 */
	public String getRemote_passwd() {
		return this.remote_passwd;
	}

	/**
	 * @param remotePasswd ��������½����
	 */
	public void setRemote_passwd(String remote_passwd) {
		this.remote_passwd = remote_passwd;
	}

	/**
	 * @return long ��ʱʱ�䣨s)
	 */
	public int getTimeout() {
		return this.timeout;
	}

	/**
	 * @param timeout ��ʱʱ��(s)
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
