/**
 * Title: FileListBooleanException.java
 * File Description: �嵥�ļ����ļ����б��д����ַ���[CHECK]�����ж���Booleanֵ
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160621
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �嵥�ļ����ļ����б��д����ַ���[CHECK]�����ж���Booleanֵ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class FileListBooleanException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_FILE_LIST_BOOLEAN";

	public FileListBooleanException() {
		super(ERROR_CODE);
	}
}

