/**
 * Title: ExecutePjIsEndException.java
 * File Description: ��Ŀ[{PROJ}]���ڽ���״̬��������ִ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160406
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��Ŀ[{PROJ}]���ڽ���״̬��������ִ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ExecutePjIsEndException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_EXECUTE_PJ_IS_END";

	public ExecutePjIsEndException() {
		super(ERROR_CODE);
	}
}

