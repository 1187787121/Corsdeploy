/**
 * Title: ServerSocTypeIsNotExistException.java
 * File Description: ������[{SERVER}]�±�û��[TYPE]��������Դ
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161124
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ������[{SERVER}]û��[TYPE]��������Դ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServerSocTypeIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_SERVER_SOC_TYPE_IS_NOT_EXIST";

	public ServerSocTypeIsNotExistException() {
		super(ERROR_CODE);
	}
}

