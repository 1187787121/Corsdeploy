/**
 * Title: ScriptExecErrorException.java
 * File Description: ½Å±¾Ö´ÐÐ´íÎó{SCRIPT}
 * @copyright 2014
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20141204
 */
package com.wk.cd.remote.exc;

import com.wk.lang.*;

/**
 * Class Description: ½Å±¾Ö´ÐÐ´íÎó{SCRIPT}
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class ScriptExecErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_SCRIPT_EXEC_ERROR";

	public ScriptExecErrorException() {
		super(ERROR_CODE);
	}
}

