/**
 * Title: ExecuteErrorCmdException.java
 * File Description: ��Ŀ[{PROJ}]�б���������ܼ���ִ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160328
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��Ŀ[{PROJ}]�б���������ܼ���ִ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ExecuteErrorCmdException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_EXECUTE_ERROR_CMD";

	public ExecuteErrorCmdException() {
		super(ERROR_CODE);
	}
}

