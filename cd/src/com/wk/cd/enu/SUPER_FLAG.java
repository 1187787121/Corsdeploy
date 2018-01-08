/**
 * Title: AF_FLAG.java.java
 * File Description: 是否上级配置
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 是否上级配置
 * @author AutoGen
 */
public class SUPER_FLAG
		extends EnumValue<SUPER_FLAG> {

	private static final long serialVersionUID = -6091356132468866071L;

	/**
	 * 构造函数
	 */
	private SUPER_FLAG(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "是否上级配置";

	/**
	 * 1 是
	 */
	public static final SUPER_FLAG YES = new SUPER_FLAG(1, "是");

	/**
	 * 2 否
	 */
	public static final SUPER_FLAG NO = new SUPER_FLAG(2, "否");
}
