/**
 * Title: InvalidLicenseException.java
 * File Description: ��Ч��License���
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161014
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: ��Ч��License���
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class InvalidLicenseException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_INVALID_LICENSE";

	public InvalidLicenseException() {
		super(ERROR_CODE);
	}
}

