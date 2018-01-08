/**
 * Title: UpdateUserSocPrivAction.java
 * File Description: �޸��û�����ԴȨ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��9��
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserSocPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserSocPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸��û�����ԴȨ��
 * @author HT
 */
public class UpdateUserSocPrivAction extends ActionBasic<UpdateUserSocPrivInputBean, UpdateUserSocPrivOutputBean>{

	
	@Inject
	private UsUserSocPrivDaoService usUserSocPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: �޸��û�����ԴȨ��
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserSocPrivOutputBean doAction(UpdateUserSocPrivInputBean input) {
		logger.info("------UpdateUserSocPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserSocPrivOutputBean output = new UpdateUserSocPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserSocPrivInfo> soc_list = input.getSoc_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserSocPrivInputBean.USER_IDCN);
		
		usUserSocPrivDaoService.deleteAllSocByUserId(user_id);
		// �޸�
		if (!Assert.isEmpty(soc_list) && soc_list.size() != 0) {
			for (UsUserSocPrivInfo socPrivInfo : soc_list) {
				socPrivInfo.setUser_id(user_id);
				usUserSocPrivDaoService.insertInfo(socPrivInfo);
			}
		}
		
		logger.info("------UpdateUserSocPrivAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserSocPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("�޸��û�����ԴȨ��", log_lst);
	}

}
