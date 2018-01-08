/**
 * Title: UsUserEnvPrivDaoService.java
 * File Description: 用户应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */
package com.wk.cd.system.ep.dao;

import java.util.List;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:用户应用环境权限表
 * @author AutoGen
 */
public class UsUserEnvPrivDaoService {
	@Inject
	private UsUserEnvPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsUserEnvPrivInfo info
	 * @return UsUserEnvPrivInfo
	 */
	public UsUserEnvPrivInfo getInfoByKey(UsUserEnvPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsUserEnvPrivInfo info
	 * @return UsUserEnvPrivInfo
	 */
	public UsUserEnvPrivInfo getInfoByKeyForUpdate(UsUserEnvPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsUserEnvPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsUserEnvPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsUserEnvPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsUserEnvPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据用户ID列表获得用户应用环境列表
	 * @param user_id
	 * @return
	 */
	public DBIterator<String> iteratorUserEnvPriv(String user_id) {
		return dao.iteratorUserEnvPriv(user_id);
	}

	/**
	 * Description: 查询用户应用环境永久权限
	 * @param user_id
	 * @return
	 */
	public DBIterator<EnvPrivBean> queryIteratorUsEnvAf(String user_id) {
		return dao.queryIteratorUsEnvAf(user_id);
	}

	/**
	 * Description: 根据用户查询部门角色应用环境权限
	 * @param user_id
	 * @return
	 */
	public DBIterator<EnvPrivBean> iteratorDprlEnvPrivByUser(String user_id) {
		return dao.iteratorDprlEnvPrivByUser(user_id);
	}

	/** 
	 * Description: 删除用户应用环境权限
	 * @param user_id 
	 */
	public void deleteUserEnvPriv(String user_id) {
		dao.deleteUserEnvPriv(user_id);
	}
}