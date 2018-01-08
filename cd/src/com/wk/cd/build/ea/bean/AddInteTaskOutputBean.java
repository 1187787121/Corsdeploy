/**
 * Title: AddInteTaskOutputBean.java
 * File Description: 保存集成任务输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 保存集成任务输出接口
 * @author Xul
 */
public class AddInteTaskOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -1098820849834689686L;
	
	/**
	 * 任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";
	
	/**
	 * 下一步骤号
	 */
	private int next_step;
	
	public static final String NEXT_STEPCN = "下一步骤号";

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
