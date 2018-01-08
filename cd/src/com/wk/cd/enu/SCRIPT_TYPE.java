/**
 * Title: SCRIPT_TYPE.java
 * File Description: 脚本类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-12-9
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 脚本类型
 * @author AutoGen
 */
public class SCRIPT_TYPE extends EnumValue<SCRIPT_TYPE> {
	 
	private static final long serialVersionUID = -3267692722180166791L;

	private SCRIPT_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 操作系统配置
	  */
	 public static final SCRIPT_TYPE OPERATION = new SCRIPT_TYPE(1, "操作系统配置");

	 /**
	  * 数据库配置
	  */
	 public static final SCRIPT_TYPE DATABASE = new SCRIPT_TYPE(2, "数据库配置");

	 /**
	  * 数据处理
	  */
	 public static final SCRIPT_TYPE DATA = new SCRIPT_TYPE(3, "数据处理");

	 /**
	  * 其他处理
	  */
	 public static final SCRIPT_TYPE OTHER = new SCRIPT_TYPE(4, "其他处理");

	 /**
	  * 启动脚本
	  */
	 public static final SCRIPT_TYPE START = new SCRIPT_TYPE(5, "启动脚本");

	 /**
	  * 停止脚本
	  */
	 public static final SCRIPT_TYPE STOP = new SCRIPT_TYPE(6, "停止脚本");

}
