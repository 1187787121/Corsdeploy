/**
 * Title: EnvStorageTaskIsExistException.java
 * File Description: ����[{ENV}]�´�������е����񣬲������
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170302
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: ����[{ENV}]�´�������е����񣬲������
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

