/**
 * Title: UsAfUserPriv.java
 * File Description:  查询用户被禁止的权限
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-10
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.dao.UsUserPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.lang.Inject;

/**
 * Class Description: 查询用户被禁止的权限
 * @author link
 */
public class UsAfUserPriv {
	@Inject
	private UsUserRsPrivDaoService daoAfRsService;
	@Inject
	private UsUserSocPrivDaoService daoAfSocService;
	@Inject
	private UsUserPrivDaoService daoAfSqlColService;

	/**
	 * Description: 查询用户被禁止的数据源权限
	 * @param user_id
	 * @return
	 */
	public List<String> queryUserAfSocPriv(String user_id) {
		return daoAfSocService.queryUserAfSocPriv(user_id);
	}

	/**
	 * Description: 查询用户被禁止的SQL操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfSqlPriv(String user_id) {
		return daoAfSqlColService.queryUserAfSqlPriv(user_id);
	}

	/**
	 * Description: 查询用户被禁止的COL操作权限
	 * @param user_id
	 * @return
	 */
	public List<UsUserPrivBean> queryUserAfColPriv(String user_id) {
		return daoAfSqlColService.queryUserAfColPriv(user_id);
	}

}
