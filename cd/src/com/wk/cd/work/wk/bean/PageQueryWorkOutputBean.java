/**
 * Title: PageQueryOutputBean.java
 * File Description:分页查询输出接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.work.wk.info.WkWorkInfo;

/**
 * Class Description:分页查询输出接口
 * @author tlw
 */
public class PageQueryWorkOutputBean
		extends PageQueryActionRootOutputBean {
	private static final long serialVersionUID = 7262731081927339838L;

	/**
	 * 任务列表
	 */
	private List<WkWorkInfo> work_list;
	
	public static final String WORK_LISTCN = "任务列表";

	/**
	 * @return work_list 任务列表
	 */
	public List<WkWorkInfo> getWork_list() {
		return work_list;
	}

	/**
	 * @param work_list 任务列表
	 */
	public void setWork_list(List<WkWorkInfo> work_list) {
		this.work_list = work_list;
	}
}
