/**
 * Title: QueryTermByTermNoInputBean.java
 * File Description: �����ն˺Ų�ѯ�ն���Ϣ������Ϣ
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-22
 */
package com.wk.cd.system.tm.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �����ն˺Ų�ѯ�ն���Ϣ������Ϣ
 * @author tlw
 */
public class QueryTermByTermNoInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 5484144925937579015L;

	/**
	 * �ն˺�
	 */
	private String term_no;

	public static final String TERM_NOCN = "�ն˺�";

	/**
	 * @return term_no �ն˺�
	 */
	public String getTerm_no() {
		return term_no;
	}

	/**
	 * @param term_no �ն˺�
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

}
