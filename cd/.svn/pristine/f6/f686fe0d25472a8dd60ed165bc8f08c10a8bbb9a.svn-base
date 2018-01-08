/**
 * Title: UsUserCombineDaoService.java
 * File Description: 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.us.bean.UsUserCombineBean;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author link
 */
public class UsUserCombineDaoService {

	@Inject
	private UsUserCombineDao daoService;
	
	/**
	 * Description: 查询用户的角色类型
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryRoleTypeByUserId(String user_id){
		List<UsUserCombineBean> user_infos = new ArrayList<UsUserCombineBean>();
		List<Integer> user_role_type_list = new ArrayList<Integer>();
		user_infos = daoService.queryUserCombineInfo(user_id);
		for(UsUserCombineBean info : user_infos){
			user_role_type_list.add(info.getRole_type());
		}
		return user_role_type_list;
	}
	
	
}
