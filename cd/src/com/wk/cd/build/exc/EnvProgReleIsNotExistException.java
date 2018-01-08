/**
 * Title: EnvProgReleIsNotExistException.java
 * File Description: 环境发布方案[{NAME}]不存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161115
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 环境发布方案[{NAME}]不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvProgReleIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_PROG_RELE_IS_NOT_EXIST";

	public EnvProgReleIsNotExistException() {
		super(ERROR_CODE);
	}
}

