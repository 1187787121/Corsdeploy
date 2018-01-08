/**
 * Title: RecordAlreadyExistException.java
 * File Description: {TABLE}��{FIELD}��¼�Ѵ���
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {TABLE}��{FIELD}��¼�Ѵ���
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class RecordAlreadyExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RECORD_ALREADY_EXIST";

	public RecordAlreadyExistException() {
		super(ERROR_CODE);
	}
}

