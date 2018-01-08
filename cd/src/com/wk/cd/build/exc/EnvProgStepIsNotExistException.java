/**
 * Title: EnvProgStepIsNotExistException.java
 * File Description: 环境方案[{PROGID}][{STEPID}]不存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161115
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 环境方案[{PROGID}][{STEPID}]不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvProgStepIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_PROG_STEP_IS_NOT_EXIST";

	public EnvProgStepIsNotExistException() {
		super(ERROR_CODE);
	}
}

