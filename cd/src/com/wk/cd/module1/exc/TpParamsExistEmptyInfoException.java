/**
 * Title: TpParamsExistEmptyInfoException.java
 * File Description: 模版参数列表中有空的信息对儿
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160115
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 模版参数列表中有空的信息对儿
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpParamsExistEmptyInfoException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_PARAMS_EXIST_EMPTY_INFO";

	public TpParamsExistEmptyInfoException() {
		super(ERROR_CODE);
	}
}

