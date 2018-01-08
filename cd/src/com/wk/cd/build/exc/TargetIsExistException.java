/**
 * Title: TargetIsExistException.java
 * File Description: 入库信息[{STORID}]不存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161119
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 入库信息[{STORID}]不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TargetIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_TARGET_IS_EXIST";

	public TargetIsExistException() {
		super(ERROR_CODE);
	}
}

