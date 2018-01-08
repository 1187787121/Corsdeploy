/**
 * Title: DeleteUserTermAction.java
 * File Description: 删除用户接入终端
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月14日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.DeleteUserTermInputBean;
import com.wk.cd.system.us.bean.DeleteUserTermOutputBean;
import com.wk.cd.system.us.dao.UsUserTermDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除用户接入终端
 * @author HT
 */
public class DeleteUserTermAction extends ActionBasic<DeleteUserTermInputBean, DeleteUserTermOutputBean>{

	@Inject
	private UsUserTermDaoService usUserTermDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 删除用户接入终端
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteUserTermOutputBean doAction(DeleteUserTermInputBean input) {
		logger.info("------DeleteUserTermAction begin------");
		logger.debug("------user_id=[{}]-----term_no=[{}]",input.getUser_id(),input.getTerm_no());
		
		DeleteUserTermOutputBean output = new DeleteUserTermOutputBean();
		String user_id=input.getUser_id();
		String term_no=input.getTerm_no();
		
		Assert.assertNotEmpty(user_id, DeleteUserTermInputBean.USER_IDCN);
		Assert.assertNotEmpty(term_no, DeleteUserTermInputBean.TERM_NOCN);
		
		
		usUserTermDaoService.deleteUserTerm(user_id,term_no);
		
		logger.info("------DeleteUserTermAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteUserTermInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		log_lst.add(input.getTerm_no());
		return lgsvc.getLogTxt("删除用户接入终端", log_lst);
	}

}
