/**
 * Title: RsOptPrivBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年5月27日
 */
package com.wk.cd.system.rs.bean;

import java.io.Serializable;

import com.wk.cd.enu.PRIV_FLAG;

/**
 * @author HT
 */
public class RsOptPrivBean implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -7255105461315197615L;
	
	/**
	 *操作编码
	 */
	private String opt_code;

	public static final String OPT_CODECN = "操作编码";

	/**
	 *操作名称
	 */
	private String opt_name;

	public static final String OPT_NAMECN = "操作名称";

	/**
	 *操作说明
	 */
	private String opt_bk_expl;

	public static final String OPT_BK_EXPLCN = "操作说明";


	/**
	 *禁用表达式
	 */
	private String dis_express;

	public static final String DIS_EXPRESSCN = "禁用表达式";


	/**
	 *权限标示
	 */
	private PRIV_FLAG priv_flag;

	public static final String PRIV_FLAGCN = "权限标示";

	/**
	 * @return opt_name
	 */
	public String getOpt_name() {
		return this.opt_name;
	}

	/**
	 * @param opt_name
	 */
	public void setOpt_name(String opt_name) {
		this.opt_name = opt_name;
	}

	/**
	 * @return opt_bk_expl
	 */
	public String getOpt_bk_expl() {
		return this.opt_bk_expl;
	}

	/**
	 * @param opt_bk_expl
	 */
	public void setOpt_bk_expl(String opt_bk_expl) {
		this.opt_bk_expl = opt_bk_expl;
	}

	
	/**
	 * @return dis_express
	 */
	public String getDis_express() {
		return this.dis_express;
	}

	/**
	 * @param dis_express
	 */
	public void setDis_express(String dis_express) {
		this.dis_express = dis_express;
	}

	/**
	 * @return priv_flag
	 */
	public PRIV_FLAG getPriv_flag() {
		return this.priv_flag;
	}

	/**
	 * @param priv_flag
	 */
	public void setPriv_flag(PRIV_FLAG priv_flag) {
		this.priv_flag = priv_flag;
	}

	/**
	 * @return opt_code
	 */
	public String getOpt_code() {
		return this.opt_code;
	}

	/**
	 * @param opt_code
	 */
	public void setOpt_code(String opt_code) {
		this.opt_code = opt_code;
	}
	
}
