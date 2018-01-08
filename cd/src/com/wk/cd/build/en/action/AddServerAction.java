/**
 * Title: AddServerAction.java
 * File Description: �����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��1��
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
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �����������ӿ�
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
	 * Description: ����������
	 * @param input
	 * @return output
	 */
	@Override
	protected AddServerOutputBean doAction(AddServerInputBean input) {
		logger.info("--------------AddServerAction Begin------------------");
		AddServerOutputBean output = new AddServerOutputBean();
		// ��ȡ�������
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
		// �ǿ�У��
		Assert.assertNotEmpty(server_name, AddServerInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(server_cn_name, AddServerInputBean.SERVER_CN_NAMECN);
		Assert.assertNotEmpty(server_bk_desc, AddServerInputBean.SERVER_BK_DESCCN);
		Assert.assertNotEmpty(server_ip, AddServerInputBean.SERVER_IP_CN);
		Assert.assertNotEmpty(server_os, AddServerInputBean.SERVER_OS_CN);
		Assert.assertNotEmpty(os_sbk_ver, AddServerInputBean.OS_SBK_VERCN);
		// �ظ�У��
		serverCommonService.checkServerIsNotExist(server_name, server_ip);
		// ���ɷ�������Info
		CeServerInfo server_info = new CeServerInfo();
		server_info.setServer_name(server_name);
		server_info.setServer_cn_name(server_cn_name);
		server_info.setServer_desc(server_bk_desc);
		server_info.setServer_ip(server_ip);
		server_info.setServer_os(server_os);
		server_info.setOs_sbk_ver(os_sbk_ver);
		server_info.setServer_db(serverCommonService.generateServerDBString(server_db_list));
		server_info.setServer_mid_ware(serverCommonService.generateEnumValueString(mid_ware_list));
		server_info.setCreate_user_id(input.getOrg_user_id());
		server_info.setCreate_bk_date(input.getDtbs_bk_date());
		server_info.setCreate_bk_time(input.getDtbs_bk_time());
		// ���ɷ���������Դ������Info
		List<CeServerDsInfo> serverds_info_list = serverCommonService
				.generateCeServerDsInfoList(server_name, soc_list, ftp_config_soc, shell_config_soc);
		// ��ʼ���
		logger.info("��ʼ�����������,SERVER_NAME��[" + server_name + "]");
		ceServerDaoService.insertInfo(server_info);
		logger.info("��ʼ�������������Դ������,SERVER_NAME��[" + server_name + "]");
		ceServerDsDaoService.insertListInfo(serverds_info_list);
		logger.info("--------------AddServerAction End------------------");
		return output;
	}

	/**
	 * Description: ������������־���
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddServerInputBean input) {
		List<String> logs = new ArrayList<String>();
		logs.add("���������ƣ�" + input.getCe_server_name());
		logs.add("��������ƣ�" + input.getServer_cn_name());
		logs.add("��������ַ��" + input.getServer_ip());
		return lgsvc.getLogTxt("������������Ϣ", logs);
	}

}