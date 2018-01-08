/**
 * Title: TpParamsListIsEmptyException.java
 * File Description: 模版参数列表为空
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160115
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 模版参数列表为空
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpParamsListIsEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_PARAMS_LIST_IS_EMPTY";

	public TpParamsListIsEmptyException() {
		super(ERROR_CODE);
	}
}

