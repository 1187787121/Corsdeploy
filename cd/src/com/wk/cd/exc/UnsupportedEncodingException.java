/**
 * Title: UnsupportedEncodingException.java
 * File Description: 不支持的字符集
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160826
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: 不支持的字符集
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class UnsupportedEncodingException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_UNSUPPORTED_ENCODING";

	public UnsupportedEncodingException() {
		super(ERROR_CODE);
	}
}

