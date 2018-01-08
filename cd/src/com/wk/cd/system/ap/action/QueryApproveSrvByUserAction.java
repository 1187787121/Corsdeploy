/**
 * Title: PageApproveSrvByUserAction.java
 * File Description: 用户查询所有可以配置审批流程的服务
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tianlw
 * @version: 1.0
 * @date: 2015-8-25
 */
package com.wk.cd.system.ap.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.bean.QuerySrvByBranchInputBean;
import com.wk.cd.system.ap.bean.QuerySrvByBranchOutputBean;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 用户查询所有可以配置审批流程的服务
 * 
 * @author tianlw
 */
public class QueryApproveSrvByUserAction extends
		ActionBasic<QuerySrvByBranchInputBean, QuerySrvByBranchOutputBean> {
	@Inject
	private SvSrvService svsrv;
	@Inject
	private ActionLogPublicService lgsrv;

	private static final Log logger = LogFactory.getLog();

	@Override
	protected QuerySrvByBranchOutputBean doAction(QuerySrvByBranchInputBean input) {
		QuerySrvByBranchOutputBean output = new QuerySrvByBranchOutputBean();
		
		String user_id = input.getOrg_user_id();
		logger.info("用户[" + user_id + "]查询服务审批配置信息");
		output.setApp_srv_list(svsrv.queryCanApproveSrv());
		return output;
	}

	@Override
	protected String getLogTxt(QuerySrvByBranchInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsrv.getLogTxt("查询服务审批配置信息", lst_val);
	}

}
