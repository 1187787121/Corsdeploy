/**
 * Title: TargetTaskTypeException.java
 * File Description: 环境[{ENV}]下存在[{TASK}]任务，不可入库
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161119
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 环境[{ENV}]下存在[{TASK}]任务，不可入库
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TargetTaskTypeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_TARGET_TASK_TYPE";

	public TargetTaskTypeException() {
		super(ERROR_CODE);
	}
}

