/**
 * Title: DeleteSocAction.java
 * File Description: 实现删除数据源
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
 * Class Description:实现删除数据源
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
	 * Description: 实现删除数据源
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteSocOutputBean doAction(DeleteSocInputBean input) {
		DeleteSocOutputBean output = new DeleteSocOutputBean();
		Assert.assertNotEmpty(input.getSoc_name(), DeleteSocInputBean.SOC_NAME);
		daoCheckService.checkSocExist(input.getSoc_name());
		daoService.deleteBySocName(input.getSoc_name());
		//删除部门数据源、SQL表、SQL列信息
		dpPublicService.deleteDeptSocBySocName(input.getSoc_name());
		//删除部门的SQL表的权限信息
		dpPublicService.deleteDeptSQLBySocName(input.getSoc_name());
		//删除部门的SQL列的权限信息
		dpPublicService.deleteDeptCOLBySocName(input.getSoc_name());
		//删除部门角色数据源信息
		usDelRolePrivService.deleteRoleSocBySocName(input.getSoc_name());
		//删除部门角色SQL表信息
		usDelRolePrivService.deleteRoleSQLBySocName(input.getSoc_name());
		//删除部门角色SQL列信息
		usDelRolePrivService.deleteRoleCOLBySocName(input.getSoc_name());
		//删除用户数据源信息
		usDelUserPrivService.deleteUserSocBySocName(input.getSoc_name());
		//删除用户SQL表信息
		usDelUserPrivService.deleteUserSQLBySocName(input.getSoc_name());
		//删除用户SQL列信息
		usDelUserPrivService.deleteUserCOLBySocName(input.getSoc_name());
		return output;
	}

	/**
	 * Description: 实现删除数据源日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteSocInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getSoc_name());
		return lgsvc.getLogTxt("删除数据源日志信息", lst_val);
	}

}
