/**
 * Title: FileListTitleException.java
 * File Description: �嵥�ļ�[�ļ����б�]�б����ֶ�[{MSG}]
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160621
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �嵥�ļ�[�ļ����б�]�б����ֶ�[{MSG}]
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class FileListTitleException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_FILE_LIST_TITLE";

	public FileListTitleException() {
		super(ERROR_CODE);
	}
}

