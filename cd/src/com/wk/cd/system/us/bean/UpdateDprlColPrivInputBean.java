/**
 * Title: UpdateDprlColPrivInputBean.java
 * File Description: �޸Ĳ��Ž�ɫColȨ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��7��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;

/**
 * Class Description: �޸Ĳ��Ž�ɫColȨ����Ϣ����ӿ�
 * @author HT
 */
public class UpdateDprlColPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 6003756151928285812L;
	
	/**
	 * ���Ž�ɫ����
	 */
	private String dprl_code;
	
	public static final String DPRL_CODECN = "���Ž�ɫ����";
	
	/**
	 * colȨ���б�
	 */
	private List<UsRoleColPrivInfo> col_list;
	
	public static final String COL_LISTCN = "colȨ���б�";

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
	 * @return col_list colȨ���б�
	 */
	public List<UsRoleColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list colȨ���б�
	 */
	public void setCol_list(List<UsRoleColPrivInfo> col_list) {
		this.col_list = col_list;
	}

}
