/**
 * Title: PageSrvByBranchOutputBean.java
 * File Description: �û���ѯ���п��������������̵ķ�������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tianlw
 * @version: 1.0
 * @date: 2015-8-25
 */
package com.wk.cd.system.ap.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: �û���ѯ���п��������������̵ķ�������ӿ�
 * @author tianlw
 */
public class QuerySrvByBranchOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -8572416020784025504L;
	
	/**
	 * ��������Ȩ����Ϣ�б�
	 */
	private List<ApproveServiceBean> app_srv_list;
	
	public static final String APP_SRV_LISTCN = "��������Ȩ����Ϣ�б�";

	/**
	 * @return app_srv_list ��������Ȩ����Ϣ�б�
	 */
	public List<ApproveServiceBean> getApp_srv_list() {
		return this.app_srv_list;
	}

	/**
	 * @param app_srv_list ��������Ȩ����Ϣ�б�
	 */
	public void setApp_srv_list(List<ApproveServiceBean> app_srv_list) {
		this.app_srv_list = app_srv_list;
	}
	
}
