/**
 * Title: UpdateChannelSrvPrivAction.java
 * File Description: 修改渠道服务权限
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
import com.wk.cd.system.ch.bean.UpdateChannelSrvPrivInputBean;
import com.wk.cd.system.ch.bean.UpdateChannelSrvPrivOutputBean;
import com.wk.cd.system.ch.dao.ChChannelSrvPrivDaoService;
import com.wk.cd.system.ch.dao.ChChannelSrvgPrivDaoService;
import com.wk.cd.system.ch.info.ChChannelSrvPrivInfo;
import com.wk.cd.system.ch.info.ChChannelSrvgPrivInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改渠道服务权限
 * @author HT
 */
public class UpdateChannelSrvPrivAction extends ActionBasic<UpdateChannelSrvPrivInputBean, UpdateChannelSrvPrivOutputBean>{

	@Inject
	private ChChannelSrvPrivDaoService chChannelSrvPrivDaoService;
	@Inject
	private ChChannelSrvgPrivDaoService chChannelSrvgPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改渠道服务权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateChannelSrvPrivOutputBean doAction(UpdateChannelSrvPrivInputBean input) {
		logger.info("------UpdateChannelSrvPrivAction begin------");
		logger.debug("channel_code=[{}]", input.getChannel_code());
		UpdateChannelSrvPrivOutputBean output = new UpdateChannelSrvPrivOutputBean();
		
		String channel_code=input.getChannel_code();
		List<ChChannelSrvPrivInfo> srv_priv=input.getSrv_priv();
		List<ChChannelSrvgPrivInfo> srvg_priv=input.getSrvg_priv();
		
		// 必填项
		Assert.assertNotEmpty(channel_code,UpdateChannelSrvPrivInputBean.CHANNEL_CODECN);
		
		//删除服务权限
		chChannelSrvgPrivDaoService.deleteSrvgPriv(channel_code);
		chChannelSrvPrivDaoService.deleteSrvPriv(channel_code);
		
		if(!Assert.isEmpty(srvg_priv)){
			for(ChChannelSrvgPrivInfo srvgPrivInfo:srvg_priv){
				srvgPrivInfo.setChannel_code(channel_code);
			}
			chChannelSrvgPrivDaoService.insertListInfo(srvg_priv);
		}
		
		if(!Assert.isEmpty(srv_priv)){
			for(ChChannelSrvPrivInfo srvPrivInfo:srv_priv){
				srvPrivInfo.setChannel_code(channel_code);
			}
			chChannelSrvPrivDaoService.insertListInfo(srv_priv);
		}
		
		
		logger.info("------UpdateChannelSrvPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateChannelSrvPrivInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("channel_code=" + input.getChannel_code());
		return lgsvc.getLogTxt("修改渠道服务权限信息", lst_val);
	}

}
