/**
 * Title: UserTermNoExistException.java
 * File Description: �û������ն�δ��Ȩ������������ն�
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151114
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: �û������ն�δ��Ȩ������������ն�
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

