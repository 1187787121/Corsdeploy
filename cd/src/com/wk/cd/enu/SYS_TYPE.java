/**
 * Title: SYS_TYPE.java
 * File Description: 应用系统类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 应用系统类型
 * @author AutoGen
 */
public class SYS_TYPE extends EnumValue<SYS_TYPE> {
	 /** 
	 * @Fields serialVersionUID : -7870324938755166064L
	 */ 
	private static final long serialVersionUID = -7870324938755166064L;

	private SYS_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 渠道
	  */
	 public static final SYS_TYPE CHANNEL = new SYS_TYPE(1, "渠道");

	 /**
	  * 中台
	  */
	 public static final SYS_TYPE MIDDLEGD = new SYS_TYPE(2, "中台");

	 /**
	  * ESB
	  */
	 public static final SYS_TYPE ESB = new SYS_TYPE(3, "ESB");

	 /**
	  * 传统业务
	  */
	 public static final SYS_TYPE TRADITIONAL = new SYS_TYPE(4, "传统业务");

	 /**
	  * 互联网
	  */
	 public static final SYS_TYPE INTERNET = new SYS_TYPE(5, "互联网");
	 
	 /**
	  * 前置
	  */
	 public static final SYS_TYPE PREPOSE = new SYS_TYPE(6, "前置");

}
