/**
 * Title: RecordNotFoundException.java
 * File Description: {TABLE}��{FIELD}��¼������
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {TABLE}��{FIELD}��¼������
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class RecordNotFoundException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RECORD_NOT_FOUND";

	public RecordNotFoundException() {
		super(ERROR_CODE);
	}
}

