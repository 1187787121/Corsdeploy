/**
 * Title: AddUserContactAction.java
 * File Description: 添加用户常用联系人
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月18日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.AddUserContactInputBean;
import com.wk.cd.system.us.bean.AddUserContactOutputBean;
import com.wk.cd.system.us.dao.UsUserContactDaoService;
import com.wk.cd.system.us.info.UsUserContactInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 添加用户常用联系人
 * @author HT
 */
public class AddUserContactAction extends ActionBasic<AddUserContactInputBean, AddUserContactOutputBean>{
	
	@Inject
	private UsUserContactDaoService contactDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 添加用户常用联系人
	 * @param input
	 * @return 
	 */
	@Override
	protected AddUserContactOutputBean doAction(AddUserContactInputBean input) {
		logger.info("------AddUserContactAction begin------");
		logger.debug("------user_id=[{}]--contact_user_id=[{}]",input.getOrg_user_id(),input.getContact_user_id());
		
		AddUserContactOutputBean output = new AddUserContactOutputBean();
		String user_id=input.getOrg_user_id();
		String contact_user_id=input.getContact_user_id();
		
		Assert.assertNotEmpty(user_id, AddUserContactInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(contact_user_id,AddUserContactInputBean.CONTACT_USER_IDCN);
		
		contactDaoService.checkUserContactNotExist(user_id,contact_user_id);
		
		UsUserContactInfo contactInfo=new UsUserContactInfo();
		contactInfo.setUser_id(user_id);
		contactInfo.setContact_user_id(contact_user_id);
		contactDaoService.insertInfo(contactInfo);
		
		logger.info("------AddUserContactAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddUserContactInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		log_lst.add(input.getContact_user_id());
		return lgsvc.getLogTxt("添加用户常用联系人", log_lst);
	}
}
