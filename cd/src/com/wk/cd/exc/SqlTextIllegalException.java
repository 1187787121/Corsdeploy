/**
 * Title: SqlTextIllegalException.java
 * File Description: sql语句{SQL}不合法
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141210
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: sql语句{SQL}不合法
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SqlTextIllegalException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_PROBLEM_SQL_TEXT_ILLEGAL";

	public SqlTextIllegalException() {
		super(ERROR_CODE);
	}
}

