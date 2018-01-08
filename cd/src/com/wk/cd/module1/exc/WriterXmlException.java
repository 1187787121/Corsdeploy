/**
 * Title: WriterXmlException.java
 * File Description: 写入XML文件[{PATH}]失败
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161031
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: 写入XML文件[{PATH}]失败
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class WriterXmlException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_WRITER_XML";

	public WriterXmlException() {
		super(ERROR_CODE);
	}
}

