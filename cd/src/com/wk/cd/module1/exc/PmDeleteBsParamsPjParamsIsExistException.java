/**
 * Title: PmDeleteBsParamsPjParamsIsExistException.java
 * File Description: ����[{BSPARAM}]��ϵͳ[{PROJ}]�б�ʹ�ã�����ɾ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160105
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����[{BSPARAM}]����Ŀ[{PROJ}]�б�ʹ�ã�����ʧ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PmDeleteBsParamsPjParamsIsExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_PM_DELETE_BS_PARAMS_PJ_PARAMS_IS_EXIST";

	public PmDeleteBsParamsPjParamsIsExistException() {
		super(ERROR_CODE);
	}
}

