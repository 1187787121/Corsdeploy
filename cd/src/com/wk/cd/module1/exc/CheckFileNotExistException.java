/**
 * Title: CheckFileNotExistException.java
 * File Description: 下载失败：[{FILE}]校验文件不存在
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160408
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 下载失败：[{FILE}]校验文件不存在
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class CheckFileNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_CHECK_FILE_NOT_EXIST";

	public CheckFileNotExistException() {
		super(ERROR_CODE);
	}
}

