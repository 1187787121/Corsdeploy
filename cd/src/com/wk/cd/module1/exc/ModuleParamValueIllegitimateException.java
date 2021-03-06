/**
 * Title: ModuleParamValueIllegitimateException.java
 * File Description: 参数值[{VALUE}]不合法
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170223
 */
package com.wk.cd.module1.exc;

import com.wk.lang.*;

/**
 * Class Description: 参数值[{VALUE}]不合法
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ModuleParamValueIllegitimateException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_MODULE_MODULE_PARAM_VALUE_ILLEGITIMATE";

	public ModuleParamValueIllegitimateException() {
		super(ERROR_CODE);
	}
}

