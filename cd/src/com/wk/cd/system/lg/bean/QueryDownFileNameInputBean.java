/**
 * Title: QueryDownFileNameInputBean.java
 * File Description: 查询生成日志文件信息输入接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.util.JaDate;

/**
 * Class Description: 查询生成日志文件信息输入接口
 * @author tlw
 */
public class QueryDownFileNameInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2173979950773173406L;

	/**
	 * 生成日志日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "生成日志日期";

	/**
	 * @return crt_bk_date 生成日志日期
	 */
	public JaDate getCrt_bk_date() {
		return crt_bk_date;
	}

	/**
	 * @param crt_bk_date 生成日志日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

}
