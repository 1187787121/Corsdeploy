/**
 * Title: GenerationConditionExecuteException.java
 * File Description: ��������[{CONDITION}]��������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160720
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��������[{CONDITION}]��������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GenerationConditionExecuteException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GENERATION_CONDITION_EXECUTE";

	public GenerationConditionExecuteException() {
		super(ERROR_CODE);
	}
}

