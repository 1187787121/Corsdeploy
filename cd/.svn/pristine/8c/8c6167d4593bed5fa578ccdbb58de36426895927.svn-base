/**
 * Title: DeleteWorkAction.java
 * File Description:删除任务 
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
import com.wk.cd.work.wk.bean.DeleteWorkInputBean;
import com.wk.cd.work.wk.bean.DeleteWorkOutputBean;
import com.wk.cd.work.wk.service.WorkConfigPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:删除任务
 * @author tlw
 */
public class DeleteWorkAction
		extends ActionBasic<DeleteWorkInputBean, DeleteWorkOutputBean> {
	@Inject
	private WorkConfigPublicService wcPucSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 
	 * @param input 输入接口
	 * @return
	 */
	@Override
	protected DeleteWorkOutputBean doAction(DeleteWorkInputBean input) {
		DeleteWorkOutputBean output = new DeleteWorkOutputBean();
		String work_code = input.getWork_code();
		logger.info("任务编码 work_code = [{}]", work_code);
		// 非空检查
		Assert.assertNotEmpty(work_code, DeleteWorkInputBean.WORK_CODECN);
		// 删除任务定义表记录
		logger.info("****************DELETE WK_WORK***************");
		wcPucSrv.checkExist(work_code);
		wcPucSrv.deleteWorkByWorkCode(work_code, input.getDtbs_bk_date(), input
				.getDtbs_bk_time(), input.getOrg_user_id());
		// 删除资源表记录
		if (wcPucSrv.getWorkRsList(work_code) != null) {
			logger.info("****************DELETE WK_WORK_RS***************");
			wcPucSrv.deleteWorkRsByWorkCode(work_code);
		}
		// 删除数据源表记录
		if (wcPucSrv.getWorkSocList(work_code) != null) {
			logger.info("****************DELETE WK_WORK_SOC***************");
			wcPucSrv.deleteWorkSocByWorkCode(work_code);
		}
		// 删除服务表记录
		if (wcPucSrv.getWorkSrvList(work_code) != null) {
			logger.info("****************DELETE WK_WORK_SRV***************");
			wcPucSrv.deleteWorkSrvByWorkCode(work_code);
		}
		return output;
	}

	/**
	 * 
	 * @param input 输入接口
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(DeleteWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getWork_code());
		return lgsrv.getLogTxt("删除任务", lst_val);
	}

}
