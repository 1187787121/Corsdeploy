/**
 * Title: GetVersionListFileException.java
 * File Description: �汾�嵥[{LIST}]������
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170222
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: �汾�嵥[{LIST}]������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GetVersionListFileException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_GET_VERSION_LIST_FILE";

	public GetVersionListFileException() {
		super(ERROR_CODE);
	}
}

