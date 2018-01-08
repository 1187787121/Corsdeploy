/**
 * Title: PageLogByLabelOutputBean.java
 * File Description: 按照标记级别分页查询日志信息输出接口 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.lg.info.LgLogMfInfo;

/**
 * Class Description: 按照标记级别分页查询日志信息输出接口
 * @author tlw
 */
public class PageLogByLabelOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = 5864813983794692066L;

	/**
	 * 日志信息列表
	 */
	private List<LgLogMfInfo> log_list;

	public static final String LOG_LISTCN = "日志信息列表";

	/**
	 * @return log_list 日志信息列表
	 */
	public List<LgLogMfInfo> getLog_list() {
		return log_list;
	}

	/**
	 * @param log_list 日志信息列表
	 */
	public void setLog_list(List<LgLogMfInfo> log_list) {
		this.log_list = log_list;
	}
}
