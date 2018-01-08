/**
 * Title: ConfigSocBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.bean;

import java.io.Serializable;

/**
 * Class Description: �����ļ�����Դ�б�
 * @author Xul
 */
public class ServerConfigBean implements Serializable{

	private static final long serialVersionUID = -6988223966149939489L;
	
	/**
	 * ����������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "����������";
	
	/**
	 * ��������ַ
	 */
	private String server_ip;

	public static final String SERVER_IPCN = "��������ַ";
	
	/**
	 * �û���·��
	 */
	private String user_root_path;
	
	public static final String USER_ROOT_PATHCN = "�û���·��";
	
	/**
	 * @return ce_server_name ����������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ����������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return server_ip ��������ַ
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param server_ip ��������ַ
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return user_root_path �û���·��
	 */
	public String getUser_root_path() {
		return user_root_path;
	}

	/**
	 * @param user_root_path �û���·��
	 */
	public void setUser_root_path(String user_root_path) {
		this.user_root_path = user_root_path;
	}
}
