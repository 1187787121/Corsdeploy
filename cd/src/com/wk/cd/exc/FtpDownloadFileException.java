/**
 * Title: FtpDownloadFileException.java
 * File Description: 无下载文件或文件不对称
 * @copyright 2017
 * @company CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 20170408
 */
package com.wk.cd.exc;

import com.wk.lang.*;

/**
 * Class Description: 无下载文件或文件不对称
 * @author AutoGen
 */
@SuppressWarnings("serial")
public class FtpDownloadFileException
	extends RuntimeBussinessException {

	private static final String ERROR_CODE = "APP_FTP_DOWNLOAD_FILE";

	public FtpDownloadFileException() {
		super(ERROR_CODE);
	}
}

