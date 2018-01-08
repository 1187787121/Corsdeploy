/**
 * Title: EnvTagStorageBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.STORAGE_RESULT;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.util.JaDateTime;

/**
 * Class Description: 
 * @author chiss
 */
public class EnvTagStorageBean {
	/**
	 * �����
	 */
	private String storage_id;
	
	public static final String STORAGE_IDCN = "�����";
	
	/**
	 * ��⿪ʼʱ��
	 */
	private JaDateTime start_bk_tm;
	
	public static final String START_BK_TMCN = "��⿪ʼʱ��";
	
	/**
	 * ������ʱ��
	 */
	private JaDateTime end_bk_tm;
	
	public static final String END_BK_TMCN = "������ʱ��";
	
	/**
	 * ���״̬
	 */
	private STORAGE_STATUS storage_status;
	
	public static final String STORAGE_STATUSCN = "���״̬";
	
	/**
	 * �����
	 */
	private STORAGE_RESULT storage_result;
	
	public static final String STORAGE_RESULTCN = "�����";
	
	/**
	 * �����б�
	 */
	private String[] package_list;
	
	public static final String PACKAGE_LISTCN = "�����б�";
	
	/**
	 * ��־����
	 */
	private String log_name;
	
	public static final String LOG_NAMECN = "��־����";
	
	/**
	 * (�汾��)Ŀ��汾��
	 */
	private String tar_ver_num;
	
	public static final String TAR_VER_NUMCN = "(�汾��)Ŀ��汾��";
	
	/**
	 * �汾����Ŀ��汾��
	 */
	private String storage_list_uuid;
	
	public static final String STORAGE_LIST_UUIDCN = "�汾����Ŀ��汾��";

	/**
	 * @return storage_id �����
	 */
	public String getStorage_id() {
		return storage_id;
	}

	/**
	 * @param storage_id �����
	 */
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}

	/**
	 * @return start_bk_tm ��⿪ʼʱ��
	 */
	public JaDateTime getStart_bk_tm() {
		return start_bk_tm;
	}

	/**
	 * @param start_bk_tm ��⿪ʼʱ��
	 */
	public void setStart_bk_tm(JaDateTime start_bk_tm) {
		this.start_bk_tm = start_bk_tm;
	}

	/**
	 * @return end_bk_tm ������ʱ��
	 */
	public JaDateTime getEnd_bk_tm() {
		return end_bk_tm;
	}

	/**
	 * @param end_bk_tm ������ʱ��
	 */
	public void setEnd_bk_tm(JaDateTime end_bk_tm) {
		this.end_bk_tm = end_bk_tm;
	}

	/**
	 * @return storage_status ���״̬
	 */
	public STORAGE_STATUS getStorage_status() {
		return storage_status;
	}

	/**
	 * @param storage_status ���״̬
	 */
	public void setStorage_status(STORAGE_STATUS storage_status) {
		this.storage_status = storage_status;
	}

	/**
	 * @return storage_result �����
	 */
	public STORAGE_RESULT getStorage_result() {
		return storage_result;
	}

	/**
	 * @param storage_result �����
	 */
	public void setStorage_result(STORAGE_RESULT storage_result) {
		this.storage_result = storage_result;
	}

	/**
	 * @return package_list �����б�
	 */
	public String[] getPackage_list() {
		return package_list;
	}

	/**
	 * @param package_list �����б�
	 */
	public void setPackage_list(String[] package_list) {
		this.package_list = package_list;
	}


	/**
	 * @return log_name ��־����
	 */
	public String getLog_name() {
		return log_name;
	}

	/**
	 * @param log_name ��־����
	 */
	public void setLog_name(String log_name) {
		this.log_name = log_name;
	}

	/**
	 * @return tar_ver_num (�汾��)Ŀ��汾��
	 */
	public String getTar_ver_num() {
		return tar_ver_num;
	}

	/**
	 * @param tar_ver_num (�汾��)Ŀ��汾��
	 */
	public void setTar_ver_num(String tar_ver_num) {
		this.tar_ver_num = tar_ver_num;
	}

	/**
	 * @return storage_list_uuid �汾����Ŀ��汾��
	 */
	public String getStorage_list_uuid() {
		return storage_list_uuid;
	}

	/**
	 * @param storage_list_uuid �汾����Ŀ��汾��
	 */
	public void setStorage_list_uuid(String storage_list_uuid) {
		this.storage_list_uuid = storage_list_uuid;
	}
}
