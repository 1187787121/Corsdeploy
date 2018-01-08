/**
 * Title: CheckProtocolTypeException.java
 * File Description: 不支持[{TYPE}]协议类型
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170816
 */
package com.wk.cd.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 不支持[{TYPE}]协议类型
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CheckProtocolTypeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_CHECK_PROTOCOL_TYPE";

	public CheckProtocolTypeException() {
		super(ERROR_CODE);
	}
}

