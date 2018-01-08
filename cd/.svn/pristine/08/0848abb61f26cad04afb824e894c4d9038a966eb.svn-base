/**
 * Title: UsRolePrivService.java
 * File Description: 部门角色权限查询
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleRsPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSocPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description: 部门角色权限查询
 * @author link
 */
public class UsGetRolePrivService {
	@Inject
	private UsRoleRsPrivDaoService daoRsService;
	@Inject
	private UsRoleSocPrivDaoService daoSocService;
	@Inject
	private UsRoleSqlPrivDaoService daoSqlService;
	@Inject
	private UsRoleColPrivDaoService daoColService;

	/**
	 * Description: 部门角色资源权限查询
	 * @param dprl_code
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<String> queryRsByDprl(String dprl_code) {
		return daoRsService.queryRsByDprl(dprl_code);
	}

	/**
	 * Description: 部门角色数据源权限查询
	 * @param dprl_code
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<String> querySocByDprl(String dprl_code) {
		return daoSocService.querySocByDprl(dprl_code);
	}

	/**
	 * Description: 部门角色SQL权限查询
	 * @param dprl_code
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsRoleSqlPrivInfo> querySqlByDprl(String dprl_code) {
		List<UsRoleSqlPrivInfo> sql_list = null;
		sql_list = daoSqlService.querySqlByDprl(dprl_code);
		return sql_list;
	}

	/**
	 * Description: 部门角色服务权限查询
	 * @param dprl_code
	 * @param start_rcd
	 * @param limited_rcd
	 * @return
	 */
	public List<UsRoleColPrivInfo> queryColByDprl(String dprl_code) {
		List<UsRoleColPrivInfo> col_list = null;
		col_list = daoColService.queryColByDprl(dprl_code);
		return col_list;
	}

	/**
	 * Description: 查询部门角色列表所拥有的资源权限
	 * @param dprl_code_arr
	 * @return
	 */
	public List<RsResInfo> queryRsByDprlArr(String dprl_code_arr) {
		return daoRsService.queryRsByDprlArr(dprl_code_arr);
	}

	/**
	 * Description: 根据部门角色编码查询数据源列表
	 * @param dprl_code
	 * @return
	 */
	public List<DtSourceInfo> querySocByDprlArr(String dprl_code_arr) {
		return daoSocService.querySocByDprlArr(dprl_code_arr);
	}

	/**
	 * Description: 根据部门角色编码数组查询SQL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleSqlPrivInfo> querySqlByDprlArr(String dprl_code) {
		return daoSqlService.querySqlByDprlArr(dprl_code);
	}
	
	/**
	 * Description: 根据部门角色编码数组查询COL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleColPrivInfo> queryColByDprlArr(String dprl_code){
		return daoColService.queryColByDprlArr(dprl_code);
	}

	
	/** 
	 * Description: 通过部门角色查询资源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorRsByDprl(String dprl_code) {
		return daoRsService.iteratorRsByDprl(dprl_code);
	}
	
	/** 
	 * Description: 通过部门角色数组查询资源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<RsResInfo> iteratorRsByDprlArray(String dprl_code) {
		return daoRsService.iteratorRsByDprlArray(dprl_code);
	}

	/** 
	 * Description:  通过部门角色查询数据源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<DtSourceInfo> iteratorSocByDprl(String dprl_code) {
		return daoSocService.iteratorSocByDprl(dprl_code);
	}
	
	/** 
	 * Description:  通过部门角色数组查询数据源信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<DtSourceInfo> iteratorSocByDprlArray(String dprl_code) {
		return daoSocService.iteratorSocByDprlArray(dprl_code);
	}

	/** 
	 * Description: 通过部门角色查询sql权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleSqlPrivInfo> iteratorSqlByDprl(String dprl_code) {
		return daoSqlService.iteratorSqlByDprl(dprl_code);
	}
	
	/** 
	 * Description: 通过部门角色数组查询sql权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleSqlPrivInfo> iteratorSqlByDprlArray(String dprl_code) {
		return daoSqlService.iteratorSqlByDprlArray(dprl_code);
	}

	/** 
	 * Description: 通过部门角色查询col权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleColPrivInfo> iteratorColByDprl(String dprl_code) {
		return daoColService.iteratorColByDprl(dprl_code);
	}
	
	/** 
	 * Description: 通过部门角色数组查询col权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleColPrivInfo> iteratorColByDprlArray(String dprl_code) {
		return daoColService.iteratorColByDprlArray(dprl_code);
	}
}
