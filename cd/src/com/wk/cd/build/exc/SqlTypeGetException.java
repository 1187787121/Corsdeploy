/**
 * Title: SqlTypeGetException.java
 * File Description: ��ȡ[{SQL}]���ʧ�ܻ������֧��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160811
 */
package com.wk.cd.build.exc;

import com.wk.lang.*;

/**
 * Class Description: ��ȡ[{SQL}]���ʧ�ܻ������֧��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SqlTypeGetException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_PROBLEM_SQL_TYPE_GET";

	public SqlTypeGetException() {
		super(ERROR_CODE);
	}
}

