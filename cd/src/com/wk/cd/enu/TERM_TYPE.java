/**
 * Title: TERM_TYPE.java
 * File Description: 终端类型
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-5
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 终端类型
 * @author AutoGen
 */
public class TERM_TYPE
		extends EnumValue<TERM_TYPE> {
	private static final long serialVersionUID = -7445834812979764935L;

	private TERM_TYPE(int value, String name) {
		super(value, name);
	}

	/**
	 * 本地终端
	 */
	public static final TERM_TYPE LOCAL = new TERM_TYPE(1, "本地终端");

	/**
	 * 远程终端
	 */
	public static final TERM_TYPE REMOTE = new TERM_TYPE(2, "远程终端");

}
