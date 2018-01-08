/**
 * Title: COMP_TYPE.java
 * File Description: 组件类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-18
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 组件类型
 * @author AutoGen
 */
public class MODULE_TYPE extends EnumValue<MODULE_TYPE> {
	 /** 
	 * @Fields serialVersionUID : -6476356314113104869L
	 */ 
	private static final long serialVersionUID = -6476356314113104869L;

	private MODULE_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 组件
	  */
	 public static final MODULE_TYPE COMPONENT = new MODULE_TYPE(1, "组件");

	 /**
	  * 组件组
	  */
	 public static final MODULE_TYPE GROUP = new MODULE_TYPE(2, "组件组");

	 /**
	  * 模板
	  */
	 public static final MODULE_TYPE TEMPLATE = new MODULE_TYPE(3, "模板");
	 
	 /**
	  * 阶段
	  */
	 public static final MODULE_TYPE PHASE = new MODULE_TYPE(4, "阶段");

	 /**
	  * 实例
	  */
	 public static final MODULE_TYPE INSTANCE = new MODULE_TYPE(5, "实例");
	 
	 /**
	  * 系统方案
	  */
	 public static final MODULE_TYPE PROGRAM = new MODULE_TYPE(6, "系统方案");
	 
	 /**
	  * 采集方案
	  */
	 public static final MODULE_TYPE COLLECT = new MODULE_TYPE(7, "采集方案");
	 
	 /**
	  * 采集任务
	  */
	 public static final MODULE_TYPE TASK = new MODULE_TYPE(8,"采集任务");
	 
	 /**
	  * 自动运维流程
	  */
	 public static final MODULE_TYPE FLOW = new MODULE_TYPE(9,"自动运维流程");
	 
	 /**
	  * 自动运维任务
	  */
	 public static final MODULE_TYPE OPTASK = new MODULE_TYPE(10,"自动运维任务");
}
