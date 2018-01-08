/**
 * Title: PageAllSocInputBean.java
 * File Description: 分页查询所有数据源信息输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description:分页查询所有数据源信息输入接口
 * @author link
 */
public class PageAllSocInputBean
		extends PageQueryActionRootInputBean {

	private static final long serialVersionUID = -3943098295949353270L;
	private String soc_name;
	public static final String SOC_NAMECN = "数据源名称";

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
