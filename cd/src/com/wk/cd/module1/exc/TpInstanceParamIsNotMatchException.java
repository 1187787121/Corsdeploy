/**
 * Title: TpInstanceParamIsNotMatchException.java
 * File Description: ǰ�δ���Ĳ����б���Ϣ��ģ��ʵ��������Ϣ��ƥ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160623
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ǰ�δ���Ĳ����б���Ϣ��ģ��ʵ��������Ϣ��ƥ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TpInstanceParamIsNotMatchException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TP_INSTANCE_PARAM_IS_NOT_MATCH";

	public TpInstanceParamIsNotMatchException() {
		super(ERROR_CODE);
	}
}

