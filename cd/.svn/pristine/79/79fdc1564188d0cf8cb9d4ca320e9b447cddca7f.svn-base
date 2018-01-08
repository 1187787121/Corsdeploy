/**
 * Title: UsUserRoleDeptService.java
 * File Description: 根据用户名查询该用户所拥有的部门角色编码以及所属的部门
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.us.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.lang.Inject;

/**
 * Class Description:根据用户名查询该用户所拥有的部门角色编码以及所属的部门
 * @author link
 */
public class UsUserGetRoleDeptService {
	@Inject
	private UsUserRoleDaoService daoDprlService;
	@Inject
	private UsUserDaoService daoUserService;
	@Inject
	private ActionLogPublicService lgsvc;
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaosrv;

	/**
	 * Description: 根据用户名查询该用户所拥有的部门角色编码
	 * @param String 输入变量类型
	 * @param user_id 输入变量
	 * @return dprl_list 返回部门角色编码列表
	 */
	public List<String> queryDprlByUserId(String user_id) {
		return daoDprlService.queryDprlByUserId(user_id);
	}
	
	/** 
	 * Description: 查找用户下的所属部门角色描述
	 * @param org_user_id
	 * @param bl_dept_id
	 * @return 
	 */
	public List<String> queryBlDrExplByUserId(String user_id,
			String bl_dept_id) {
		return daoDprlService.queryBlDrExplByUserId(user_id,bl_dept_id);
	}
	
	/**
	 * 获取用户对应的所有角色编码
	 * @param user_id 用户名
	 * @return 角色编码
	 */
	public List<String> queryUserRole(String user_id){
		List<String> dprl_list = queryDprlByUserId(user_id);
		String dprl_str = listToString(dprl_list);
		return usDeptRoleDaosrv.queryRoleCode(dprl_str);
	}
	/**
	 * Description: 根据用户查询所属的部门
	 * @param user_id
	 * @return
	 */
	public List<String> queryDeptByUserId(String user_id) {
		List<String> dept_list = new ArrayList<String>();
		if (!Assert.isEmpty(user_id)) {
			dept_list = daoUserService.queryDeptByUserId(user_id);
			return dept_list;
		}
		return null;
	}

	/**
	 * Description:根据用户名查询该用户所属的部门服务的日志信息
	 * @param input 输入变量
	 * @return
	 */
	protected String getLogTxt(String user_id) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(user_id);
		return lgsvc.getLogTxt("根据用户名查询所所属的部门", lst_val);
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
}
