/**
 * Title: TemplateSocTagNotExistInNodeException.java
 * File Description: �ڵ㣺[{NODE}]��û������Դ��ǩ��[{SOCTAG}]��Ӧ������Դ
 * @copyright 2015
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20151230
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �ڵ�[{NODE}]��û������Դ��ǩ[{SOCTAG}]��Ӧ������Դ
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class TemplateSocTagNotExistInNodeException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_TEMPLATE_SOC_TAG_NOT_EXIST_IN_NODE";
											  
	public TemplateSocTagNotExistInNodeException() {
		super(ERROR_CODE);
	}
}

