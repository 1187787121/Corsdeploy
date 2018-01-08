/**
 * Title: UsRoleColPrivDaoService.java
 * File Description: 部门角色SQL字段操作权限表
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
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门角色SQL字段操作权限表
 * @author AutoGen
 */
public class UsRoleColPrivDaoService {
	@Inject
	private UsRoleColPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsRoleColPrivInfo info
	 * @return UsRoleColPrivInfo
	 */
	public UsRoleColPrivInfo getInfoByKey(UsRoleColPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleColPrivInfo info
	 * @return UsRoleColPrivInfo
	 */
	public UsRoleColPrivInfo getInfoByKeyForUpdate(UsRoleColPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleColPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleColPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleColPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleColPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门角色编码查询COL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleColPrivInfo> queryColByDprl(String dprl_code) {
		return dao.queryColByDprl(dprl_code);
	}

	/**
	 * Description: 根据部门角色编码数组查询COL操作权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<UsRoleColPrivInfo> queryColByDprlArr(String dprl_code) {
		return dao.queryColByDprlArr(dprl_code);
	}

	/**
	 * Description: 检查部门角色对应的SQL记录是否已经存在
	 * @param dprl_code
	 * @param srv_name
	 */
	public void checkDprlColNotExist(UsRoleColPrivInfo info) {
		if (dao.countColByDprlCode(info.getDprl_code(), info.getSoc_name(),
				info.getTbl_name(), info.getCol_name()) > 0) {
			throw new RecordAlreadyExistException().addScene("TABLE",
					UsRoleColPrivInfo.TABLECN).addScene(
					"FIELD",
					"部门角色编码" + info.getDprl_code() + "数据源名称" + info.getSoc_name()
							+ "表名" + info.getTbl_name() + "字段名"
							+ info.getCol_name());
		} else {
			Assert.assertNotEmpty(info.getIns_priv_flag(),
					UsRoleColPrivInfo.INS_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getDel_priv_flag(),
					UsRoleColPrivInfo.DEL_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getUpd_priv_flag(),
					UsRoleColPrivInfo.UPD_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getSel_priv_flag(),
					UsRoleColPrivInfo.SEL_PRIV_FLAGCN);
		}
	}

	public int countColByDprlCode(String dprl_code, String soc_name,
			String tbl_name, String col_name) {
		return dao.countColByDprlCode(dprl_code, soc_name, tbl_name, col_name);
	}

	/**
	 * Description: 检查部门角色对应的SQL记录是否已经存在
	 * @param dprl_code
	 * @param srv_name
	 */
	public void checkDprlColExist(UsRoleColPrivInfo info) {
		if (dao.countColByDprlCode(info.getDprl_code(), info.getSoc_name(),
				info.getTbl_name(), info.getCol_name()) == 0) {
			throw new RecordNotFoundException().addScene("TABLE",
					UsRoleColPrivInfo.TABLECN).addScene(
					"FIELD",
					"部门角色编码" + info.getDprl_code() + "数据源名称" + info.getSoc_name()
							+ "表名" + info.getTbl_name() + "字段名"
							+ info.getCol_name());
		} else {
			Assert.assertNotEmpty(info.getIns_priv_flag(),
					UsRoleColPrivInfo.INS_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getDel_priv_flag(),
					UsRoleColPrivInfo.DEL_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getUpd_priv_flag(),
					UsRoleColPrivInfo.UPD_PRIV_FLAGCN);
			Assert.assertNotEmpty(info.getSel_priv_flag(),
					UsRoleColPrivInfo.SEL_PRIV_FLAGCN);
		}
	}

	/**
	 * Description: 为部门角色删除col操作权限
	 * @param sql_list
	 */
	public void delColByDprlCode(List<UsRoleColPrivInfo> col_list) {
		for (UsRoleColPrivInfo info : col_list) {
			checkDprlColExist(info);
			dao.delete(info);
		}
	}

	/**
	 * Description: 部门角色COL操作权限更改
	 * @param info
	 */
	public void updateColPriv(UsRoleColPrivInfo info) {
		checkDprlColExist(info);
		dao.updateColPriv(info.getIns_priv_flag().getValue(), info
				.getDel_priv_flag().getValue(), info.getUpd_priv_flag()
				.getValue(), info.getSel_priv_flag().getValue(), info
				.getDprl_code(), info.getSoc_name(), info.getTbl_name(), info
				.getCol_name());
	}

	/**
	 * Description: 根据部门角色编码删除部门角色的所有COL权限
	 * @param dprl_code
	 */
	public void deleteAllColByDprl(String dprl_code) {
		dao.deleteAllColByDprl(dprl_code);
	}

	/** 
	 * Description:  通过部门角色查询col权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleColPrivInfo> iteratorColByDprl(String dprl_code) {
		return dao.queryIteratorColByDprl(dprl_code);
	}
	
	/** 
	 * Description:  通过部门角色数组查询col权限信息
	 * @param dprl_code
	 * @return 
	 */
	public DBIterator<UsRoleColPrivInfo> iteratorColByDprlArray(String dprl_code) {
		return dao.queryIteratorColByDprlArray(dprl_code);
	}
	
	
	/**
	 * 
	 * Description: 根据数据源名称删除部门角色的所有数据源col列权限信息
	 * @param soc_name
	 * @return
	 */
	public int deleteRoleCOLBySocName(String soc_name){
		return dao.deleteRoleCOLBySocName(soc_name);
	}

	/** 
	 * Description: 根据部门角色代码查询部门角色SQL权限信息
	 * @param dprl_code
	 * @return 
	 */
	public List<UsRoleColPrivInfo> queryColPrivByDprl(String dprl_code) {
		List<UsRoleColPrivInfo> col_list = new ArrayList<UsRoleColPrivInfo>();
		DBIterator<UsRoleColPrivInfo> col_iterator = dao.queryColPrivByDprl(dprl_code);
		try {
			while (col_iterator.hasNext()) {
				col_list.add(col_iterator.next());
			}
		} finally {
			col_iterator.close();
		}
		return col_list;
	}

}