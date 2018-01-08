/**
 * Title: PasswordInitialException.java
 * File Description: 密码为初始值，请修改密码
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151112
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 密码为初始值，请修改密码
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PasswordInitialException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_PASSWORD_INITIAL";

	public PasswordInitialException() {
		super(ERROR_CODE);
	}
}

