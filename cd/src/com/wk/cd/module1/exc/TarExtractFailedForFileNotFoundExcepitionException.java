/**
 * Title: TarExtractFailedForFileNotFoundExcepitionException.java
 * File Description: 解压上传投产包[{FILE}]失败，因为[{FILE}]未找到
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160105
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 解压上传投产包[{FILE}]未找到

 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TarExtractFailedForFileNotFoundExcepitionException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TAR_EXTRACT_FAILED_FOR_FILE_NOT_FOUND_EXCEPITION";

	public TarExtractFailedForFileNotFoundExcepitionException() {
		super(ERROR_CODE);
	}
}

