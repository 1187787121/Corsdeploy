/**
 * Title: RemoteSocTypeNotFtpOrSftpException.java
 * File Description: ����Դ{SOC}��Э�����Ͳ���FTP��SFTP
 * @copyright 2016
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20160517
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: ����Դ{SOC}��Э�����Ͳ���FTP��SFTP
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class RemoteSocTypeNotFtpOrSftpException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_REMOTE_SOC_TYPE_NOT_FTP_OR_SFTP";

	public RemoteSocTypeNotFtpOrSftpException() {
		super(ERROR_CODE);
	}
}

