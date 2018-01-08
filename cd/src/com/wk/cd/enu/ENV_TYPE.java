/**
 * Title: ENV_TYPE.java
 * File Description: 环境类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 环境类型
 * @author AutoGen
 */
public class ENV_TYPE extends EnumValue<ENV_TYPE> {
	 /**
	 * 3490597017393526572L
	 */
	private static final long serialVersionUID = 3490597017393526572L;

	private ENV_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 开发环境
	  */
	 public static final ENV_TYPE DEVELOPMENT = new ENV_TYPE(1, "开发环境");

	 /**
	  * 测试环境
	  */
	 public static final ENV_TYPE TEST = new ENV_TYPE(2, "测试环境");

	 /**
	  * 版本环境
	  */
	 public static final ENV_TYPE VERSION = new ENV_TYPE(3, "版本环境");

	 /**
	  * 准生产环境
	  */
	 public static final ENV_TYPE PRODUCTING = new ENV_TYPE(4, "准生产环境");

	 /**
	  * 生产环境
	  */
	 public static final ENV_TYPE PRODUCT = new ENV_TYPE(5, "生产环境");

}
