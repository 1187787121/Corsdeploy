/**
 * Title: QueryRsPrivInputBean.java
 * File Description: ��ѯ�û���ԴȨ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯ�û���ԴȨ������ӿ�
 * @author lixl
 */
public class QueryRsPrivInputBean extends ActionRootInputBean{
	private static final long serialVersionUID = -8479569051412555915L;
	
	/**
	 * ϵͳ��Դ����
	 */
	private String bl_rs_code;

	public static final String BL_RS_CODECN = "����һ����Դ����";

	/**
	 * @return bl_rs_code
	 */
	public String getBl_rs_code() {
		return this.bl_rs_code;
	}

	/**
	 * @param bl_rs_code
	 */
	public void setBl_rs_code(String bl_rs_code) {
		this.bl_rs_code = bl_rs_code;
	}
}
