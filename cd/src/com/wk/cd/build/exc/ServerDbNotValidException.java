/**
 * Title: ServerDbNotValidException.java
 * File Description: ���������ݿ����ò���ȷ
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161119
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ���������ݿ����ò���ȷ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServerDbNotValidException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_SERVER_DB_NOT_VALID";

	public ServerDbNotValidException() {
		super(ERROR_CODE);
	}
}

