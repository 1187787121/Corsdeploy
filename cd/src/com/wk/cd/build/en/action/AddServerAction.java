/**
 * Title: AddServerAction.java
 * File Description: 新增服务器接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wk.cd.build.en.bean.AddServerInputBean;
import com.wk.cd.build.en.bean.AddServerOutputBean;
import com.wk.cd.build.en.bean.DBBean;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.build.en.info.CeServerDsInfo;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.SERVER_OS;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.remote.agent.service.CheckAgentExistService;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 新增服务器接口
 * @author yangl
 */
public class AddServerAction
		extends ActionBasic<AddServerInputBean, AddServerOutputBean> {

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
	 * Description: 新增服务器
	 * @param input
	 * @return output
	 */
	@Override
	protected AddServerOutputBean doAction(AddServerInputBean input) {
		logger.info("--------------AddServerAction Begin------------------");
		AddServerOutputBean output = new AddServerOutputBean();
		// 获取输入参数
		String server_name = input.getCe_server_name();
		String server_cn_name = input.getServer_cn_name();
		String server_bk_desc = input.getServer_bk_desc();
		String server_ip = input.getServer_ip();
		SERVER_OS server_os = input.getServer_os();
		String os_sbk_ver = input.getOs_sbk_ver();
		DBBean[] server_db_list = input.getServer_db_list();
		int[] mid_ware_list = input.getMid_ware_list();
		List<String> soc_list = Assert.isEmpty(input.getSoc_name_list())?null:Arrays.asList(input.getSoc_name_list());
		String ftp_config_soc = input.getFtp_config_soc();
		String shell_config_soc = input.getShell_config_soc();
		YN_FLAG exist_agent_yn_flag = input.getExist_agent_yn_flag();
		YN_FLAG agent_config_yn_flag = input.getAgent_config_yn_flag();
		
		// 非空校验
		Assert.assertNotEmpty(server_name, AddServerInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(server_cn_name, AddServerInputBean.SERVER_CN_NAMECN);
		Assert.assertNotEmpty(server_bk_desc, AddServerInputBean.SERVER_BK_DESCCN);
		Assert.assertNotEmpty(server_ip, AddServerInputBean.SERVER_IP_CN);
		Assert.assertNotEmpty(server_os, AddServerInputBean.SERVER_OS_CN);
		Assert.assertNotEmpty(os_sbk_ver, AddServerInputBean.OS_SBK_VERCN);
		// 重复校验
		serverCommonService.checkServerIsNotExist(server_name, server_ip);
		// 生成服务器表Info
		CeServerInfo server_info = new CeServerInfo();
		server_info.setServer_name(server_name);
		server_info.setServer_cn_name(server_cn_name);
		server_info.setServer_desc(server_bk_desc);
		server_info.setServer_ip(server_ip);
		server_info.setServer_os(server_os);
		server_info.setOs_sbk_ver(os_sbk_ver);
		server_info.setServer_db(serverCommonService.generateServerDBString(server_db_list));
		server_info.setServer_mid_ware(serverCommonService.generateEnumValueString(mid_ware_list));
		server_info.setExist_agent_yn_flag(exist_agent_yn_flag);
		server_info.setAgent_config_yn_flag(agent_config_yn_flag);
		server_info.setCreate_user_id(input.getOrg_user_id());
		server_info.setCreate_bk_date(input.getDtbs_bk_date());
		server_info.setCreate_bk_time(input.getDtbs_bk_time());
		
		// 生成服务器数据源关联表Info
		List<CeServerDsInfo> serverds_info_list = serverCommonService
				.generateCeServerDsInfoList(server_name, soc_list, ftp_config_soc, shell_config_soc);
		// 开始插表
		logger.info("开始插入服务器表,SERVER_NAME：[" + server_name + "]");
		ceServerDaoService.insertInfo(server_info);
		logger.info("开始插入服务器数据源关联表,SERVER_NAME：[" + server_name + "]");
		ceServerDsDaoService.insertListInfo(serverds_info_list);
		logger.info("--------------AddServerAction End------------------");
		return output;
	}

	/**
	 * Description: 新增服务器日志输出
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddServerInputBean input) {
		List<String> logs = new ArrayList<String>();
		logs.add("服务器名称：" + input.getCe_server_name());
		logs.add("服务器简称：" + input.getServer_cn_name());
		logs.add("服务器地址：" + input.getServer_ip());
		return lgsvc.getLogTxt("新增服务器信息", logs);
	}

}
