/**
 * Title: EnvTagStorgProjIsExistException.java
 * File Description: ����Ŀ����������������[{PROJ}],����ɾ��
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170109
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: ����Ŀ����������������[{PROJ}],����ɾ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class EnvTagStorgProjIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_ENV_TAG_STORG_PROJ_IS_EXIST";

	public EnvTagStorgProjIsExistException() {
		super(ERROR_CODE);
	}
}

