/**
 * Title: UserNotLoginException.java
 * File Description: 用户{USER_ID}没有登录
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 用户{USER_ID}没有登录
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class UserNotLoginException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_USER_NOT_LOGIN";

	public UserNotLoginException() {
		super(ERROR_CODE);
	}
}

