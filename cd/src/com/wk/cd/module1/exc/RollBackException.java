/**
 * Title: RollBackException.java
 * File Description: [{SYSTEM}]系统下的项目[{PROJ}]回退失败:[{RES}]
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160418
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: [{SYSTEM}]系统下的项目[{PROJ}]回退失败:[{RES}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class RollBackException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_ROLL_BACK";

	public RollBackException() {
		super(ERROR_CODE);
	}
}

