/**
 * Title: ExcelFileIOException.java
 * File Description: {FILE}传入的Excel:{PARM}
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151020
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: {FILE}传入的Excel:{PARM}
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ExcelFileIOException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_EXCEL_FILE_I_O";

	public ExcelFileIOException() {
		super(ERROR_CODE);
	}
}

