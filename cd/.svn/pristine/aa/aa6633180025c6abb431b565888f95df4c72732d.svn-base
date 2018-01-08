/**
 * Title: MonitorCompTestAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年10月27日
 */
package com.wk.cd.module1.action;

import java.util.List;

import com.wk.cd.module1.ProcessContext;
import com.wk.cd.module1.ProcessManager;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.bean.ViewMonitorModuleInputBean;
import com.wk.cd.module1.bean.ViewMonitorModuleOutputBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.IViewActionBasic;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class ViewMonitorModuleAction
		extends IViewActionBasic<ViewMonitorModuleInputBean, ViewMonitorModuleOutputBean> {
	private static final Log logger = LogFactory.getLog();

	public ViewMonitorModuleOutputBean moitorModuleTestMsg(ViewMonitorModuleInputBean input) {
		logger.debug("----------ViewMonitorCompAction_getCompTestMsg Begin-----------");
		Assert.assertNotEmpty(input.getId(), ViewMonitorModuleInputBean.IDCN);
		ViewMonitorModuleOutputBean output = new ViewMonitorModuleOutputBean();
		com.wk.cd.module1.Process process = null;
		String id = input.getId();
		try {
			process = ProcessManager.instance.getProcessInstance(id);
			ProcessContext pc = process.getCtx();

			output.setTotal_phase(pc.getInstance_info().getPhaseCount());

			output.setCurrent_phase(pc.getCurrentStage() + 1);
			// 当前步骤
			output.setCurrent_step(pc.getCurrentStep() + 1);

			output.setTotal_step(pc.getCurrentCmdCount());
			output.setCurrent_cmd(pc.getCurrentCmd());
			List<Result> results = pc.getResults();
			if (!Assert.isEmpty(results)) {
				Result result = results.get(results.size() - 1);
				output.setCurrent_cmd_status(result.getStatus());
			}
		} catch (Exception e) {
			return null;
		}

		logger.debug("----------ViewMonitorCompAction_getCompTestMsg End-----------");
		return output;
	}

}
