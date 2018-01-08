/**
 * Title: ViewUsforAppAction.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2015年11月12日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.us.bean.UserAppBean;
import com.wk.cd.system.us.bean.ViewUsInputBean;
import com.wk.cd.system.us.bean.ViewUsOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.lang.Inject;

/**
 * Class Description: 查询执行用户
 * @author Xul
 */
public class ViewUsforAppAction
		extends IViewActionBasic<ViewUsInputBean, ViewUsOutputBean> {

	@Inject
	private DpDeptDaoService deptDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;

	/**
	 * Description: 根据执行用户ID查询所在部门所有用户ID和中文名,提供Web端
	 * @return
	 */
	public ViewUsOutputBean getDeptUserListForWeb(ViewUsInputBean input) {

		// 非空检验
		Assert.assertNotEmpty(input.getOrg_user_id(), ViewUsInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(input.getOrg_dept_id(), ViewUsInputBean.ORG_DEPT_IDCN);

		// 根据上级部门编码查询所有的部门
		ViewUsOutputBean output = new ViewUsOutputBean();
		List<String> dept_list = deptDaoService.querySubDeptIdByKey(input.getOrg_user_id());
		dept_list.add(input.getOrg_dept_id());
		List<UserAppBean> user_list = new ArrayList<UserAppBean>();
		// 便利查询每个部分下的所有用户
		for (int i = 0; i < dept_list.size(); i++) {
			String bl_dept_id = dept_list.get(i);
			user_list.addAll(usUserDaoService.queryUsersByDeptId(bl_dept_id));
		}
		// 获取公共帐号ID
		String public_account = CfgTool.getProjectPropterty("cv.dept.share.user");
		// 添加公共帐号标志
		for (int i = 0; i < user_list.size(); i++) {
			user_list.get(i).setIs_public_account(0);
			// 若配置文件中部门共享账户不为空，则返回是否共享账户为1
			if (!Assert.isEmpty(public_account)) {
				if (public_account.equals(user_list.get(i).getUser_id())) {
					user_list.get(i).setIs_public_account(1);
				}
			}
		}

		output.setUser_dept_list(user_list);
		return output;
	}

	/**
	 * Description: 根据执行用户ID查询所在部门所有用户ID和中文名,提供APP端
	 * @param input
	 * @return
	 */
	public ViewUsOutputBean getDeptUserlist(ViewUsInputBean input) {
		String execute_user_id = input.getExecute_user_id();

		// 非空检验
		Assert.assertNotEmpty(execute_user_id, ViewUsInputBean.EXECUTE_USER_IDCN);

		ViewUsOutputBean output = new ViewUsOutputBean();
		UsUserInfo userInfo = usUserDaoService.getInfoByKey(execute_user_id);
		if (Assert.isEmpty(userInfo)) {
			throw new RecordNotFoundException().addScene("TABLE", UsUserInfo.TABLECN).addScene("FIELD",
					execute_user_id);
		}
		List<UserAppBean> user_app_list = usUserDaoService.queryUsersByDeptId(userInfo.getBl_dept_id());
		output.setUser_app_list(user_app_list);
		return output;
	}

	/**
	 * Description: 根据部门编号查询该部门所有用户ID和中文名
	 * @param input
	 * @return
	 */
	public ViewUsOutputBean getUserListByDept(ViewUsInputBean input) {
		ViewUsOutputBean output = new ViewUsOutputBean();
		String dept_id = input.getDept_id();
		// 非空检验
		Assert.assertNotEmpty(dept_id, ViewUsInputBean.DEPT_IDCN);
		List<UserAppBean> user_list = usUserDaoService.queryUsersByDeptId(dept_id);
		output.setUser_list(user_list);
		return output;
	}

	/**
	 * Description: 根据部门编号查询部门角色列表
	 * @param input
	 * @return
	 */
	public ViewUsOutputBean queryRoleByDept(ViewUsInputBean input) {
		ViewUsOutputBean output = new ViewUsOutputBean();

		// 非空校验
		String dept_id = input.getDept_id();
		Assert.assertNotEmpty(dept_id, ViewUsInputBean.DEPT_IDCN);

		// 根据部门编号查询部门角色
		List<UsDeptRoleInfo> dept_role_list = usDeptRoleDaoService.queryRoleInfoByDept(dept_id);
		output.setDept_role_list(dept_role_list);

		return output;
	}

	/**
	 * Description: 校验用户是否存在
	 * @param input
	 * @return
	 */
	public ViewUsOutputBean checkUserId(ViewUsInputBean input) {
		ViewUsOutputBean output = new ViewUsOutputBean();
		Assert.assertNotEmpty(input.getData(), ViewUsInputBean.DATACN);
		UsUserInfo info = usUserDaoService.getInfoByKey(input.getData());
		if (!Assert.isEmpty(info) && info.getRcd_state() == RCD_STATE.NORMAL) {
			output.setResult(true);
		}
		return output;
	}
}
