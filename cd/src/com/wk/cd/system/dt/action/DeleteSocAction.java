/**
 * Title: DeleteSocAction.java
 * File Description: ʵ��ɾ������Դ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.system.dt.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.dt.bean.DeleteSocInputBean;
import com.wk.cd.system.dt.bean.DeleteSocOutputBean;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsDelRolePrivService;
import com.wk.cd.system.us.service.UsDelUserPrivService;
import com.wk.lang.Inject;

/**
 * Class Description:ʵ��ɾ������Դ
 * @author link
 */
public class DeleteSocAction
		extends ActionBasic<DeleteSocInputBean, DeleteSocOutputBean> {

	@Inject
	private DtSourceDaoService daoService;
	@Inject
	private DtCheckSocExistService daoCheckService;
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private UsDelRolePrivService usDelRolePrivService;
	@Inject
	private UsDelUserPrivService usDelUserPrivService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: ʵ��ɾ������Դ
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteSocOutputBean doAction(DeleteSocInputBean input) {
		DeleteSocOutputBean output = new DeleteSocOutputBean();
		Assert.assertNotEmpty(input.getSoc_name(), DeleteSocInputBean.SOC_NAME);
		daoCheckService.checkSocExist(input.getSoc_name());
		daoService.deleteBySocName(input.getSoc_name());
		//ɾ����������Դ��SQL��SQL����Ϣ
		dpPublicService.deleteDeptSocBySocName(input.getSoc_name());
		//ɾ�����ŵ�SQL���Ȩ����Ϣ
		dpPublicService.deleteDeptSQLBySocName(input.getSoc_name());
		//ɾ�����ŵ�SQL�е�Ȩ����Ϣ
		dpPublicService.deleteDeptCOLBySocName(input.getSoc_name());
		//ɾ�����Ž�ɫ����Դ��Ϣ
		usDelRolePrivService.deleteRoleSocBySocName(input.getSoc_name());
		//ɾ�����Ž�ɫSQL����Ϣ
		usDelRolePrivService.deleteRoleSQLBySocName(input.getSoc_name());
		//ɾ�����Ž�ɫSQL����Ϣ
		usDelRolePrivService.deleteRoleCOLBySocName(input.getSoc_name());
		//ɾ���û�����Դ��Ϣ
		usDelUserPrivService.deleteUserSocBySocName(input.getSoc_name());
		//ɾ���û�SQL����Ϣ
		usDelUserPrivService.deleteUserSQLBySocName(input.getSoc_name());
		//ɾ���û�SQL����Ϣ
		usDelUserPrivService.deleteUserCOLBySocName(input.getSoc_name());
		return output;
	}

	/**
	 * Description: ʵ��ɾ������Դ��־��Ϣ
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteSocInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSoc_name());
		return lgsvc.getLogTxt("ɾ������Դ��־��Ϣ", lst_val);
	}

}
