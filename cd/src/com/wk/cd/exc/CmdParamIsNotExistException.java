/**
 * Title: CmdParamIsNotExistException.java
 * File Description: ����[{CMD}]�в���[{PARAM}]δ�ҵ�ƥ�����
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160613
 */
package com.wk.cd.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����[{CMD}]�в���[{PARAM}]δ�ҵ�ƥ�����
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CmdParamIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_CMD_PARAM_IS_NOT_EXIST";

	public CmdParamIsNotExistException() {
		super(ERROR_CODE);
	}
}

