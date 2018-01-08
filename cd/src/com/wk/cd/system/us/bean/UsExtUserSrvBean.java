/**
 * Title: UsExtUserSrvInfo.java
 * File Description: 用户服务权限扩展信息表
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-13
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.enu.PRIV_TYPE;

/**
 * Class Description:用户服务权限扩展信息表
 * @author link
 */
public class UsExtUserSrvBean {

	/**
	 * 表名称
	 */
	public static final String TABLECN = "用户服务权限扩展信息表";

	/**
	 * 服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

	/**
	 * 服务类型
	 */
	private PRIV_TYPE srv_type;

	public static final String SRV_TYPECN = "服务类型";

	/**
	 * 服务类型
	 */
	private PRIV_TYPE srv_bk_desc;

	public static final String SRV_BK_DESCCN = "服务描述";

	/**
	 * @return srv_name 服务名称
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 * @param srv_name 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 * @return srv_type 服务类型
	 */
	public PRIV_TYPE getSrv_type() {
		return this.srv_type;
	}

	/**
	 * @param srv_type 服务类型
	 */
	public void setSrv_type(PRIV_TYPE srv_type) {
		this.srv_type = srv_type;
	}

	/**
	 * @return srv_bk_desc 服务描述
	 */
	public PRIV_TYPE getSrv_bk_desc() {
		return this.srv_bk_desc;
	}

	/**
	 * @param srv_bk_desc 服务描述
	 */
	public void setSrv_bk_desc(PRIV_TYPE srv_bk_desc) {
		this.srv_bk_desc = srv_bk_desc;
	}

}
