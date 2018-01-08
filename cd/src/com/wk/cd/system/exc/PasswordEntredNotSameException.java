/**
 * Title: PasswordEntredNotSameException.java
 * File Description: 密码输入不一致
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20150211
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 密码输入不一致
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PasswordEntredNotSameException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_PASSWORD_ENTRED_NOT_SAME";

	public PasswordEntredNotSameException() {
		super(ERROR_CODE);
	}
}

