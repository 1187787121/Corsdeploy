/**
 * Title: AddInteProgStepOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��11��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author Administrator
 */
public class AddInteProgStepOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 5359261971488927724L;
	
	/**
	 * ��һ������
	 */
	private int next_step_id;
	
	public static final String NEXT_STEP_IDCN = "��һ������";

	/**
	 * @return next_step_id ��һ������
	 */
	public int getNext_step_id() {
		return next_step_id;
	}

	/**
	 * @param next_step_id ��һ������
	 */
	public void setNext_step_id(int next_step_id) {
		this.next_step_id = next_step_id;
	}

}
