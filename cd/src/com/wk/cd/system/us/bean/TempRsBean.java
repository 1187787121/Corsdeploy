/**
 * Title: TempRsBean.java
 * File Description: 临时资源bean
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年3月18日
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.system.us.info.UsUserRsPrivInfo;

/**
 * Class Description: 临时资源bean
 * @author HT
 */
public class TempRsBean extends UsUserRsPrivInfo{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 8894812009178137879L;
	
	/**
	 *资源名称
	 */
	private String rs_cn_name;

	public static final String RS_CN_NAMECN = "资源名称";

	/**
	 * @return rs_cn_name 资源名称
	 */
	public String getRs_cn_name() {
		return rs_cn_name;
	}

	/**
	 * @param rs_cn_name 资源名称
	 */
	public void setRs_cn_name(String rs_cn_name) {
		this.rs_cn_name = rs_cn_name;
	}
}
