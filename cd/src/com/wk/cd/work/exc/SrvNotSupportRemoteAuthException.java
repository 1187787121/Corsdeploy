/**
 * Title: SrvNotSupportRemoteAuthException.java
 * File Description: ����{OPT}��֧��Զ����Ȩ
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141210
 */
package com.wk.cd.work.exc;

import com.wk.lang.*;

/**
 * Class Description: ����{OPT}��֧��Զ����Ȩ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SrvNotSupportRemoteAuthException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORK_SRV_NOT_SUPPORT_REMOTE_AUTH";

	public SrvNotSupportRemoteAuthException() {
		super(ERROR_CODE);
	}
}

