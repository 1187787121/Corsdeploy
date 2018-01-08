/**
 * Title: PackNameRepeatException.java
 * File Description: 包名[{PACkNAME}]重复
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161215
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 包名[{PACKNAME}]重复
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PackNameRepeatException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_PACK_NAME_REPEAT";

	public PackNameRepeatException() {
		super(ERROR_CODE);
	}
}

