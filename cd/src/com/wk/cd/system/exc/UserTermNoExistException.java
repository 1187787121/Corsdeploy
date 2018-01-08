/**
 * Title: UserTermNoExistException.java
 * File Description: 用户接入终端未授权，请申请接入终端
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151114
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 用户接入终端未授权，请申请接入终端
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class UserTermNoExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_USER_TERM_NO_EXIST";

	public UserTermNoExistException() {
		super(ERROR_CODE);
	}
}

