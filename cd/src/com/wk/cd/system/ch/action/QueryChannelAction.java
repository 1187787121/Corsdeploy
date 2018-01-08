/**
 * Title: QueryChannelAction.java
 * File Description: 查询渠道信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月10日
 */
package com.wk.cd.system.ch.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ch.bean.QueryChannelInputBean;
import com.wk.cd.system.ch.bean.QueryChannelOutputBean;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询渠道信息
 * @author HT
 */
public class QueryChannelAction extends ActionBasic<QueryChannelInputBean, QueryChannelOutputBean>{

	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询渠道信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryChannelOutputBean doAction(QueryChannelInputBean input) {
		logger.info("------QueryChannelAction begin------");
		logger.debug("channel_code=[{}]", input.getChannel_code());
		QueryChannelOutputBean output = new QueryChannelOutputBean();
		
		String channel_code=input.getChannel_code();
		
		// 必填项
		Assert.assertNotEmpty(channel_code,QueryChannelInputBean.CHANNEL_CODECN);
		
		chChannelDaoService.checkChannelCodeExist(channel_code);
		
		ChChannelInfo info=new ChChannelInfo();
		info.setChannel_code(channel_code);
		info=chChannelDaoService.getInfoByKey(info);
		
		
		output.setChannel_code(channel_code);
		output.setChannel_cn_name(info.getChannel_cn_name());
		output.setChannel_type(info.getChannel_type());
		output.setChannel_bk_desc(info.getChannel_bk_desc());
		
		logger.info("------QueryChannelAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryChannelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("channel_code=" + input.getChannel_code());
		return lgsvc.getLogTxt("查询渠道信息", lst_val);
	}

}
