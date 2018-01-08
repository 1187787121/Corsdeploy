/**
 * Title: CeEnvironmentServerDao.java
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
import com.wk.db.EntityDao;
import com.wk.db.SqlParam;

/**
 * Class description:环境服务器表
 * @author AutoGen
 */
abstract class CeEnvironmentServerDao
		extends EntityDao<CeEnvironmentServerInfo> {

	/**
	 * Description: 删除环境服务关联
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int deleteEnvirServerByEnvName(String env_name);

	/**
	 * Description: 根据环境名称获得关联信息
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract List<CeEnvironmentServerInfo> getInfoByEnvName(String env_name);

	/**
	 * Description: 获得挂载服务器的个数
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countServerNumByEnvName(String env_name);

	/**
	 * Description: 获得服务器对应的环境个数
	 * @param server_name
	 * @return
	 */
	@SqlParam(condition = "SERVER_NAME =:server_name")
	abstract int countEnvNumByServerName(String server_name);

	/**
	 * Description: 通过环境名称得到对应的所有关联信息
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract List<CeEnvironmentServerInfo> getEnvirServerByEnvName(
			String env_name);

	/**
	 * Description: 根据服务器获得应用系统和环境列表
	 * @param server_name
	 * @return
	 */
	@SqlParam(sql = "SELECT distinct ce.* FROM ce_environment_server ces LEFT JOIN ce_environment ce ON ces.ENV_NAME=ce.ENV_NAME WHERE ces.SERVER_NAME=:server_name")
	abstract List<ServerEnvSysBean> queryEnvSysByServer(String server_name);
	
	/**
	 * Description: 根据环境名称获得关联服务器名(去重)
	 * @param env_name
	 * @return
	 */
	@SqlParam(sql = "select DISTINCT(SERVER_NAME) from CE_ENVIRONMENT_SERVER where ENV_NAME =:env_name")
	abstract List<String> getDistinctServerNameByEnv(String env_name);

	/** 
	 * Description: 根据服务器名称获得所有环境名（去重）
	 * @param server_name
	 * @return 
	 */
	@SqlParam(querySet = "ENV_NAME", condition = "SERVER_NAME =:server_name", distinct = true)
	abstract List<String> queryInfoByServerName(String server_name);

}