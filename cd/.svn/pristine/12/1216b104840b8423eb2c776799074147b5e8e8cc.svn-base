/**
 * Title: UsRoleEnvPrivDaoService.java
 * File Description: 部门角色应用环境权限表
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */
package com.wk.cd.system.ep.dao;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.info.*;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门角色应用环境权限表
 * @author AutoGen
 */
public class UsRoleEnvPrivDaoService {
	@Inject
	private UsRoleEnvPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param UsRoleEnvPrivInfo info
	 * @return UsRoleEnvPrivInfo
	 */
	public UsRoleEnvPrivInfo getInfoByKey(UsRoleEnvPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param UsRoleEnvPrivInfo info
	 * @return UsRoleEnvPrivInfo
	 */
	public UsRoleEnvPrivInfo getInfoByKeyForUpdate(UsRoleEnvPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param UsRoleEnvPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleEnvPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<UsRoleEnvPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleEnvPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 根据部门角色查询环境权限
	 * @param dprl_code
	 * @return
	 */
	public DBIterator<String> iteratorEnvPrivByDprl(String dprl_code) {
		return dao.iteratorEnvPrivByDprl(dprl_code);
	}

	/**
	 * Description: 查询部门角色应用环境权限列表
	 * @param dprl_code
	 * @return
	 */
	public List<EnvPrivBean> queryDprlEnvPriv(String dprl_code) {
		List<EnvPrivBean> env_list = new ArrayList<EnvPrivBean>();
		DBIterator<EnvPrivBean> env_iterator = dao.queryDprlEnvPriv(dprl_code);
		try {
			while (env_iterator.hasNext()) {
				env_list.add(env_iterator.next());
			}
		} finally {
			env_iterator.close();
		}
		return env_list;
	}

	/**
	 * Description: 删除部门角色应用环境权限
	 * @param dprl_code
	 */
	public void deleteDprlEnvPriv(String dprl_code) {
		dao.deleteDprlEnvPriv(dprl_code);
	}

	/**
	 * Description: 查询用户部门角色是否有环境权限
	 * @param user_id
	 * @param env_name
	 * @return
	 */
	public boolean hasUserDprlEnvPriv(String user_id, String env_name) {
		int count = dao.countDprlEnvPrivByUser(user_id, env_name);
		return count > 0;
	}
}