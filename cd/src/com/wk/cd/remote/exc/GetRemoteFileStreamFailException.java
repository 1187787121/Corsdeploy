/**
 * Title: GetRemoteFileStreamFailException.java
 * File Description: ��ȡԶ���ļ���ʧ��{FILE}
 * @copyright 2014
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20141201
 */
package com.wk.cd.remote.exc;

import com.wk.lang.*;

/**
 * Class Description: ��ȡԶ���ļ���ʧ��{FILE}
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class GetRemoteFileStreamFailException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_GET_REMOTE_FILE_STREAM_FAIL";

	public GetRemoteFileStreamFailException() {
		super(ERROR_CODE);
	}
}

