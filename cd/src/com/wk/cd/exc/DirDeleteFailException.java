/**
 * Title: DirDeleteFailException.java
 * File Description: Ŀ¼{DIR}ɾ��ʧ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161117
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: Ŀ¼{DIR}ɾ��ʧ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class DirDeleteFailException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_DIR_DELETE_FAIL";

	public DirDeleteFailException() {
		super(ERROR_CODE);
	}
}

