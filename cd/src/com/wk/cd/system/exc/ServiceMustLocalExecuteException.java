/**
 * Title: ServiceMustLocalExecuteException.java
 * File Description: 服务必须本地执行
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150106
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 服务必须本地执行
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class ServiceMustLocalExecuteException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_SERVICE_MUST_LOCAL_EXECUTE";

	public ServiceMustLocalExecuteException() {
		super(ERROR_CODE);
	}
}

