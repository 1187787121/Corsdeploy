/**
 * Title: PageTermOutputBean.java
 * File Description: 查看终端输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-22
 */
package com.wk.cd.system.tm.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.tm.info.TmTermInfo;

/**
 * Class Description: 查看终端输出接口
 * @author tlw
 */
public class PageTermOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = -9003583143851937210L;

	private List<TmTermInfo> term_list;

	public static final String TERM_LISTCN = "终端信息列表";

	/**
	 * @return term_list 终端信息列表
	 */
	public List<TmTermInfo> getTerm_list() {
		return term_list;
	}

	/**
	 * @param term_list 终端信息列表
	 */
	public void setTerm_list(List<TmTermInfo> term_list) {
		this.term_list = term_list;
	}

}
