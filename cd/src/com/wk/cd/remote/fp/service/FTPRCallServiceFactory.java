/**
 * Title: FTPRCallServiceFactory.java
 * File Description: FTPԶ�̵���ʵ������
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/25/2014
 */

package com.wk.cd.remote.fp.service;
import java.util.HashMap;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.BeanTool;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.exc.DataErrorException;

/**
 * Class Description:FTPԶ�̵���ʵ������
 * @author lixl
 */
class FTPRCallServiceFactory {
	private static Map<String, FTPRCallInterface> ftp_cache 
		= new HashMap<String, FTPRCallInterface>();
	/**
	 * ��ȡFTP����ʵ��
	 * @author lixl (2014-11-26)
	 * @param pro_type Э������(ֻ����FTP���͵�һ��)
	 * @return FTPRCallInterface 
	 */
	static FTPRCallInterface getFtpService(PROTOCOL_TYPE pro_type){
		Assert.assertNotEmpty(pro_type, "Э������");

		FTPRCallInterface inst = ftp_cache.get(pro_type.getName());
		if(inst == null) {
			inst = getInstance(pro_type);
			ftp_cache.put(pro_type.getName(), inst);
		}
		return inst;
	}

	private static FTPRCallInterface getInstance(PROTOCOL_TYPE pro_type){
		if(pro_type == PROTOCOL_TYPE.AS400_FTP) {
			return BeanTool.getBeanByClzz(AS400FTPRCallService.class);
		}else if(pro_type == PROTOCOL_TYPE.PLT_FTP){
			return BeanTool.getBeanByClzz(PLTFTPRCallService.class);
		}else if(pro_type == PROTOCOL_TYPE.SCP_FTP){
			return BeanTool.getBeanByClzz(SCPFTPRCallService.class);
		}else if(pro_type == PROTOCOL_TYPE.SFTP || pro_type == PROTOCOL_TYPE.SSH){
			return BeanTool.getBeanByClzz(SFTPJSCHRCallService.class);
		}else{
			throw new DataErrorException().addScene("INPUT", 
									"Э������:"+pro_type.getValue());
		}
	}
}
