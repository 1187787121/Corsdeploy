/**
 * Title: RollbackCommandNotExistException.java
 * File Description: 项目[{PROJ}]回退命令不存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160512
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 项目[{PROJ}]回退命令不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class RollbackCommandNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_ROLLBACK_COMMAND_NOT_EXIST";

	public RollbackCommandNotExistException() {
		super(ERROR_CODE);
	}
}

