/**
 * Title: WasParamsPassIsEmptyException.java
 * File Description: Was�����û�����Ϊ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160614
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: Was�����û�����Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class WasParamsPassIsEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_WAS_PARAMS_PASS_IS_EMPTY";

	public WasParamsPassIsEmptyException() {
		super(ERROR_CODE);
	}
}

