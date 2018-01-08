/**
 * Title: ViewEnvPrivOutputBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017��1��4��
 */
package com.wk.cd.system.ep.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author HT
 */
public class ViewEnvPrivOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3274558148790793227L;
	
	/**
	 * Ӧ�û���Ȩ���б�
	 */
	private List<EnvPrivBean> env_list;
	
	public static final String ENV_LISTCN = "Ӧ�û���Ȩ���б�";
	
	/**
	 * �ϼ�Ӧ�û���Ȩ���б�
	 */
	private List<EnvPrivBean> sup_env_list;
	
	public static final String SUP_ENV_LISTCN = "�ϼ�Ӧ�û���Ȩ���б�";
	
	/**
	 *  ���Ž�ɫӦ�û���Ȩ���б�
	 */
	private List<EnvPrivBean> dr_env_list;
	
	public static final String DR_ENV_LISTCN = "���Ž�ɫӦ�û���Ȩ���б�";

	/**
	 * @return env_list
	 */
	public List<EnvPrivBean> getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list
	 */
	public void setEnv_list(List<EnvPrivBean> env_list) {
		this.env_list = env_list;
	}

	/**
	 * @return sup_env_list
	 */
	public List<EnvPrivBean> getSup_env_list() {
		return sup_env_list;
	}

	/**
	 * @param sup_env_list
	 */
	public void setSup_env_list(List<EnvPrivBean> sup_env_list) {
		this.sup_env_list = sup_env_list;
	}

	/**
	 * @return dr_env_list
	 */
	public List<EnvPrivBean> getDr_env_list() {
		return dr_env_list;
	}

	/**
	 * @param dr_env_list
	 */
	public void setDr_env_list(List<EnvPrivBean> dr_env_list) {
		this.dr_env_list = dr_env_list;
	}
	
}
