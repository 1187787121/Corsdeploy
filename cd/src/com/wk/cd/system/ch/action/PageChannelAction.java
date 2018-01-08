/**
 * Title: PageChannelAction.java
 * File Description: 分页查询渠道信息
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
import com.wk.cd.system.ch.bean.PageChannelInputBean;
import com.wk.cd.system.ch.bean.PageChannelOutputBean;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询渠道信息
 * @author HT
 */
public class PageChannelAction extends ActionBasic<PageChannelInputBean, PageChannelOutputBean>{

	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 分页查询渠道信息
	 * @param input
	 * @return 
	 */
	@Override
	protected PageChannelOutputBean doAction(PageChannelInputBean input) {
		logger.info("------PageChannelAction begin------");
		PageChannelOutputBean output = new PageChannelOutputBean();
		
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		
		// 必填项
		Assert.assertNotEmpty(start_recd,PageChannelInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd,PageChannelInputBean.LIMIT_RECDCN);
		
		List<ChChannelInfo> channel_list=chChannelDaoService.pageChannel(start_recd,limit_recd);
		
		int count =chChannelDaoService.countChannel();
		
		output.setChannel_list(channel_list);
		output.setAll_recd(count);
		
		logger.info("------PageChannelAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageChannelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("分页查询渠道信息", lst_val);
	}

}
