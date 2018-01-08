/**
 * Title: ReferenceBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月7日
 */
package com.wk.cd.module1.bean;

import com.wk.cd.enu.MODULE_TYPE;

/**
 * Class Description: 
 * @author Administrator
 */
public class ReferenceBean {
	/**
	 * 引用类别
	 */
	private MODULE_TYPE ref_type;
	
	public static final String REF_TYPECN = "引用类别";
	   
	/**
	 * 被引用ID
	 */
	private String ref_id;
	
	public static final String REF_IDCN = "被引用ID";
	
	/**
	 * 被引用名
	 */
	private String ref_name;
	
	public static final String REF_NAMECN = "被引用名";

	/**
	 * @return ref_type 引用类别
	 */
	public MODULE_TYPE getRef_type() {
		return ref_type;
	}

	/**
	 * @param ref_type 引用类别
	 */
	public void setRef_type(MODULE_TYPE ref_type) {
		this.ref_type = ref_type;
	}

	/**
	 * @return ref_id 被引用ID
	 */
	public String getRef_id() {
		return ref_id;
	}

	/**
	 * @param ref_id 被引用ID
	 */
	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}

	/**
	 * @return ref_name 被引用名
	 */
	public String getRef_name() {
		return ref_name;
	}

	/**
	 * @param ref_name 被引用名
	 */
	public void setRef_name(String ref_name) {
		this.ref_name = ref_name;
	}
	
}
