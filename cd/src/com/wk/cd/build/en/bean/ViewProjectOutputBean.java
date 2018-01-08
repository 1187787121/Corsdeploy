/**
 * Title: ViewProjectOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class ViewProjectOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -854093483827393003L;
	
	/**
	 * 项目信息
	 */
    private CeProjectBean proj_bean;
    
    public static final String PROJ_BEANCN = "项目信息";
    
    /**
     * 项目信息列表
     */
    private List<CeProjectBean> proj_list;

    public static final String PROJ_LISTCN = "项目信息列表";
    
    /**
     * 任务列表
     */
    private List<EnvTaskBean> task_info_list;
    
    public static final String TASK_INFO_LISTCN = "项目信息列表";
    
	/**
	 * @return proj_bean 项目信息
	 */
	public CeProjectBean getProj_bean() {
		return this.proj_bean;
	}

	/**
	 * @param proj_bean 项目信息
	 */
	public void setProj_bean(CeProjectBean proj_bean) {
		this.proj_bean = proj_bean;
	}

	/**
	 * @return proj_list 项目信息列表
	 */
	public List<CeProjectBean> getProj_list() {
		return proj_list;
	}

	/**
	 * @param proj_list 项目信息列表
	 */
	public void setProj_list(List<CeProjectBean> proj_list) {
		this.proj_list = proj_list;
	}

	/**
	 * @return task_info_list 任务列表
	 */
	public List<EnvTaskBean> getTask_info_list() {
		return task_info_list;
	}

	/**
	 * @param task_info_list 任务列表
	 */
	public void setTask_info_list(List<EnvTaskBean> task_info_list) {
		this.task_info_list = task_info_list;
	}
}
