/**
 * Title: PjProblemTypeListRepeatException.java
 * File Description: Ͷ�������������ͳ����ظ�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160426
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: Ͷ�������������ͳ����ظ�
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PjProblemTypeListRepeatException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_PJ_PROBLEM_TYPE_LIST_REPEAT";

	public PjProblemTypeListRepeatException() {
		super(ERROR_CODE);
	}
}

