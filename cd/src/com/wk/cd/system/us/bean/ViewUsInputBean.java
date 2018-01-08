/**
 * Title: ViewUsInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2015��11��12��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯִ���û�����ӿ�
 * @author Xul
 */
public class ViewUsInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 4820333629195947923L;

	/**
	 * ��У�����
	 */
	private String data;

	public static final String DATACN = "��У�����";

	/**
	 * ִ���û�
	 */
	private String execute_user_id;

	public static final String EXECUTE_USER_IDCN = "ִ���û�";

	/**
	 * ���ű��
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű��";

	/**
	 * @return ִ���û�
	 */
	public String getExecute_user_id() {
		return execute_user_id;
	}

	/**
	 * @param execute_user_id ִ���û�
	 */
	public void setExecute_user_id(String execute_user_id) {
		this.execute_user_id = execute_user_id;
	}

	/**
	 * @return dept_id ���ű��
	 */
	public String getDept_id() {
		return dept_id;
	}

	/**
	 * @param dept_id ���ű��
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @return data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 */
	public void setData(String data) {
		this.data = data;
	}

}
