/**
 * Title: ELE_TYPE.java
 * File Description: 构成要素
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-31
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 构成要素
 * @author AutoGen
 */
public class ELE_TYPE extends EnumValue<ELE_TYPE> {
	 /** 
	 * @Fields serialVersionUID : -4532357712947425201L
	 */ 
	private static final long serialVersionUID = -4532357712947425201L;

	private ELE_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 源码
	  */
	 public static final ELE_TYPE SOURCE = new ELE_TYPE(1, "源码");

	 /**
	  * 目标
	  */
	 public static final ELE_TYPE TARGET = new ELE_TYPE(2, "目标");

	 /**
	  * 数据
	  */
	 public static final ELE_TYPE DATA = new ELE_TYPE(3, "数据");

}
