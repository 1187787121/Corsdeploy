/**
 * Title: DeleteUserTermInputBean.java
 * File Description: ɾ���û������ն�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��14��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ɾ���û������ն�����ӿ�
 * @author HT
 */
public class DeleteUserTermInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -9011183355559769716L;

	/**
	 * �û���
	 */
	private String user_id;
	
	public final static String USER_IDCN="�û���";
	
	/**
	 * �ն˺�
	 */
	private String term_no;
	
	public final static String TERM_NOCN="�ն˺�";
	
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
	 * @return term_no �ն˺�
	 */
	public String getTerm_no() {
		return this.term_no;
	}

	/**
	 * @param term_no �ն˺�
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}
}
