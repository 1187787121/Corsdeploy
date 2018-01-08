/**
 * Title: SQL_TYPE.java
 * File Description: sql类型
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-12-12
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: sql类型
 * @author AutoGen
 */
public class SQL_TYPE
		extends EnumValue<SQL_TYPE> {

	private static final long serialVersionUID = 946287352663469905L;

	/**
	 * 构造函数
	 */
	private SQL_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "sql类型";

	/**
	 * 1 select
	 */
	public static final SQL_TYPE SELECT = new SQL_TYPE(1, "select");

	/**
	 * 2 update
	 */
	public static final SQL_TYPE UPDATE = new SQL_TYPE(2, "update");

	/**
	 * 3 insert
	 */
	public static final SQL_TYPE INSERT = new SQL_TYPE(3, "insert");

	/**
	 * 4 delete
	 */
	public static final SQL_TYPE DELETE = new SQL_TYPE(4, "delete");
}
