/**
 * Title: ServerAlreadyExistException.java
 * File Description: 服务器[{SERVER}]已经存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161102
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 服务器[{SERVER}]已经存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServerAlreadyExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_SERVER_ALREADY_EXIST";

	public ServerAlreadyExistException() {
		super(ERROR_CODE);
	}
}

