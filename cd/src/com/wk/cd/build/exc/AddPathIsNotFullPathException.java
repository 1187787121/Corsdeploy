/**
 * Title: AddPathIsNotFullPathException.java
 * File Description: ·��[{DIR}]���ǺϷ���ȫ·��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160125
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: ·��[{DIR}]���ǺϷ���ȫ·��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class AddPathIsNotFullPathException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_ADD_PATH_IS_NOT_FULL_PATH";

	public AddPathIsNotFullPathException() {
		super(ERROR_CODE);
	}
}

