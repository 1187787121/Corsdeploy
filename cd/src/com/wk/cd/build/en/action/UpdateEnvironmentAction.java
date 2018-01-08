/**
 * Title: UpdateEnvironmentAction.java
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

import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.bean.UpdateEnvironmentInputBean;
import com.wk.cd.build.en.bean.UpdateEnvironmentOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸�Ӧ�û�����Ϣ
 * @author xuph
 */
public class UpdateEnvironmentAction extends ActionBasic<UpdateEnvironmentInputBean, UpdateEnvironmentOutputBean>{
     @Inject private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
     @Inject private CeEnvironmentDaoService ceEnvironmentDaoService;
     @Inject private EnvironmentPublicService cePublishService;
     @Inject private ServerCommonService serverCommonService;
	 @Inject private ActionLogPublicService lgsvc;
	 private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: �޸�Ӧ�û�����Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateEnvironmentOutputBean doAction(UpdateEnvironmentInputBean input) {
		logger.info("--------------------UpdateEnvironmentAction Begin-------------");
		UpdateEnvironmentOutputBean output = new UpdateEnvironmentOutputBean();
		String env_name = input.getEnv_name();
		String sys_name = input.getSys_name();
		String env_cn_name = input.getEnv_cn_name();
		String env_bk_desc = input.getEnv_bk_desc();
		DT_RANGE dt_range = input.getDt_range();
		String[] ele_types = input.getEle_type();
		ENV_TYPE env_type = input.getEnv_type();
		List<ServerBean>server_list = input.getServer_list();
		// �ǿ�У��
		Assert.assertNotEmpty(env_name, UpdateEnvironmentInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(sys_name, UpdateEnvironmentInputBean.SYS_NAMECN);
		Assert.assertNotEmpty(env_cn_name, UpdateEnvironmentInputBean.ENV_CN_NAMECN);
		Assert.assertNotEmpty(env_bk_desc, UpdateEnvironmentInputBean.ENV_BK_DESCCN);
		Assert.assertNotEmpty(ele_types, UpdateEnvironmentInputBean.ELE_TYPECN);
		Assert.assertNotEmpty(env_type.getValue()==0?null:env_type.getValue(), UpdateEnvironmentInputBean.ENV_TYPECN);
		// У�����ݷ�Χ�ֶ�
		cePublishService.checkDtRangeIsNotEmpty(dt_range,ele_types);
		// �Ϸ���У��
		cePublishService.checkEnvNameIsExist(env_name);
		// ����Ҫ��
		String ele_type = cePublishService.getEleTypeStr(ele_types);
		// ��û�����������Ϣ
		List<CeEnvironmentServerInfo> envir_servers = cePublishService.getCeEnvServerList(server_list, env_name);
		//���»�����Ϣ��
		logger.info("�޸�Ӧ�û��� Env_name=[{}]",env_name);
		ceEnvironmentDaoService.updateEnvirMsgByKey(env_cn_name,env_bk_desc,ele_type,dt_range,env_type,sys_name,input.getDtbs_bk_date(),input.getDtbs_bk_time(),input.getOrg_user_id(),env_name);
		//ɾ������
		ceEnvironmentServerDaoService.deleteEnvirServerByEnvName(env_name);
		// ���뻷�����������
		ceEnvironmentServerDaoService.insertListInfo(envir_servers);
		logger.info("--------------------UpdateEnvironmentAction End-------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param arg0
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateEnvironmentInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("Env_name=" + input.getEnv_name());
		lst_val.add("Env_cn_name=" + input.getEnv_cn_name());
		return lgsvc.getLogTxt("�޸�Ӧ�û���", lst_val);
	}

}
