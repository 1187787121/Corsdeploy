/**
 * Title: PageLogInputBean.java
 * File Description: 按照日期分页查询输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.lg.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.util.JaDate;

/**
 * Class Description: 按照日期分页查询输入接口
 * @author tlw
 */
public class PageLogByDateAndLabelInputBean
		extends PageQueryActionRootInputBean {

	private static final long serialVersionUID = -2981699644541678361L;

	/**
	 * 起始日期
	 */
	private JaDate start_bk_date;

	public static final String START_BK_DATECN = "起始日期";

	/**
	 * 截止日期
	 */
	private JaDate end_bk_date;

	public static final String END_BK_DATECN = "截止日期";

	/**
	 * 日志标记
	 */
	private int log_label;

	public static final String LOG_LABELCN = "日志标记";
	
	/**
	 * 日志级别
	 */
	private int log_level;

	public static final String LOG_LEVELCN = "日志级别";

	/**
	 * @return start_date 起始日期
	 */
	public JaDate getStart_bk_date() {
		return this.start_bk_date;
	}

	/**
	 * @param start_date 起始日期
	 */
	public void setStart_bk_date(JaDate start_bk_date) {
		this.start_bk_date = start_bk_date;
	}

	/**
	 * @return end_date 截止日期
	 */
	public JaDate getEnd_bk_date() {
		return this.end_bk_date;
	}

	/**
	 * @param end_date 截止日期
	 */
	public void setEnd_bk_date(JaDate end_bk_date) {
		this.end_bk_date = end_bk_date;
	}

	/**
	 * @return log_label 日志标记
	 */
	public int getLog_label() {
		return this.log_label;
	}

	/**
	 * @param logLabel 日志标记
	 */
	public void setLog_label(int log_label) {
		this.log_label = log_label;
	}

	/**
	 * @return log_level
	 */
	public int getLog_level() {
		return this.log_level;
	}

	/**
	 * @param log_level
	 */
	public void setLog_level(int log_level) {
		this.log_level = log_level;
	}
}
