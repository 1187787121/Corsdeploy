/**
 * Title: CryptErrorException.java
 * File Description: 加解密错误{E}
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: 加解密错误{E}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CryptErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_CRYPT_ERROR";

	public CryptErrorException() {
		super(ERROR_CODE);
	}
}

