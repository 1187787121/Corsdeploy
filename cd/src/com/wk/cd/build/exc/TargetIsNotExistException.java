/**
 * Title: TargetIsNotExistException.java
 * File Description: �����Ϣ[{STORID}]�Ѿ�����
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161119
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �����Ϣ[{STORID}]�Ѿ�����
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TargetIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_TARGET_IS_NOT_EXIST";

	public TargetIsNotExistException() {
		super(ERROR_CODE);
	}
}

