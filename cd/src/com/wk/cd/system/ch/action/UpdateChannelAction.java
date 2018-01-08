/**
 * Title: UpdateChannelAction.java
 * File Description: �޸�������Ϣ 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��12��
 */
package com.wk.cd.system.ch.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CHANNEL_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ch.bean.UpdateChannelInputBean;
import com.wk.cd.system.ch.bean.UpdateChannelOutputBean;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸�������Ϣ
 * @author HT
 */
public class UpdateChannelAction extends ActionBasic<UpdateChannelInputBean, UpdateChannelOutputBean>{

	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: �޸�������Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateChannelOutputBean doAction(UpdateChannelInputBean input) {
		logger.info("------UpdateChannelAction begin------");
		logger.debug("channel_code=[{}]", input.getChannel_code());
		UpdateChannelOutputBean output = new UpdateChannelOutputBean();
		
		String channel_code=input.getChannel_code();
		String channel_cn_name=input.getChannel_cn_name();
		CHANNEL_TYPE channel_type=input.getChannel_type();
		String channel_bk_desc=input.getChannel_bk_desc();
		
		// ������
		Assert.assertNotEmpty(channel_code,UpdateChannelInputBean.CHANNEL_CODECN);
		Assert.assertNotEmpty(channel_cn_name,UpdateChannelInputBean.CHANNEL_CN_NAMECN);
		Assert.assertNotEmpty(channel_type,UpdateChannelInputBean.CHANNEL_TYPECN);
		Assert.assertNotEmpty(channel_bk_desc,UpdateChannelInputBean.CHANNEL_BK_DESCCN);
		
		
		
		chChannelDaoService.checkChannelCodeExist(channel_code);
		
		ChChannelInfo info=new ChChannelInfo();
		info.setChannel_code(channel_code);
		info.setChannel_cn_name(channel_cn_name);
		info.setChannel_type(channel_type);
		info.setChannel_bk_desc(channel_bk_desc);
		info.setRcd_state(RCD_STATE.NORMAL);
		chChannelDaoService.updateChannel(info);
		
		logger.info("------UpdateChannelAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateChannelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("channel_code=" + input.getChannel_code());
		lst_val.add("channel_cn_name=" + input.getChannel_cn_name());
		lst_val.add("channel_type=" + input.getChannel_type());
		lst_val.add("channel_bk_desc=" + input.getChannel_bk_desc());
		return lgsvc.getLogTxt("�޸�������Ϣ", lst_val);
	}

}
