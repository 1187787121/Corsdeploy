/**
 * Title: ExeCompModuleTypeExistException.java
 * File Description: �����ִ��������ѡ����Դ[{SOC}]�����һ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161213
 */
package com.wk.cd.build.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �����ִ��������ѡ����Դ[{SOC}]�����һ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ExeCompModuleTypeExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_EXE_COMP_MODULE_TYPE_EXIST";

	public ExeCompModuleTypeExistException() {
		super(ERROR_CODE);
	}
}

