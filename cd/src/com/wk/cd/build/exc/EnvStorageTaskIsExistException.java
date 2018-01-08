/**
 * Title: EnvStorageTaskIsExistException.java
 * File Description: 环境[{ENV}]下存在入库中的任务，不可入库
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170302
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: 环境[{ENV}]下存在入库中的任务，不可入库
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvStorageTaskIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_STORAGE_TASK_IS_EXIST";

	public EnvStorageTaskIsExistException() {
		super(ERROR_CODE);
	}
}

