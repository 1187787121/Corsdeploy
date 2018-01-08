/**
 * Title: UsRoleEnvPrivDaoService.java
 * File Description: ���Ž�ɫӦ�û���Ȩ�ޱ�
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
 * Class description:���Ž�ɫӦ�û���Ȩ�ޱ�
 * @author AutoGen
 */
public class UsRoleEnvPrivDaoService {
	@Inject
	private UsRoleEnvPrivDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param UsRoleEnvPrivInfo info
	 * @return UsRoleEnvPrivInfo
	 */
	public UsRoleEnvPrivInfo getInfoByKey(UsRoleEnvPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param UsRoleEnvPrivInfo info
	 * @return UsRoleEnvPrivInfo
	 */
	public UsRoleEnvPrivInfo getInfoByKeyForUpdate(UsRoleEnvPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param UsRoleEnvPrivInfo info
	 * @return int
	 */
	public int insertInfo(UsRoleEnvPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<UsRoleEnvPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<UsRoleEnvPrivInfo> infos) {
		return dao.insert(infos);
	}

	/**
	 * Description: ���ݲ��Ž�ɫ��ѯ����Ȩ��
	 * @param dprl_code
	 * @return
	 */
	public DBIterator<String> iteratorEnvPrivByDprl(String dprl_code) {
		return dao.iteratorEnvPrivByDprl(dprl_code);
	}

	/**
	 * Description: ��ѯ���Ž�ɫӦ�û���Ȩ���б�
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
	 * Description: ɾ�����Ž�ɫӦ�û���Ȩ��
	 * @param dprl_code
	 */
	public void deleteDprlEnvPriv(String dprl_code) {
		dao.deleteDprlEnvPriv(dprl_code);
	}

	/**
	 * Description: ��ѯ�û����Ž�ɫ�Ƿ��л���Ȩ��
	 * @param user_id
	 * @param env_name
	 * @return
	 */
	public boolean hasUserDprlEnvPriv(String user_id, String env_name) {
		int count = dao.countDprlEnvPrivByUser(user_id, env_name);
		return count > 0;
	}
}