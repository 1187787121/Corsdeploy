/**
 * Title: WasParamsIpIsExistException.java
 * File Description: Was����Ip���Ϸ�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160614
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: Was����Ip���Ϸ�
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class WasParamsIpIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_WAS_PARAMS_IP_IS_EXIST";

	public WasParamsIpIsExistException() {
		super(ERROR_CODE);
	}
}

