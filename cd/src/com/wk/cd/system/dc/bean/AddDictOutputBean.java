/**
 * Title: AddDictOutputBean.java
 * File Description: 新增数据字典输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014年11月17日
 */
package com.wk.cd.system.dc.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 新增数据字典输出接口类
 * @author HT
 */
public class AddDictOutputBean extends ActionRootOutputBean{
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = -3299775847944119161L;
	/**
	 * 域名称
	 */
	private String domain_name;
	public static final String DOMAIN_NAMECN = "域名称";
	
	/**
	 * @return domain_name 域名称
	 */
	public String getDomain_name() {
		return this.domain_name;
	}

	/**
	 * @param domain_name 域名称
	 */
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
}
