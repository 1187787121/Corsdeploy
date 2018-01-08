/**
 * Title: DeleteSocInputBean.java
 * File Description: 删除数据源输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:删除数据源输入接口
 * @author link
 */
public class DeleteSocInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 5632344738472012845L;
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
