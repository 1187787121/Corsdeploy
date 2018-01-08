/**
 * Title: DATABASE_TYPE.java
 * File Description: 数据库类型
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-08-13
 */
package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Class Description: 数据库类型
 * @author AutoGen
 */
public class DATABASE_TYPE
		extends EnumValue<DATABASE_TYPE> {

	private static final long serialVersionUID = -8349643057148899545L;

	/**
	* 构造函数
	*/
	private DATABASE_TYPE(int value, String name) {
		super(value, name);
	}

	public static final String ENUMCN = "数据库类型";

	/**
	* 1 MySql
	*/
	public static final DATABASE_TYPE MYSQL = new DATABASE_TYPE(1, "MySql");

	/**
	* 2 DB2
	*/
	public static final DATABASE_TYPE DB2 = new DATABASE_TYPE(2, "DB2");

	/**
	* 3 Oracle
	*/
	public static final DATABASE_TYPE ORACLE = new DATABASE_TYPE(3, "Oracle");

	/**
	* 4 Microsoft
	*/
	public static final DATABASE_TYPE MICROSOFT = new DATABASE_TYPE(4, "Microsoft");

	/**
	* 5 Sqlserver
	*/
	public static final DATABASE_TYPE SQLSERVER = new DATABASE_TYPE(5, "Sqlserver");
}

