/**
 * Title: PageLogByLabelInputBean.java
 * File Description:按照标记级别分页查询日志信息输入接口 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: 按照标记级别分页查询日志信息输入接口
 * @author tlw
 */
public class PageLogByLabelInputBean
		extends PageQueryActionRootInputBean {

	private static final long serialVersionUID = 6834996738239179795L;

	/**
	 * 日志标记
	 */
	private int log_label;

	public static final String LOG_LABELCN = "日志标记";

	/**
	 * @return log_label 日志标记
	 */
	public int getLog_label() {
		return log_label;
	}

	/**
	 * @param log_label 日志标记
	 */
	public void setLog_label(int log_label) {
		this.log_label = log_label;
	}

}
