/**
 * Title: CeEnvironmentServerDaoService.java
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
import com.wk.lang.Inject;

/**
 * Class description:������������
 * @author AutoGen
 */
public class CeEnvironmentServerDaoService {
	@Inject
	private CeEnvironmentServerDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param CeEnvironmentServerInfo info
	 * @return CeEnvironmentServerInfo
	 */
	public CeEnvironmentServerInfo getInfoByKey(CeEnvironmentServerInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param CeEnvironmentServerInfo info
	 * @return CeEnvironmentServerInfo
	 */
	public CeEnvironmentServerInfo getInfoByKeyForUpdate(
			CeEnvironmentServerInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param CeEnvironmentServerInfo info
	 * @return int
	 */
	public int insertInfo(CeEnvironmentServerInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<CeEnvironmentServerInfo>
	 * @return int
	 */
	public int insertListInfo(List<CeEnvironmentServerInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ɾ�������������
	 * @param env_name
	 */
	public int deleteEnvirServerByEnvName(String env_name) {
		return dao.deleteEnvirServerByEnvName(env_name);

	}

	/**
	 * Description: ���ݻ������ƻ�ù�����Ϣ
	 * @param env_name
	 * @return
	 */
	public List<CeEnvironmentServerInfo> queryInfoByEnvName(String env_name) {
		List<CeEnvironmentServerInfo> list = dao.getInfoByEnvName(env_name);
		return list;
	}
	
	/**
	 * Description: ���ݷ��������ƻ�����л�������ȥ�أ�
	 * @param server_name
	 * @return
	 */
	public List<String> queryInfoByServerName(String server_name) {
		return dao.queryInfoByServerName(server_name);
	}

	/**
	 * Description: ��ù��ط������ĸ���
	 * @param env_name
	 * @return
	 */
	public int countServerNumByEnvName(String env_name) {
		return dao.countServerNumByEnvName(env_name);
	}

	/**
	 * Description: ��÷�������Ӧ�Ļ�������
	 * @param server_name
	 * @return
	 */
	public int countEnvNumByServerName(String server_name) {
		return dao.countEnvNumByServerName(server_name);
	}

	/**
	 * Description: ͨ���������Ƶõ���Ӧ�����й�����Ϣ
	 * @param env_name
	 * @return
	 */
	public List<CeEnvironmentServerInfo> getEnvirServerByEnvName(
			String env_name) {
		return dao.getEnvirServerByEnvName(env_name);
	}

	/**
	 * Description: ���ݷ��������Ӧ��ϵͳ�ͻ����б�
	 * @param server_name
	 * @return
	 */
	public List<ServerEnvSysBean> queryEnvSysByServer(String server_name) {
		return dao.queryEnvSysByServer(server_name);
	}
	
	/**
	 * Description: ���ݻ������ƻ�ù�����������(ȥ��)
	 * @param env_name
	 * @return
	 */
	public List<String> getDistinctServerNameByEnv(String env_name) {
		return dao.getDistinctServerNameByEnv(env_name);
	}

}