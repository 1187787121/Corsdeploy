/**
 * Title: PageEnvProgAction.java
 * File Description: 环境方案列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.QueryEnvProgInputBean;
import com.wk.cd.build.ea.bean.QueryEnvProgOutputBean;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 环境方案列表
 * @author chiss
 */
public class QueryEnvProgAction extends ActionBasic<QueryEnvProgInputBean, QueryEnvProgOutputBean>{
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 环境方案列表
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryEnvProgOutputBean doAction(QueryEnvProgInputBean input) {
		logger.info("-----------QueryEnvProgAction begin----------");
		QueryEnvProgOutputBean output = new QueryEnvProgOutputBean();
		String env_name = input.getEnv_name();
		String order_col_name = input.getOrder_col_name();
		ORDER_TYPE order_type = input.getOrder_type();
		//查询表里面的信息
		List<PgProgramInfo> program_list = pgProgramDaoService.getEnvProgByEnvName(env_name,order_col_name,order_type);
		output.setProgram_list(program_list);
		logger.info("-----------QueryEnvProgAction end----------");
		return output;
	}

	/** 
	 * Description: 环境方案列表
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryEnvProgInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("环境名称: "+input.getEnv_name());
		return lgsvc.getLogTxt("关联方案列表", log_lst);
	}

}
