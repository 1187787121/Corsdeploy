/**
 * Title: DonNotSupportStepExecuteException.java
 * File Description: {BUSI}ϵͳ�£�{PROJ}��Ŀ��֧�ְ�����ִ������׼��
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151215
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {BUSI}ϵͳ�£�{PROJ}��Ŀ��֧�ְ�����ִ������׼��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class DonNotSupportStepExecuteException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_DON_NOT_SUPPORT_STEP_EXECUTE";

	public DonNotSupportStepExecuteException() {
		super(ERROR_CODE);
	}
}

