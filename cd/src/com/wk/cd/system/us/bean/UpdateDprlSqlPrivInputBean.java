/**
 * Title: UpdateDprlSqlPrivInputBean.java
 * File Description: �޸Ĳ��Ž�ɫSqlȨ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��7��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;

/**
 * Class Description: �޸Ĳ��Ž�ɫSqlȨ����Ϣ����ӿ�
 * @author HT
 */
public class UpdateDprlSqlPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -1887500125744889479L;
	
	/**
	 * ���Ž�ɫ����
	 */
	private String dprl_code;
	
	public static final String DPRL_CODECN = "���Ž�ɫ����";
	
	/**
	 * sqlȨ���б�
	 */
	private List<UsRoleSqlPrivInfo> sql_list;
	
	public static final String SQL_LISTCN = "sqlȨ���б�";

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
	 * @return sql_list sqlȨ���б�
	 */
	public List<UsRoleSqlPrivInfo> getSql_list() {
		return this.sql_list;
	}

	/**
	 * @param sql_list sqlȨ���б�
	 */
	public void setSql_list(List<UsRoleSqlPrivInfo> sql_list) {
		this.sql_list = sql_list;
	}
}
