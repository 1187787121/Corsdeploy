/**
 * Title: SUBMIT_TYPE.java
 * File Description: 提交类型
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-23
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 提交类型
 * @author AutoGen
 */
public class SUBMIT_TYPE
		extends EnumValue<SUBMIT_TYPE> {

	private static final long serialVersionUID = -3042759986473515763L;

	/**
	 * 构造函数
	 */
	private SUBMIT_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "提交类型";

	/**
	 * 1 申请
	 */
	public static final SUBMIT_TYPE APPLY = new SUBMIT_TYPE(1, "申请");

	/**
	 * 2 提交执行
	 */
	public static final SUBMIT_TYPE EXECUTE = new SUBMIT_TYPE(2, "提交执行");

	/**
	 * 3 后台运行
	 */
	public static final SUBMIT_TYPE RUN = new SUBMIT_TYPE(3, "后台运行");
	
	/**
	 * 4 本地授权通过
	 */
	public static final SUBMIT_TYPE ALLOW = new SUBMIT_TYPE(4, "本地授权通过");
	
	/**
	 * 5 审批已申请
	 */
	public static final SUBMIT_TYPE APPLYALREADY = new SUBMIT_TYPE(5, "审批已申请");
}
