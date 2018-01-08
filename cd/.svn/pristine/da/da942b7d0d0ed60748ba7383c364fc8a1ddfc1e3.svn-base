/**
 * Title: ApproveServiceBean.java
 * File Description: 用户查询所有可以配置审批流程的服务输出接口
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tianlw
 * @version: 1.0
 * @date: 2015-8-25
 */
package com.wk.cd.system.ap.bean;

import java.io.Serializable;

import com.wk.cd.enu.AUTH_FLAG;
import com.wk.cd.enu.CHECK_FLAG;
import com.wk.cd.enu.FUN_TYPE;

/**
 * Class Description: 用户查询所有可以配置审批流程的服务输入接口
 * @author tianlw
 */
public class ApproveServiceBean implements Serializable{

	private static final long serialVersionUID = 5257317660370014351L;

	/**
	 * 服务名称
	 */
	private String srv_name;
	
	public static final String SRV_NAMECN = "服务名称";
	
	/**
	 * 服务描述
	 */
	private String srv_bk_desc;
	
	public static final String SRV_BK_DESCCN = "服务描述";
	
	/**
	 * 是否复核
	 */
	private CHECK_FLAG check_flag;
	
	public static final String CHECK_FLAGCN = "是否复核";
	
	/**
	 * 是否授权
	 */
	private AUTH_FLAG auth_flag;
	
	public static final String AUTH_FLAGCN = "是否授权";
	
	/**
	 * 服务类型
	 */
	private FUN_TYPE srv_fun_type;
	
	public static final String SRV_FUN_TYPECN = "服务类型";

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
	 * @return srv_bk_desc 服务描述
	 */
	public String getSrv_bk_desc() {
		return this.srv_bk_desc;
	}

	/**
	 * @param srv_bk_desc 服务描述
	 */
	public void setSrv_bk_desc(String srv_bk_desc) {
		this.srv_bk_desc = srv_bk_desc;
	}

	/**
	 * @return check_flag 是否复核
	 */
	public CHECK_FLAG getCheck_flag() {
		return this.check_flag;
	}

	/**
	 * @param check_flag 是否复核
	 */
	public void setCheck_flag(CHECK_FLAG check_flag) {
		this.check_flag = check_flag;
	}
	
	/**
	 * @return auth_flag 是否授权
	 */
	public AUTH_FLAG getAuth_flag() {
		return this.auth_flag;
	}

	/**
	 * @param auth_flag 是否授权
	 */
	public void setAuth_flag(AUTH_FLAG auth_flag) {
		this.auth_flag = auth_flag;
	}

	/**
	 * @return srv_fun_type 服务类型
	 */
	public FUN_TYPE getSrv_fun_type() {
		return this.srv_fun_type;
	}

	/**
	 * @param srv_fun_type 服务类型
	 */
	public void setSrv_fun_type(FUN_TYPE srv_fun_type) {
		this.srv_fun_type = srv_fun_type;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "ApproveServiceBean [srv_name=" + srv_name + ", srv_bk_desc=" + srv_bk_desc + ", check_flag=" + check_flag + ", auth_flag=" + auth_flag + ", srv_fun_type="
				+ srv_fun_type + "]";
	}
	
	
}
