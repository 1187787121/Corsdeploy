/**
 * Title: QueryWorkByWorkCodeInputBean.java
 * File Description:������������ѯ������Ϣ����ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:������������ѯ������Ϣ����ӿ�
 * @author tlw
 */
public class QueryWorkByWorkCodeInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -1417113656736449223L;

	/**
	 * �������
	 */
	private String work_code;

	public static final String WORK_CODECN = "�������";

	/**
	 * @return work_code �������
	 */
	public String getWork_code() {
		return work_code;
	}

	/**
	 * @param work_code �������
	 */
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
}
