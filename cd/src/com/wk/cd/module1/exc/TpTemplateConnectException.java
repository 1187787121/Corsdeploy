/**
 * Title: TpTemplateFtpConnectException.java
 * File Description: δ�ܳɹ���������
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151022
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: {SOURCE}δ�ܳɹ���������
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpTemplateConnectException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_TEMPLATE_CONNECT";

	public TpTemplateConnectException() {
		super(ERROR_CODE);
	}
}
