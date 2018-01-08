/**
 * Title: UpdateDprlSocPrivInputBean.java
 * File Description: �޸Ĳ��Ž�ɫ����ԴȨ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��7��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �޸Ĳ��Ž�ɫ����ԴȨ����Ϣ����ӿ�
 * @author HT
 */
public class UpdateDprlSocPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -1630735601776084048L;
	
	/**
	 * ���Ž�ɫ����
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����_V";

	/**
	 * ����Դ�����б�
	 */
	private String[] soc_ary;

	public static final String SOC_ARYCN = "����Դ�����б�_V";

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

	/**
	 * @return soc_ary ����Դ�����б�
	 */
	public String[] getSoc_ary() {
		return this.soc_ary;
	}

	/**
	 * @param soc_ary ����Դ�����б�
	 */
	public void setSoc_ary(String[] soc_ary) {
		this.soc_ary = soc_ary;
	}
}
