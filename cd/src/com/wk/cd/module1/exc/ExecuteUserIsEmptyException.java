/**
 * Title: ExecuteUserIsEmptyException.java
 * File Description: 系统:[{BUSI}]项目:[{PROJ}]中执行用户为空
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160412
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 系统:[{BUSI}]项目:[{PROJ}]中执行用户为空
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ExecuteUserIsEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_EXECUTE_USER_IS_EMPTY";

	public ExecuteUserIsEmptyException() {
		super(ERROR_CODE);
	}
}

