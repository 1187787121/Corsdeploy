/**
 * Title: SocTagIsNotExistExcepionException.java
 * File Description: �ڵ�[{NODE}]�е�"����Դ��"��"����Դ��ǩ"������Ϊ��
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160223
 */
package com.wk.cd.module1.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: �ڵ�[{NODE}]�е�"����Դ��"��"����Դ��ǩ"������Ϊ��
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SocTagIsNotExistException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOC_TAG_IS_NOT_EXIST_EXCEPION";

	public SocTagIsNotExistException() {
		super(ERROR_CODE);
	}
}

