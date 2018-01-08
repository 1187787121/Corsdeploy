/**
 * Title: YN_FLAG.java
 * File Description: 是否标志
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 是否标志
 * @author AutoGen
 */
public class YN_FLAG
		extends EnumValue<YN_FLAG> {

	private static final long serialVersionUID = -6758581760111554391L;

	/**
	 * 构造函数
	 */
	private YN_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "是否标志";

	/**
	 * 1 是
	 */
	public static final YN_FLAG YES = new YN_FLAG(1, "是");

	/**
	 * 2 否
	 */
	public static final YN_FLAG NO = new YN_FLAG(2, "否");
}
