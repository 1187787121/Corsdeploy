/**
 * Title: UserForceLogoutInputBean.java
 * File Description:  ǿ��ǩ������ӿ�
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 2/13/2015
 */

package com.wk.cd.system.us.bean;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:ǿ��ǩ������ӿ�
 * @author lixl
 */
public class UserForceLogoutInputBean extends ActionRootInputBean {
	private static final long serialVersionUID = 8491529415933440559L;

	/**
	 * �û���
	 */
	private String user_id;

	public static final String USER_IDCN = "�û���";

	/**
	 * @return String �û���
	 */
	public String getUser_id(){
		return user_id;
	}

	/**
	 * @param user_id �û���
	 */
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}
}

