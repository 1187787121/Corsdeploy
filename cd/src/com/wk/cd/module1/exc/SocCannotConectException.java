/**
 * Title: SocCannotConectException.java
 * File Description: ����Դ[{SOC}]���ܽ�������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160712
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����Դ[{SOC}]���ܽ�������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SocCannotConectException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOC_CANNOT_CONECT";

	public SocCannotConectException() {
		super(ERROR_CODE);
	}
}

