/**
 * Title: UpdateDeptEnvPrivInputBean.java
 * File Description: �޸Ĳ���Ӧ�û���Ȩ������ӿ�
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
 * Class Description: �޸Ĳ���Ӧ�û���Ȩ������ӿ�
 * @author HT
 */
public class UpdateDeptEnvPrivInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4168747589961265768L;
	
	/**
	 * ���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";
	
	/**
	 * ����Ȩ���б�
	 */
	private List<EnvPrivBean> env_list;
	
	public static final String ENV_LISTCN = "����Ȩ���б�";

	/**
	 * @return dept_id ���ű���
	 */
	public String getDept_id() {
		return dept_id;
	}

	/**
	 * @param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
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
