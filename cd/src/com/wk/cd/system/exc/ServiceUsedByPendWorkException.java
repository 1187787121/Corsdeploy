/**
 * Title: ServiceUsedByPendWorkException.java
 * File Description: �����ڴ���������{PARM1}��ʹ��
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: �����ڴ���������{PARM1}��ʹ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ServiceUsedByPendWorkException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_SERVICE_USED_BY_PEND_WORK";

	public ServiceUsedByPendWorkException() {
		super(ERROR_CODE);
	}
}

