/**
 * Title: ListResInputBean.java
 * File Description: ����������ܵ���Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-12-3
 */
package com.wk.cd.system.rs.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ����������ܵ���Ϣ����ӿ�
 * @author xuy
 */
public class ListResInputBean extends ActionRootInputBean {
	
	private static final long serialVersionUID = -4630403627754299778L;
	private String rs_code;
	public static final String RS_CODECN = "��Դ����";
	
	/**
	 * @return rs_code ��Դ����
	 */
	public String getRs_code() {
		return rs_code;
	}
	/**
	 * @param rsCode ��Դ����
	 */
	public void setRs_code(String rsCode) {
		rs_code = rsCode;
	}

	
}
