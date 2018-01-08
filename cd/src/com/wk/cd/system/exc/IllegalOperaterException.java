/**
 * Title: IllegalOperaterException.java
 * File Description: {PARM1}无权限操作{PARM2}
 * @copyright 2014
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20141126
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: {PARM1}无权限操作{PARM2}
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class IllegalOperaterException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_ILLEGAL_OPERATER";

	public IllegalOperaterException() {
		super(ERROR_CODE);
	}
}

