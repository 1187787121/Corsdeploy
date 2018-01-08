/**
 * Title: PasswordOutOfDateException.java
 * File Description: 密码已过期，请联系管理员
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151112
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 密码已过期，请联系管理员
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PasswordOutOfDateException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_PASSWORD_OUT_OF_DATE";

	public PasswordOutOfDateException() {
		super(ERROR_CODE);
	}
}

