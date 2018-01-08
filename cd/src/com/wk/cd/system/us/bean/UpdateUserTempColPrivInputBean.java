/**
 * Title: UpdateUserTempColPrivInputBean.java
 * File Description: �޸��û���ʱColȨ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��9��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsUserColPrivInfo;

/**
 * Class Description: �޸��û���ʱColȨ������ӿ�
 * @author HT
 */
public class UpdateUserTempColPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 1177504758067751955L;
	
	/**
	 * �û���
	 */
	private String user_id;
	
	public static final String USER_IDCN = "�û���";
	
	/**
	 * colȨ���б�
	 */
	private List<UsUserColPrivInfo> col_list;
	
	public static final String COL_LISTCN = "colȨ���б�";

	/**
	 * @return user_id �û���
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return col_list colȨ���б�
	 */
	public List<UsUserColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list colȨ���б�
	 */
	public void setCol_list(List<UsUserColPrivInfo> col_list) {
		this.col_list = col_list;
	}

}