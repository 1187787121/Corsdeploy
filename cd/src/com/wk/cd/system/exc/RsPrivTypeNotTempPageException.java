/**
 * Title: RsPrivTypeNotTempPageException.java
 * File Description: 延期资源权限非页面资源
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160324
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: 延期资源权限非页面资源
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class RsPrivTypeNotTempPageException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_RS_PRIV_TYPE_NOT_TEMP_PAGE";

	public RsPrivTypeNotTempPageException() {
		super(ERROR_CODE);
	}
}

