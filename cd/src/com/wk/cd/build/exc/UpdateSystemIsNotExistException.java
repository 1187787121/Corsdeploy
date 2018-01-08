/**
 * Title: UpdateSystemIsNotExistException.java
 * File Description: 应用系统[{SYSTEM}]不存在存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161102
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 应用系统[{SYSTEM}]不存在存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class UpdateSystemIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_UPDATE_SYSTEM_IS_NOT_EXIST";

	public UpdateSystemIsNotExistException() {
		super(ERROR_CODE);
	}
}

