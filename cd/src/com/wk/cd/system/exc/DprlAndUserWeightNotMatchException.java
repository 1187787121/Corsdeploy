/**
 * Title: DprlAndUserWeightNotMatchException.java
 * File Description: ���Ž�ɫ���û�Ȩ����Ϣ��ƥ��
 * @copyright 2015
 * @company CORSWORK
 * @author AntoGen
 * @version 1.0
 * @date 20150228
 */
package com.wk.cd.system.exc;

import com.wk.lang.*;

/**
 * Class Description: ���Ž�ɫ���û�Ȩ����Ϣ��ƥ��
 * @author AntoGen
 */
@SuppressWarnings("serial")
public class DprlAndUserWeightNotMatchException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_SYSTEM_DPRL_AND_USER_WEIGHT_NOT_MATCH";

	public DprlAndUserWeightNotMatchException() {
		super(ERROR_CODE);
	}
}

