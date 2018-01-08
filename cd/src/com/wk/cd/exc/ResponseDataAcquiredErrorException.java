/**
 * Title: ResponseDataAcquiredErrorException.java
 * File Description: 响应数据获取失败{SEQ}
 * @copyright 2014
 * @company CORSWORK
 * @author AnutoGen
 * @version 1.0
 * @date 20141224
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: 响应数据获取失败{SEQ}
 * @author AnutoGen
 */
@SuppressWarnings("serial")
public class ResponseDataAcquiredErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RESPONSE_DATA_ACQUIRED_ERROR";

	public ResponseDataAcquiredErrorException() {
		super(ERROR_CODE);
	}
}

