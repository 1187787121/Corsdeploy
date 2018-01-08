/**
 * Title: PageSrvgAction.java
 * File Description: 分页查询服务组列表
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.bean.PageSrvgInputBean;
import com.wk.cd.system.sv.bean.PageSrvgOutputBean;
import com.wk.cd.system.sv.dao.SvSrvgDaoService;
import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询服务组列表
 * @author HT
 */
public class PageSrvgAction extends ActionBasic<PageSrvgInputBean, PageSrvgOutputBean>{

	@Inject
	private SvSrvgDaoService svSrvgDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 分页查询服务组列表
	 * @param input
	 * @return 
	 */
	@Override
	protected PageSrvgOutputBean doAction(PageSrvgInputBean input) {
		logger.info("------PageSrvgAction begin------");
		PageSrvgOutputBean output = new PageSrvgOutputBean();
		
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		
		// 必填项
		Assert.assertNotEmpty(start_recd,PageSrvgInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd,PageSrvgInputBean.LIMIT_RECDCN);
		
		List<SvSrvgInfo> srvg_list=svSrvgDaoService.pageSrvg(start_recd,limit_recd);
		
		int count =svSrvgDaoService.countSrvg();
		
		output.setSrvg_list(srvg_list);
		output.setAll_recd(count);
		
		logger.info("------PageSrvgAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageSrvgInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsvc.getLogTxt("分页查询服务组信息", lst_val);
	}

}
