/**
 * Title: ListTarNameIsNotInFileNameTableException.java
 * File Description: �嵥�ļ�[{LIST}]��[{TABLE}]�ڡ��ļ����б���δ�ҵ���Ӧ��Ͷ������
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160523
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �嵥�ļ�[{LIST}]��[{TABLE}]�ڡ��ļ����б���δ�ҵ���Ӧ��Ͷ������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ListTarNameIsNotInFileNameTableException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_LIST_TAR_NAME_IS_NOT_IN_FILE_NAME_TABLE";

	public ListTarNameIsNotInFileNameTableException() {
		super(ERROR_CODE);
	}
}

