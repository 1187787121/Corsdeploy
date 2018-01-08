/**
 * Title: DeleteSrvInputBean.java
 * File Description: 删除服务输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:删除服务输入接口
 * @author tlw
 */
public class DeleteSrvInputBean
		extends ActionRootInputBean {
	private static final long serialVersionUID = 7279412003631492820L;

	/**
	 * 服务名称
	 */
	private String srv_name;
	
	public static final String SRV_NAMECN = "服务名称";

	/**
	 * @return srv_name 服务名称
	 */
	public String getSrv_name() {
		return srv_name;
	}

	/**
	 * @param srv_name 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}
}
