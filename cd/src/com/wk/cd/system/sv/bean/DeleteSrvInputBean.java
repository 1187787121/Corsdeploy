/**
 * Title: DeleteSrvInputBean.java
 * File Description: ɾ����������ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:ɾ����������ӿ�
 * @author tlw
 */
public class DeleteSrvInputBean
		extends ActionRootInputBean {
	private static final long serialVersionUID = 7279412003631492820L;

	/**
	 * ��������
	 */
	private String srv_name;
	
	public static final String SRV_NAMECN = "��������";

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
}
