/**
 * Title: TASK_STATUS.java
 * File Description: 任务状态
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-19
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 任务状态
 * @author AutoGen
 */
public class TASK_STATUS extends EnumValue<TASK_STATUS> {

	private static final long serialVersionUID = -7633536715780757378L;

	private TASK_STATUS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 待执行
	  */
	 public static final TASK_STATUS PENDING = new TASK_STATUS(1, "待执行");

	 /**
	  * 执行中
	  */
	 public static final TASK_STATUS RUNNING = new TASK_STATUS(2, "执行中");

	 /**
	  * 已执行
	  */
	 public static final TASK_STATUS EXECUTED = new TASK_STATUS(3, "已执行");
	 
	 /**
	  * 回退中
	  */
	 public static final TASK_STATUS ROLLBACKING = new TASK_STATUS(4, "回退中");

	 /**
	  * 回退成功
	  */
	 public static final TASK_STATUS ROLLBACKOK = new TASK_STATUS(5, "回退成功");

	 /**
	  * 回退失败
	  */
	 public static final TASK_STATUS ROLLBACKFAIL = new TASK_STATUS(6, "回退失败");

	 /**
	  * 关闭
	  */
	 public static final TASK_STATUS CLOSE = new TASK_STATUS(7, "关闭");

}
