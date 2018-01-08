/**
 * Title: UserRoleRsBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月2日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.system.rs.info.RsResInfo;

/**
 * Class Description: 用户资源权限bean
 * @author HT
 */
public class UsRsAfBean extends RsResInfo{
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = -1164156726342190649L;

	/**
	 *允许禁止标志
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "允许禁止标志";

	/**
	 * @return af_flag 允许禁止标志
	 */
	public AF_FLAG getAf_flag() {
		return this.af_flag;
	}

	/**
	 * @param af_flag 允许禁止标志
	 */ 
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}
	
}
