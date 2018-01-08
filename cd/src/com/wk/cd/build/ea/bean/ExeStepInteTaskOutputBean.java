/**
 * Title: ExeStepInteTaskOutputBean.java
 * File Description: 单步执行集成任务输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 单步执行集成任务输出接口
 * @author Xul
 */
public class ExeStepInteTaskOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 194580003728811726L;
	
	/**
	 * 下一步骤号
	 */
	private int next_step;
	
	public static final String NEXT_STEPCN = "下一步骤号";

	/**
	 * @return next_step 下一步骤号
	 */
	public int getNext_step() {
		return next_step;
	}

	/**
	 * @param next_step 下一步骤号
	 */
	public void setNext_step(int next_step) {
		this.next_step = next_step;
	}
}
