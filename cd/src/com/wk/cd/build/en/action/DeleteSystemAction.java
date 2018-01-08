/**
 * Title: DeleteSystemAction.java
 * File Description: ɾ��Ӧ��ϵͳ
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��1��
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.DeleteSystemInputBean;
import com.wk.cd.build.en.bean.DeleteSystemOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeSystemCfgDaoService;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.dao.CeSystemTemplateDaoService;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.build.exc.SystemExistEnvException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ɾ��Ӧ��ϵͳ
 * @author chiss
 */
public class DeleteSystemAction extends ActionBasic<DeleteSystemInputBean, DeleteSystemOutputBean> {
	@Inject
	private CeSystemDaoService ceSystemDaoService;
	@Inject
	private SystemService systemService;
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private CeSystemTemplateDaoService ceSystemTemplateDaoService;
	@Inject
	private CeSystemCfgDaoService ceSystemCfgDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: ɾ��Ӧ��ϵͳ����
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteSystemOutputBean doAction(DeleteSystemInputBean input) {
		logger.info("-----------DeleteSystemAction begin----------");
		DeleteSystemOutputBean output = new DeleteSystemOutputBean();
		String sys_name = input.getSys_name();
		// �ǿ�У��
		Assert.assertNotEmpty(sys_name, DeleteSystemInputBean.SYS_NAMECN);
		// У���Ƿ����
		systemService.checkSystemNameIsNotExist(sys_name);
		// Ӧ��ϵͳ�´��ڹ�������������ɾ��
		if(ceEnvironmentDaoService.countEnvironmentBySysName(sys_name) > 0) {
			throw new SystemExistEnvException().addScene("SYSTEM", sys_name);
		}
		// ɾ�� ģ�������
		ceSystemTemplateDaoService.deleteSystempBySysName(sys_name);
		// ɾ��Ӧ��ϵͳ�����ļ���
		ceSystemCfgDaoService.deleteSystemCfgBySysName(sys_name);
		// ɾ��һ����¼
		CeSystemInfo info = new CeSystemInfo();
		info.setSys_name(sys_name);
		ceSystemDaoService.deleteSystem(info);
		logger.info("-----------DeleteSystemAction begin----------");
		return output;
	}

	/**
	 * Description: ɾ��Ӧ��ϵͳ��־д��
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteSystemInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("sys_name��" + input.getSys_name());
		return lgsvc.getLogTxt("ɾ��ϵͳ", lst_val);
	}

}
