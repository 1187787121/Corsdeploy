/**
 * Title: UpdateUserTempRsPrivInputBean.java
 * File Description: �޸��û���ʱ��ԴȨ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��9��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �޸��û���ʱ��ԴȨ������ӿ�
 * @author HT
 */
public class UpdateUserTempRsPrivInputBean extends ActionRootInputBean{
	
	private static final long serialVersionUID = 6428286961183603283L;
	
	/**
	 * �û���
	 */
	private String user_id;
	
	public static final String USER_IDCN="�û���_V";
	
	/**
	 * ��ԴȨ���б�
	 */
	private List<UsUserRsPrivBean> rs_list;

	public static final String RS_LISTCN = "��ԴȨ���б�_V";
	
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
	 * @return rs_list ��ԴȨ���б�
	 */
	public List<UsUserRsPrivBean> getRs_list() {
		return this.rs_list;
	}

	/**
	 * @param rs_list ��ԴȨ���б�
	 */
	public void setRs_list(List<UsUserRsPrivBean> rs_list) {
		this.rs_list = rs_list;
	}

}
