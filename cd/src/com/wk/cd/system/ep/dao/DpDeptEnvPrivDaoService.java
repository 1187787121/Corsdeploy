/**
 * Title: DpDeptEnvPrivDaoService.java
 * File Description: ����Ӧ�û���Ȩ�ޱ�
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
 * Class description:����Ӧ�û���Ȩ�ޱ�
 * @author AutoGen
 */
public class DpDeptEnvPrivDaoService {
	@Inject private DpDeptEnvPrivDao dao;

	/**
	 * ����������ѯһ����¼
	 * @param DpDeptEnvPrivInfo info
	 * @return DpDeptEnvPrivInfo
	 */
	public DpDeptEnvPrivInfo getInfoByKey(DpDeptEnvPrivInfo info) {
		return dao.get(info);
	}

	/**
	 * ����������ѯһ����¼���Լ�¼����
	 * @param DpDeptEnvPrivInfo info
	 * @return DpDeptEnvPrivInfo
	 */
	public DpDeptEnvPrivInfo getInfoByKeyForUpdate(DpDeptEnvPrivInfo info) {
		return dao.getForUpdate(info);
	}

	/**
	 * ����һ����¼
	 * @param DpDeptEnvPrivInfo info
	 * @return int
	 */
	public int insertInfo(DpDeptEnvPrivInfo info) {
		return dao.insert(info);
	}

	/**
	 * ���������¼
	 * @param List<DpDeptEnvPrivInfo>
	 * @return int
	 */
	public int insertListInfo(List<DpDeptEnvPrivInfo> infos) {
		return dao.insert(infos);
	}

	/** 
	 * Description: ��ѯ���Ž�ɫ�б���ӵ�е�Ӧ�û���Ȩ��
	 * @param dept_id
	 * @return 
	 */
	public DBIterator<String> iteratorDeptEnvPriv(String dept_id) {
		return dao.iteratorDeptEnvPriv(dept_id);
	}

	/** 
	 * Description: ɾ������Ӧ�û���Ȩ��
	 * @param dept_id 
	 */
	public void deleteDeptEnvPriv(String dept_id) {
		dao.deleteDeptEnvPriv(dept_id);
	}

	/** 
	 * Description: ��ѯ����Ӧ�û���Ȩ���б�
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