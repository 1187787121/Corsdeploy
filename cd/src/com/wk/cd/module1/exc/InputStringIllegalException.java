/**
 * Title: InputStringIllegalException.java
 * File Description: {FIELD}����ֵ{STRING}���Ϸ�
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151224
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {FIELD}����ֵ{STRING}���Ϸ�
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
