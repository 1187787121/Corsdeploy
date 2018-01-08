/**
 * Title: QueryDprlPrivInputBean.java
 * File Description: ��ѯ���Ž�ɫȨ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��7��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯ���Ž�ɫȨ����Ϣ����ӿ�
 * @author HT
 */
public class QueryDprlPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -3528022416110479155L;
	
	/**
	 * ���Ž�ɫ����
	 */
	private String dprl_code;
	
	public static final String DPRL_CODECN = "���Ž�ɫ����";

	/**
	 * @return dprl_code ���Ž�ɫ����
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 * @param dprl_code ���Ž�ɫ����
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
	}
}
