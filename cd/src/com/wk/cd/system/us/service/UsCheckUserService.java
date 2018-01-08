/**
 * Title: UsCheckUser.java
 * File Description: 检查用户的登录状态和记录状态是否正常
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.USE_STATE;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.exc.IllegalOperaterException;
import com.wk.cd.system.exc.UserTermPendAuthException;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.dao.UsUserTermDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserTermInfo;
import com.wk.lang.Inject;

/**
 * Class Description:检查用户的登录状态和记录状态是否正常
 * @author link
 */
public class UsCheckUserService {
	@Inject
	private UsUserDaoService daoService;
	@Inject
	private UsDeptRoleDaoService checkService;
	@Inject
	private UsPasswdService daoPwdService;
	@Inject
	private UsUserGetRoleDeptService daodeptsrv;
	@Inject
	private UsUserCombineDaoService daoGetUserInfoService;
	@Inject
	private UsUserRoleDaoService userRoleDaoService;
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private UsUserTermDaoService usUserTermDaoService;
	@Inject
	private UsGetUserInfoService usGetUserInfoService;
	
	/**
	 * 
	 * Description: 根据用户id获取用户信息
	 * @param user_id
	 * @return
	 */
	public UsUserInfo getUserInfoById(String user_id) {
		UsUserInfo info=new UsUserInfo();
		info.setUser_id(user_id);
		return daoService.getInfoByKey(user_id);
	}

	/**
	 * Description: 检查用户的记录状态是否正常
	 * @param user_id
	 */
	public void checkUserState(String user_id) {
		daoService.checkUserState(user_id);
	}

	/**
	 * Description: 检查用户的登录状态
	 * @param user_id
	 */
	public void checkUserLoginNum(String user_id) {
		daoService.checkUserLoginNum(user_id);
	}

	/**
	 * Description: 用户登录
	 * @param user_id
	 * @param user_passwd
	 */
	public UsUserInfo userLoginIn(String user_id, String user_passwd) {
		daoPwdService.checkUserPassWd(user_id, user_passwd);
		UsUserInfo info = daoService.getInfoByKeyForUpdate(user_id);
		return info;
	}

	/**
	 * Description: 用户退出
	 * @param user_id
	 * @param user_passwd
	 */
	public void userLoginOut(String user_id) {
		UsUserInfo info = daoService.getInfoByKeyForUpdate(user_id);
		info.setLogin_bk_num(info.getLogin_bk_num()-1);
		if(info.getLogin_bk_num()<0) {
			info.setLogin_bk_num(0);
		}
		daoService.updateLoginNumByUserId(info.getLogin_bk_num(), user_id);
	}

	/**
	 * 用户强制登出
	 * @param user_id 
	 */
	public void userForceLogout(String user_id){
		daoService.updateLoginNumByUserId(0, user_id);
	}

	/**
	 * Description: 检查部门角色编码是否存在
	 * @param dprl_code
	 */
	public void checkDprlExist(String dprl_code) {
		checkService.checkDprlExist(dprl_code);
	}

	/**
	 * 检查用户是否是管理员，如果不是，则检查用户是否属于传入的机构，如果不是则报错
	 * @param user_id 用户名
	 * @param dept_id 部门编码
	 */
	public void checkUserDeptMatch(String user_id, String dept_id) {
		Assert.assertNotEmpty(user_id, "用户名");
		Assert.assertNotEmpty(dept_id, "部门编码");
		List<Integer> user_role_typelist = daoGetUserInfoService
				.queryRoleTypeByUserId(user_id);
		if (!user_role_typelist.contains(1) && !user_role_typelist.contains(2)) {
			List<String> dept_list = daodeptsrv.queryDeptByUserId(user_id);
			if (!dept_list.contains(dept_id)) {
				UsUserInfo userInfo=new UsUserInfo();
				userInfo.setUser_id(user_id);
				DpDeptInfo dpInfo = new DpDeptInfo();
				dpInfo.setDept_id(dept_id);
				throw new IllegalOperaterException().addScene("PARM1", daoService.getInfoByKey(userInfo).getUser_cn_name()+"[" + user_id + "]")
						.addScene("PARM2", dpDeptDaoService.getInfoByKey(dpInfo).getDept_cn_name() + "[" + dept_id + "]");
			}
		}
	}

	/** 
	 * 检查部门角色下是否存在用户
	 * @param dprl_code
	 * @param auth_user_id
	 * @return 
	 */
	public boolean checkDprlHasUser(String dprl_code, String auth_user_id) {
		return userRoleDaoService.checkDprlHasUser(dprl_code,auth_user_id);
	}

	/** 
	 * 检查用户名密码是否正确
	 * @param auth_user_id
	 * @param user_passed
	 * @return 
	 */
	public void checkLocalAuthPasswd(String auth_user_id, String user_passed) {
		daoPwdService.checkUserPassWd(auth_user_id, user_passed);
	}

	/** 根据部门角色编码查询部门角色说明
	 * @param dprl_code
	 * @return 
	 */
	public String queryExplByDprl(String dprl_code) {
		UsDeptRoleInfo dprlInfo=new UsDeptRoleInfo();
		dprlInfo.setDprl_code(dprl_code);
		dprlInfo=checkService.getInfoByKey(dprlInfo);
		return dprlInfo.getBk_expl();
	}

	/** 
	 * Description: 校验用户登录终端是否为用户绑定终端
	 * @param org_user_id
	 * @param remote_ip
	 * @return 
	 */
	public boolean checkUserTermExist(String user_id, String term_no) {
		UsUserTermInfo info=new UsUserTermInfo();
		info.setUser_id(user_id);
		info.setTerm_no(term_no);
		info=usUserTermDaoService.getInfoByKey(info);
		if(!Assert.isEmpty(info)&&info.getUse_state()==USE_STATE.USED){
			return true;
		}else if(!Assert.isEmpty(info)&&info.getUse_state()==USE_STATE.UNUSE){
			throw new UserTermPendAuthException();
		}
		return false;
	}
	

	/** 
	 * Description: 判断用户是否管理员
	 * @param user_id
	 * @return 
	 */
	public boolean isUserManager(String user_id) {
		List<Integer> role_types=usGetUserInfoService.queryRoleTypeByUserId(user_id);
		for (int role_type : role_types) {
			if (role_type <= 2) {
				return true;
			}
		}
		return false;
	}

	/** 
	 * Description: 判断多个部门角色是否管理员
	 * @param dprl_list
	 * @return 
	 */
	public boolean isDprlsManager(String[] dprl_list) {
		for(String dprl_code:dprl_list){
			Integer role_type=checkService.getRoleTypeByDprl(dprl_code);
			if(role_type <= 2){
				return true;
			}
		}
		return false;
	}
}
