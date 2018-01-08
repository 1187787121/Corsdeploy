/**
 * Title: ComponentFileIsNotExistException.java
 * File Description: 组件中文件不存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161026
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 组件中文件不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ComponentFileIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_COMPONENT_FILE_IS_NOT_EXIST";

	public ComponentFileIsNotExistException() {
		super(ERROR_CODE);
	}
}

