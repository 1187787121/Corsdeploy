/**
 * Title: QueryDownFileNameOutputBean.java
 * File Description: 查询生成日志文件信息输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.lg.info.LgLogDownInfo;

/**
 * Class Description: 查询生成日志文件信息输出接口
 * @author tlw
 */
public class QueryDownFileNameOutputBean
		extends ActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -3708077262944592833L;

	/**
	 * 日志下载文件信息
	 */
	private List<LgLogDownInfo> log_down_list;

	public static final String LOG_DOWN_LISTCN = "日志下载文件信息";

	/**
	 * @return log_down_list 日志下载文件信息
	 */
	public List<LgLogDownInfo> getLog_down_list() {
		return log_down_list;
	}

	/**
	 * @param log_down_list 日志下载文件信息
	 */
	public void setLog_down_list(List<LgLogDownInfo> log_down_list) {
		this.log_down_list = log_down_list;
	}

}
