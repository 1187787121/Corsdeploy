/**
 * Title: EXE_STATUS.java
 * File Description: 执行状态
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 执行状态
 * @author AutoGen
 */
public class EXE_STATUS extends EnumValue<EXE_STATUS> {

	private static final long serialVersionUID = -8108246828912416154L;

	private EXE_STATUS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 待执行
	  */
	 public static final EXE_STATUS PENDING = new EXE_STATUS(1, "待执行");

	 /**
	  * 执行中
	  */
	 public static final EXE_STATUS RUNNING = new EXE_STATUS(2, "执行中");

	 /**
	  * 已执行
	  */
	 public static final EXE_STATUS EXECUTED = new EXE_STATUS(3, "已执行");
	 
	 /**
	  * 跳过
	  */
	 public static final EXE_STATUS SKIP = new EXE_STATUS(4, "跳过");
}
