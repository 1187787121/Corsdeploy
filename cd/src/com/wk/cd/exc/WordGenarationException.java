/**
 * Title: WordGenarationException.java
 * File Description: word���ɴ���[{NAME}]
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151019
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: word���ɴ���[{NAME}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class WordGenarationException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_WORD_GENARATION";

	public WordGenarationException() {
		super(ERROR_CODE);
	}
}

