/**
 * Title: QUERY_WKTYPE.java
 * File Description: 查询类型
 * @copyright 2014
 * @company CORSWORK
 * @author LIJIE
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 查询类型
 * @author AutoGen
 */
public class QUERY_TYPE
		extends EnumValue<QUERY_TYPE> {

	private static final long serialVersionUID = 8726019860125877806L;

	/**
	 * 构造函数
	 */
	private QUERY_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "查询类型";

	/**
	 * 1 提交
	 */
	public static final QUERY_TYPE SUBMITWORK = new QUERY_TYPE(1, "提交");

	/**
	 * 2 复核
	 */
	public static final QUERY_TYPE CHECKWORK = new QUERY_TYPE(2, "复核");

	/**
	 * 3 授权
	 */
	public static final QUERY_TYPE AUTHWORK = new QUERY_TYPE(3, "授权");
	
	/**
	 * 4 执行
	 */
	public static final QUERY_TYPE EXCWORK = new QUERY_TYPE(4, "执行");
	
	/**
	 * 5 关闭
	 */
	public static final QUERY_TYPE CLOSEWORK = new QUERY_TYPE(5, "关闭");
}

