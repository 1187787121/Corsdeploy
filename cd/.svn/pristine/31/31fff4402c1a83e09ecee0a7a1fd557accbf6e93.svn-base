/**
 * Title: PageMineUncheckWorkOutputBean.java
 * File Description: 查询由我复核的任务列表输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月9日
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 查询由我复核的任务列表输出接口
 * @author Xul
 */
public class QueryMineUncheckWorkOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -4085973651649318161L;
	
	 /**
     * 已复核任务列表
     */
    private List<PendWorkBean> uncheck_work_list;

    public static final String UNCHECK_WORK_LISTCN = "已复核任务列表";
    
    /**
     * 待复核任务列表
     */
    private List<PendWorkBean> executory_uncheck_list;
    
    public static final String EXECUTORY_UNCHECK_LISTCN = "待复核任务列表";
    
    /**
     * 待复核任务个数
     */
    private int executory_count;

    public static final String EXECUTORY_COUNTCN = "待复核任务个数";

	/**
	 * @return uncheck_work_list 已复核任务列表
	 */
	public List<PendWorkBean> getUncheck_work_list() {
		return uncheck_work_list;
	}

	/**
	 * @param uncheck_work_list 已复核任务列表
	 */
	public void setUncheck_work_list(List<PendWorkBean> uncheck_work_list) {
		this.uncheck_work_list = uncheck_work_list;
	}

	/**
	 * @return executory_uncheck_list 待复核任务列表
	 */
	public List<PendWorkBean> getExecutory_uncheck_list() {
		return executory_uncheck_list;
	}

	/**
	 * @param executory_uncheck_list 待复核任务列表
	 */
	public void setExecutory_uncheck_list(List<PendWorkBean> executory_uncheck_list) {
		this.executory_uncheck_list = executory_uncheck_list;
	}

	/**
	 * @return executory_count 待复核任务个数
	 */
	public int getExecutory_count() {
		return executory_count;
	}

	/**
	 * @param executory_count 待复核任务个数
	 */
	public void setExecutory_count(int executory_count) {
		this.executory_count = executory_count;
	}
    
}
