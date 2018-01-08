/**
 * Title: SystemEnvirBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��1��
 */
package com.wk.cd.build.en.bean;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.SERVER_TYPE;

/**
 * Class Description: 
 * @author chiss
 */
public class SystemEnvirBean {
	/**
	 * ��������
	 */
	private String env_cn_name;
	
	public static final String ENV_CN_NAMECN = "��������";
	
	/**
	 * Ӧ��ϵͳ�����ļ��
	 */
	private String sys_cn_name;
	
	public static final String SYS_CN_NAMECN = "Ӧ��ϵͳ�����ļ��";
	
	/**
	 * ��������
	 */
	private ENV_TYPE env_type;
	
	public static final String ENV_TYPECN = "��������";
	
	/**
	 * ����������
	 */
	private SERVER_TYPE server_type;
	
	public static final String SERVER_TYPECN = "����������"; 
	
	/**
	 * ���ݷ�Χ
	 */
	private DT_RANGE dt_range;
	
	public static final String DT_RANGECN = "���ݷ�Χ";
	
	/**
	 * ����Ҫ��
	 */
	private String ele_type;
	
	public static final String ELE_TYPECN = "����Ҫ��";
	/**
	 * @return env_cn_name ��������
	 */
	public String getEnv_cn_name() {
		return env_cn_name;
	}

	/**
	 * @param env_cn_name ��������
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 * @return env_type ��������
	 */
	public ENV_TYPE getEnv_type() {
		return env_type;
	}

	/**
	 * @param env_type ��������
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 * @return server_type ����������
	 */
	public SERVER_TYPE getServer_type() {
		return server_type;
	}

	/**
	 * @param server_type ����������
	 */
	public void setServer_type(SERVER_TYPE server_type) {
		this.server_type = server_type;
	}

	/**
	 * @return dt_range ���ݷ�Χ
	 */
	public DT_RANGE getDt_range() {
		return dt_range;
	}

	/**
	 * @param dt_range ���ݷ�Χ
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

	/**
	 * @return ele_type ����Ҫ��
	 */
	public String getEle_type() {
		return ele_type;
	}

	/**
	 * @param ele_type ����Ҫ��
	 */
	public void setEle_type(String ele_type) {
		this.ele_type = ele_type;
	}

	/**
	 * @return Ӧ��ϵͳ�����ļ��
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param Ӧ��ϵͳ�����ļ��
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "SystemEnvirBean [env_cn_name=" + env_cn_name + ", sys_cn_name="
				+ sys_cn_name + ", env_type=" + env_type + ", server_type="
				+ server_type + ", dt_range=" + dt_range + ", ele_type="
				+ ele_type + "]";
	}
	
}
