/**
 * Title: QueryUserPrivInputBean.java
 * File Description:  ��ѯ�û�Ȩ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��8��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯ�û�Ȩ����Ϣ����ӿ�
 * @author HT
 */
public class QueryUserPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 7281579348726145018L;

	/**
	 * �û���
	 */
	private String user_id;
	
	public static final String USER_IDCN = "�û���";

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
}
