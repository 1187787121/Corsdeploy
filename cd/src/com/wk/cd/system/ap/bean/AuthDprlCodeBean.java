/**
 * Title: AuthDprlCodeBean.java
 * File Description: 授权列表信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.ap.bean;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.SUPER_FLAG;

/**
 * Class Description: 授权列表信息
 * @author tlw
 */
public class AuthDprlCodeBean {

	/**
	 * 授权类型
	 */
	private AUTH_TYPE auth_type;

	public static final String AUTH_TYPECN = "授权类型";

	/**
	 * 授权部门角色
	 */
	private String auth_dprl_code;

	public static final String AUTH_DPRL_CODECN = "授权部门角色";

	/**
	 * 部门角色名称
	 */
	private String role_cn_name;

	public static final String ROLE_CN_NAMECN = "角色名称";

	/**
	 * 授权序号
	 */
	private int auth_seq;

	public static final String AUTH_SEQCN = "授权序号";

	/**
	 *审批类别
	 */
	private APROV_CATEGORY auth_aprov_category;

	public static final String AUTH_APROV_CATEGORYCN = "审批类别";
	
	/**
	 * 是否上级配置
	 */
	private SUPER_FLAG super_flag;
	
	public static final String SUPER_FLAGCN = "是否上级配置";
	
	/**
	 * @return auth_type 授权类型
	 */
	public AUTH_TYPE getAuth_type() {
		return auth_type;
	}

	/**
	 * @param auth_type 授权类型
	 */
	public void setAuth_type(AUTH_TYPE auth_type) {
		this.auth_type = auth_type;
	}

	/**
	 * @return auth_dprl_code 授权部门角色
	 */
	public String getAuth_dprl_code() {
		return auth_dprl_code;
	}

	/**
	 * @param auth_dprl_code 授权部门角色
	 */
	public void setAuth_dprl_code(String auth_dprl_code) {
		this.auth_dprl_code = auth_dprl_code;
	}

	/**
	 * @return role_cn_name 角色名称
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 * @param role_cn_name 角色名称
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
	}

	/**
	 * @return auth_seq 授权序号
	 */
	public int getAuth_seq() {
		return this.auth_seq;
	}

	/**
	 * @param authSeq 授权序号
	 */
	public void setAuth_seq(int auth_seq) {
		this.auth_seq = auth_seq;
	}

	/**
	 * @return auth_aprov_category 审批类别
	 */
	public APROV_CATEGORY getAuth_aprov_category() {
		return this.auth_aprov_category;
	}

	/**
	 * @param auth_aprov_category 审批类别
	 */
	public void setAuth_aprov_category(APROV_CATEGORY auth_aprov_category) {
		this.auth_aprov_category = auth_aprov_category;
	}
	
	/**
	 * @return 是否上级配置
	 */
	public SUPER_FLAG getSuper_flag() {
		return this.super_flag;
	}

	/**
	 * @param super_flag 是否上级配置
	 */
	public void setSuper_flag(SUPER_FLAG super_flag) {
		this.super_flag = super_flag;
	}

}
