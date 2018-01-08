/**
 * Title: SysEnvBean.java
 * File Description: ��������ϵͳ�����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��15��
 */
package com.wk.cd.build.en.bean;

import java.io.Serializable;
import java.util.List;

import com.wk.cd.enu.SYS_TYPE;

/**
 * Class Description: ��������ϵͳ�����ӿ�
 * @author Xul
 */
public class SysEnvBean implements Serializable{

	private static final long serialVersionUID = 2193974039313925532L;
	
	/**
	 *Ӧ��ϵͳ����
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ����";

	/**
	 *Ӧ��ϵͳ���
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "Ӧ��ϵͳ���";
	
	/**
	 *ϵͳ����
	 */
	private SYS_TYPE sys_type;
	
	public static final String SYS_TYPECN = "ϵͳ����";
	
	/**
	 *���������б�
	 */
	private List<SysEnvAndTaskBean> env_task_list;
	
	public static final String ENV_TASK_LISTCN = "���������б�";
	
	/**
	 *@return sys_name Ӧ��ϵͳ����
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name Ӧ��ϵͳ����
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return sys_cn_name Ӧ��ϵͳ���
	 */
	public String getSys_cn_name() {
		return this.sys_cn_name;
	}

	/**
	 *@param sys_cn_name Ӧ��ϵͳ���
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}
	
	/**
	 * @return sys_type ϵͳ����
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type ϵͳ����
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 * @return env_task_list ���������б�
	 */
	public List<SysEnvAndTaskBean> getEnv_task_list() {
		return env_task_list;
	}

	/**
	 * @param env_task_list ���������б�
	 */
	public void setEnv_task_list(List<SysEnvAndTaskBean> env_task_list) {
		this.env_task_list = env_task_list;
	}
}
