/**
 * Title: DeleteEnvProgInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��10��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author Administrator
 */
public class DeleteEnvProgInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4457471145090195949L;

	/**
	 * �������
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "�������";

	/**
	 * @return prog_id �������
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
}
