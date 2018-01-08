/**
 * Title: MID_WARE.java
 * File Description: 数据库类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 数据库类型
 * @author AutoGen
 */
public class MID_WARE extends EnumValue<MID_WARE> {
	 /** 
	 * @Fields serialVersionUID : -8297925310062280737L
	 */ 
	private static final long serialVersionUID = -8297925310062280737L;

	private MID_WARE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * TOMCAT
	  */
	 public static final MID_WARE TOMCAT = new MID_WARE(1, "TOMCAT");

	 /**
	  * WEBLOGIC
	  */
	 public static final MID_WARE WEBLOGIC = new MID_WARE(2, "WEBLOGIC");

	 /**
	  * WAS
	  */
	 public static final MID_WARE WAS = new MID_WARE(3, "WAS");

}
