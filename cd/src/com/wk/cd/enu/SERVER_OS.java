/**
 * Title: SERVER_OS.java
 * File Description: 操作系统
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 操作系统
 * @author AutoGen
 */
public class SERVER_OS extends EnumValue<SERVER_OS> {
	 /**
	 * 938425379950823947L
	 */
	private static final long serialVersionUID = 938425379950823947L;

	private SERVER_OS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * AIX系统
	  */
	 public static final SERVER_OS AIX = new SERVER_OS(2, "AIX系统");

	 /**
	  * LINUX系统
	  */
	 public static final SERVER_OS LINUX = new SERVER_OS(1, "LINUX系统");

	 /**
	  * UNIX系统
	  */
	 public static final SERVER_OS UNIX = new SERVER_OS(3, "UNIX系统");

	 /**
	  * OS400系统
	  */
	 public static final SERVER_OS OS400 = new SERVER_OS(4, "OS400系统");

}
