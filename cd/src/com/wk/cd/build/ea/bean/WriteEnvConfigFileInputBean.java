/**
 * Title: WriteEnvConfigFileInputBean.java
 * File Description: �޸������ļ���������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �޸������ļ���������ӿ�
 * @author Xul
 */
public class WriteEnvConfigFileInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -6879983315945823772L;
	
	/**
	 * ��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";
	
	/**
	 * ����������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "����������";
	
	/**
	 * ���κ�
	 */
	private String batch_no;

	public static final String BATCH_NOCN = "���κ�";
	
	/**
	 * �ļ����·��
	 */
	private String relative_path;
	
	public static final String RELATIVE_PATHCN = "�ļ����·��";
	
	/**
	 * �ַ���
	 */
	private String encoding;
	
	public static final String ENCODINGCN = "�ַ���";
	
	/**
	 * �ļ��ַ���
	 */
	private String config_string;
	
	public static final String CONFIG_STRINGCN = "�ļ��ַ���";
	
	/**
	 * ����ϵͳ
	 */
	private String system;
	
	public static final String SYSTEMCN = "����ϵͳ";
	
	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}
	
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
	 * @return batch_no ���κ�
	 */
	public String getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no ���κ�
	 */
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	/**
	 * @return relative_path �ļ����·��
	 */
	public String getRelative_path() {
		return relative_path;
	}

	/**
	 * @param relative_path �ļ����·��
	 */
	public void setRelative_path(String relative_path) {
		this.relative_path = relative_path;
	}

	/**
	 * @return encoding �ַ���
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding �ַ���
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * @return config_string �ļ��ַ���
	 */
	public String getConfig_string() {
		return config_string;
	}

	/**
	 * @param config_string �ļ��ַ���
	 */
	public void setConfig_string(String config_string) {
		this.config_string = config_string;
	}
	
	/**
	 * @return system ����ϵͳ
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system ����ϵͳ
	 */
	public void setSystem(String system) {
		this.system = system;
	}
}
