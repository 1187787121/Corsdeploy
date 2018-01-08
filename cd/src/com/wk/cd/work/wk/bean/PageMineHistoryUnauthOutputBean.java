/**
 * Title: PageMineHistoryUnauthOutputBean.java
 * File Description: 分页查询我的历史授权任务列表输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月10日
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 分页查询我的历史授权任务列表输出接口
 * @author Xul
 */
public class PageMineHistoryUnauthOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 2161833774201279902L;
	
	/**
	 * 授权历史任务列表
	 */
	private List<HistoryWorkBean> unauth_history_list;
	
	public static final String UNAUTH_HISTORY_LISTCN = "授权历史任务列表";

	/**
	 * @return unauth_history_list 授权历史任务列表
	 */
	public List<HistoryWorkBean> getUnauth_history_list() {
		return unauth_history_list;
	}

	/**
	 * @param unauth_history_list 授权历史任务列表
	 */
	public void setUnauth_history_list(List<HistoryWorkBean> unauth_history_list) {
		this.unauth_history_list = unauth_history_list;
	}
	
}
