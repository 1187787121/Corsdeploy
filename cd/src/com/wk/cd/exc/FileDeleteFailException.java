/**
 * Title: FileDeleteFailException.java
 * File Description: �ļ�{FILE}ɾ��ʧ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161115
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: �ļ�{FILE}ɾ��ʧ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class FileDeleteFailException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_FILE_DELETE_FAIL";

	public FileDeleteFailException() {
		super(ERROR_CODE);
	}
}

