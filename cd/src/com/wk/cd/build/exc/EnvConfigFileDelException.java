/**
 * Title: EnvConfigFileDelException.java
 * File Description: ���������ļ�ɾ������:{REASON}
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161118
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ���������ļ�ɾ������:{REASON}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvConfigFileDelException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_CONFIG_FILE_DEL";

	public EnvConfigFileDelException() {
		super(ERROR_CODE);
	}
}

