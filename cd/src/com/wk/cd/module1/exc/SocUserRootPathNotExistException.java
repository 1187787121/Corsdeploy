/**
 * Title: SocUserRootPathNotExistException.java
 * File Description: ����Դ[{SOC}]���û���Ŀ¼[{DIR}]������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160712
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����Դ[{SOC}]���û���Ŀ¼[{DIR}]������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SocUserRootPathNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOC_USER_ROOT_PATH_NOT_EXIST";

	public SocUserRootPathNotExistException() {
		super(ERROR_CODE);
	}
}

