/**
 * Title: PdfGenarationException.java
 * File Description: pdf���ɴ���[{REASON}]
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160720
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: pdf���ɴ���[{REASON}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class PdfGenarationException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_PDF_GENARATION";

	public PdfGenarationException() {
		super(ERROR_CODE);
	}
}

