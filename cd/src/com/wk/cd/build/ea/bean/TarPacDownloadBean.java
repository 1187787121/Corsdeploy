/**
 * Title: TarPacDownloadBean.java
 * File Description: Ŀ������ؽӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��29��
 */
package com.wk.cd.build.ea.bean;

import java.io.Serializable;

/**
 * Class Description: Ŀ������ؽӿ�
 * @author Xul
 */
public class TarPacDownloadBean implements Serializable{

	private static final long serialVersionUID = -7922655049123498294L;
	
	/**
	 * Զ���ļ�ȫ·��
	 */
	private String romote_full_path;
	
	public static final String ROMOTE_FULL_PATHCN = "Զ���ļ�ȫ·��";
	
	/**
	 * �����ļ�ȫ·��
	 */
	private String local_full_path;
	
	public static final String LOCAL_FULL_PATHCN = "�����ļ�ȫ·��";
	
	/**
	 * ����Դ��
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ��";

	/**
	 * @return romote_full_path Զ���ļ�ȫ·��
	 */
	public String getRomote_full_path() {
		return romote_full_path;
	}

	/**
	 * @param romote_full_path Զ���ļ�ȫ·��
	 */
	public void setRomote_full_path(String romote_full_path) {
		this.romote_full_path = romote_full_path;
	}

	/**
	 * @return local_full_path �����ļ�ȫ·��
	 */
	public String getLocal_full_path() {
		return local_full_path;
	}

	/**
	 * @param local_full_path �����ļ�ȫ·��
	 */
	public void setLocal_full_path(String local_full_path) {
		this.local_full_path = local_full_path;
	}

	/**
	 * @return soc_name ����Դ��
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ��
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}
	
}
