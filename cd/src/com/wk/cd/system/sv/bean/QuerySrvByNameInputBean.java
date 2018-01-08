/**
 * Title: QuerySrvByNameInputBean.java
 * File Description:���շ�������ѯ������Ϣ����ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ���շ�������ѯ������Ϣ����ӿ�
 * @author tlw
 */
public class QuerySrvByNameInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 9002207012698991434L;

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
	 * @param srvName ��������
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

}
