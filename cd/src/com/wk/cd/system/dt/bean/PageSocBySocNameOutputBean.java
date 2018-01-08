/**
 * Title: PageSocBySocNameOutputBean.java
 * File Description: 根据用户名模糊查询数据源信息输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.dt.info.DtSourceInfo;

/**
 * Class Description: 根据用户名模糊查询数据源信息输出接口
 * @author link
 */
public class PageSocBySocNameOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = -312040159205583532L;
	private List<DtSourceInfo> dt_source_list;
	public static final String DT_SOURCE_LIST = "数据源信息列表";

	/**
	 * @return dt_source_list 数据源信息列表
	 */
	public List<DtSourceInfo> getDt_source_list() {
		return this.dt_source_list;
	}

	/**
	 * @param dt_source_list 数据源信息列表
	 */
	public void setDt_source_list(List<DtSourceInfo> dt_source_list) {
		this.dt_source_list = dt_source_list;
	}

}
