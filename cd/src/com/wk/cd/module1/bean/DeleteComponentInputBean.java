/**
 * Title: DeleteTpComponentInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��18��
 */
package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:
 * @author yangl
 */
public class DeleteComponentInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5134527483756364361L;

	/**
	 * ���ID
	 */
	private String id;

	public static final String COMP_IDCN = "���ID";

	/**
	 * @return comp_id ���ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id ���ID
	 */
	public void setId(String id) {
		this.id = id;
	}

}