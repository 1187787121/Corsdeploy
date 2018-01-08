/**
 * Title: PageMineHistoryWorkOutputBean.java
 * File Description: 分页查询我的历史任务列表输出接口
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
 * Class Description: 分页查询我的历史任务列表输出接口
 * @author Xul
 */
public class PageMineHistoryWorkOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 3300015048604139252L;
	
	/**
	 * 我的历史任务列表
	 */
	private List<HistoryWorkBean> mine_history_list;
	
	public static final String MINE_HISTORY_LISTCN = "我的历史任务列表";

	/**
	 * @return mine_history_list 我的历史任务列表
	 */
	public List<HistoryWorkBean> getMine_history_list() {
		return mine_history_list;
	}

	/**
	 * @param mine_history_list 我的历史任务列表
	 */
	public void setMine_history_list(List<HistoryWorkBean> mine_history_list) {
		this.mine_history_list = mine_history_list;
	}
	
	
}
