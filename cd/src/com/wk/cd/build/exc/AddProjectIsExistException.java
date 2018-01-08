/**
 * Title: AddProjectIsExistException.java
 * File Description: 项目管理[{PROJECT}]已经存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161102
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 项目管理[{PROJECT}]已经存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class AddProjectIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ADD_PROJECT_IS_EXIST";

	public AddProjectIsExistException() {
		super(ERROR_CODE);
	}
}

