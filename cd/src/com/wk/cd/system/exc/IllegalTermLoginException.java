/**
 * Title: IllegalTermLoginException.java
 * File Description: ·Ç·¨ÖÕ¶ËµÇÂ¼[{TERM_NO}]
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20150311
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: ·Ç·¨ÖÕ¶ËµÇÂ¼[{TERM_NO}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class IllegalTermLoginException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_ILLEGAL_TERM_LOGIN";

	public IllegalTermLoginException() {
		super(ERROR_CODE);
	}
}

