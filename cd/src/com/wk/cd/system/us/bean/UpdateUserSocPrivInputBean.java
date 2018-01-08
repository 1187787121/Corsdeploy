/**
 * Title: UpdateUserSocPrivInputBean.java
 * File Description: �޸��û�����ԴȨ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��9��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;

/**
 * Class Description: �޸��û�����ԴȨ������ӿ�
 * @author HT
 */
public class UpdateUserSocPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -8549125455332404009L;
	
	/**
	 * �û���
	 */
	private String user_id;

	public static final String USER_IDCN = "�û���_V";

	/**
	 * ����ԴȨ���б�
	 */
	private List<UsUserSocPrivInfo> soc_list;

	public static final String SOC_LISTCN = "����ԴȨ���б�_V";

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
	 * @return soc_list ����ԴȨ���б�
	 */
	public List<UsUserSocPrivInfo> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list ����ԴȨ���б�
	 */
	public void setSoc_list(List<UsUserSocPrivInfo> soc_list) {
		this.soc_list = soc_list;
	}
}