/**
 * Title: PageUnhandleWorkByUserIdOutputBean.java
 * File Description:查询需待复核或者待授权的任务输出接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-7
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;

/**
 * Class Description:查询需待复核或者待授权的任务输出接口 
 * @author tlw
 */
public class PageUnhandleWorkOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = 4474576633953079825L;

	/**
	 * 待处理任务列表
	 */
	private List<WkDealStateInfo> unhandle_work_list;

	public static final String UN_HANDLE_WORK_LISTCN = "待处理任务列表";

	/**
	 * @return unhandle_work_list 待处理任务列表
	 */
	public List<WkDealStateInfo> getUnhandle_work_list() {
		return unhandle_work_list;
	}

	/**
	 * @param unhandle_work_list 待处理任务列表
	 */
	public void setUnhandle_work_list(List<WkDealStateInfo> unhandle_work_list) {
		this.unhandle_work_list = unhandle_work_list;
	}

}
