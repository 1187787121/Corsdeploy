/**
 * Title: DeleteDprlAction.java
 * File Description: 实现删除部门角色及权限功能
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.exc.DerlHasUserException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.DeleteDprlInputBean;
import com.wk.cd.system.us.bean.DeleteDprlOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsRoleOptPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.service.UsDelRolePrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 实现删除部门角色及权限功能
 * @author link
 */
public class DeleteDprlAction
		extends ActionBasic<DeleteDprlInputBean, DeleteDprlOutputBean> {

	@Inject
	private UsDeptRoleDaoService daoService;
	@Inject
	private UsDelRolePrivService delPrivService;
	@Inject
	private UsUserRoleDaoService usUserRoleDaoService;
	@Inject
	private UsRoleOptPrivDaoService usRoleOptPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description:实现删除部门角色功能
	 * @param input 输入接口
	 * @return
	 */
	@Override
	protected DeleteDprlOutputBean doAction(DeleteDprlInputBean input) {
		DeleteDprlOutputBean output = new DeleteDprlOutputBean();
		if (!Assert.isEmpty(input.getDprl_code())) {
			daoService.checkDprlExist(input.getDprl_code());
			logger.debug("传入的部门角色编码是:[{}]", input.getDprl_code());
			//*根据角色编码查找用户数
			int count_user=usUserRoleDaoService.countUserByDprlCode(input.getDprl_code());
			if (count_user>0) {
				String dept_role_name = daoService.getDeptByDprl(input.getDprl_code()).getBk_expl();
				throw new DerlHasUserException().addScene("PARM", dept_role_name + "[" + input.getDprl_code() + "]");
			}
			daoService.deleteDprl(input.getDprl_code());
			delPrivService.deleteAllRsByDprl(input.getDprl_code());
			delPrivService.deleteAllSocByDprl(input.getDprl_code());
			delPrivService.deleteAllSqlByDprl(input.getDprl_code());
			delPrivService.deleteAllColByDprl(input.getDprl_code());
			usRoleOptPrivDaoService.deleteOptPrivByDprl(input.getDprl_code());
		}
		
		return output;
	}

	/**
	 * Description:删除部门角色日志信息
	 * @param input 输入接口
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteDprlInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getDprl_code());
		return lgsvc.getLogTxt("删除部门角色日志信息", lst_val);
	}

}
