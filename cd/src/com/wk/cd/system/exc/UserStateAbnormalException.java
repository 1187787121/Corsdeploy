/**
 * Title: UserStateAbnormalException.java
 * File Description: {TABLE}中{FIELD}记录状态不正常
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141208
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: {TABLE}中{FIELD}记录状态不正常
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class UserStateAbnormalException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_USER_STATE_ABNORMAL";

	public UserStateAbnormalException() {
		super(ERROR_CODE);
	}
}

