/**
 * Title: RegisterServiceErrorException.java
 * File Description: ע�����{SRV_NAME}����
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: ע�����{SRV_NAME}����
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class RegisterServiceErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REGISTER_SERVICE_ERROR";

	public RegisterServiceErrorException() {
		super(ERROR_CODE);
	}
}

