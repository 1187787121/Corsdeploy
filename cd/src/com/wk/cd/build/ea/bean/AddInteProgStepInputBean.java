/**
 * Title: AddInteProgStepInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��11��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author Administrator
 */
public class AddInteProgStepInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -6340661296055105892L;
	
	/**
	 * �������
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "�������";
	
	/**
	 * ���ɲ�����Ϣ
	 */
	private List<InteStepBean> step_list;
	
	public static final String  STEP_LISTCN= "���ɲ�����Ϣ";

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

	/**
	 * @return step_list
	 */
	public List<InteStepBean> getStep_list() {
		return step_list;
	}

	/**
	 * @param step_list
	 */
	public void setStep_list(List<InteStepBean> step_list) {
		this.step_list = step_list;
	}
}
