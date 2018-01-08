/**
 * Title: AddSystemIsExistException.java
 * File Description: 应用系统[{SYSTEM}]已经存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161102
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 应用系统[{SYSTEM}]已经存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class AddSystemIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ADD_SYSTEM_IS_EXIST";

	public AddSystemIsExistException() {
		super(ERROR_CODE);
	}
}

