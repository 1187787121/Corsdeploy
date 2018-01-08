/**
 * Title: AsyncSystemNotStartException.java
 * File Description: ��{CLASS}û�и��Ʒ���
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.async.exc;

import com.wk.lang.*;

/**
 * Class Description: �첽ϵͳδ����{SYS_ID}
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class AsyncSystemNotStartException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_ASYNC_SYSTEM_NOT_START";

	public AsyncSystemNotStartException() {
		super(ERROR_CODE);
	}
}