/**
 * Title: ReadEnvConfigFileInputBean.java
 * File Description: ��ȡ�����ļ���������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ȡ�����ļ���������ӿ�
 * @author Xul
 */
public class ReadEnvConfigFileInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -425851689853052811L;
	
	/**
	 * ��������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "��������";
	
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
	 * @return ce_server_name ��������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ��������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
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
}
