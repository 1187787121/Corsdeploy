/**
 * Title: UpdateTermInputBean.java
 * File Description: 修改终端输入接口 
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
 * Class Description: 修改终端输入接口
 * @author tlw
 */
public class UpdateTermInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -3874020857406254468L;

	/**
	 * 终端号
	 */
	private String term_no;

	public static final String TERM_NOCN = "终端号";

	/**
	 * 终端类型
	 */
	private TERM_TYPE term_type;

	public static final String TERM_TYPECN = "终端类型";

	/**
	 * 终端说明
	 */
	private String term_bk_desc;

	public static final String TERM_BK_DESCCN = "终端说明";

	/**
	 * @return term_no 终端号
	 */
	public String getTerm_no() {
		return term_no;
	}

	/**
	 * @param term_no 终端号
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	/**
	 * @return term_type 终端类型
	 */
	public TERM_TYPE getTerm_type() {
		return term_type;
	}

	/**
	 * @param term_type 终端类型
	 */
	public void setTerm_type(TERM_TYPE term_type) {
		this.term_type = term_type;
	}

	/**
	 * @return term_bk_desc 终端说明
	 */
	public String getTerm_bk_desc() {
		return term_bk_desc;
	}

	/**
	 * @param term_bk_desc 终端说明
	 */
	public void setTerm_bk_desc(String term_bk_desc) {
		this.term_bk_desc = term_bk_desc;
	}
}
