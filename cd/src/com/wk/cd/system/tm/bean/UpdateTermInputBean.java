/**
 * Title: UpdateTermInputBean.java
 * File Description: �޸��ն�����ӿ� 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-22
 */
package com.wk.cd.system.tm.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.TERM_TYPE;

/**
 * Class Description: �޸��ն�����ӿ�
 * @author tlw
 */
public class UpdateTermInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -3874020857406254468L;

	/**
	 * �ն˺�
	 */
	private String term_no;

	public static final String TERM_NOCN = "�ն˺�";

	/**
	 * �ն�����
	 */
	private TERM_TYPE term_type;

	public static final String TERM_TYPECN = "�ն�����";

	/**
	 * �ն�˵��
	 */
	private String term_bk_desc;

	public static final String TERM_BK_DESCCN = "�ն�˵��";

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

	/**
	 * @return term_type �ն�����
	 */
	public TERM_TYPE getTerm_type() {
		return term_type;
	}

	/**
	 * @param term_type �ն�����
	 */
	public void setTerm_type(TERM_TYPE term_type) {
		this.term_type = term_type;
	}

	/**
	 * @return term_bk_desc �ն�˵��
	 */
	public String getTerm_bk_desc() {
		return term_bk_desc;
	}

	/**
	 * @param term_bk_desc �ն�˵��
	 */
	public void setTerm_bk_desc(String term_bk_desc) {
		this.term_bk_desc = term_bk_desc;
	}
}
