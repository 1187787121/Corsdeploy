/**
 * Title: DataErrorException.java
 * File Description: {INPUT}不正确
 * @copyright 2014
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20141223
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {INPUT}不正确
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class DataErrorException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_DATA_ERROR";

	public DataErrorException() {
		super(ERROR_CODE);
	}
}

