/**
 * Title: STEP_TYPE.java
 * File Description: 步骤类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 步骤类型
 * @author AutoGen
 */
public class STEP_TYPE extends EnumValue<STEP_TYPE> {

	private static final long serialVersionUID = 8183136285193546239L;

	private STEP_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 版本
	  */
	 public static final STEP_TYPE VERSION = new STEP_TYPE(1, "版本");

	 /**
	  * 脚本
	  */
	 public static final STEP_TYPE SCRIPT = new STEP_TYPE(2, "脚本");

	 /**
	  * 编译
	  */
	 public static final STEP_TYPE COMPILE = new STEP_TYPE(3, "编译");

	 /**
	  * 入库
	  */
	 public static final STEP_TYPE STORAGE = new STEP_TYPE(4, "入库");

}
