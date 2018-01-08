/**
 * Title: ModifyPjParamsNumberFormatException.java
 * File Description: 传入的参数[{PJPARAM}]不是数字类型。
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160105
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 传入的参数[{PJPARAM}]不是[{TYPE}]类型。
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ModifyPjParamsNumberFormatException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_MODIFY_PJ_PARAMS_NUMBER_FORMAT";

	public ModifyPjParamsNumberFormatException() {
		super(ERROR_CODE);
	}
}

