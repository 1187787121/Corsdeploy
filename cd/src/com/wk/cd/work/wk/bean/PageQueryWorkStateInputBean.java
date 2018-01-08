/**
 * Title: PageQueryWorkStateInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: lijie
 * @version: 1.0
 * @date: 2015-3-30
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;
import com.wk.cd.enu.QUERY_TYPE;

/**
 * Class Description: 分页查询任务状态的输入接口
 * @author lijie
 */
public class PageQueryWorkStateInputBean extends PageQueryActionRootInputBean {
	private static final long serialVersionUID = -3275196528610099547L;

	/**
	 * 查询类型
	 */
	private QUERY_TYPE query_type;

	public static final String QUERY_TYPECN = "查询类型";

	/**
	 * @return query_type 查询类型
	 */
	public QUERY_TYPE getQuery_type() {
		return query_type;
	}

	/**
	 * @param query_type 查询类型
	 */
	public void setQuery_type(QUERY_TYPE query_type) {
		this.query_type = query_type;
	}

	
}
