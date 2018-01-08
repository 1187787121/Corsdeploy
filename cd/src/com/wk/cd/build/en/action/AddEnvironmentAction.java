/**
 * Title: AddCeEnvironmentAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.AddEnvironmentInputBean;
import com.wk.cd.build.en.bean.AddEnvironmentOutputBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.system.ep.dao.DpDeptEnvPrivDaoService;
import com.wk.cd.system.ep.dao.UsUserEnvPrivDaoService;
import com.wk.cd.system.ep.info.DpDeptEnvPrivInfo;
import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ���Ӧ�û���
 * @author xuph
 */
public class AddEnvironmentAction
		extends ActionBasic<AddEnvironmentInputBean, AddEnvironmentOutputBean> {
	@Inject
	private EnvironmentPublicService cePublishService;
	@Inject
	private ServerCommonService serverCommonService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private UsUserEnvPrivDaoService userEnvPrivDaoService;
	@Inject
	private DpDeptEnvPrivDaoService dpDeptEnvPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ���Ӧ�û�������
	 * @param input
	 * @return
	 */
	@Override
	protected AddEnvironmentOutputBean doAction(AddEnvironmentInputBean input) {
		logger.info("---------------------AddCeEnvironmentAction Begin-------------");
		AddEnvironmentOutputBean output = new AddEnvironmentOutputBean();
		String user_id = input.getOrg_user_id();
		String dept_id = input.getOrg_dept_id();

		String env_name = input.getEnv_name();
		String sys_name = input.getSys_name();
		String env_cn_name = input.getEnv_cn_name();
		String env_bk_desc = input.getEnv_bk_desc();
		DT_RANGE dt_range = input.getDt_range();
		String[] ele_types = input.getEle_type();
		ENV_TYPE env_type = input.getEnv_type();
		List<ServerBean> server_list = input.getServer_list();
		// �ǿ�У��
		Assert.assertNotEmpty(user_id, AddEnvironmentInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(dept_id, AddEnvironmentInputBean.ORG_DEPT_IDCN);
		Assert.assertNotEmpty(env_name, AddEnvironmentInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(sys_name, AddEnvironmentInputBean.SYS_NAMECN);
		Assert.assertNotEmpty(env_cn_name, AddEnvironmentInputBean.ENV_CN_NAMECN);
		Assert.assertNotEmpty(env_bk_desc, AddEnvironmentInputBean.ENV_BK_DESCCN);
		Assert.assertNotEmpty(ele_types, AddEnvironmentInputBean.ELE_TYPECN);
		Assert.assertNotEmpty(env_type.getValue() == 0 ? null : env_type.getValue(), AddEnvironmentInputBean.ENV_TYPECN);
		// У�����ݷ�Χ�ֶ�
		cePublishService.checkDtRangeIsNotEmpty(dt_range, ele_types);
		// �Ϸ���У��
		cePublishService.checkEnvNameNotExist(env_name);
		// ����Ҫ��
		String ele_type = cePublishService.getEleTypeStr(ele_types);
		// Ӧ�û�������
		CeEnvironmentInfo envir_info = new CeEnvironmentInfo();
		envir_info.setEnv_name(env_name);
		envir_info.setEnv_cn_name(env_cn_name);
		envir_info.setEle_type(ele_type);
		envir_info.setEnv_bk_desc(env_bk_desc);
		envir_info.setDt_range(dt_range);
		envir_info.setEnv_type(env_type);
		envir_info.setSys_name(sys_name);
		envir_info.setCreate_bk_date(input.getDtbs_bk_date());
		envir_info.setCreate_bk_time(input.getDtbs_bk_time());
		envir_info.setCreate_user_id(input.getOrg_user_id());
		logger.info("����Ӧ�û��� Env_name=[{}]", env_name);
		// ��������������
		List<CeEnvironmentServerInfo> envir_servers = cePublishService.getCeEnvServerList(server_list, env_name);
		// ����Ӧ�û�����
		ceEnvironmentDaoService.insertInfo(envir_info);
		// ���뻷�����������
		ceEnvironmentServerDaoService.insertListInfo(envir_servers);

		// ��ӻ����û�����Ȩ��
		DpDeptEnvPrivInfo deptEnvPrivInfo = new DpDeptEnvPrivInfo();
		deptEnvPrivInfo.setDept_id(dept_id);
		deptEnvPrivInfo.setEnv_name(env_name);
		deptEnvPrivInfo.setSys_name(sys_name);
		dpDeptEnvPrivDaoService.insertInfo(deptEnvPrivInfo);
		// ��ӻ����û�Ȩ��
		UsUserEnvPrivInfo userEnvPrivInfo = new UsUserEnvPrivInfo();
		userEnvPrivInfo.setUser_id(user_id);
		userEnvPrivInfo.setEnv_name(env_name);
		userEnvPrivInfo.setSys_name(sys_name);
		userEnvPrivInfo.setPriv_type(PRIV_TYPE.PERPETUAL);
		userEnvPrivInfo.setAf_flag(AF_FLAG.ALLOW);
		userEnvPrivDaoService.insertInfo(userEnvPrivInfo);
		logger.info("---------------------AddCeEnvironmentAction End---------------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddEnvironmentInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("Env_name=" + input.getEnv_name());
		lst_val.add("Env_cn_name=" + input.getEnv_cn_name());
		return lgsvc.getLogTxt("����Ӧ�û���", lst_val);
	}

}
