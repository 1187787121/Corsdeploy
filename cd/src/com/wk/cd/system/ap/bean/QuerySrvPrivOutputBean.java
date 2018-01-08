/**
 * Title: QuerySrvPrivOutputBean.java
 * File Description: 查询服务权限信息输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-5
 */
package com.wk.cd.system.ap.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.sv.info.SvSrvInfo;

/**
 * Class Description: 查询服务权限信息输出接口
 * @author tlw
 */
public class QuerySrvPrivOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 2734705804455348829L;

	/**
	 * 服务定义信息
	 */
	private SvSrvInfo srv_info;

	public static final String SRV_INFOCN = "服务定义信息";

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
	 * @return srv_info 服务定义信息
	 */
	public SvSrvInfo getSrv_info() {
		return this.srv_info;
	}

	/**
	 * @param srvInfo 服务定义信息
	 */
	public void setSrv_info(SvSrvInfo srv_info) {
		this.srv_info = srv_info;
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
