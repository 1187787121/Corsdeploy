/**
 * Title: ExeBuildDeployInputBean.java
 * File Description: 一键执行构建应用部署输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年12月12日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 一键执行构建应用部署输入接口
 * @author Xul
 */
public class ExeBuildDeployInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -5537669123662300955L;
	
	/**
	 * 任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";
	
	/**
	 * 阶段编号
	 */
	private int phase_id;

	public static final String PHASE_IDCN = "阶段编号";
	
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
	 * @return phase_id 阶段编号
	 */
	public int getPhase_id() {
		return phase_id;
	}

	/**
	 * @param phase_id 阶段编号
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}
}
