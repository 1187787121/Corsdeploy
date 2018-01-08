/**
 * Title: UsDelRolePrivService.java
 * File Description: 删除部门角色权限
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-2
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleRsPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSocPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.lang.Inject;

/**
 * Class Description: 删除部门角色权限
 * @author link
 */
public class UsDelRolePrivService {
	@Inject
	private UsRoleRsPrivDaoService daoRsService;
	@Inject
	private UsRoleSocPrivDaoService daoSocService;
	@Inject
	private UsRoleSqlPrivDaoService daoSqlService;
	@Inject
	private UsRoleColPrivDaoService daoColService;

	/**
	 * Description: 为部门角色删除资源权限
	 * @param dprl_code
	 * @param rs_list
	 */
	public void delRsByDprlCode(String dprl_code, List<String> rs_list) {
		daoRsService.delRsByDprlCode(dprl_code, rs_list);
	}

	/**
	 * Description: 为部门角色删除数据源权限
	 * @param dprl_code
	 * @param soc_list
	 */
	public void delSocByDprlCode(String dprl_code, List<String> soc_list) {
		daoSocService.delSocByDprlCode(dprl_code, soc_list);
	}

	/**
	 * Description: 为部门角色删除SQL权限
	 * @param sql_list
	 */
	public void delSqlByDprlCode(List<UsRoleSqlPrivInfo> sql_list) {
		daoSqlService.delSqlByDprlCode(sql_list);
	}

	/**
	 * Description: 为部门角色删除COL权限
	 * @param col_list
	 */
	public void delColByDprlCode(List<UsRoleColPrivInfo> col_list) {
		daoColService.delColByDprlCode(col_list);
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有资源权限
	 * @param dprl_code
	 */
	public void deleteAllRsByDprl(String dprl_code) {
		daoRsService.deleteAllRsByDprl(dprl_code);
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有数据源权限
	 * @param dprl_code
	 */
	public void deleteAllSocByDprl(String dprl_code) {
		daoSocService.deleteAllSocByDprl(dprl_code);
	}
	/**
	 * Description: 根据数据源名称删除部门角色的所有数据源权限信息
	 * @param soc_name
	 */
	public void deleteRoleSocBySocName(String soc_name) {
		daoSocService.deleteRoleSocBySocName(soc_name);
	}
	/**
	 * Description: 根据数据源名称删除部门角色的所有相应数据源的SQL表权限信息
	 * @param soc_name
	 */
	public void deleteRoleSQLBySocName(String soc_name) {
		daoSqlService.deleteRoleSQLBySocName(soc_name);
	}
	
	/**
	 * Description: 根据数据源名称删除部门角色的所有相应数据源的SQL表权限信息
	 * @param soc_name
	 */
	public void deleteRoleCOLBySocName(String soc_name) {
		daoColService.deleteRoleCOLBySocName(soc_name);
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有SQL权限
	 * @param dprl_code
	 */
	public void deleteAllSqlByDprl(String dprl_code) {
		daoSqlService.deleteAllSqlByDprl(dprl_code);
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有COL权限
	 * @param dprl_code
	 */
	public void deleteAllColByDprl(String dprl_code) {
		daoColService.deleteAllColByDprl(dprl_code);
	}

}
