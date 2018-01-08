/**
 * Title: SALLOW_FLAG.java
 * File Description: 允许执行标志
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-1-5
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description:允许执行标志
 * @author AutoGen
 */
public class SALLOW_FLAG
		extends EnumValue<SALLOW_FLAG> {
	private static final long serialVersionUID = 8443577769507140564L;

	private SALLOW_FLAG(int value, String name) {
		super(value, name);
	}

	/**
	 * 本地执行
	 */
	public static final SALLOW_FLAG ALWLOCAL = new SALLOW_FLAG(1, "本地执行");

	/**
	 * 远程执行
	 */
	public static final SALLOW_FLAG ALWREMOTE = new SALLOW_FLAG(2, "远程执行");

	/**
	 * 不控制
	 */
	public static final SALLOW_FLAG ALL = new SALLOW_FLAG(3, "不控制");

}
