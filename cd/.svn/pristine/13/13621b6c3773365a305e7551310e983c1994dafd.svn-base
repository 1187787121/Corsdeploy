/**
 * Title: QuerySrvgAction.java
 * File Description: 查询服务组信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.sv.bean.QuerySrvgInputBean;
import com.wk.cd.system.sv.bean.QuerySrvgOutputBean;
import com.wk.cd.system.sv.dao.SvSrvgDaoService;
import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询服务组信息
 * @author HT
 */
public class QuerySrvgAction extends ActionBasic<QuerySrvgInputBean, QuerySrvgOutputBean>{

	@Inject
	private SvSrvgDaoService svSrvgDaoService;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询服务组信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QuerySrvgOutputBean doAction(QuerySrvgInputBean input) {
		logger.info("------QuerySrvgAction begin------");
		logger.debug("srvg_code=[{}]", input.getSrvg_code());
		QuerySrvgOutputBean output = new QuerySrvgOutputBean();
		
		String srvg_code=input.getSrvg_code();
		
		// 必填项
		Assert.assertNotEmpty(srvg_code,QuerySrvgInputBean.SRVG_CODECN);
		
		svSrvgDaoService.checkSrvgCodeExist(srvg_code);
		
		SvSrvgInfo info=new SvSrvgInfo();
		info.setSrvg_code(srvg_code);
		info=svSrvgDaoService.getInfoByKey(info);
		
		
		output.setSrvg_code(srvg_code);
		output.setSrvg_cn_name(info.getSrvg_cn_name());
		output.setSrvg_fun_type(info.getSrvg_fun_type());
		output.setSrvg_bk_desc(info.getSrvg_bk_desc());
		
		logger.info("------QuerySrvgAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QuerySrvgInputBean input) {
		// TODO Auto-generated method stub
		return null;
	}

}
