/**
 * Title: COMP_TYPE.java
 * File Description: 组件类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-18
 */
package com.wk.cd.module1.enu;

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
	  * 阶段
	  */
	 public static final MODULE_TYPE PHASE = new MODULE_TYPE(3, "阶段");

	 /**
	  * 系统方案
	  */
	 public static final MODULE_TYPE PROGRAM = new MODULE_TYPE(6, "系统方案");
	 
	 /**
	  * 实例
	  */
	 public static final MODULE_TYPE INSTANCE = new MODULE_TYPE(4, "实例");
	 
	 /**
	  * 模板
	  */
	 public static final MODULE_TYPE TEMPLATE = new MODULE_TYPE(5, "模板");
	 
}
