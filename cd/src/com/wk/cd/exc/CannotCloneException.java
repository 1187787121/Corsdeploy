/**
 * Title: CannotCloneException.java
 * File Description: ��{CLASS}û�и��Ʒ���
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: ��{CLASS}û�и��Ʒ���
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class CannotCloneException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_CANNOT_CLONE";

	public CannotCloneException() {
		super(ERROR_CODE);
	}
}

