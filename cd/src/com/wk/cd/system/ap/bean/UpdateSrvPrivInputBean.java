/**
 * Title: UpdateSrvPrivInputBean.java
 * File Description:修改服务复核、授权定义输入接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.ap.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:修改服务复核、授权定义输入接口
 * @author tlw
 */
public class UpdateSrvPrivInputBean
		extends ActionRootInputBean {
	private static final long serialVersionUID = -1265361449245139534L;

	/**
	 * 服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

	/**
	 * 复核部门角色列表
	 */
	private List<ChkDprlCodeBean> srv_check_list;

	public static final String SRV_CHECK_LISTCN = "复核部门角色列表";

	/**
	 * 授权部门角色列表
	 */
	private List<AuthDprlCodeBean> srv_auth_list;

	public static final String SRV_AUTH_LISTCN = "授权部门角色列表";

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

	/**
	 * @return srv_check_list 复核角色列表
	 */
	public List<ChkDprlCodeBean> getSrv_check_list() {
		return srv_check_list;
	}

	/**
	 * @param srv_check_list 复核角色列表
	 */
	public void setSrv_check_list(List<ChkDprlCodeBean> srv_check_list) {
		this.srv_check_list = srv_check_list;
	}

	/**
	 * @return srv_auth_list 授权信息列表
	 */
	public List<AuthDprlCodeBean> getSrv_auth_list() {
		return srv_auth_list;
	}

	/**
	 * @param srv_auth_list 授权信息列表
	 */
	public void setSrv_auth_list(List<AuthDprlCodeBean> srv_auth_list) {
		this.srv_auth_list = srv_auth_list;
	}
}
