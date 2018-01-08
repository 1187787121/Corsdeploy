/**
 * Title: SourceTypeNotSupportRemoteFileException.java
 * File Description: 数据源[{SOC}]协议类型为[{PROTOCOL}]不支持查看远程文件和目录
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160520
 */
package com.wk.cd.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 数据源[{SOC}]协议类型为[{PROTOCOL}]不支持查看远程文件和目录
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SourceTypeNotSupportRemoteFileException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOURCE_TYPE_NOT_SUPPORT_REMOTE_FILE";

	public SourceTypeNotSupportRemoteFileException() {
		super(ERROR_CODE);
	}
}

