/**
 * Title: ProcessTemplateWithoutIDException.java
 * File Description: 流程模板[{NAME}]中没有模板ID
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161102
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 流程模板[{NAME}]中没有模板ID
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ProcessTemplateWithoutIDException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_PROCESS_TEMPLATE_WITHOUT_I_D";

	public ProcessTemplateWithoutIDException() {
		super(ERROR_CODE);
	}
}

