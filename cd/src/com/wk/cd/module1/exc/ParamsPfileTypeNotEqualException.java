/**
 * Title: ParamsPfileTypeNotEqualException.java
 * File Description: ��ӵ���Դ�����ļ���׺���Ͳ������Ͳ�һ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160114
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��ӵ���Դ�����ļ���׺���Ͳ������Ͳ�һ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class ParamsPfileTypeNotEqualException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_PARAMS_PFILE_TYPE_NOT_EQUAL";

	public ParamsPfileTypeNotEqualException() {
		super(ERROR_CODE);
	}
}

