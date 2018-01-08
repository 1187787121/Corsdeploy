/**
 * Title: QueryMineUnauthWorkOutputBean.java
 * File Description: 查询由我授权的任务列表输出接口
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
 * Class Description: 查询由我授权的任务列表输出接口
 * @author Xul
 */
public class QueryMineUnauthWorkOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 8942093334284571968L;
	
	/**
     * 已授权任务列表
     */
    private List<PendWorkBean> unauth_work_list;

    public static final String UNAUTH_WORK_LISTCN = "已授权任务列表";
    
    /**
     * 待授权任务列表
     */
    private List<PendWorkBean> executory_unauth_list;
    
    public static final String EXECUTORY_UNAUTH_LISTCN = "待授权任务列表";
    
    /**
     * 待授权任务个数
     */
    private int executory_count;

    public static final String EXECUTORY_COUNTCN = "待授权任务个数";

	/**
	 * @return unauth_work_list 已授权任务列表
	 */
	public List<PendWorkBean> getUnauth_work_list() {
		return unauth_work_list;
	}

	/**
	 * @param unauth_work_list 已授权任务列表
	 */
	public void setUnauth_work_list(List<PendWorkBean> unauth_work_list) {
		this.unauth_work_list = unauth_work_list;
	}

	/**
	 * @return executory_unauth_list 待授权任务列表
	 */
	public List<PendWorkBean> getExecutory_unauth_list() {
		return executory_unauth_list;
	}

	/**
	 * @param executory_unauth_list 待授权任务列表
	 */
	public void setExecutory_unauth_list(List<PendWorkBean> executory_unauth_list) {
		this.executory_unauth_list = executory_unauth_list;
	}

	/**
	 * @return executory_count 待授权任务个数
	 */
	public int getExecutory_count() {
		return executory_count;
	}

	/**
	 * @param executory_count 待授权任务个数
	 */
	public void setExecutory_count(int executory_count) {
		this.executory_count = executory_count;
	}
	
}
