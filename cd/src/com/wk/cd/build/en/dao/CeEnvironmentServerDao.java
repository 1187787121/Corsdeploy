/**
 * Title: CeEnvironmentServerDao.java
 * File Description: ������������
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
 * Class description:������������
 * @author AutoGen
 */
abstract class CeEnvironmentServerDao
		extends EntityDao<CeEnvironmentServerInfo> {

	/**
	 * Description: ɾ�������������
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int deleteEnvirServerByEnvName(String env_name);

	/**
	 * Description: ���ݻ������ƻ�ù�����Ϣ
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract List<CeEnvironmentServerInfo> getInfoByEnvName(String env_name);

	/**
	 * Description: ��ù��ط������ĸ���
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract int countServerNumByEnvName(String env_name);

	/**
	 * Description: ��÷�������Ӧ�Ļ�������
	 * @param server_name
	 * @return
	 */
	@SqlParam(condition = "SERVER_NAME =:server_name")
	abstract int countEnvNumByServerName(String server_name);

	/**
	 * Description: ͨ���������Ƶõ���Ӧ�����й�����Ϣ
	 * @param env_name
	 * @return
	 */
	@SqlParam(condition = "ENV_NAME =:env_name")
	abstract List<CeEnvironmentServerInfo> getEnvirServerByEnvName(
			String env_name);

	/**
	 * Description: ���ݷ��������Ӧ��ϵͳ�ͻ����б�
	 * @param server_name
	 * @return
	 */
	@SqlParam(sql = "SELECT distinct ce.* FROM ce_environment_server ces LEFT JOIN ce_environment ce ON ces.ENV_NAME=ce.ENV_NAME WHERE ces.SERVER_NAME=:server_name")
	abstract List<ServerEnvSysBean> queryEnvSysByServer(String server_name);
	
	/**
	 * Description: ���ݻ������ƻ�ù�����������(ȥ��)
	 * @param env_name
	 * @return
	 */
	@SqlParam(sql = "select DISTINCT(SERVER_NAME) from CE_ENVIRONMENT_SERVER where ENV_NAME =:env_name")
	abstract List<String> getDistinctServerNameByEnv(String env_name);

	/** 
	 * Description: ���ݷ��������ƻ�����л�������ȥ�أ�
	 * @param server_name
	 * @return 
	 */
	@SqlParam(querySet = "ENV_NAME", condition = "SERVER_NAME =:server_name", distinct = true)
	abstract List<String> queryInfoByServerName(String server_name);

}