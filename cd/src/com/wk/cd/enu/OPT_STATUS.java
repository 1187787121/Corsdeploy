/**
 * Title: OPT_STATUS.java
 * File Description: 操作状态
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-10
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 操作状态
 * @author AutoGen
 */
public class OPT_STATUS extends EnumValue<OPT_STATUS> {

	private static final long serialVersionUID = -8914581095198172886L;

	private OPT_STATUS(int value, String name) {
		 super(value, name);
	 }

	 /**
	  * 待修改
	  */
	 public static final OPT_STATUS PENDING = new OPT_STATUS(1, "待修改");

	 /**
	  * 修改成功
	  */
	 public static final OPT_STATUS SUCCESS = new OPT_STATUS(2, "修改成功");

	 /**
	  * 修改失败
	  */
	 public static final OPT_STATUS FAIL = new OPT_STATUS(3, "修改失败");

}
