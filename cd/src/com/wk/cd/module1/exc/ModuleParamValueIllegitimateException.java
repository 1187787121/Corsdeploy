/**
 * Title: ModuleParamValueIllegitimateException.java
 * File Description: ����ֵ[{VALUE}]���Ϸ�
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170223
 */
package com.wk.cd.module1.exc;

import com.wk.lang.*;

/**
 * Class Description: ����ֵ[{VALUE}]���Ϸ�
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
