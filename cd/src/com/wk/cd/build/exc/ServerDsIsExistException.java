/**
 * Title: ServerDsIsExistException.java
 * File Description: ������[{SERVER}]����Դ[{SOC}]��¼������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161119
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ������[{SERVER}]����Դ[{SOC}]��¼������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServerDsIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_SERVER_DS_IS_EXIST";

	public ServerDsIsExistException() {
		super(ERROR_CODE);
	}
}

