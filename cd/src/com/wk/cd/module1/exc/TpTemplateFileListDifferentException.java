/**
 * Title: TpTemplateFileListDifferentException.java
 * File Description: {PARM}���ļ��б�һ��
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151021
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {PARM}���ļ��б�һ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpTemplateFileListDifferentException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_TEMPLATE_FILE_LIST_DIFFERENT";

	public TpTemplateFileListDifferentException() {
		super(ERROR_CODE);
	}
}

