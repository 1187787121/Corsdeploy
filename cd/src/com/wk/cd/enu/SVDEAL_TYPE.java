/**
 * Title: SVDEAL_TYPE.java
 * File Description: 服务处理类型
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-03-18
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 服务处理类型
 * @author AutoGen
 */
public class SVDEAL_TYPE
		extends EnumValue<SVDEAL_TYPE> {

	private static final long serialVersionUID = 2005938546105990951L;

	/**
	* 构造函数
	*/
	private SVDEAL_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "服务处理类型";

	/**
	* 1 执行成功
	*/
	public static final SVDEAL_TYPE RUNSUCCESS = new SVDEAL_TYPE(1, "执行成功");

	/**
	* 2 本地授权
	*/
	public static final SVDEAL_TYPE LOCAL = new SVDEAL_TYPE(2, "本地授权");

	/**
	* 3 复核授权
	*/
	public static final SVDEAL_TYPE CHECKAUTH = new SVDEAL_TYPE(3, "复核授权");

	/**
	* 4 待审批
	*/
	public static final SVDEAL_TYPE WAIT = new SVDEAL_TYPE(4, "待审批");
}

