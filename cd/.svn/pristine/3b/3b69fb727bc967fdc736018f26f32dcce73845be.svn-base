/**
 * Title: UsDelUserPrivService.java
 * File Description: 删除用户权限
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.bean.UsUserPrivBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.lang.Inject;

/**
 * Class Description:删除用户权限
 * @author link
 */
public class UsDelUserPrivService {
	@Inject
	private UsUserRsPrivDaoService daoRsService;
	@Inject
	private UsUserSocPrivDaoService daoSocService;
	@Inject
	private UsUserSqlPrivDaoService daoSqlService;
	@Inject
	private UsUserColPrivDaoService daoColService;

	/**
	 * Description: 删除用户的所有资源权限
	 * @param user_id
	 */
	public void deleteAllRsByUserId(String user_id) {
		daoRsService.deleteAllRsByUserId(user_id);
	}

	/**
	 * Description: 删除用户的数据源权限
	 * @param user_id
	 * @param soc_list
	 */
	public void delUserSocPriv(String user_id, List<String> soc_list) {
		daoSocService.deleteUserSocPriv(user_id, soc_list);
	}

	/**
	 * Description: 删除用户的所有数据源权限
	 * @param user_id
	 */
	public void deleteAllSocByUserId(String user_id) {
		daoSocService.deleteAllSocByUserId(user_id);
	}

	/**
	 * Description: 删除用户的SQL权限
	 * @param sql_list
	 */
	public void delUserSqlPriv(List<UsUserPrivBean> sql_list) {
		daoSqlService.deleteUserSqlPriv(sql_list);
	}

	/**
	 * Description: 删除用户的所有SQL权限
	 * @param user_id
	 */
	public void deleteAllSqlByUserId(String user_id) {
		daoSqlService.deleteAllSqlByUserId(user_id);
	}

	/**
	 * Description: 删除用户的COL权限
	 * @param col_list
	 */
	public void delUserColPriv(List<UsUserPrivBean> col_list) {
		daoColService.deleteUserColPriv(col_list);
	}

	/**
	 * Description: 删除用户的所有COL权限
	 * @param user_id
	 */
	public void deleteAllColByUserId(String user_id) {
		daoColService.deleteAllColByUserId(user_id);
	}
	
	/**
	 * Description: 删除用户数据源信息
	 * @param soc_name
	 */
	public int deleteUserSocBySocName(String soc_name) {
		return daoSocService.deleteUserSocBySocName(soc_name);
	}
	
	/**
	 * Description: 删除用户数据源SQL信息
	 * @param soc_name
	 */
	public int deleteUserSQLBySocName(String soc_name) {
		return daoSqlService.deleteUserSQLBySocName(soc_name);
	}
	
	/**
	 * Description: 删除用户数据源COL信息
	 * @param soc_name
	 */
	public int deleteUserCOLBySocName(String soc_name) {
		return daoColService.deleteUserCOLBySocName(soc_name);
	}
}
