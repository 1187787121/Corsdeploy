/**
 * Title: ExeRemainInteTaskInputBean.java
 * File Description: 一键执行余下集成步骤输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 一键执行余下集成步骤输入接口
 * @author Xul
 */
public class ExeRemainInteTaskInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 7078704077927981861L;
	
	/**
	 * 任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";
	
	/**
	 * 步骤编号
	 */
	private int step_id;

	public static final String STEP_IDCN = "步骤编号";
	
	/**
	 * @return work_id 任务编号
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 * @return step_id 步骤编号
	 */
	public int getStep_id() {
		return step_id;
	}

	/**
	 * @param step_id 步骤编号
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}
}
