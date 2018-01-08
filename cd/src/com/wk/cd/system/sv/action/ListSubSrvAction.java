/**
 * Title: ListSubSrvAction.java
 * File Description: 查询服务组包含服务列表
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
import com.wk.cd.system.sv.bean.ListSubSrvInputBean;
import com.wk.cd.system.sv.bean.ListSubSrvOutputBean;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询服务组包含服务列表
 * @author HT
 */
public class ListSubSrvAction extends ActionBasic<ListSubSrvInputBean, ListSubSrvOutputBean>{

	@Inject
	private SvSrvDaoService svSrvDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询服务组包含服务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected ListSubSrvOutputBean doAction(ListSubSrvInputBean input) {
		logger.info("------ListSubSrvAction begin------");
		logger.debug("sup_srvg_code=[{}]", input.getSup_srvg_code());
		ListSubSrvOutputBean output = new ListSubSrvOutputBean();
		
		String sup_srvg_code=input.getSup_srvg_code();
		
		// 必填项
		Assert.assertNotEmpty(sup_srvg_code,ListSubSrvInputBean.SUP_SRVG_CODECN);
		
		List<SvSrvInfo> srv_list=svSrvDaoService.listSubSrvBySrvgCode(sup_srvg_code);
		
		output.setSrv_list(srv_list);
		
		logger.info("------ListSubSrvAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(ListSubSrvInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("sup_srvg_code=" + input.getSup_srvg_code());
		return lgsvc.getLogTxt("查询服务组包含服务列表", lst_val);
	}
}
