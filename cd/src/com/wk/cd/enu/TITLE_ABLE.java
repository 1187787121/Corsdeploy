/**
 * Title: TITLE_ABLE.java
 * File Description: 是否有标题
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 是否有标题
 * @author AutoGen
 */
public class TITLE_ABLE
		extends EnumValue<TITLE_ABLE> {

	private static final long serialVersionUID = -899565663752074838L;

	/**
	 * 构造函数
	 */
	private TITLE_ABLE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "是否有标题";

	/**
	 * 1 是
	 */
	public static final TITLE_ABLE YES = new TITLE_ABLE(1, "是");

	/**
	 * 2 否
	 */
	public static final TITLE_ABLE NO = new TITLE_ABLE(2, "否");
}
