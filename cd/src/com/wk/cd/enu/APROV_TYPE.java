/**
 * Title: APROV_TYPE.java
 * File Description: 审批展示类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-15
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 审批展示类型
 * @author AutoGen
 */
public class APROV_TYPE extends EnumValue<APROV_TYPE> {

	private static final long serialVersionUID = -8184602612800041913L;
	
	/**
	 * 构造函数
	 */
	private APROV_TYPE(int value, String name) {
		 super(value, name);
	 }
	
	public static final String ENUMCN = "审批展示类型";
	
	 /**
	  * 1 仅接口
	  */
	 public static final APROV_TYPE ONLY_INTERFACE = new APROV_TYPE(1, "仅接口");

	 /**
	  * 2 静态页面
	  */
	 public static final APROV_TYPE STATIC_PAGE = new APROV_TYPE(2, "静态页面");

	 /**
	  * 3 定制页面
	  */
	 public static final APROV_TYPE CUSTOM_PAGE = new APROV_TYPE(3, "定制页面");

}
