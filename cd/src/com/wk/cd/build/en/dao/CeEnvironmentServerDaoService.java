/**
 * Title: CeEnvironmentServerDaoService.java
 * File Description: 环境服务器表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-10-29
 */
package com.wk.cd.build.en.dao;

import java.util.List;

import com.wk.cd.build.en.bean.ServerEnvSysBean;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.lang.Inject;

/**
 * Class description:环境服务器表
 * @author AutoGen
 */
public class CeEnvironmentServerDaoService {
	@Inject
	private CeEnvironmentServerDao dao;

	/**
	 * 根据主键查询一条记录
	 * @param CeEnvironmentServerInfo info
	 * @return CeEnvironmentServerInfo
	 */
	public CeEnvironmentServerInfo getInfoByKey(CeEnvironmentServerInfo info) {
		return dao.get(info);
	}

	/**
	 * 根据主键查询一条记录并对记录加锁
	 * @param CeEnvironmentServerInfo info
	 * @return CeEnvironmentServerInfo
	 */
	public CeEnvironmentServerInfo getInfoByKeyForUpdate(
			CeEnvironmentServerInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * 插入一条记录
	 * @param CeEnvironmentServerInfo info
	 * @return int
	 */
	public int insertInfo(CeEnvironmentServerInfo info) {
		return dao.insert(info);
	}

	/**
	 * 插入多条记录
	 * @param List<CeEnvironmentServerInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeEnvironmentServerInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: 删除环境服务关联
	 * @param env_name
	 */
	public int deleteEnvirServerByEnvName(String env_name) {
		return dao.deleteEnvirServerByEnvName(env_name);

	}

	/**
	 * Description: 根据环境名称获得关联信息
	 * @param env_name
	 * @return
	 */
	public List<CeEnvironmentServerInfo> queryInfoByEnvName(String env_name) {
		List<CeEnvironmentServerInfo> list = dao.getInfoByEnvName(env_name);
		return list;
	}
	
	/**
	 * Description: 根据服务器名称获得所有环境名（去重）
	 * @param server_name
	 * @return
	 */
	public List<String> queryInfoByServerName(String server_name) {
		return dao.queryInfoByServerName(server_name);
	}

	/**
	 * Description: 获得挂载服务器的个数
	 * @param env_name
	 * @return
	 */
	public int countServerNumByEnvName(String env_name) {
		return dao.countServerNumByEnvName(env_name);
	}

	/**
	 * Description: 获得服务器对应的环境个数
	 * @param server_name
	 * @return
	 */
	public int countEnvNumByServerName(String server_name) {
		return dao.countEnvNumByServerName(server_name);
	}

	/**
	 * Description: 通过环境名称得到对应的所有关联信息
	 * @param env_name
	 * @return
	 */
	public List<CeEnvironmentServerInfo> getEnvirServerByEnvName(
			String env_name) {
		return dao.getEnvirServerByEnvName(env_name);
	}

	/**
	 * Description: 根据服务器获得应用系统和环境列表
	 * @param server_name
	 * @return
	 */
	public List<ServerEnvSysBean> queryEnvSysByServer(String server_name) {
		return dao.queryEnvSysByServer(server_name);
	}
	
	/**
	 * Description: 根据环境名称获得关联服务器名(去重)
	 * @param env_name
	 * @return
	 */
	public List<String> getDistinctServerNameByEnv(String env_name) {
		return dao.getDistinctServerNameByEnv(env_name);
	}

}