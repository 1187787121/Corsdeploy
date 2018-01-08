/**
 * Title: SERVER_TYPE.java
 * File Description: 服务器类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 服务器类型
 * @author AutoGen
 */
public class SERVER_TYPE extends EnumValue<SERVER_TYPE> {
	 /**
	 * 822896854568418238L
	 */
	private static final long serialVersionUID = 822896854568418238L;

	private SERVER_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 应用服务器
	  */
	 public static final SERVER_TYPE APPLY = new SERVER_TYPE(1, "应用服务器");

	 /**
	  * 数据库服务器
	  */
	 public static final SERVER_TYPE DATABASE = new SERVER_TYPE(2, "数据库服务器");

	 /**
	  * 版本服务器
	  */
	 public static final SERVER_TYPE VERSION = new SERVER_TYPE(3, "版本服务器");

}
