/**
 * Title: InputStringIllegalException.java
 * File Description: {FIELD}传入值{STRING}不合法
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151224
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {FIELD}传入值{STRING}不合法
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class InputStringIllegalException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_INPUT_STRING_ILLEGAL";

	public InputStringIllegalException() {
		super(ERROR_CODE);
	}
}

