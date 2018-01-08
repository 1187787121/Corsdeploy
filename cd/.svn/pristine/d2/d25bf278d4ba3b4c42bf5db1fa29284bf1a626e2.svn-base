/**
 * Title: PageSrvByFunTypeAction.java
 * File Description: 按照服务类型分页查询服务定义信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-25
 */
package com.wk.cd.system.sv.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.bean.PageSrvByFunTypeInputBean;
import com.wk.cd.system.sv.bean.PageSrvByFunTypeOutputBean;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 按照服务类型分页查询服务定义信息
 * @author tlw
 */
public class PageSrvByFunTypeAction
		extends
		ActionBasic<PageSrvByFunTypeInputBean, PageSrvByFunTypeOutputBean> {
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private SvSrvService svSrv;

	private static final Log logger = LogFactory.getLog();

	
	/**
	 * 服务维护模块分页查询
	 * 
	 * @param PageAllSrvInputBean input 输入接口
	 * 
	 * @return PageAllOutputBean 输出接口
	 */
	@Override
	protected PageSrvByFunTypeOutputBean doAction(
			PageSrvByFunTypeInputBean input) {
		logger.info("------PageSrvByFunTypeAction begin------");
		logger.debug("srv_fun_type=[{}]", input.getSrv_fun_type());
		PageSrvByFunTypeOutputBean output = new PageSrvByFunTypeOutputBean();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		FUN_TYPE srv_fun_type = input.getSrv_fun_type();
		// 查询用户名下的服务
		List<SvSrvInfo> user_srv_list = svSrv.pageSrvByFunType(srv_fun_type, start_recd, limit_recd);
		int count = svSrv.countSrvByFunType(srv_fun_type);
		// 分页查询服务
		output.setList_info(user_srv_list);
		output.setAll_recd(count);
		logger.info("------PageSrvByFunTypeAction begin------");
		return output;
	}


	/**
	 * 写日志
	 * 
	 * @param PageAllSrvInputBean input 输入接口
	 * 
	 * @return String 日志信息
	 */
	@Override
	protected String getLogTxt(PageSrvByFunTypeInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		return lgsrv.getLogTxt("按照服务类型分页查询服务定义", lst_val);
	}

}
