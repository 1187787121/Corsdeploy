/**
 * Title: FileTableNameIsEmptyException.java
 * File Description: �嵥�ļ�[{LIST}]��[{NAMETABLE}]��[{REASON}]
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160524
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �嵥�ļ�[{LIST}]��[{NAMETABLE}]��[{REASON}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class FileTableNameIsEmptyException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_FILE_TABLE_NAME_IS_EMPTY";

	public FileTableNameIsEmptyException() {
		super(ERROR_CODE);
	}
}

