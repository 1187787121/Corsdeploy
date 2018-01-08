/**
 * Title: DeleteSrvAction.java
 * File Description: 删除服务
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.sv.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.exc.ServiceUsedByPendWorkException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.bean.DeleteSrvInputBean;
import com.wk.cd.system.sv.bean.DeleteSrvOutputBean;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:删除服务
 * @author tlw
 */
public class DeleteSrvAction
		extends ActionBasic<DeleteSrvInputBean, DeleteSrvOutputBean> {
	@Inject
	private SvSrvService ssSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private WorkPublicService wksrv;
	@Inject
	private ApPublicService apsrv;
	private static final Log logger = LogFactory.getLog();

	@Override
	/** 
	 * Description: 按照服务名称删除服务
	 * @param input 输入接口
	 * @return 
	 */
	protected DeleteSrvOutputBean doAction(DeleteSrvInputBean input) {
		DeleteSrvOutputBean output = new DeleteSrvOutputBean();
		// 服务名称非空检查
		String srv_name = input.getSrv_name();
		logger.info("按照服务名称删除服务：srv_name = [{}]", srv_name);
		Assert.assertNotEmpty(srv_name, DeleteSrvInputBean.SRV_NAMECN);
		logger.info("**************DELETE  SV_SRV***************");
		// 检查服务是否有对应待处理任务
		List<WORKFLOW_STATE> workflow_state_list = new ArrayList<WORKFLOW_STATE>();
		workflow_state_list.add(WORKFLOW_STATE.RECHECK);
		workflow_state_list.add(WORKFLOW_STATE.APPROVAL);
		workflow_state_list.add(WORKFLOW_STATE.EXECUTORY);
		List<String> work_list = wksrv.queryPendWorkSeqByWorkState(srv_name, workflow_state_list);
		if(!Assert.isEmpty(work_list)){
			throw new ServiceUsedByPendWorkException().addScene("PARM1", work_list.get(0));
		}
		// 删除服务定义表中信息（逻辑删除）
		SvSrvInfo info = new SvSrvInfo();
		info.setSrv_name(srv_name);
		ssSrv.queryNoDelSvSrvByName(info);
		ssSrv.lgDeleteSvSrv(srv_name);
		// 删除服务复核表中信息
		if (apsrv.countSvSrvCheck(srv_name) > 0) {
			logger.info("**************DELETE  SV_SRV_CHECK***************");
			apsrv.deleteSvSrvCheck(srv_name);
		}
		// 删除服务授权表中信息
		if (apsrv.countSvSrvAuth(srv_name) > 0) {
			logger.info("**************DELETE  SV_SRV_AUTH***************");
			apsrv.deleteSvSrvAuth(srv_name);
		}
		// 删除服务数据源定义表中信息
		if (ssSrv.countSvSrvSoc(srv_name) > 0) {
			logger.info("**************DELETE  SV_SRV_SOC***************");
			ssSrv.deleteSvSrvSoc(srv_name);
		}
		// 删除远程服务调用配置表中记录信息
		if (ssSrv.countSvRemoteSrv(srv_name) > 0) {
			logger.info("**************DELETE SV_REMOTE_SRV*************");
			ssSrv.deleteSvRemoteSrv(srv_name);
		}
		return output;
	}

	/**
	 * Description: 删除服务
	 * @param input 输入接口
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(DeleteSrvInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSrv_name());
		return lgsrv.getLogTxt("删除服务", lst_val);
	}

}
