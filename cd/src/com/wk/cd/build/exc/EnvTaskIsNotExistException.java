/**
 * Title: EnvTaskIsNotExistException.java
 * File Description: ��������[{TASK}]������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161202
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��������[{TASK}]������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvTaskIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_TASK_IS_NOT_EXIST";

	public EnvTaskIsNotExistException() {
		super(ERROR_CODE);
	}
}

