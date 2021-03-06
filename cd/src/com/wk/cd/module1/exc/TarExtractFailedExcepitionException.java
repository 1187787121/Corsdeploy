/**
 * Title: TarExtractFailedExcepitionException.java
 * File Description: 解压上传投产包[{FILE}]失败
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160105
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 解压上传投产包[{FILE}]失败
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TarExtractFailedExcepitionException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TAR_EXTRACT_FAILED_EXCEPITION";

	public TarExtractFailedExcepitionException() {
		super(ERROR_CODE);
	}
}

