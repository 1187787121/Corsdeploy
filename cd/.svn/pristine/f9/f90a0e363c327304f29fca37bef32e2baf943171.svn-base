/**
 * Title: QuerySrvByNameAction.java
 * File Description: 按照服务名称查询服务信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-24
 */
package com.wk.cd.system.sv.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.SOC_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.bean.QuerySrvByNameInputBean;
import com.wk.cd.system.sv.bean.QuerySrvByNameOutputBean;
import com.wk.cd.system.sv.info.SvRemoteSrvInfo;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 按照服务名称查询服务信息
 * @author tlw
 */
public class QuerySrvByNameAction
		extends ActionBasic<QuerySrvByNameInputBean, QuerySrvByNameOutputBean> {
	@Inject
	private SvSrvService ssSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	@Override
	/**
	 * 按服务名称精确查询服务信息
	 * @param QuerySrvByNameInputBean input 输入接口
	 * @return QuerySrvByNameOutputBean 输出接口
	 */
	protected QuerySrvByNameOutputBean doAction(QuerySrvByNameInputBean input) {
		QuerySrvByNameOutputBean output = new QuerySrvByNameOutputBean();
		// 服务名称非空检查
		String srv_name = input.getSrv_name();
		logger.debug("srv_name = [{}]", srv_name);
		Assert.assertNotEmpty(srv_name, QuerySrvByNameInputBean.SRV_NAMECN);
		// 查询服务定义表信息
		SvSrvInfo srv_info = new SvSrvInfo();
		srv_info.setSrv_name(srv_name);
		srv_info = ssSrv.queryNoDelSvSrvByName(srv_info);
		// 输出接口赋值
		// 服务数据源定义表信息
		if (srv_info.getSoc_flag() == SOC_FLAG.YES) {
			output.setSrv_soc_list(ssSrv.queryNoDelSvSrvSocByName(srv_name));
		}
		// 远程服务调用配置表信息
		SvRemoteSrvInfo remote_info = ssSrv
				.queryNoDelRemoteSrvInfoByNameNoError(srv_name);
		if (!Assert.isEmpty(remote_info)) {
			output.setRemote_srv_name(remote_info.getRemote_srv_name());
			output.setSrv_type(remote_info.getSrv_type());
			output.setSrv_root_path(remote_info.getSrv_root_path());
		}
		output.setSrv_name(srv_info.getSrv_name());
		output.setSrv_bk_desc(srv_info.getSrv_bk_desc());
		output.setSrv_fun_type(srv_info.getSrv_fun_type());
		output.setSrv_class_name(srv_info.getSrv_class_name());
		output.setSrv_method_name(srv_info.getSrv_method_name());
		output.setSallow_flag(srv_info.getSallow_flag());
		output.setSoc_flag(srv_info.getSoc_flag());
		return output;
	}

	@Override
	/**
	 * 写日志
	 * @param QuerySrvByNameInputBean input 输入接口
	 * @return String 日志信息
	 */
	protected String getLogTxt(QuerySrvByNameInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSrv_name());
		return lgsrv.getLogTxt("服务维护：服务名称查询服务信息", lst_val);
	}

}
