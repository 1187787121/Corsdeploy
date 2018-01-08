/**
 * Title: QueryAllChChannelAction.java
 * File Description: 查询所有渠道信息列表
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月14日
 */
package com.wk.cd.system.ch.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ch.bean.QueryAllChChannelInputBean;
import com.wk.cd.system.ch.bean.QueryAllChChannelOutputBean;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询所有渠道信息列表
 * @author HT
 */
public class QueryAllChChannelAction extends ActionBasic<QueryAllChChannelInputBean, QueryAllChChannelOutputBean>{

	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private DpPublicService dpsvc;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询所有渠道信息列表
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryAllChChannelOutputBean doAction(QueryAllChChannelInputBean input) {
		logger.info("------QueryAllChChannelAction begin------");
		QueryAllChChannelOutputBean output = new QueryAllChChannelOutputBean();
		
		List<ChChannelInfo> channel_list=chChannelDaoService.listAllChannel();
		
		output.setChannel_list(channel_list);
		
		logger.info("------QueryAllChChannelAction end------");
		return output;
	}


	@Override
	protected void chkInput(QueryAllChChannelInputBean input) {
		Assert.assertNotEmpty(input.getOrg_user_id(), QueryAllChChannelInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(input.getOrg_srv_name(), QueryAllChChannelInputBean.ORG_SRV_NAMECN);
		Assert.assertNotEmpty(input.getOrg_rs_code(), QueryAllChChannelInputBean.ORG_RS_CODECN);
		Assert.assertNotEmpty(input.getSubmit_type(), QueryAllChChannelInputBean.SUBMIT_TYPECN);
	}

	@Override
	protected void chkTerm(QueryAllChChannelInputBean input) {
	}

	@Override
	protected void chkSQLPriv(QueryAllChChannelInputBean input) {
	}

	@Override
	protected void chkDeptPriv(QueryAllChChannelInputBean input) {
	}

	@Override
	protected void chkState(QueryAllChChannelInputBean input){
	}

	/** 
	 * Description:  写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryAllChChannelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("查询所有渠道信息", lst_val);
	}

}
