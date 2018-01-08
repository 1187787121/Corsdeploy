/**
 * Title: InputYearGreaterThenThisYearException.java
 * File Description: 传入年份{YEAR}大于当前年份
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151201
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 传入年份{YEAR}大于当前年份
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class InputYearGreaterThenThisYearException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_INPUT_YEAR_GREATER_THEN_THIS_YEAR";

	public InputYearGreaterThenThisYearException() {
		super(ERROR_CODE);
	}
}

