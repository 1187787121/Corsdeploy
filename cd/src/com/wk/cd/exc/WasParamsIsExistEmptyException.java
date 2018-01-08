/**
 * Title: WasParamsIsExistEmptyException.java
 * File Description: Was参数有空的信息对儿
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160614
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: Was参数有空的信息对儿
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class WasParamsIsExistEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_WAS_PARAMS_IS_EXIST_EMPTY";

	public WasParamsIsExistEmptyException() {
		super(ERROR_CODE);
	}
}

