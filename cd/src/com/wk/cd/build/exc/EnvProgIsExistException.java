/**
 * Title: EnvProgIsExistException.java
 * File Description: ��������[{ENVPROG}]�Ѿ�����
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161115
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��������[{ENVPROG}]�Ѿ�����
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvProgIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_PROG_IS_EXIST";

	public EnvProgIsExistException() {
		super(ERROR_CODE);
	}
}

