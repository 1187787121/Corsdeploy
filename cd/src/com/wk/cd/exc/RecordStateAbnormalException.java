/**
 * Title: RecordStateAbnormalException.java
 * File Description: {TABLE}中{FIELD}记录状态不正常
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {TABLE}中{FIELD}记录状态不正常
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class RecordStateAbnormalException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RECORD_STATE_ABNORMAL";

	public RecordStateAbnormalException() {
		super(ERROR_CODE);
	}
}

