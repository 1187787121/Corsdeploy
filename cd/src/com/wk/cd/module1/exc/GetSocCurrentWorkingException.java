/**
 * Title: GetSocCurrentWorkingException.java
 * File Description: ��ȡ�ڵ�[{SOC}]��ǰ�û�����Ŀ¼ʧ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160626
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ��ȡ�ڵ�[{SOC}]��ǰ�û�����Ŀ¼ʧ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class GetSocCurrentWorkingException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_GET_SOC_CURRENT_WORKING";

	public GetSocCurrentWorkingException() {
		super(ERROR_CODE);
	}
}

