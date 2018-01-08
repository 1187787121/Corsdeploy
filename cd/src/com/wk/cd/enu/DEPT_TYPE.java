/**
 * Title: DEPT_TYPE.java
 * File Description: 部门类型
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 部门类型
 * @author AutoGen
 */
public class DEPT_TYPE
		extends EnumValue<DEPT_TYPE> {

	private static final long serialVersionUID = -8723243221707104020L;

	/**
	 * 构造函数
	 */
	private DEPT_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "部门类型";

	/**
	 * 1 科技
	 */
	public static final DEPT_TYPE TECH = new DEPT_TYPE(1, "科技");

	/**
	 * 2 电子银行
	 */
	public static final DEPT_TYPE EBANK = new DEPT_TYPE(2, "电子银行");

	/**
	 * 3 清算中心
	 */
	public static final DEPT_TYPE CLEARING = new DEPT_TYPE(3, "清算中心");

	/**
	 * 4 财务
	 */
	public static final DEPT_TYPE FINANCE = new DEPT_TYPE(4, "财务");

	/**
	 * 5 信贷
	 */
	public static final DEPT_TYPE CREDIT = new DEPT_TYPE(5, "信贷");

	/**
	 * 6 营业
	 */
	public static final DEPT_TYPE BUSINESS = new DEPT_TYPE(6, "营业");

	/**
	 * 7 法人行社
	 */
	public static final DEPT_TYPE BRANCH = new DEPT_TYPE(7, "法人行社");
}
