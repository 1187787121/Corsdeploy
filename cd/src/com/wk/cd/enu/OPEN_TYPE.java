/**
 * Title: OPEN_TYPE.java
 * File Description: 开放类型
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-8-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 开放类型
 * @author AutoGen
 */
public class OPEN_TYPE extends EnumValue<OPEN_TYPE> {
	private static final long serialVersionUID = -5122143827087337022L;
	
	/**
	 * 构造函数
	 */
	private OPEN_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 开放
	  */
	 public static final OPEN_TYPE ALLOW = new OPEN_TYPE(1, "开放");

	 /**
	  * 禁止
	  */
	 public static final OPEN_TYPE FORBID = new OPEN_TYPE(2, "禁止");

}
