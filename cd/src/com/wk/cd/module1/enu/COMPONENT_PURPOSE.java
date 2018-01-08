/**
 * Title: COMPONENT_PURPOSE.java
 * File Description: 组件用途
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-4-6
 */
package com.wk.cd.module1.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 组件用途
 * @author AutoGen
 */
public class COMPONENT_PURPOSE extends EnumValue<COMPONENT_PURPOSE> {
	 /** 
	 * @Fields serialVersionUID : 7557506646574530419L
	 */ 
	private static final long serialVersionUID = 7557506646574530419L;

	private COMPONENT_PURPOSE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 应用发布
	  */
	 public static final COMPONENT_PURPOSE PROD = new COMPONENT_PURPOSE(1, "应用发布");

	 /**
	  * 日志巡检
	  */
	 public static final COMPONENT_PURPOSE COLLECT = new COMPONENT_PURPOSE(2, "日志巡检");
	 
	 /**
	  * 作业调度
	  */
	 public static final COMPONENT_PURPOSE OPERATION = new COMPONENT_PURPOSE(3, "作业调度");
	 
	 /**
	  * 故障维护
	  */
	 public static final COMPONENT_PURPOSE PROBLEM = new COMPONENT_PURPOSE(4, "故障维护");
	 
	 /**
	  * 发布验证
	  */
	 public static final COMPONENT_PURPOSE CHECK = new COMPONENT_PURPOSE(5, "发布验证");

}
