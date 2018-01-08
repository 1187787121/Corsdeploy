/**
 * Title: IgUserService.java
 * File Description: �������û�������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016��10��24��
 */
package com.wk.cd.dlk.us.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.dlk.us.dao.IgUserDaoService;
import com.wk.cd.dlk.us.info.IgUserInfo;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �������û�������
 * @author HT
 */
public class IgUserService {
	@Inject private IgUserDaoService igUserDaoService;
	
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ��ѯ�û���Ϣ
	 * @param userid
	 * @return
	 */
	public IgUserInfo getIgUserInfoByKey(String userid) {
		IgUserInfo info = new IgUserInfo();
		info.setUserid(userid);
		info = igUserDaoService.getInfoByKey(info);
		if(Assert.isEmpty(info)){
			throw new RecordNotFoundException().addScene("TABLE", IgUserInfo.TABLECN).addScene(
					"FIELD", userid);
		}
		return info;
	}
}
