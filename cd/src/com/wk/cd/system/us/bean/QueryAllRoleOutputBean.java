/**
 * Title: QueryAllRoleOutputBean.java
 * File Description:��ѯ���н�ɫ��Ϣ����ӿ� 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-2-3
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsRoleInfo;

/**
 * Class Description:��ѯ���н�ɫ��Ϣ����ӿ�
 * @author link
 */
public class QueryAllRoleOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 3389876892855208654L;

	public List<UsRoleInfo> role_list;
	public static final String ROLE_LISTCN = "��ɫ�б�";

	/**
	 * @return role_list ��ɫ�б�
	 */
	public List<UsRoleInfo> getRole_list() {
		return this.role_list;
	}

	/**
	 * @param role_list ��ɫ�б�
	 */
	public void setRole_list(List<UsRoleInfo> role_list) {
		this.role_list = role_list;
	}

}
