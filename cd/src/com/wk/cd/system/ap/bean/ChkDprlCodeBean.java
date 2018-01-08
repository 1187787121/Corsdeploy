/**
 * Title: ChkDprlCodeBean.java
 * File Description: 复核部门角色列表信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.ap.bean;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.SUPER_FLAG;

/**
 * Class Description: 复核部门角色列表信息
 * @author tlw
 */
public class ChkDprlCodeBean {

	/**
	 * 复核部门角色
	 */
	private String chk_dprl_code;

	public static final String CHK_DPRL_CODECN = "复核部门角色";

	/**
	 * 部门角色名称
	 */
	private String role_cn_name;

	public static final String ROLE_CN_NAMECN = "角色名称";

	/**
	 * 复核序号
	 */
	private int check_seq;

	public static final String CHECK_SEQCN = "复核序号";

	/**
	 *审批类别
	 */
	private APROV_CATEGORY chk_aprov_category;

	public static final String CHK_APROV_CATEGORYCN = "审批类别";
	
	/**
	 * 是否上级配置
	 */
	private SUPER_FLAG super_flag;
	
	public static final String SUPER_FLAGCN = "是否上级配置";
	
	/**
	 * @return chk_dprl_code 复核部门角色
	 */
	public String getChk_dprl_code() {
		return chk_dprl_code;
	}

	/**
	 * @param chk_dprl_code 复核部门角色
	 */
	public void setChk_dprl_code(String chk_dprl_code) {
		this.chk_dprl_code = chk_dprl_code;
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
	 * @return check_seq 复核序号
	 */
	public int getCheck_seq() {
		return this.check_seq;
	}

	/**
	 * @param checkSeq 复核序号
	 */
	public void setCheck_seq(int check_seq) {
		this.check_seq = check_seq;
	}

	/**
	 * @return chk_aprov_category 审批类别
	 */
	public APROV_CATEGORY getChk_aprov_category() {
		return this.chk_aprov_category;
	}

	/**
	 * @param chk_aprov_category 审批类别
	 */
	public void setChk_aprov_category(APROV_CATEGORY chk_aprov_category) {
		this.chk_aprov_category = chk_aprov_category;
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
