/**
 * Title: QueryUserContactAction.java
 * File Description: 查询用户常用联系人
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
import com.wk.cd.system.us.bean.QueryUserContactInputBean;
import com.wk.cd.system.us.bean.QueryUserContactOutputBean;
import com.wk.cd.system.us.bean.UsExtUserBean;
import com.wk.cd.system.us.dao.UsExtUserDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询用户常用联系人
 * @author HT
 */
public class QueryUserContactAction extends ActionBasic<QueryUserContactInputBean, QueryUserContactOutputBean>{

	@Inject
	private UsExtUserDaoService daoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询用户常用联系人
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryUserContactOutputBean doAction(QueryUserContactInputBean input) {
		logger.info("------QueryUserContactAction begin------");
		logger.debug("------user_id=[{}]------",input.getOrg_user_id());
		
		QueryUserContactOutputBean output = new QueryUserContactOutputBean();
		String user_id=input.getOrg_user_id();
		
		Assert.assertNotEmpty(user_id, QueryUserContactInputBean.ORG_USER_IDCN);
		List<UsExtUserBean> contact_list = new ArrayList<UsExtUserBean>();		
		
		contact_list = daoService.queryUserContact(user_id);
		output.setContact_list(contact_list);
		logger.info("------QueryUserContactAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryUserContactInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		return lgsvc.getLogTxt("查询用户常用联系人", log_lst);
	}

}
