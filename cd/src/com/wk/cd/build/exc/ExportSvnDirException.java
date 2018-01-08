/**
 * Title: ExportSvnDirException.java
 * File Description: 导出版本目录失败
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170220
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: 导出版本目录失败
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ExportSvnDirException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_BUILD_EXPORT_SVN_DIR";

	public ExportSvnDirException() {
		super(ERROR_CODE);
	}
}

