/**
 * Title: RequestDataRegistErrorException.java
 * File Description: �������ݵǼ�ʧ��{SEQ}
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: �������ݵǼ�ʧ��{SEQ}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class RequestDataRegistErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REQUEST_DATA_REGIST_ERROR";

	public RequestDataRegistErrorException() {
		super(ERROR_CODE);
	}
}

