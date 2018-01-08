/**
 * Title: QuerySrvByNameInputBean.java
 * File Description:按照服务名查询服务信息输入接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 按照服务名查询服务信息输入接口
 * @author tlw
 */
public class QuerySrvByNameInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 9002207012698991434L;

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
	 * @param srvName 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

}
