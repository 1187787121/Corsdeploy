/**
 * Title: QuerySocBySocNameAction.java
 * File Description: 根据数据源名称查询数据源的详细信息
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-11
 */
package com.wk.cd.system.dt.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.bean.QuerySocBySocNameInputBean;
import com.wk.cd.system.dt.bean.QuerySocBySocNameOutputBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;

/**
 * Class Description: 根据数据源名称查询数据源的详细信息
 * @author link
 */
public class QuerySocBySocNameAction
		extends
		ActionBasic<QuerySocBySocNameInputBean, QuerySocBySocNameOutputBean> {

	@Inject
	private DtSocService getSocService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:根据数据源名称查询数据源的详细信息
	 * @param input
	 * @return
	 */
	@Override
	protected QuerySocBySocNameOutputBean doAction(
			QuerySocBySocNameInputBean input) {
		QuerySocBySocNameOutputBean output = new QuerySocBySocNameOutputBean();
		DtSourceInfo info = new DtSourceInfo();
		Assert.assertNotEmpty(input.getSoc_name(),
				QuerySocBySocNameInputBean.SOC_NAMECN);
		info = getSocService.querySocDetailBySocName(input.getSoc_name());
		output.setSoc_name(info.getSoc_name());
		output.setSoc_ip(info.getSoc_ip());
		output.setSoc_port(info.getSoc_port());
		output.setProtocol_type(info.getProtocol_type());
		output.setRemote_uname(info.getRemote_uname());
		output.setBk_timeout(info.getBk_timeout());
		output.setJdbc_drv(info.getJdbc_drv());
		output.setJdbc_url(info.getJdbc_url());
		output.setJdbc_schema(info.getJdbc_schema());
		output.setCvt_type(info.getCvt_type());
		output.setFtp_local_script(info.getFtp_local_script());
		output.setCvt_local_script(info.getCvt_local_script());
		output.setSoc_domain(info.getSoc_domain());
		output.setSoc_bk_desc(info.getSoc_bk_desc());
		output.setUser_root_path(info.getUser_root_path());
		output.setEncoding_type(info.getEncoding_type());
		output.setEnvironment_variables(info.getEnvironment_variables());
		// 数据源参数字段
		if(info.getProtocol_type().getValue()==11||info.getProtocol_type().getValue()==12){
			String soc_params = info.getSoc_params();
			output.setSoc_params(getSocService.getWasParamsPartInfo(soc_params));
		}
		return output;
	}

	/**
	 * Description:根据数据源名称查询数据源的详细信息日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(QuerySocBySocNameInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSoc_name());
		return lgsvc.getLogTxt("根据数据源名称查询数据源的详细信息", lst_val);
	}

}
