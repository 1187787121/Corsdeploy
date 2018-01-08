/**
 * Title: EnvPrivBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017��1��4��
 */
package com.wk.cd.system.ep.bean;

import com.wk.cd.enu.AF_FLAG;

/**
 * Class Description:
 * @author HT
 */
public class EnvPrivBean {

	/**
	 * ��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 * �������
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "�������";
	
	/**
	 * Ӧ��ϵͳ���
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "Ӧ��ϵͳ���";

	/**
	 * Ӧ��ϵͳ
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ";
	
	/**
	 *�����ֹ��־
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "�����ֹ��־";


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
	 * @return sys_name Ӧ��ϵͳ
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name Ӧ��ϵͳ
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return env_cn_name �������
	 */
	public String getEnv_cn_name() {
		return env_cn_name;
	}

	/**
	 * @param env_cn_name �������
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 * @return sys_cn_name Ӧ��ϵͳ���
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param sys_cn_name Ӧ��ϵͳ���
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return af_flag
	 */
	public AF_FLAG getAf_flag() {
		return af_flag;
	}

	/**
	 * @param af_flag
	 */
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}
}
