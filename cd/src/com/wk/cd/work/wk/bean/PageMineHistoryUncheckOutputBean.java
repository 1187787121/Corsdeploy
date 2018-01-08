/**
 * Title: PageMineHistoryUncheckOutputBean.java
 * File Description: 分页查询我的历史复核任务列表输出接口
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
 * Class Description: 分页查询我的历史复核任务列表输出接口
 * @author Xul
 */
public class PageMineHistoryUncheckOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 2482925578403448572L;
	
	/**
	 * 复核历史任务列表
	 */
	private List<HistoryWorkBean> uncheck_history_list;
	
	public static final String UNCHECK_HISTORY_LISTCN = "复核历史任务列表";

	/**
	 * @return uncheck_history_list 复核历史任务列表
	 */
	public List<HistoryWorkBean> getUncheck_history_list() {
		return uncheck_history_list;
	}

	/**
	 * @param uncheck_history_list 复核历史任务列表
	 */
	public void setUncheck_history_list(List<HistoryWorkBean> uncheck_history_list) {
		this.uncheck_history_list = uncheck_history_list;
	}
	
}
