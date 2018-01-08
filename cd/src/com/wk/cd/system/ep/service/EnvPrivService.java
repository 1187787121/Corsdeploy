/**
 * Title: EnvPrivService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2017��1��4��
 */
package com.wk.cd.system.ep.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.system.ep.bean.EnvPrivBean;
import com.wk.cd.system.ep.dao.DpDeptEnvPrivDaoService;
import com.wk.cd.system.ep.dao.UsRoleEnvPrivDaoService;
import com.wk.cd.system.ep.dao.UsUserEnvPrivDaoService;
import com.wk.cd.system.ep.info.UsUserEnvPrivInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author HT
 */
public class EnvPrivService {

	@Inject
	private UsRoleEnvPrivDaoService usRoleEnvPrivDaoService;
	@Inject
	private DpDeptEnvPrivDaoService dpDeptEnvPrivDaoService;
	@Inject
	private UsUserEnvPrivDaoService usUserEnvPrivDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;

	/**
	 * Description: ���ҽ�ɫ�б�Ļ���Ȩ��
	 * @param dprl_list
	 * @return
	 */
	public List<String> queryEnvPrivByDprls(List<String> dprl_list) {
		List<String> env_list = new ArrayList<String>();
		Map<String, String> env_map = new HashMap<String, String>();
		for (String dprl_code : dprl_list) {
			DBIterator<String> env_iterator = usRoleEnvPrivDaoService.iteratorEnvPrivByDprl(dprl_code);
			try {
				while (env_iterator.hasNext()) {
					String env_name = env_iterator.next();
					if (!env_map.containsKey(env_name)) {
						env_map.put(env_name, env_name);
					}
				}
			} finally {
				env_iterator.close();
			}
		}
		env_list.addAll(env_map.values());
		return env_list;
	}

	/**
	 * Description: ��ѯ���Ž�ɫ�б���ӵ�е�Ӧ�û���Ȩ��
	 * @param sub_dept_ids
	 * @return
	 */
	public List<String> queryEnvPrivByDepts(List<String> dept_list) {
		List<String> env_list = new ArrayList<String>();
		Map<String, String> env_map = new HashMap<String, String>();
		for (String dept_id : dept_list) {
			DBIterator<String> env_iterator = dpDeptEnvPrivDaoService.iteratorDeptEnvPriv(dept_id);
			try {
				while (env_iterator.hasNext()) {
					String env_name = env_iterator.next();
					if (!env_map.containsKey(env_name)) {
						env_map.put(env_name, env_name);
					}
				}
			} finally {
				env_iterator.close();
			}
		}
		env_list.addAll(env_map.values());
		return env_list;
	}

	/**
	 * Description: �����û�ID�б����û�Ӧ�û����б�
	 * @param user_list
	 * @return
	 */
	public List<String> queryEnvPrivByUserId(List<String> user_list) {
		List<String> env_list = new ArrayList<String>();
		Map<String, String> env_map = new HashMap<String, String>();
		for (String user_id : user_list) {
			DBIterator<String> env_iterator = usUserEnvPrivDaoService.iteratorUserEnvPriv(user_id);
			try {
				while (env_iterator.hasNext()) {
					String env_name = env_iterator.next();
					if (!env_map.containsKey(env_name)) {
						env_map.put(env_name, env_name);
					}
				}
			} finally {
				env_iterator.close();
			}
		}
		env_list.addAll(env_map.values());

		return env_list;
	}

	/**
	 * Description: ɾ������Ӧ�û���Ȩ��
	 * @param dept_id
	 */
	public void deleteDeptEnvPriv(String dept_id) {
		dpDeptEnvPrivDaoService.deleteDeptEnvPriv(dept_id);
	}

	/**
	 * Description: ��ѯ����Ӧ�û���Ȩ���б�
	 * @param dept_id
	 * @return
	 */
	public List<EnvPrivBean> queryDeptEnvPriv(String dept_id) {
		return dpDeptEnvPrivDaoService.queryDeptEnvPriv(dept_id);
	}

