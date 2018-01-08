/**
 * Title: QueryFirstNavigagePrivAction.java
 * File Description: 获取用户一级导航权限
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年11月23日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.QueryFirstNavigagePrivInputBean;
import com.wk.cd.system.us.bean.QueryFirstNavigagePrivOutputBean;
import com.wk.cd.system.us.service.UsGetUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 获取用户一级导航权限
 * @author HT
 */
public class QueryFirstNavigagePrivAction extends ActionBasic<QueryFirstNavigagePrivInputBean, QueryFirstNavigagePrivOutputBean>{

	@Inject
	private UsGetUserPrivService usGetUserPrivService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 获取用户一级导航权限
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryFirstNavigagePrivOutputBean doAction(QueryFirstNavigagePrivInputBean input) {
		logger.info("------QueryFirstNavigagePrivAction begin------");
		logger.debug("------user_id=[{}]",input.getOrg_user_id());
		QueryFirstNavigagePrivOutputBean output=new QueryFirstNavigagePrivOutputBean();
		
		String user_id=input.getOrg_user_id();
		Assert.assertNotEmpty(user_id, QueryFirstNavigagePrivInputBean.ORG_USER_IDCN);
		
		JaDate dt_date=input.getDtbs_bk_date();
		JaTime dt_time=input.getDtbs_bk_time();
		JaDateTime dt_datetime=JaDateTime.valueOf(dt_date.getYear(), dt_date.getMonth(), dt_date.getDay(), dt_time.getHour(), dt_time.getMinute(), dt_time.getSecond(), dt_time.getMillisecond());
	
		
		List<RsResInfo> nav_list=usGetUserPrivService.getFirstNavigatePriv(user_id,dt_datetime);
		
		output.setNav_list(nav_list);
		logger.info("------QueryFirstNavigagePrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryFirstNavigagePrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		return lgsvc.getLogTxt("获取用户一级导航权限", log_lst);
	}
}
