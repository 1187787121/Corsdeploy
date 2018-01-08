/**
 * Title: STORAGE_RESULT.java
 * File Description: 入库结果
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 入库结果
 * @author AutoGen
 */
public class STORAGE_RESULT extends EnumValue<STORAGE_RESULT> {

	private static final long serialVersionUID = -91310907448954959L;

	private STORAGE_RESULT(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 成功
	  */
	 public static final STORAGE_RESULT SUCCESS = new STORAGE_RESULT(1, "成功");

	 /**
	  * 失败
	  */
	 public static final STORAGE_RESULT FAIL = new STORAGE_RESULT(2, "失败");

}
