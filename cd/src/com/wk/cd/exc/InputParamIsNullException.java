/**
 * Title: InputParamIsNullException.java
 * File Description: {PARM}�е���{FIELD}Ϊ��
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141118
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {PARM}�е���{FIELD}Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class InputParamIsNullException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_INPUT_PARAM_IS_NULL";

	public InputParamIsNullException() {
		super(ERROR_CODE);
	}
}

