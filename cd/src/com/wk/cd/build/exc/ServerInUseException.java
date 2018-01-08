/**
 * Title: ServerInUseException.java
 * File Description: 服务器存在关联的环境，不能进行[{OPT}]操作
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161102
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 服务器存在关联的环境，不能进行[{OPT}]操作
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServerInUseException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_SERVER_IN_USE";

	public ServerInUseException() {
		super(ERROR_CODE);
	}
}

