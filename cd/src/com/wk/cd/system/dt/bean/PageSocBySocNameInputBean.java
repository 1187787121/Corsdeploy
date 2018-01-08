/**
 * Title: PageSocBySocNameInputBean.java
 * File Description: 根据用户名模糊查询数据源信息输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: 根据用户名模糊查询数据源信息输入接口
 * @author link
 */
public class PageSocBySocNameInputBean
		extends PageQueryActionRootInputBean {

	private static final long serialVersionUID = -8759963576934684987L;
	private String soc_name;
	public static final String SOC_NAME = "数据源名称";

	/**
	 * @return soc_name 数据源名称
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 * @param soc_name 数据源名称
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

}
