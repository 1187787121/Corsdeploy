/**
 * Title: QueryLogByWorkSeqAction.java
 * File Description: 精确查询日志信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.lg.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.lg.bean.LogBean;
import com.wk.cd.system.lg.bean.QueryLogByWorkSeqInputBean;
import com.wk.cd.system.lg.bean.QueryLogByWorkSeqOutputBean;
import com.wk.cd.system.lg.dao.LgLogLabelDaoService;
import com.wk.cd.system.lg.dao.LgLogMfDaoService;
import com.wk.cd.system.lg.info.LgLogLabelInfo;
import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.service.UsUserGetRoleDeptService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 精确查询日志信息
 * @author tlw
 */
public class QueryLogByWorkSeqAction
		extends
		ActionBasic<QueryLogByWorkSeqInputBean, QueryLogByWorkSeqOutputBean> {

	@Inject
	private LgLogMfDaoService lgmfsrv;
	@Inject
	private LgLogLabelDaoService lglabelsrv;
	@Inject
	private UsUserGetRoleDeptService ussrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private UsUserDaoService usUsersrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 精确查询日志信息
	 * @param input 输入信息
	 * @return 日志详细信息
	 */
	@Override
	protected QueryLogByWorkSeqOutputBean doAction(
			QueryLogByWorkSeqInputBean input) {
		QueryLogByWorkSeqOutputBean output = new QueryLogByWorkSeqOutputBean();
		LogBean log_bean = new LogBean();
		String query_work_seq = input.getQuery_work_seq();
		String user_id = input.getOrg_user_id();
		logger.info("查询流水[{}]的精确日志信息", query_work_seq);
		LgLogMfInfo mf_info = new LgLogMfInfo();
		mf_info.setWork_seq(query_work_seq);
		mf_info = lgmfsrv.queryLogInfoByKey(mf_info);
		LgLogLabelInfo label_info = new LgLogLabelInfo();
		List<String> dept_list = ussrv.queryDeptByUserId(user_id);
		Assert.assertNotEmpty(dept_list, "用户所属机构");
		if (!dept_list.contains(mf_info.getCrt_dept_id())) {
			
			throw new IllegalOperaterException().addScene("PARM1", 
					usUsersrv.queryDetailByUserId(user_id).getUser_cn_name()+"("+user_id+")")
					.addScene("parm2", query_work_seq);
		}
		label_info.setWork_seq(query_work_seq);
		label_info.setUser_id(input.getOrg_user_id());
		label_info = lglabelsrv.getInfoByKey(label_info);
		if (!Assert.isEmpty(label_info)) {
			log_bean.setLog_label(label_info.getLog_label());
		}
		log_bean.setWork_seq(mf_info.getWork_seq());
		log_bean.setOrg_channel_code(mf_info.getOrg_channel_code());
		log_bean.setOrg_term_no(mf_info.getOrg_term_no());
		log_bean.setOrg_work_code(mf_info.getOrg_work_code());
		log_bean.setOrg_srv_name(mf_info.getOrg_srv_name());
		log_bean.setOrg_rs_code(mf_info.getOrg_rs_code());
		log_bean.setOrg_ary_socname(mf_info.getOrg_ary_socname());
		log_bean.setPend_work_seq(mf_info.getPend_work_seq());
		log_bean.setPend_work_code(mf_info.getPend_work_code());
		log_bean.setPend_srv_name(mf_info.getPend_srv_name());
		log_bean.setPend_rs_code(mf_info.getPend_rs_code());
		log_bean.setPend_ary_socname(mf_info.getPend_ary_socname());
		log_bean.setPendwk_bk_expl(mf_info.getPendwk_bk_expl());
		log_bean.setPbl_code(mf_info.getPbl_code());
		log_bean.setLog_txt(mf_info.getLog_txt());
		log_bean.setCrt_user_id(mf_info.getCrt_user_id());
		log_bean.setCrt_dept_id(mf_info.getCrt_dept_id());
		log_bean.setCrt_bk_date(mf_info.getCrt_bk_date());
		log_bean.setCrt_bk_time(mf_info.getCrt_bk_time());
		log_bean.setBackup_fld(mf_info.getBackup_fld());
		output.setLog_bean(log_bean);
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(QueryLogByWorkSeqInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("查询的日志流水" + input.getQuery_work_seq());
		return lgsrv.getLogTxt("精确查询日志信息", lst_val);
	}

}
