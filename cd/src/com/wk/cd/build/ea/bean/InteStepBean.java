/**
 * Title: InteStepBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年12月20日
 */
package com.wk.cd.build.ea.bean;

/**
 * Class Description: 
 * @author Administrator
 */
public class InteStepBean {

	/**
	 * 步骤编号
	 */
	private int step_id;
	
	public static final String STEP_IDCN = "步骤编号";
	
	/**
	 * 集成步骤详细信息
	 */
	private EnvProgStepBean prog_step_bean;
	
	public static final String PROG_STEP_BEANCN = "集成步骤详细信息";

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
