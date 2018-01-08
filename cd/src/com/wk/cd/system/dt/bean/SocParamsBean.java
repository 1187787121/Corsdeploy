/**
 * Title: SocParamsBean.java
 * File Description: ����Դ�����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016-6-13
 */
package com.wk.cd.system.dt.bean;

/**
 * Class Description: ����Դ�����ӿ�
 * @author xuph
 */
public class SocParamsBean {

	private String url;
	public static final String URLCN = "URL";

	private String ip;
	public static final String IPCN = "IP��ַ";

	private int port;
	public static final String PORTCN = "�˿ں�";

	private String uname;
	public static final String UNAMECN = "Ŀ�������û���";

	private String passwd;
	public static final String PASSWDCN = "Ŀ����������";

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return ip IP��ַ
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * @param ip IP��ַ
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return port �˿ں�
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * @param port �˿ں�
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return remote_uname Ŀ�������û���
	 */
	public String getUname() {
		return this.uname;
	}

	/**
	 * @param uname Ŀ�������û���
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return remote_passwd Ŀ����������
	 */
	public String getPasswd() {
		return this.passwd;
	}

	/**
	 * @param passwd Ŀ����������
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
