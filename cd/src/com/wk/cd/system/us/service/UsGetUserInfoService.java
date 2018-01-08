/**
 * Title: UsGetUserInfoService.java
 * File Description: 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author link
 */
public class UsGetUserInfoService {

	@Inject
	private UsUserCombineDaoService daoGetUserInfoService;
	@Inject
	private UsDeptRoleDaoService daoDeptRoleSrv;
	@Inject
	private UsUserRoleDaoService daoUserRoleSrv;
	@Inject
	private UsUserDaoService usUserDaoService;
	

	/**
	 * Description: 根据用户名查询用户的角色类型
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryRoleTypeByUserId(String user_id) {
		List<Integer> user_role_typelist = new ArrayList<Integer>();
		user_role_typelist = daoGetUserInfoService
				.queryRoleTypeByUserId(user_id);
		return user_role_typelist;

	}
	
	/**
	 * Description: 根据部门编码和角色编码查询部门角色编码
	 * @param dept_id 部门编码
	 * @param role_code 角色编码
	 * @return 部门角色编码
	 */
	public String queryDprlByDeptAndRole(String dept_id, String role_code){
		return daoDeptRoleSrv.queryDprlByDeptAndRole(dept_id, role_code);
	}
	
	/**
	 * Description: 根据部门角色编码查询权重值最小的用户信息
	 * @param dprl_code 部门角色编码
	 * @param user_ids 不能做复核授权的用户信息
	 * @return
	 */
	public UsUserRoleInfo queryUserRoleByDprlAndMinWeght(String dprl_code, List<String> deal_user_ids){
		String user_ids = listToString(deal_user_ids);
		return daoUserRoleSrv.queryUserRoleByDprlAndMinWeght(dprl_code, user_ids);
	}
	
	/**
	 * Description: 根据部门角色编码查询部门角色信息
	 * @param dprl_code 部门角色编码
	 * @param List<String> deal_user_ids 已做过复核的人员信息
	 * @return
	 */
	public List<UsUserRoleInfo> queryUserByDprlCode(String dprl_code, List<String> deal_user_ids){
		String user_ids = listToString(deal_user_ids);
		return daoUserRoleSrv.queryUserRoleByDprlCode(dprl_code, user_ids);
	}
	
	/**
	 * 将输入的列表转换为字符串，例如列表中为a b c，转换后则为('a','b','c')
	 * @param list 输入列表
	 * @return 字符串
	 */
	private String listToString(List<String> list) {
		String str = "";
		// 输入的部门角色列表信息为空报错
		if (Assert.isEmpty(list)) {
			throw new DataErrorException().addScene("INPUT", "输入列表信息");
		}
		for (String s : list) {
			str += "'" + s + "',";
		}
		str = "(" + str.substring(0, str.length() - 1) + ")";
		return str;
	}
	
	/**
	 * Description: 根据用户名查询用户信息
	 * @param user_id
	 * @return
	 */
	public UsUserInfo getUserInfoByUserId(String user_id) {
		return usUserDaoService.getInfoByKey(user_id);
	}
	
	/** 
	 * Description: 根据用户名查询用户中文名
	 * @param user_id 用户ID
	 * @return 用户中文名
	 */
	public String getUserCnNameByUserId(String user_id) {
		return usUserDaoService.getUserCnNameByUserId(user_id);
	}
}
