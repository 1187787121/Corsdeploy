/**
 * Title: CHECK_FLAG.java
 * File Description: 是否允许复核
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 是否允许复核
 * @author AutoGen
 */
public class CHECK_FLAG
		extends EnumValue<CHECK_FLAG> {

	private static final long serialVersionUID = 2597305397280090694L;

	/**
	 * 构造函数
	 */
	private CHECK_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "是否允许复核";

	/**
	 * 1 允许
	 */
	public static final CHECK_FLAG ALLOW = new CHECK_FLAG(1, "允许");

	/**
	 * 2 禁止
	 */
	public static final CHECK_FLAG FORBID = new CHECK_FLAG(2, "禁止");
}
