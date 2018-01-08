/**
 * Title: FileTransferFailException.java
 * File Description: �ļ�{FILE}����ʧ��
 * @copyright 2014
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20141201
 */
package com.wk.cd.remote.exc;

import com.wk.lang.*;

/**
 * Class Description: �ļ�{FILE}����ʧ��
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class FileTransferFailException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_FILE_TRANSFER_FAIL";

	public FileTransferFailException() {
		super(ERROR_CODE);
	}
}

