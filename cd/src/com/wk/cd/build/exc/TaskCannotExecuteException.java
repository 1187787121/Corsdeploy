/**
 * Title: TaskCannotExecuteException.java
 * File Description: ����{REASON},����ִ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161123
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����{REASON},����ִ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TaskCannotExecuteException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_TASK_CANNOT_EXECUTE";

	public TaskCannotExecuteException() {
		super(ERROR_CODE);
	}
}

