/**
 * Title: UpdateSrvPrivInputBean.java
 * File Description:�޸ķ��񸴺ˡ���Ȩ��������ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.ap.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:�޸ķ��񸴺ˡ���Ȩ��������ӿ�
 * @author tlw
 */
public class UpdateSrvPrivInputBean
		extends ActionRootInputBean {
	private static final long serialVersionUID = -1265361449245139534L;

	/**
	 * ��������
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "��������";

	/**
	 * ���˲��Ž�ɫ�б�
	 */
	private List<ChkDprlCodeBean> srv_check_list;

	public static final String SRV_CHECK_LISTCN = "���˲��Ž�ɫ�б�";

	/**
	 * ��Ȩ���Ž�ɫ�б�
	 */
	private List<AuthDprlCodeBean> srv_auth_list;

	public static final String SRV_AUTH_LISTCN = "��Ȩ���Ž�ɫ�б�";

	/**
	 * @return srv_name ��������
	 */
	public String getSrv_name() {
		return srv_name;
	}

	/**
	 * @param srv_name ��������
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 * @return srv_check_list ���˽�ɫ�б�
	 */
	public List<ChkDprlCodeBean> getSrv_check_list() {
		return srv_check_list;
	}

	/**
	 * @param srv_check_list ���˽�ɫ�б�
	 */
	public void setSrv_check_list(List<ChkDprlCodeBean> srv_check_list) {
		this.srv_check_list = srv_check_list;
	}

	/**
	 * @return srv_auth_list ��Ȩ��Ϣ�б�
	 */
	public List<AuthDprlCodeBean> getSrv_auth_list() {
		return srv_auth_list;
	}

	/**
	 * @param srv_auth_list ��Ȩ��Ϣ�б�
	 */
	public void setSrv_auth_list(List<AuthDprlCodeBean> srv_auth_list) {
		this.srv_auth_list = srv_auth_list;
	}
}
