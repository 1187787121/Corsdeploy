/**
 * Title: InteStepBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��12��20��
 */
package com.wk.cd.build.ea.bean;

/**
 * Class Description: 
 * @author Administrator
 */
public class InteStepBean {

	/**
	 * ������
	 */
	private int step_id;
	
	public static final String STEP_IDCN = "������";
	
	/**
	 * ���ɲ�����ϸ��Ϣ
	 */
	private EnvProgStepBean prog_step_bean;
	
	public static final String PROG_STEP_BEANCN = "���ɲ�����ϸ��Ϣ";

	/**
	 * @return step_id
	 */
	public int getStep_id() {
		return step_id;
	}

	/**
	 * @param step_id
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	/**
	 * @return prog_step_bean
	 */
	public EnvProgStepBean getProg_step_bean() {
		return prog_step_bean;
	}

	/**
	 * @param prog_step_bean
	 */
	public void setProg_step_bean(EnvProgStepBean prog_step_bean) {
		this.prog_step_bean = prog_step_bean;
	}
}
