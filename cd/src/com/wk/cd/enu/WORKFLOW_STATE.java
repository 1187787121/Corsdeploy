/**
 * Title: WORKFLOW_STATE.java
 * File Description: 工作状态
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 工作状态
 * @author AutoGen
 */
public class WORKFLOW_STATE
		extends EnumValue<WORKFLOW_STATE> {

	private static final long serialVersionUID = 4325794868926420045L;

	/**
	 * 构造函数
	 */
	private WORKFLOW_STATE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "工作状态";

	/**
	 * 1 待复核
	 */
	public static final WORKFLOW_STATE RECHECK = new WORKFLOW_STATE(1, "待复核");

	/**
	 * 2 复核拒绝
	 */
	public static final WORKFLOW_STATE CHECK_REFUSE = new WORKFLOW_STATE(2,"复核拒绝");

	/**
	 * 3 待授权
	 */
	public static final WORKFLOW_STATE APPROVAL = new WORKFLOW_STATE(3, "待授权");

	/**
	 * 4 授权拒绝
	 */
	public static final WORKFLOW_STATE APP_REFUSE = new WORKFLOW_STATE(4,"授权拒绝");

	/**
	 * 5 待执行
	 */
	public static final WORKFLOW_STATE EXECUTORY = new WORKFLOW_STATE(5,"待执行");

	/**
	 * 6 执行完毕
	 */
	public static final WORKFLOW_STATE COMPLETE = new WORKFLOW_STATE(6, "执行完毕");
	
	/**
	 * 7 关闭任务
	 */
	public static final WORKFLOW_STATE CLOSE = new WORKFLOW_STATE(7, "关闭任务");
}
