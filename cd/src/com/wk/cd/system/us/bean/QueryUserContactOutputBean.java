/**
 * Title: QueryUserContactOutputBean.java
 * File Description: ��ѯ�û�������ϵ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��11��18��
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ��ѯ�û�������ϵ������ӿ�
 * @author HT
 */
public class QueryUserContactOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -7491846418475000084L;

	/**
	 * ������ϵ���б�
	 */
	private List<UsExtUserBean> contact_list;
	
	public static final String CONTACT_LISTCN = "������ϵ���б�";

	/**
	 * @return contact_list ������ϵ���б�
	 */
	public List<UsExtUserBean> getContact_list() {
		return this.contact_list;
	}

	/**
	 * @param contact_list ������ϵ���б�
	 */
	public void setContact_list(List<UsExtUserBean> contact_list) {
		this.contact_list = contact_list;
	}
}
