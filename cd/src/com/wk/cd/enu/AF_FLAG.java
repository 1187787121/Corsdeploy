/**
 * Title: AF_FLAG.java
 * File Description: 允许禁止标志
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 允许禁止标志
 * @author AutoGen
 */
public class AF_FLAG
		extends EnumValue<AF_FLAG> {

	private static final long serialVersionUID = -6091356132468866071L;

	/**
	 * 构造函数
	 */
	private AF_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "允许禁止标志";

	/**
	 * 1 允许
	 */
	public static final AF_FLAG ALLOW = new AF_FLAG(1, "允许");

	/**
	 * 2 禁止
	 */
	public static final AF_FLAG FORBID = new AF_FLAG(2, "禁止");
}
