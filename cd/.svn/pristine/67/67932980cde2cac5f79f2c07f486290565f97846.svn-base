/**
 * Title: DeleteServerAction.java
 * File Description: 删除服务器接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.DeleteServerInputBean;
import com.wk.cd.build.en.bean.DeleteServerOutputBean;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除服务器接口
 * @author yangl
 */
public class DeleteServerAction
		extends ActionBasic<DeleteServerInputBean, DeleteServerOutputBean> {

	@Inject
	private ServerCommonService serverCommonService;
	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private CeServerDsDaoService ceServerDsDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 修改服务器
	 * @param input
	 * @return output
	 */
	@Override
	protected DeleteServerOutputBean doAction(DeleteServerInputBean input) {
		logger.info("--------------DeleteServerAction Begin------------------");
		DeleteServerOutputBean output = new DeleteServerOutputBean();
		String server_name = input.getCe_server_name();
		Assert.assertNotEmpty(server_name, DeleteServerInputBean.CE_SERVER_NAMECN);
		// 存在校验
		serverCommonService.checkServerIsExist(server_name);
		serverCommonService.checkServerNotBeUsed(server_name);
		// 开始删除表
		ceServerDsDaoService.deleteInfoByServerName(server_name);
		ceServerDaoService.deleteInfoByServerName(server_name);
		logger.info("--------------DeleteServerAction End------------------");
		return output;
	}

	/**
	 * Description: 删除服务器日志输出
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteServerInputBean input) {
		List<String> logs = new ArrayList<String>();
		logs.add("服务器名称：" + input.getCe_server_name());
		return lgsvc.getLogTxt("删除服务器信息", logs);
	}

}
