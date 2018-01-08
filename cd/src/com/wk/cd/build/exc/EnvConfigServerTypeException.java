/**
 * Title: EnvConfigServerTypeException.java
 * File Description: [{ENV}]下没有配置[{TYPE}]
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161129
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: [{ENV}]下没有配置[{TYPE}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvConfigServerTypeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_CONFIG_SERVER_TYPE";

	public EnvConfigServerTypeException() {
		super(ERROR_CODE);
	}
}

