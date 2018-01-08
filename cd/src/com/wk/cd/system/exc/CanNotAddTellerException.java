/**
 * Title: CanNotAddTellerException.java
 * File Description: {PARM}对应机构号为空，不能维护柜员信息
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: {PARM}对应机构号为空，不能维护柜员信息
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class CanNotAddTellerException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_CANNOT_ADD_TELLER";

	public CanNotAddTellerException() {
		super(ERROR_CODE);
	}
}

