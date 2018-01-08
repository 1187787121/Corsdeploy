/**
 * Title: RecordAlreadyExistException.java
 * File Description: {TABLE}中{FIELD}记录已存在
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {TABLE}中{FIELD}记录已存在
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

