/**
 * Title: SourceTypeNotSupportRemoteFileException.java
 * File Description: ����Դ[{SOC}]Э������Ϊ[{PROTOCOL}]��֧�ֲ鿴Զ���ļ���Ŀ¼
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160520
 */
package com.wk.cd.exc;

import com.wk.lang.RuntimeBussinessException;

/**
 * Class Description: ����Դ[{SOC}]Э������Ϊ[{PROTOCOL}]��֧�ֲ鿴Զ���ļ���Ŀ¼
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class SourceTypeNotSupportRemoteFileException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_RELEASE_SOURCE_TYPE_NOT_SUPPORT_REMOTE_FILE";

	public SourceTypeNotSupportRemoteFileException() {
		super(ERROR_CODE);
	}
}

