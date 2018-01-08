/**
 * Title: DeleteChannelAction.java
 * File Description: 删除渠道信息
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
import com.wk.cd.system.ch.bean.DeleteChannelInputBean;
import com.wk.cd.system.ch.bean.DeleteChannelOutputBean;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.dao.ChChannelSrvPrivDaoService;
import com.wk.cd.system.ch.dao.ChChannelSrvgPrivDaoService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除渠道信息
 * @author HT
 */
public class DeleteChannelAction extends ActionBasic<DeleteChannelInputBean, DeleteChannelOutputBean>{

	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ChChannelSrvgPrivDaoService chChannelSrvgPrivDaoService;
	@Inject
	private ChChannelSrvPrivDaoService chChannelSrvPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 删除渠道信息
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteChannelOutputBean doAction(DeleteChannelInputBean input) {
		logger.info("------DeleteChannelAction begin------");
		logger.debug("channel_code=[{}]", input.getChannel_code());
		DeleteChannelOutputBean output = new DeleteChannelOutputBean();
		
		String channel_code=input.getChannel_code();
		
		// 必填项
		Assert.assertNotEmpty(channel_code,DeleteChannelInputBean.CHANNEL_CODECN);
		
		chChannelDaoService.checkChannelCodeExist(channel_code);
		
		chChannelDaoService.deleteChannel(channel_code);
		
		chChannelSrvgPrivDaoService.deleteSrvgPriv(channel_code);
		
		chChannelSrvPrivDaoService.deleteSrvPriv(channel_code);
		
		logger.info("------DeleteChannelAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteChannelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("channel_code=" + input.getChannel_code());
		return lgsvc.getLogTxt("删除渠道信息", lst_val);
	}

}
