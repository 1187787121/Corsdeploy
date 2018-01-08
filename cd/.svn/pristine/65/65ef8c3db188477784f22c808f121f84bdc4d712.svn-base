/**
 * Title: DeleteUserAction.java
 * File Description: 删除用户
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.exc.CanNotDeleteSelfUserIdException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.DeleteUserInputBean;
import com.wk.cd.system.us.bean.DeleteUserOutputBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserOptPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.lang.Inject;

/**
 * Class Description:删除用户
 * @author link
 */
public class DeleteUserAction
		extends ActionBasic<DeleteUserInputBean, DeleteUserOutputBean> {

	@Inject
	private UsUserDaoService daoService;
	@Inject
	private UsUserRoleDaoService usroledao;
	@Inject
	private UsUserColPrivDaoService colprivdao;
	@Inject
	private UsUserRsPrivDaoService rsprivdao;
	@Inject
	private UsUserSocPrivDaoService socprivdao;
	@Inject
	private UsUserSqlPrivDaoService sqlprivdao;
	@Inject
	private UsUserOptPrivDaoService usUserOptPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description:删除用户
	 * @param input
	 * @return
	 */
	@Override
	protected DeleteUserOutputBean doAction(DeleteUserInputBean input) {
		DeleteUserOutputBean output = new DeleteUserOutputBean();
		String user_id = input.getUser_id();
		Assert.assertNotEmpty(user_id, DeleteUserInputBean.USER_IDCN);
		if (user_id.equals(input.getOrg_user_id())) {
			throw new CanNotDeleteSelfUserIdException();
		}
		daoService.deleteUser(input.getUser_id());
		colprivdao.deleteAllColByUserId(user_id);
		usroledao.deleteUserDprlByUserId(user_id);
		rsprivdao.deleteAllRsByUserId(user_id);
		socprivdao.deleteAllSocByUserId(user_id);
		sqlprivdao.deleteAllSqlByUserId(user_id);
		usUserOptPrivDaoService.deleteOptPrivByUser(user_id);
		return output;
	}

	/**
	 * Description:删除用户日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteUserInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getUser_id());
		return lgsvc.getLogTxt("删除用户", lst_val);
	}

}
