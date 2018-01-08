/**
 * Title: CompleteBuildTaskInputBean.java
 * File Description: 完成环境构建任务接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016年12月13日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 完成环境构建任务接口
 * @author wangj
 */
public class CompleteBuildTaskInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 2548992575335459340L;
	
	/**
	 * 任务编号
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "任务编号";

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

}
