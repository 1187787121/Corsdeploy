/**
 * Title: GetVersionListException.java
 * File Description: ��ȡ�汾���б�ʧ��[{REASON}]
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170222
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: ��ȡ�汾���б�ʧ��[{REASON}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GetVersionListException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_GET_VERSION_LIST";

	public GetVersionListException() {
		super(ERROR_CODE);
	}
}

