/**
 * Title: UpdateLogLabelAction.java
 * File Description: 修改日志标记
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
import com.wk.cd.system.lg.bean.UpdateLogLabelInputBean;
import com.wk.cd.system.lg.bean.UpdateLogLabelOutputBean;
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
 * Class Description: 修改日志标记
 * @author tlw
 */
public class UpdateLogLabelAction
		extends ActionBasic<UpdateLogLabelInputBean, UpdateLogLabelOutputBean> {

	@Inject
	private LgLogLabelDaoService lglabelsrv;
	@Inject
	private LgLogMfDaoService lgmfsrv;
	@Inject
	private UsUserGetRoleDeptService ussrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private UsUserDaoService usUsersrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 修改日志标记
	 * @param input 输入信息
	 * @return 返回信息
	 */
	@Override
	protected UpdateLogLabelOutputBean doAction(UpdateLogLabelInputBean input) {
		UpdateLogLabelOutputBean output = new UpdateLogLabelOutputBean();
		String query_work_seq = input.getQuery_work_seq();
		int log_label = input.getLog_label();
		String user_id = input.getOrg_user_id();
		logger.info("修改流水[{}]的标记为[{}]", query_work_seq, log_label);
		// 查找流水在日志表中是否存在
		LgLogMfInfo info = new LgLogMfInfo();
		info.setWork_seq(query_work_seq);
		info = lgmfsrv.queryLogInfoByKey(info);
		List<String> dept_list = ussrv.queryDeptByUserId(user_id);
		Assert.assertNotEmpty(dept_list, "用户所属机构");
		if (!Assert.isEmpty(info.getCrt_dept_id()) && !dept_list.contains(info.getCrt_dept_id())) {
			throw new IllegalOperaterException().addScene("PARM1", 
					usUsersrv.queryDetailByUserId(user_id).getUser_cn_name()+"("+user_id+")")
					.addScene("parm2", query_work_seq);
		}
		// 查找流水表在日志标记级别表中是否存在
		LgLogLabelInfo info1 = new LgLogLabelInfo();
		info1.setWork_seq(query_work_seq);
		info1.setUser_id(input.getOrg_user_id());
		info1 = lglabelsrv.getInfoByKey(info1);
		if (Assert.isEmpty(info1)) {
			if (log_label == 0) {
				return output;
			}
			info1 = new LgLogLabelInfo();
			info1.setWork_seq(query_work_seq);
			info1.setUser_id(input.getOrg_user_id());
			info1.setLog_label(log_label);
			info1.setLabel_bk_date(input.getDtbs_bk_date());
			info1.setLabel_bk_time(input.getDtbs_bk_time());
			lglabelsrv.insertInfo(info1);
		} else {
			if (log_label != 0) {
				info1 = new LgLogLabelInfo();
				info1.setWork_seq(query_work_seq);
				info1.setUser_id(input.getOrg_user_id());
				info1.setLog_label(log_label);
				info1.setLabel_bk_date(input.getDtbs_bk_date());
				info1.setLabel_bk_time(input.getDtbs_bk_time());
				lglabelsrv.updateInfo(info1);
			} else {
				info1 = new LgLogLabelInfo();
				info1.setWork_seq(query_work_seq);
				info1.setUser_id(input.getOrg_user_id());
				lglabelsrv.deleteInfo(info1);
			}
		}
		return output;
	}

	/**
	 * 生成日志信息
	 * @param input 输入信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(UpdateLogLabelInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("修改标记的流水" + input.getQuery_work_seq());
		lst_val.add("日志标记级别" + input.getLog_label());
		return lgsrv.getLogTxt("修改日志标记级别信息", lst_val);
	}

}
