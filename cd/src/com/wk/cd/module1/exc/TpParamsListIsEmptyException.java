/**
 * Title: TpParamsListIsEmptyException.java
 * File Description: ģ������б�Ϊ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160115
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ģ������б�Ϊ��
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

