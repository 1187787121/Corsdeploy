/**
 * Title: UsRoleSqlPrivDaoService.java
 * File Description: 部门角色SQL操作权限表
 * @copyright: 2014
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2014-11-14
 */
package com.wk.cd.system.us.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.exc.RecordAlreadyExistException;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门角色SQL操作权限表
 * @author AutoGen
 */
public class UsRoleSqlPrivDaoService {
	@Inject
	private UsRoleSqlPrivDao dao;
	@Inject 
	private UsDeptRoleDaoService usDeptRoleDaoService;
	/**
	 * 根据主键查询一条记录
	 * @param UsRoleSqlPrivInfo info
	 * @return UsRoleSqlPrivInfo
	 */
	public UsRoleSqlPrivInfo getInfoByKey(UsRoleSqlPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleSqlPrivInfo info
	 * @return UsRoleSqlPrivInfo
	 */
	public UsRoleSqlPrivInfo getInfoByKeyForUpdate(UsRoleSqlPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleSqlPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleSqlPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleSqlPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleSqlPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门角色编码查询SQL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleSqlPrivInfo> querySqlByDprl(String dprl_code) {
		return dao.querySqlByDprl(dprl_code);
	}

	/**
	 * Description: 根据部门角色编码数组查询SQL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleSqlPrivInfo> querySqlByDprlArr(String dprl_code) {
		return dao.querySqlByDprlArr(dprl_code);
	}

	/**
	 * Description: 检查部门角色对应的SQL记录是否已经存在
	 * @param info
	 */
	public void checkDprlSqlNotExist(UsRoleSqlPrivInfo info) {
		if (dao.countSqlByDprlCode(info.getDprl_code(), info.getSoc_name(),
				info.getTbl_name()) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleSqlPrivInfo.TABLECN).addScene(
					"FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(info.getDprl_code()).getBk_expl() + "[" + info.getDprl_code() +"]" + "数据源" + info.getSoc_name()
							+ "表名" + info.getTbl_name());
		} else {
			Assert.assertNotEmpty(info.getIns_priv_flag(),
					UsRoleSqlPrivInfo.INS_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getDel_priv_flag(),
					UsRoleSqlPrivInfo.DEL_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getUpd_priv_flag(),
					UsRoleSqlPrivInfo.UPD_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getSel_priv_flag(),
					UsRoleSqlPrivInfo.SEL_PRIV_FLAGCN);
		}
	}

	public int countSqlByDprlCode(String dprl_code, String soc_name,
			String tbl_name) {
		return dao.countSqlByDprlCode(dprl_code, soc_name, tbl_name);
	}

	/**
	 * Description: 检查部门角色对应的SQL记录是否已经存在
	 * @param info
	 */
	private void checkDprlSqlExist(UsRoleSqlPrivInfo info) {
		if (dao.countSqlByDprlCode(info.getDprl_code(), info.getSoc_name(),
				info.getTbl_name()) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleSqlPrivInfo.TABLECN).addScene(
					"FIELD",
					"部门角色" + usDeptRoleDaoService.getDeptByDprl(info.getDprl_code()).getBk_expl() + "[" + info.getDprl_code() +"]" + "数据源" + info.getSoc_name()
							+ "表名" + info.getTbl_name());
		} else {
			Assert.assertNotEmpty(info.getIns_priv_flag(),
					UsRoleSqlPrivInfo.INS_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getDel_priv_flag(),
					UsRoleSqlPrivInfo.DEL_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getUpd_priv_flag(),
					UsRoleSqlPrivInfo.UPD_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getSel_priv_flag(),
					UsRoleSqlPrivInfo.SEL_PRIV_FLAGCN);
		}
	}

	/**
	 * Description: 为部门角色删除sql操作权限
	 * @param sql_list
	 */
	public void delSqlByDprlCode(List<UsRoleSqlPrivInfo> sql_list) {
		for (UsRoleSqlPrivInfo info : sql_list) {
			checkDprlSqlExist(info);
			dao.delete(info);
		}
	}

	/**
	 * Description: 更改部门角色SQL操作权限
	 * @param info 输入接口
	 */
	public void updateSqlPriv(UsRoleSqlPrivInfo info) {
		checkDprlSqlExist(info);
		dao.updateSqlPriv(info.getIns_priv_flag().getValue(), info
				.getDel_priv_flag().getValue(), info.getUpd_priv_flag()
				.getValue(), info.getSel_priv_flag().getValue(), info
				.getDprl_code(), info.getSoc_name(), info.getTbl_name());
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有SQL权限
	 * @param dprl_code
	 */
	public void deleteAllSqlByDprl(String dprl_code) {
		dao.deleteAllSqlByDprl(dprl_code);
	}

	/** 
	 * Description: 通过部门角色查询sql权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleSqlPrivInfo> iteratorSqlByDprl(String dprl_code) {
		return dao.queryIteratorSqlByDprl(dprl_code);
	}
	/** 
	 * Description: 通过部门角色数组查询sql权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleSqlPrivInfo> iteratorSqlByDprlArray(String dprl_code) {
		return dao.queryIteratorSqlByDprlArray(dprl_code);
	}
	
	/**
	 * 
	 * Description: 根据数据源名称删除部门角色的所有数据源SQL表权限信息
	 * @param soc_name
	 * @return
	 */
	public int deleteRoleSQLBySocName(String soc_name){
		return dao.deleteRoleSQLBySocName(soc_name);
	}

	/** 
	 * Description: 根据部门角色编码查询sql权限
	 * @param dprl_code
	 * @return 
	 */
	public List<UsRoleSqlPrivInfo> querySqlPrivByDprl(String dprl_code) {
		List<UsRoleSqlPrivInfo> sql_list = new ArrayList<UsRoleSqlPrivInfo>();
		DBIterator<UsRoleSqlPrivInfo> sql_iterator = dao.querySqlPrivByDprl(dprl_code);
		try {
			while (sql_iterator.hasNext()) {
				sql_list.add(sql_iterator.next());
			}
		} finally {
			sql_iterator.close();
		}
		return sql_list;
	}

}