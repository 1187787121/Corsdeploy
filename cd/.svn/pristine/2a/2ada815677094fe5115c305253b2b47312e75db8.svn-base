/**
 * Title: QueryUserTermListAction.java
 * File Description: 查询用户接入终端列表
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
import com.wk.cd.system.us.bean.PageUserTermInputBean;
import com.wk.cd.system.us.bean.PageUserTermOutputBean;
import com.wk.cd.system.us.bean.UsUserTermBean;
import com.wk.cd.system.us.dao.UsUserTermDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询用户接入终端列表
 * @author HT
 */
public class PageUserTermAction extends ActionBasic<PageUserTermInputBean, PageUserTermOutputBean>{

	@Inject
	private UsUserTermDaoService usUserTermDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询用户接入终端列表
	 * @param input
	 * @return 
	 */
	@Override
	protected PageUserTermOutputBean doAction(PageUserTermInputBean input) {
		logger.info("------PageUserTermAction begin------");
		logger.debug("------user_cn_name=[{}]",input.getUser_cn_name());
		
		PageUserTermOutputBean output = new PageUserTermOutputBean();
		String user_cn_name=input.getUser_cn_name();
		int start_recd=input.getStart_recd();
		int limit_recd=input.getLimit_recd();
		// 必填项
		Assert.assertNotEmpty(start_recd,PageUserTermInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd,PageUserTermInputBean.LIMIT_RECDCN);
		
		List<UsUserTermBean> term_list=usUserTermDaoService.pageUnUseUserTerm(user_cn_name,start_recd,limit_recd);
		
		int all_recd=usUserTermDaoService.countUnUseUserTerm(user_cn_name);
		
		output.setTerm_list(term_list);
		
		output.setAll_recd(all_recd);
		
		logger.info("------PageUserTermAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageUserTermInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_cn_name());
		return lgsvc.getLogTxt("分页查询用户接入终端列表", log_lst);
	}

}
