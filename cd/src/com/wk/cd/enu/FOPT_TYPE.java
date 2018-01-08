/**
 * Title: FOPT_TYPE.java
 * File Description: 操作类型
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 操作类型
 * @author AutoGen
 */
public class FOPT_TYPE extends EnumValue<FOPT_TYPE> {

	private static final long serialVersionUID = 4112615376242345655L;

	private FOPT_TYPE(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 修改
	  */
	 public static final FOPT_TYPE MODIFY = new FOPT_TYPE(1, "修改");

	 /**
	  * 删除
	  */
	 public static final FOPT_TYPE DELETE = new FOPT_TYPE(2, "删除");

}
