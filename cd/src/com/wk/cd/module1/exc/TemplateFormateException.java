/**
 * Title: TemplateFormateException.java
 * File Description: {FILE}�ļ���;{TABLE}��;��{PARM}���ֶβ����ϸ�ʽҪ��
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151020
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {FILE}�ļ���;{TABLE}��;��{PARM}���ֶβ����ϸ�ʽҪ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TemplateFormateException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TEMPLATE_FORMATE";

	public TemplateFormateException() {
		super(ERROR_CODE);
	}
}

