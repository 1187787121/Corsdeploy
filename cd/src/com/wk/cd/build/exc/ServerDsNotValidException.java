/**
 * Title: ServerDsNotValidException.java
 * File Description: ����������Դ���ò���ȷ
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161119
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����������Դ���ò���ȷ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServerDsNotValidException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_SERVER_DS_NOT_VALID";

	public ServerDsNotValidException() {
		super(ERROR_CODE);
	}
}

