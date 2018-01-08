/**
 * Title: DpDeptEnvPrivDaoService.java
 * File Description: 部门应用环境权限表
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
import com.wk.cd.system.ep.info.DpDeptEnvPrivInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class description:部门应用环境权限表
 * @author AutoGen
 */
public class DpDeptEnvPrivDaoService {
	@Inject private DpDeptEnvPrivDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param DpDeptEnvPrivInfo info
	 * @return DpDeptEnvPrivInfo
	 */
	public DpDeptEnvPrivInfo getInfoByKey(DpDeptEnvPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param DpDeptEnvPrivInfo info
	 * @return DpDeptEnvPrivInfo
	 */
	public DpDeptEnvPrivInfo getInfoByKeyForUpdate(DpDeptEnvPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param DpDeptEnvPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptEnvPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<DpDeptEnvPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptEnvPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: 查询部门角色列表所拥有的应用环境权限
	 * @param dept_id
	 * @return 
	 */
	public DBIterator<String> iteratorDeptEnvPriv(String dept_id) {
		return dao.iteratorDeptEnvPriv(dept_id);
	}

	/** 
	 * Description: 删除部门应用环境权限
	 * @param dept_id 
	 */
	public void deleteDeptEnvPriv(String dept_id) {
		dao.deleteDeptEnvPriv(dept_id);
	}

	/** 
	 * Description: 查询部门应用环境权限列表
	 * @param dept_id
	 * @return 
	 */
	public List<EnvPrivBean> queryDeptEnvPriv(String dept_id) {
		List<EnvPrivBean> env_list =new ArrayList<EnvPrivBean>();
		DBIterator<EnvPrivBean> env_iterator= dao.queryDeptEnvPriv(dept_id);
		try {
			while (env_iterator.hasNext()) {
				env_list.add(env_iterator.next());
			}	
		}finally {
			env_iterator.close();
		}
		return env_list;
	}

}