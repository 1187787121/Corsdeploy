/**
 * Title: TaskCannotCloseException.java
 * File Description: ������ִ�н��������ܹر�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161123
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ������ִ�н��������ܹر�
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TaskCannotCloseException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_TASK_CANNOT_CLOSE";

	public TaskCannotCloseException() {
		super(ERROR_CODE);
	}
}

