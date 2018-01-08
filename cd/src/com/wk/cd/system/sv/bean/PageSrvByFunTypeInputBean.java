/**
 * Title: PageSrvByFunTypeInputBean.java
 * File Description: �������ҳʽ��ѯ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.FUN_TYPE;

/**
 * Class Description: �������ҳʽ��ѯ����ӿ�
 * @author tlw
 */
public class PageSrvByFunTypeInputBean
		extends PageQueryActionRootInputBean {
	private static final long serialVersionUID = -5643200228806420404L;

	/**
	 * ��������
	 */
	private FUN_TYPE srv_fun_type;

	public static final String SRV_FUN_TYPECN = "��������";

	/**
	 * @return srv_fun_type ��������
	 */
	public FUN_TYPE getSrv_fun_type() {
		return this.srv_fun_type;
	}

	/**
	 * @param srv_fun_type ��������
	 */
	public void setSrv_fun_type(FUN_TYPE srv_fun_type) {
		this.srv_fun_type = srv_fun_type;
	}

}
