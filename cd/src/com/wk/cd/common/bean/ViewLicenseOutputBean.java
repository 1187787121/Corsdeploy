/**
 * Title: ViewLicenseOutputBean.java
 * File Description: ��ȡLicense��Ϣ����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��9��29��
 */
package com.wk.cd.common.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.util.JaDate;

/**
 * Class Description: ��ȡLicense��Ϣ����ӿ�
 * @author Xul
 */
public class ViewLicenseOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -5222603585751148028L;
	
	/**
	 * Ӣ����
	 */
	private String name;
	
	public static final String NAMECN = "Ӣ����";
	
	/**
	 * ������
	 */
	private String zh_name;
	
	public static final String ZH_NAMECN = "������";
	
	/**
	 * ������
	 */
	private JaDate expire_end_date;
	
	public static final String EXPIRE_END_DATECN = "������";
	
	/**
	 * License���
	 */
	private String license;
	
	public static final String LICENSECN = "License���";
	
	/**
	 * IP
	 */
	private String ip;
	
	public static final String IPCN = "IP";
	
	/**
	 * ʣ�ൽ������
	 */
	private int expire_days;
	
	public static final String EXPIRE_DAYSCN = "ʣ�ൽ������";
	
	/**
	 * ע���־
	 */
	private int regist_flag;
	
	public static final String REGIST_FLAGCN = "ע���־";
	
	/**
	 * ��Ȩ��Ϣ
	 */
	private String version_msg;
	
	public static final String VERSION_MSGCN="��Ȩ��Ϣ";
	
	/**
	 * APP��½״̬
	 */
	private boolean app_state;
	
	public static final String APP_STATECN = "APP��½״̬";

	/**
	 * @return name Ӣ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Ӣ����
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return zh_name ������
	 */
	public String getZh_name() {
		return zh_name;
	}

	/**
	 * @param zh_name ������
	 */
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}

	/**
	 * @return expire_end_date ������
	 */
	public JaDate getExpire_end_date() {
		return expire_end_date;
	}

	/**
	 * @param expire_end_date ������
	 */
	public void setExpire_end_date(JaDate expire_end_date) {
		this.expire_end_date = expire_end_date;
	}

	/**
	 * @return license License���
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * @param license License���
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * @return ip IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return expire_days ʣ�ൽ������
	 */
	public int getExpire_days() {
		return expire_days;
	}

	/**
	 * @param expire_days ʣ�ൽ������
	 */
	public void setExpire_days(int expire_days) {
		this.expire_days = expire_days;
	}

	/**
	 * @return regist_flag ע���־
	 */
	public int getRegist_flag() {
		return regist_flag;
	}

	/**
	 * @param regist_flag ע���־
	 */
	public void setRegist_flag(int regist_flag) {
		this.regist_flag = regist_flag;
	}

	/**
	 * @return version_msg
	 */
	public String getVersion_msg() {
		return version_msg;
	}

	/**
	 * @param version_msg
	 */
	public void setVersion_msg(String version_msg) {
		this.version_msg = version_msg;
	}

	/**
	 * @return app_state
	 */
	public boolean isApp_state() {
		return app_state;
	}

	/**
	 * @param app_state
	 */
	public void setApp_state(boolean app_state) {
		this.app_state = app_state;
	}
	
	
}
