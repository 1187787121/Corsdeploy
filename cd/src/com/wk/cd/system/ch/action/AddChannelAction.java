/**
 * Title: AddChannelAction.java
 * File Description: 新增渠道信息 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月10日
 */
package com.wk.cd.system.ch.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CHANNEL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ch.bean.AddChannelInputBean;
import com.wk.cd.system.ch.bean.AddChannelOutputBean;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:新增渠道信息 
 * @author HT
 */
public class AddChannelAction extends ActionBasic<AddChannelInputBean, AddChannelOutputBean>{

	@Inject
	private GenNoService genNoService;
	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 新增渠道信息 
	 * @param input
	 * @return 
	 */
	@Override
	protected AddChannelOutputBean doAction(AddChannelInputBean input) {
		logger.info("------AddChannelAction begin------");
		logger.debug("channel_cn_name=[{}]", input.getChannel_cn_name());
		AddChannelOutputBean output = new AddChannelOutputBean();
		
		String channel_cn_name=input.getChannel_cn_name();
		CHANNEL_TYPE channel_type=input.getChannel_type();
		String channel_bk_desc=input.getChannel_bk_desc();
		
		// 必填项
		Assert.assertNotEmpty(channel_cn_name,AddChannelInputBean.CHANNEL_CN_NAMECN);
		Assert.assertNotEmpty(channel_type,AddChannelInputBean.CHANNEL_TYPECN);
		Assert.assertNotEmpty(channel_bk_desc,AddChannelInputBean.CHANNEL_BK_DESCCN);
		
		String channel_code=genNoService.getChannelCode(input.getDtbs_bk_date());
		
		chChannelDaoService.checkChannelCodeNoExist(channel_code);
		
		chChannelDaoService.checkChannelCnNameNoExist(channel_cn_name);
		
		ChChannelInfo info=new ChChannelInfo();
		info.setChannel_code(channel_code);
		info.setChannel_cn_name(channel_cn_name);
		info.setChannel_type(channel_type);
		info.setChannel_bk_desc(channel_bk_desc);
		info.setRcd_state(RCD_STATE.NORMAL);
		chChannelDaoService.insertInfo(info);
		
		logger.info("------AddChannelAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddChannelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("channel_cn_name=" + input.getChannel_cn_name());
		lst_val.add("channel_type=" + input.getChannel_type());
		lst_val.add("channel_bk_desc=" + input.getChannel_bk_desc());
		return lgsvc.getLogTxt("新增渠道信息", lst_val);
	}

}
