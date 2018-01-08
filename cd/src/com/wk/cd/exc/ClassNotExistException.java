/**
 * Title: ClassNotExistException.java
 * File Description: {CLASSNAME}不存在
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151224
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {CLASSNAME}不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ClassNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_CLASS_NOT_EXIST";

	public ClassNotExistException() {
		super(ERROR_CODE);
	}
}

