/**
 * Title: GetFileDocumentException.java
 * File Description: ��ȡ�ļ�[{PATH}]DOMʧ�ܡ�
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20161031
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��ȡ�ļ�[{PATH}]DOMʧ�ܡ�
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GetFileDocumentException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GET_FILE_DOCUMENT";

	public GetFileDocumentException() {
		super(ERROR_CODE);
	}
}

