/**
 * Title: PROG_TYPE.java
 * File Description: 方案类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 方案类型
 * @author AutoGen
 */
public class PROG_TYPE extends EnumValue<PROG_TYPE> {

	private static final long serialVersionUID = -6425392058122113743L;

	private PROG_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 构建
	  */
	 public static final PROG_TYPE BUILD = new PROG_TYPE(1, "构建");

	 /**
	  * 集成
	  */
	 public static final PROG_TYPE INTEGRATION = new PROG_TYPE(2, "集成");

	 /**
	  * 发布
	  */
	 public static final PROG_TYPE PUBLISH = new PROG_TYPE(3, "发布");

}
