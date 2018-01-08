/**
 * Title: SvSrvCheckInfo.java
 * File Description: 服务复核定义表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.ap.info;

import java.io.Serializable;

import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.SUPER_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:服务复核定义表
 * @author AutoGen
 */
@Table("SV_SRV_CHECK")
@PrimaryKey({"check_dept_id","srv_name","check_seq"})
public class SvSrvCheckInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "服务复核定义表";

	/**
	 * 配置部门编码
	 */
	private String check_dept_id;
	
	public static final String CHECK_DEPT_IDCN = "配置部门编码";
	
	/**
	 *服务名称
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "服务名称";

	/**
	 *复核序号
	 */
	private int check_seq;

	public static final String CHECK_SEQCN = "复核序号";
	
	/**
	 *审批类别
	 */
	private APROV_CATEGORY chk_aprov_category;

	public static final String CHK_APROV_CATEGORYCN = "审批类别";

	/**
	 *复核部门角色
	 */
	private String chk_dprl_code;

	public static final String CHK_DPRL_CODECN = "复核部门角色";
	
	/**
	 * 角色描述
	 */
	private String role_cn_name;
	
	public static final String ROLE_CN_NAMECN = "角色描述";
	
	/**
	 * 是否上级配置
	 */
	private SUPER_FLAG super_flag;
	
	public static final String SUPER_FLAGCN = "是否上级配置";

	/**
	 * @return check_dept_id 配置部门编码
	 */
	public String getCheck_dept_id() {
		return this.check_dept_id;
	}

	/**
	 * @param check_dept_id 配置部门编码
	 */
	public void setCheck_dept_id(String check_dept_id) {
		this.check_dept_id = check_dept_id;
	}
	
	/**
	 *@return srv_name 服务名称
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 *@param srv_name 服务名称
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 *@return check_seq 复核序号
	 */
	public int getCheck_seq() {
		return this.check_seq;
	}

	/**
	 *@param check_seq 复核序号
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
	 *@return chk_dprl_code 复核部门角色
	 */
	public String getChk_dprl_code() {
		return this.chk_dprl_code;
	}

	/**
	 *@param chk_dprl_code 复核部门角色
	 */
	public void setChk_dprl_code(String chk_dprl_code) {
		this.chk_dprl_code = chk_dprl_code;
	}

	/**
	 *@return role_cn_name 角色描述
	 */
	public String getRole_cn_name() {
		return this.role_cn_name;
	}

	/**
	 *@param role_cn_name 角色描述
	 */
	public void setRole_cn_name(String role_cn_name) {
		this.role_cn_name = role_cn_name;
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
