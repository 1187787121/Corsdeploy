/**
 * Title: ListSubOptInputBean.java
 * File Description: ��ѯ��Դ���¼������б�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��5��27��
 */
package com.wk.cd.system.rs.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * @author HT
 */
public class ListSubOptInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2727759107327556362L;
	
	/**
	 * ��Դ����
	 */
	private String rs_code; 
	
	public static final String RS_CODECN="��Դ����";

	/**
	 * @return rs_code ��Դ����
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/**
	 * @param rs_code ��Դ����
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}
	
}
