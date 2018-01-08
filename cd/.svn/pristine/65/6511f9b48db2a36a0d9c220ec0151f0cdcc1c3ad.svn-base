/**
 * Title: QuerySrvPrivBySrvnameAction.java
 * File Description: 根据服务名称查询审批信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-5
 */
package com.wk.cd.system.ap.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AUTH_FLAG;
import com.wk.cd.enu.CHECK_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.bean.QuerySrvPrivInputBean;
import com.wk.cd.system.ap.bean.QuerySrvPrivOutputBean;
import com.wk.cd.system.ap.dao.SvSrvAuthDaoService;
import com.wk.cd.system.ap.dao.SvSrvCheckDaoService;
import com.wk.cd.system.exc.ServiceForbidApproveException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 根据服务名称查询审批信息
 * 
 * @author tlw
 */
public class QuerySrvPrivBySrvNameAction extends
		ActionBasic<QuerySrvPrivInputBean, QuerySrvPrivOutputBean> {
	@Inject
	private SvSrvCheckDaoService chksrv;
	@Inject
	private SvSrvAuthDaoService authsrv;
	@Inject
	private SvSrvDaoService svsrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 根据服务名称查询授权复核信息
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected QuerySrvPrivOutputBean doAction(QuerySrvPrivInputBean input) {
		QuerySrvPrivOutputBean output = new QuerySrvPrivOutputBean();
		
		String srv_name = input.getSrv_name();
		Assert.assertNotEmpty(srv_name, QuerySrvPrivInputBean.SRV_NAMECN);
		logger.info("QuerySrvPrivOutputBean Begin, srv_name = [{}]", srv_name);
		SvSrvInfo srv_info = new SvSrvInfo();
		srv_info.setSrv_name(srv_name);
		srv_info = svsrv.getInfoByName(srv_info);
		
		//服务禁止审批时报错
		if (srv_info.getCheck_flag() == CHECK_FLAG.FORBID
				&& srv_info.getAuth_flag() == AUTH_FLAG.FORBID) {
			throw new ServiceForbidApproveException().addScene("PARM", "["
					+ srv_info.getSrv_name() + ":" + srv_info.getSrv_bk_desc()
					+ "]");
		}
		//查找服务复核信息
		if (srv_info.getCheck_flag() == CHECK_FLAG.ALLOW) {
			output.setSrv_check_list(chksrv.queryCheckBySrvName(input
					.getOrg_dept_id(), srv_name));
		}
		
		//查找服务授权信息
		if (srv_info.getAuth_flag() == AUTH_FLAG.ALLOW) {
			output.setSrv_auth_list(authsrv.queryAuthBySrvName(input
					.getOrg_dept_id(), srv_name));
		}
		output.setSrv_info(srv_info);
		return output;
	}

	/**
	 * 生成日志信息
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QuerySrvPrivInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSrv_name());
		return lgsrv.getLogTxt("查询服务权限信息", lst_val);
	}

}
