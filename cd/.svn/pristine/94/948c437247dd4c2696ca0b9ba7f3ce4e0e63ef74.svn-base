/**
 * Title: QueryChannelSrvPrivAction.java
 * File Description: 
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
import com.wk.cd.system.ch.bean.QueryChannelSrvPrivInputBean;
import com.wk.cd.system.ch.bean.QueryChannelSrvPrivOutputBean;
import com.wk.cd.system.ch.dao.ChChannelSrvPrivDaoService;
import com.wk.cd.system.ch.dao.ChChannelSrvgPrivDaoService;
import com.wk.cd.system.ch.info.ChChannelSrvPrivInfo;
import com.wk.cd.system.ch.info.ChChannelSrvgPrivInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.dao.SvSrvgDaoService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询渠道服务权限
 * @author HT
 */
public class QueryChannelSrvPrivAction extends ActionBasic<QueryChannelSrvPrivInputBean, QueryChannelSrvPrivOutputBean>{

	@Inject
	private SvSrvDaoService svSrvDaoService;
	@Inject
	private SvSrvgDaoService svSrvgDaoService;
	@Inject
	private ChChannelSrvPrivDaoService chChannelSrvPrivDaoService;
	@Inject
	private ChChannelSrvgPrivDaoService chChannelSrvgPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询渠道服务权限
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryChannelSrvPrivOutputBean doAction(
			QueryChannelSrvPrivInputBean input) {
		logger.info("------QueryChannelSrvPrivAction begin------");
		logger.debug("channel_code=[{}]", input.getChannel_code());
		QueryChannelSrvPrivOutputBean output = new QueryChannelSrvPrivOutputBean();
		
		String channel_code=input.getChannel_code();
		// 必填项
		Assert.assertNotEmpty(channel_code,QueryChannelSrvPrivInputBean.CHANNEL_CODECN);
		
		List<SvSrvInfo> srv_list=svSrvDaoService.listAllSrv();
		
		List<SvSrvgInfo> srvg_list=svSrvgDaoService.listAllSrvg();
		
		List<ChChannelSrvPrivInfo> srv_priv=chChannelSrvPrivDaoService.listAllSrvPriv(channel_code);
		
		List<ChChannelSrvgPrivInfo> srvg_priv=chChannelSrvgPrivDaoService.listAllSrvgPriv(channel_code);
		
		output.setSrv_list(srv_list);
		output.setSrvg_list(srvg_list);
		output.setSrv_priv(srv_priv);
		output.setSrvg_priv(srvg_priv);
		
		logger.info("------QueryChannelSrvPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryChannelSrvPrivInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("channel_code=" + input.getChannel_code());
		return lgsvc.getLogTxt("查询渠道服务权限信息", lst_val);
	}

}
