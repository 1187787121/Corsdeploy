/**
 * Title: UpdateDprlRsOptPrivInputBean.java
 * File Description: �޸Ĳ��Ž�ɫ��Դ������Ȩ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��7��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsRoleOptPrivInfo;

/**
 * Class Description: �޸Ĳ��Ž�ɫ��Դ������Ȩ����Ϣ����ӿ�
 * @author HT
 */
public class UpdateDprlRsOptPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -822565438298002799L;
	
	/**
	 * ���Ž�ɫ����
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����";

	/**
	 * ��Դ���������б�
	 */
	private String[] rs_ary;

	public static final String RS_ARYCN = "��Դ���������б�";
	
	/**
	 * ���Ž�ɫ����Ȩ���б�
	 */
	private List<UsRoleOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "���Ž�ɫ����Ȩ���б�";

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
	 * @return rs_ary ��Դ���������б�
	 */
	public String[] getRs_ary() {
		return this.rs_ary;
	}

	/**
	 * @param rs_ary ��Դ���������б�
	 */
	public void setRs_ary(String[] rs_ary) {
		this.rs_ary = rs_ary;
	}

	/**
	 * @return opt_priv ���Ž�ɫ����Ȩ���б�
	 */
	public List<UsRoleOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv ���Ž�ɫ����Ȩ���б�
	 */
	public void setOpt_priv(List<UsRoleOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}
}
