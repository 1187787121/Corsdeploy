/**
 * Title: QueryWorkByWorkCodeAction.java
 * File Description:按照任务编码精确查询任务编码信息 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.rs.service.RsPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.wk.bean.DtSourceBean;
import com.wk.cd.work.wk.bean.QueryWorkByWorkCodeInputBean;
import com.wk.cd.work.wk.bean.QueryWorkByWorkCodeOutputBean;
import com.wk.cd.work.wk.bean.SvServiceBean;
import com.wk.cd.work.wk.info.WkWorkInfo;
import com.wk.cd.work.wk.info.WkWorkRsInfo;
import com.wk.cd.work.wk.info.WkWorkSocInfo;
import com.wk.cd.work.wk.info.WkWorkSrvInfo;
import com.wk.cd.work.wk.service.WorkConfigPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:按照任务编码精确查询任务编码信息
 * @author tlw
 */
public class QueryWorkByWorkCodeAction
		extends
		ActionBasic<QueryWorkByWorkCodeInputBean, QueryWorkByWorkCodeOutputBean> {
	@Inject
	private WorkConfigPublicService wcPubSrv;
	@Inject
	private UsGetUserInfoService usSrv;
	@Inject
	private RsPublicService rsSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 按照任务编码精确查询任务编码信息
	 * @param input 输入接口
	 * @return 任务精确信息
	 */
	@Override
	protected QueryWorkByWorkCodeOutputBean doAction(
			QueryWorkByWorkCodeInputBean input) {
		QueryWorkByWorkCodeOutputBean output = new QueryWorkByWorkCodeOutputBean();
		String work_code = input.getWork_code();
		logger.info("任务编码 work_code = [{}]", work_code);
		// 非空检查
		Assert.assertNotEmpty(work_code,
				QueryWorkByWorkCodeInputBean.WORK_CODECN);
		// 获取用户角色类型
		List<Integer> role_type_list = usSrv.queryRoleTypeByUserId(input
				.getOrg_user_id());
		// 查找任务定义信息
		WkWorkInfo info = wcPubSrv.queryInfoByWorkCode(work_code,
				role_type_list);
		output.setWork_code(work_code);
		output.setWork_cn_name(info.getWork_cn_name());
		output.setWork_bk_desc(info.getWork_bk_desc());
		output.setIs_publish(info.getIs_publish());
		output.setWork_fun_type(info.getWork_fun_type());
		output.setCrt_bk_date(info.getCrt_bk_date());
		output.setCrt_bk_time(info.getCrt_bk_time());
		output.setCrt_user_id(info.getCrt_user_id());
		// 查找任务资源信息
		List<WkWorkRsInfo> rs_list = wcPubSrv.getWorkRsList(work_code);
		List<String> rs_str = new ArrayList<String>();
		List<RsResInfo> rs_code_list = new ArrayList<RsResInfo>();
		if (!Assert.isEmpty(rs_list)) {
			for (WkWorkRsInfo rs_info : rs_list) {
				rs_str.add(rs_info.getRs_code());
				logger.info("资源编码" + rs_info.getRs_code());
			}
			rs_code_list = rsSrv.queryInfoByCodeString(rs_str);
		}
		output.setRs_code_list(rs_code_list);
		// 查找任务数据源信息
		List<WkWorkSocInfo> soc_list = wcPubSrv.getWorkSocList(work_code);
		List<DtSourceBean> soc_name_list = new ArrayList<DtSourceBean>();
		if (!Assert.isEmpty(soc_list)) {
			for (WkWorkSocInfo soc : soc_list) {
				DtSourceBean soc_name = new DtSourceBean();
				soc_name.setSoc_name(soc.getSoc_name());
				soc_name_list.add(soc_name);
			}
		}
		output.setSoc_list(soc_name_list);
		// 查找任务服务信息
		List<WkWorkSrvInfo> srv_list = wcPubSrv.getWorkSrvList(work_code);
		List<SvServiceBean> srv_name_list = new ArrayList<SvServiceBean>();
		if (!Assert.isEmpty(srv_list)) {
			for (WkWorkSrvInfo srv : srv_list) {
				SvServiceBean srv_name = new SvServiceBean();
				srv_name.setSrv_name(srv.getSrv_name());
				srv_name_list.add(srv_name);
			}
		}
		output.setSrv_list(srv_name_list);
		return output;
	}

	/**
	 * 写日志信息
	 * @param input 输入接口
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(QueryWorkByWorkCodeInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWork_code());
		return lgsrv.getLogTxt("精确查询任务定义", lst_val);
	}

}