	/**
	 * Description: ��ѯ����Ӧ�û���Ȩ��
	 * @return
	 */
	public List<EnvPrivBean> queryAllEnvPriv() {
		return ceEnvironmentDaoService.queryAllEnvPriv();
	}

	/**
	 * Description: ��ѯ���Ž�ɫӦ�û���Ȩ���б�
	 * @param dprl_code
	 * @return
	 */
	public List<EnvPrivBean> queryDprlEnvPriv(String dprl_code) {
		return usRoleEnvPrivDaoService.queryDprlEnvPriv(dprl_code);
	}

	/**
	 * Description: ɾ�����Ž�ɫӦ�û���Ȩ��
	 * @param dprl_code
	 */
	public void deleteDprlEnvPriv(String dprl_code) {
		usRoleEnvPrivDaoService.deleteDprlEnvPriv(dprl_code);
	}

	/**
	 * Description: ��ѯ�û�Ӧ�û���Ȩ��
	 * @param user_id
	 * @return
	 */
	public List<EnvPrivBean> getUserEnvPriv(String user_id) {
		List<EnvPrivBean> env_list = new ArrayList<EnvPrivBean>();
		Map<String, EnvPrivBean> uenvMap = new HashMap<String, EnvPrivBean>();
		DBIterator<EnvPrivBean> drenvIterator = usUserEnvPrivDaoService.iteratorDprlEnvPrivByUser(user_id);
		try {
			while (drenvIterator.hasNext()) {
				EnvPrivBean env_priv = drenvIterator.next();
				uenvMap.put(env_priv.getEnv_name(), env_priv);
			}
		} finally {
			drenvIterator.close();
		}

		DBIterator<EnvPrivBean> uenvIterator = usUserEnvPrivDaoService.queryIteratorUsEnvAf(user_id);
		try {
			while (uenvIterator.hasNext()) {
				EnvPrivBean env_priv = uenvIterator.next();
				if (AF_FLAG.ALLOW == env_priv.getAf_flag()) {
					uenvMap.put(env_priv.getEnv_name(), env_priv);
				} else if (AF_FLAG.FORBID == env_priv.getAf_flag()) {
					uenvMap.remove(env_priv.getEnv_name());
				}

			}
		} finally {
			uenvIterator.close();
		}

		env_list.addAll(uenvMap.values());
		return env_list;
	}

	/**
	 * Description: �����û���ѯ���Ž�ɫȨ��
	 * @param user_id
	 * @return
	 */
	public List<EnvPrivBean> getDprlEnvPrivByUser(String user_id) {
		List<EnvPrivBean> env_list = new ArrayList<EnvPrivBean>();
		DBIterator<EnvPrivBean> envIterator = usUserEnvPrivDaoService.iteratorDprlEnvPrivByUser(user_id);
		try {
			while (envIterator.hasNext()) {
				env_list.add(envIterator.next());
			}
		} finally {
			envIterator.close();
		}
		return env_list;
	}

	/**
	 * Description: ɾ���û�Ӧ�û���Ȩ��
	 * @param user_id
	 */
	public void deleteUserEnvPriv(String user_id) {
		usUserEnvPrivDaoService.deleteUserEnvPriv(user_id);
	}

	/**
	 * Description: �û��Ƿ��л�����Ȩ��
	 * @param user_id
	 * @param env_name
	 * @return
	 */
	public boolean hasUserEnvPriv(String user_id, String env_name) {
		UsUserEnvPrivInfo info = new UsUserEnvPrivInfo();
		info.setUser_id(user_id);
		info.setEnv_name(env_name);
		info.setPriv_type(PRIV_TYPE.PERPETUAL);
		info = usUserEnvPrivDaoService.getInfoByKey(info);
		if (!Assert.isEmpty(info) && info.getAf_flag() == AF_FLAG.ALLOW) {
			return true;
		} else if (!Assert.isEmpty(info) && info.getAf_flag() == AF_FLAG.FORBID) {
			return false;
		} else {
			return usRoleEnvPrivDaoService.hasUserDprlEnvPriv(user_id, env_name);
		}
	}
}
