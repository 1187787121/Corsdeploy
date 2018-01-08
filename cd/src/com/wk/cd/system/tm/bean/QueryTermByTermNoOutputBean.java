/**
 * Title: QueryTermByTermOutputBean.java
 * File Description: 根据终端号查询终端信息输出信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.tm.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.tm.info.TmTermInfo;

/**
 * Class Description: 根据终端号查询终端信息输出信息
 * @author tlw
 */
public class QueryTermByTermNoOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = -2021773088975357239L;

	/**
	 * 终端配置信息
	 */
	private TmTermInfo tm_term_info;

	public static String TM_TERM_LISTCN = "终端配置信息";

	/**
	 * @return tm_term_list 终端配置信息
	 */
	public TmTermInfo getTm_term_info() {
		return tm_term_info;
	}

	/**
	 * @param tm_term_list 终端配置信息
	 */
	public void setTm_term_info(TmTermInfo tm_term_info) {
		this.tm_term_info = tm_term_info;
	}

}
