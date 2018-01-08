/**
 * Title: UpdateDprlEnvPrivInputBean.java
 * File Description: �޸Ĳ��Ž�ɫӦ�û���Ȩ������ӿ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017��1��4��
 */
package com.wk.cd.system.ep.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �޸Ĳ��Ž�ɫӦ�û���Ȩ������ӿ�
 * @author HT
 */
public class UpdateDprlEnvPrivInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4239055892685748744L;

	/**
	 * ���Ž�ɫ����
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����";
	
	/**
	 * ����Ȩ���б�
	 */
	private List<EnvPrivBean> env_list;
	
	public static final String ENV_LISTCN = "����Ȩ���б�";

	/**
	 * @return dprl_code  ���Ž�ɫ����
	 */
	public String getDprl_code() {
		return dprl_code;
	}

	/**
	 * @param dprl_code ���Ž�ɫ����
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}

	/**
	 * @return env_list ����Ȩ���б�
	 */
	public List<EnvPrivBean> getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list ����Ȩ���б�
	 */
	public void setEnv_list(List<EnvPrivBean> env_list) {
		this.env_list = env_list;
	}
}
