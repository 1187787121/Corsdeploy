/**
 * Title: DeleteUserContactInputBean.java
 * File Description: 删除常用联系人输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月18日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 删除常用联系人输入接口
 * @author HT
 */
public class DeleteUserContactInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -4239184760928441907L;
	
	/**
	 * 联系人ID
	 */
	private String contact_user_id;
	
	public static final String CONTACT_USER_IDCN = "联系人ID";

	/**
	 * @return contact_user_id 联系人ID
	 */
	public String getContact_user_id() {
		return this.contact_user_id;
	}

	/**
	 * @param contact_user_id 联系人ID
	 */
	public void setContact_user_id(String contact_user_id) {
		this.contact_user_id = contact_user_id;
	}

}
