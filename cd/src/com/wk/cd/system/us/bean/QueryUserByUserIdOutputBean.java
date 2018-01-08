/**
 * Title: QueryUserByUserIdOutputBean.java
 * File Description: �����û�����ѯ�û���ϸ��Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsUserRoleInfo;

/**
 * Class Description:�����û�����ѯ�û���ϸ��Ϣ����ӿ�
 * @author link
 */
public class QueryUserByUserIdOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 2626601738837554185L;
	
	/**
	 * �û���ϸ��Ϣ
	 */
	private UsExtUserBean user_bean;
	
	public static final String USER_BEANCN = "�û���ϸ��Ϣ";
	
	/**
	 * �û���ɫ��Ϣ
	 */
	private List<UsUserRoleInfo> us_roles;
	
	public static final String US_ROLESCN = "�û���ɫ��Ϣ";

	/**
	 * @return user_bean �û���ϸ��Ϣ
	 */
	public UsExtUserBean getUser_bean() {
		return this.user_bean;
	}

	/**
	 * @param user_bean �û���ϸ��Ϣ
	 */
	public void setUser_bean(UsExtUserBean user_bean) {
		this.user_bean = user_bean;
	}
	
	/**
	 * @return us_roles �û���ɫ��Ϣ
	 */
	public List<UsUserRoleInfo> getUs_roles() {
		return this.us_roles;
	}

	/**
	 * @param us_roles �û���ɫ��Ϣ
	 */
	public void setUs_roles(List<UsUserRoleInfo> us_roles) {
		this.us_roles = us_roles;
	}

}
