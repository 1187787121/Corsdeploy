package com.wk.cd.work.wk.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.work.wk.bean.ViewWorkInputBean;
import com.wk.cd.work.wk.bean.ViewWorkOutputBean;
import com.wk.cd.work.wk.dao.WkDetailRegisterDaoService;
import com.wk.cd.work.wk.info.WkDetailRegisterInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class ViewWorkAction extends IViewActionBasic<ViewWorkInputBean, ViewWorkOutputBean> {
	private static final Log logger = LogFactory.getLog();

	@Inject
	private WorkPublicService wkPubSrv;

	@Inject
	private WkDetailRegisterDaoService wkDetailRegisterDaoService;

	public ViewWorkOutputBean queryExecutoryWorkByUserId(ViewWorkInputBean input) {
		logger.info("********queryExecutoryWorkByUserId Begin***********");
		ViewWorkOutputBean output = new ViewWorkOutputBean();

		String crt_user_id = input.getOrg_user_id();

		Assert.assertNotEmpty(crt_user_id, "发起用户");

		int unauth_executory_count = this.wkPubSrv.countExecutoryUncheckOrUnauth(crt_user_id, WORKFLOW_STATE.APPROVAL);

		int uncheck_executory_count = this.wkPubSrv.countExecutoryUncheckOrUnauth(crt_user_id, WORKFLOW_STATE.RECHECK);

		int mine_executory_count = this.wkPubSrv.countMineExecutoryWork(crt_user_id);

		output.setUnauth_executory_count(unauth_executory_count);
		output.setUncheck_executory_count(uncheck_executory_count);
		output.setMine_executory_count(mine_executory_count);

		logger.info("********queryExecutoryWorkByUserId End***********");
		return output;
	}

	public ViewWorkOutputBean queryStaticPage(ViewWorkInputBean input) {
		logger.info("********queryApplyPage Start***********");
		ViewWorkOutputBean output = new ViewWorkOutputBean();
		String wrk_seq = input.getWrk_seq();
		WkDetailRegisterInfo info = new WkDetailRegisterInfo();
		info.setPend_work_seq(wrk_seq);
		WkDetailRegisterInfo wk_register_info = this.wkDetailRegisterDaoService.getInfoByKey(info);
		if (APROV_TYPE.STATIC_PAGE.equals(wk_register_info.getAprov_type())) {
			if (Assert.isEmpty(wk_register_info.getApply_html())) {
				throw new RecordNotFoundException().addScene("TABLE", "待处理任务明细登记表").addScene("FIELD", "申请静态页面代码");
			}
			output.setApply_html(new String(wk_register_info.getApply_html()));
		}
		logger.info("********queryApplyPage End***********");
		return output;
	}
}